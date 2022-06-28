package com.example.martialarts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.martialarts.model.DatabaseHandler;
import com.example.martialarts.model.MartialArt;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddMartialArtActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;

    private TextInputEditText nameEditText;
    private TextInputEditText colorEditText;
    private TextInputEditText priceEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        databaseHandler = new DatabaseHandler(this);

        Button cancelButton = findViewById(R.id.button_cancel);
        Button addMartialArtButton = findViewById(R.id.button_add);

        nameEditText = findViewById(R.id.editText_name);
        colorEditText = findViewById(R.id.editText_color);
        priceEditText = findViewById(R.id.editText_price);

        nameEditText.requestFocus();

        cancelButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        addMartialArtButton.setOnClickListener(v -> checkForm());
    }

    private void checkForm() {
        TextInputEditText[] allFields = {
                nameEditText,
                colorEditText,
                priceEditText
        };

        List<TextInputEditText> errorFields = new ArrayList<>();

        for (TextInputEditText inputField : allFields) {
            if (TextUtils.isEmpty(inputField.getText())) {

                errorFields.add(inputField); //add field if empty

                for (int i = errorFields.size() - 1; i >= 0 ; i--) {
                    TextInputEditText currentField = errorFields.get(i);
                    currentField.setError(getString(R.string.error_message)); //set the error
                    currentField.requestFocus(); //focus the field
                    showKeyboard(); //show the keyboard
                }
            }
        }

        if (errorFields.isEmpty()) {
            closeKeyboard();
            addMartialArtObjectToDatabase();
            finish();
        }
    }

    private void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void addMartialArtObjectToDatabase() {
        String martialArtName = String.valueOf(nameEditText.getText());
        String martialArtColor = String.valueOf(colorEditText.getText());
        double martialArtPrice = Double.parseDouble(String.valueOf(priceEditText.getText()));

        MartialArt martialArt = new MartialArt(
                0,
                martialArtName,
                martialArtColor,
                martialArtPrice
        );

        databaseHandler.addMartialArt(martialArt);
        Intent intent = new Intent();
        intent.putExtra(MainActivity.OBJECT_KEY, martialArt);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}