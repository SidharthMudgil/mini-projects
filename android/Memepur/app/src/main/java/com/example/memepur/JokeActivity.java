package com.example.memepur;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memepur.fragment.JokeFragment;
import com.example.memepur.model.Category;
import com.example.memepur.model.CategoryManager;
import com.example.memepur.model.Joke;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class JokeActivity extends AppCompatActivity {

    private JokeFragment jokeFragment;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_KEY);
        setTitle(category.getCategoryName());

        jokeFragment = JokeFragment.newInstance(category);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.joke_fragment_container, jokeFragment)
                .addToBackStack(null)
                .commit();

        FloatingActionButton fab = findViewById(R.id.fab_new_joke);
        fab.setOnClickListener(v -> createShowPrompt());
    }

    public void createShowPrompt() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.joke_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageButton closeButton = dialog.findViewById(R.id.joke_close_cancel);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        Button addButton = dialog.findViewById(R.id.input_add_button);
        addButton.setOnClickListener(v -> {
            TextInputEditText jokeBodyET = dialog.findViewById(R.id.input_joke_body);
            RatingBar ratingBar = dialog.findViewById(R.id.input_joke_rating);

            String jokeBody = String.valueOf(jokeBodyET.getText());

            if (jokeBody.length() > 1) {
                Toast.makeText(JokeActivity.this, "Added", Toast.LENGTH_SHORT).show();
                double jokeRating = ratingBar.getRating();
                Joke joke = new Joke(jokeBody, jokeRating);
                jokeFragment.addJokeToCategory(joke);
            } else {
                Toast.makeText(JokeActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        CategoryManager.updateCategory(category, getApplicationContext());

        finish();
    }
}