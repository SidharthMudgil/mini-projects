package com.example.memepur;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memepur.databinding.ActivityMainBinding;
import com.example.memepur.fragment.CategoryFragment;
import com.example.memepur.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryFragment.CategoryInteractionListener {

    public static final String CATEGORY_KEY = "category@memempur";
    private CategoryFragment categoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.memepur.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentById(R.id.category_fragment_container);

        FloatingActionButton fab = findViewById(R.id.fab_new_category);
        fab.setOnClickListener(v -> createShowPrompt());
    }

    public void createShowPrompt() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.category_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageButton closeButton = dialog.findViewById(R.id.category_close_cancel);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        Button createButton = dialog.findViewById(R.id.input_create_button);
        createButton.setOnClickListener(v -> {

            TextInputEditText categoryNameET = dialog.findViewById(R.id.input_category_name);
            String categoryName = String.valueOf(categoryNameET.getText());

            if (categoryName.length() > 1){
                Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
                Category category = new Category(categoryName, new ArrayList<>());
                categoryFragment.createNewCategory(category);
                categoryIsClicked(category);
            } else {
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void categoryIsClicked(Category category) {
        Intent categoryIntent = new Intent(this, JokeActivity.class);
        categoryIntent.putExtra(CATEGORY_KEY, category);
        try {
            startActivity(categoryIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}