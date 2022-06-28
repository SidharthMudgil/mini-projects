package com.codehours.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> titleNotes;
    static ArrayList<String> savedNotes;
    static ArrayAdapter<String> arrayAdapter;

    SharedPreferences sp;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addNew) {
            try {
                titleNotes.add("");
                savedNotes.add("");
                openNewNote(titleNotes.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void openNewNote(int index) {
        Intent intent = new Intent(this, OpenNoteActivity.class);
        intent.putExtra("note_ID", index);
        startActivity(intent);
    }

    private void editMemory() {
        try {
            sp.edit().putString("saved_notes", ObjectSerializer.serialize(MainActivity.savedNotes)).apply();
            sp.edit().putString("title_notes", ObjectSerializer.serialize(MainActivity.titleNotes)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDialog(int flag) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_delete)
                .setTitle("DELETE NOTE")
                .setMessage("Are you sure you want to delete this note")
                .setPositiveButton("Confirm", (dialogInterface, i) -> {
                    try {
                        titleNotes.remove(flag);
                        savedNotes.remove(flag);
                        editMemory();
                        Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                        arrayAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                })
                .show();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("com.codehours.notesapp", MODE_PRIVATE);
        try {
            titleNotes = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("title_notes", ObjectSerializer.serialize(new ArrayList<>())));
            savedNotes = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("saved_notes", ObjectSerializer.serialize(new ArrayList<>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (titleNotes.size() == savedNotes.size()) {
            if (titleNotes.size() == 0) {
                String instructions = "For adding new note:\n" +
                        "   1. Click on 3 dots\n" +
                        "   2. Click on add new\n" +
                        "\nFor deleting note:\n" +
                        "   1. Click & Hold the note\n" +
                        "   2. Confirm to delete";
                titleNotes.add("Example Note");
                savedNotes.add(instructions);
            }
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleNotes) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#FD989C"));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(38);
                return view;
            }
        };
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> openNewNote(i));
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            createDialog(i);
            return true;
        });
    }
}