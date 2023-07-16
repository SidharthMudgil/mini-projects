package com.example.martialarts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martialarts.controller.MartialArtAdapter;
import com.example.martialarts.databinding.ActivityMainBinding;
import com.example.martialarts.model.DatabaseHandler;
import com.example.martialarts.model.MartialArt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class MainActivity extends AppCompatActivity implements MartialArtAdapter.MartialArtUpdateListener, UpdateDialog.UpdateDialogListener {

    static public final String TAG = MainActivity.class.getSimpleName();
    static public final String OBJECT_KEY = "martialArt@Object";
    static private final int ACTION_ADD = R.id.action_add;
    static private final int ACTION_CLEAR = R.id.action_clear;
    static private final int ACTION_DELETE = R.id.action_delete;

    private DatabaseHandler databaseHandler;

    private RecyclerView recyclerView;
    private MartialArtAdapter martialArtAdapter;
    private ArrayList<MartialArt> martialArts;

    private MartialArt deletedMartialArt = null;

    private final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            if (direction == ItemTouchHelper.LEFT) {
                deletedMartialArt = martialArts.get(position);
                removeMartialArtObjectFromDatabase(position);

                Snackbar.make(recyclerView, "MartialArt is deleted", Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_undo, v -> {
                            databaseHandler.addMartialArt(deletedMartialArt);
                            martialArts.add(position, deletedMartialArt);
                            martialArtAdapter.notifyItemInserted(position);
                        })
                        .show();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    assert data != null;
                    MartialArt martialArt = (MartialArt) data.getSerializableExtra(OBJECT_KEY);
                    martialArts.add(martialArt);
                    martialArtAdapter.notifyItemInserted(martialArts.size() - 1);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.martialarts.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        databaseHandler = new DatabaseHandler(this);
        martialArts = databaseHandler.getAllMartialArts();

        recyclerView = findViewById(R.id.rv);
        martialArtAdapter = new MartialArtAdapter(martialArts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(martialArtAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menu_action = item.getItemId();

        switch (menu_action) {
            case ACTION_ADD:
                Intent addMartialArtIntent = new Intent(MainActivity.this, AddMartialArtActivity.class);
                launcher.launch(addMartialArtIntent);
                break;
            case ACTION_CLEAR:
                if (!martialArts.isEmpty()) {
                    clearAllMartialArtsFromDatabase();
                }
                break;
            case ACTION_DELETE:
                if (!martialArts.isEmpty()) {
                    createShowMartialArtSelectionDialog();
                }
                break;
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createShowMartialArtSelectionDialog() {
        List<String> multiItemsList = new ArrayList<>();

        for (MartialArt martialArt : martialArts) {
            multiItemsList.add(martialArt.getMartialArtName());
        }

        CharSequence[] multiItems = multiItemsList.toArray(new CharSequence[0]);
        boolean[] checkedItems = new boolean[martialArts.size()];
        Arrays.fill(checkedItems, false);

        ArrayList<Integer> selectedItems = new ArrayList<>();

        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle(R.string.alert_title_delete)
                .setNeutralButton(R.string.cancel, (dialog, which) -> Arrays.fill(checkedItems, false))
                .setPositiveButton(R.string.positive_button_delete, (dialog, which) -> removeSelectedMartialArtObjectFromDatabase(selectedItems))
                .setMultiChoiceItems(multiItems, checkedItems, (dialog, which, isChecked) -> {
                    if (isChecked) {
                        selectedItems.add(which);
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(Integer.valueOf(which));
                    }
                }).show();
    }

    private void removeMartialArtObjectFromDatabase(int flag) {
        MartialArt martialArt = martialArts.get(flag);
        databaseHandler.removeMartialArtById(martialArt.getMartialArtID());
        martialArts.remove(flag);
        martialArtAdapter.notifyItemRemoved(flag);
    }

    private void removeSelectedMartialArtObjectFromDatabase(ArrayList<Integer> selectedItems) {
        ArrayList<Integer> toDelete = new ArrayList<>();
        for (int item : selectedItems) {
            toDelete.add(martialArts.get(item).getMartialArtID());
            martialArts.remove(item);
            martialArtAdapter.notifyItemRemoved(item);
        }
        for (int pos : toDelete) {
            databaseHandler.removeMartialArtById(pos);
        }
    }

    private void clearAllMartialArtsFromDatabase() {
        databaseHandler.removeAllMartialArt();
        int size = martialArts.size();
        martialArts.clear();
        martialArtAdapter.notifyItemRangeRemoved(0, size);
    }

    private void createShowMartialArtUpdatingDialog(int flag, int id) {
        UpdateDialog updateDialog = UpdateDialog.newInstance(flag, id, martialArts.get(flag));
        updateDialog.show(getSupportFragmentManager(), "Update Dialog Fragment");
    }

    @Override
    public void updateMartialArt(int flag) {
        int id = martialArts.get(flag).getMartialArtID();
        createShowMartialArtUpdatingDialog(flag, id);
    }

    @Override
    public void onUpdateButtonClick(int flag, MartialArt martialArt) {
        MartialArt oldMartialArt = martialArts.get(flag);

        Log.d(TAG, "old: " + oldMartialArt.getMartialArtID());
        Log.d(TAG, "old: " + oldMartialArt.getMartialArtName());
        Log.d(TAG, "old: " + oldMartialArt.getMartialArtColor());
        Log.d(TAG, "old: " + oldMartialArt.getMartialArtPrice());

        Log.d(TAG, "new: " + martialArt.getMartialArtID());
        Log.d(TAG, "new: " + martialArt.getMartialArtName());
        Log.d(TAG, "new: " + martialArt.getMartialArtColor());
        Log.d(TAG, "new: " + martialArt.getMartialArtPrice());

        databaseHandler.updateMartialArt(
                martialArt.getMartialArtID(),
                martialArt.getMartialArtName(),
                martialArt.getMartialArtColor(),
                martialArt.getMartialArtPrice()
        );

        Log.d(TAG, "before change: " + martialArts.get(flag).getMartialArtName());
        martialArts.set(flag, martialArt);
        Log.d(TAG, "after change: " + martialArts.get(flag).getMartialArtName());
        martialArtAdapter.notifyItemChanged(flag);

        Snackbar.make(recyclerView, "MartialArt is updated", Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_undo, v -> {
                    databaseHandler.updateMartialArt(
                            oldMartialArt.getMartialArtID(),
                            oldMartialArt.getMartialArtName(),
                            oldMartialArt.getMartialArtColor(),
                            oldMartialArt.getMartialArtPrice()
                    );

                    martialArts.set(flag, oldMartialArt);
                    martialArtAdapter.notifyItemChanged(flag);
                })
                .show();
    }
}