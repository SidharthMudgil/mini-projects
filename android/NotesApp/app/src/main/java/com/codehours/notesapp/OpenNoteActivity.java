package com.codehours.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class OpenNoteActivity extends AppCompatActivity {
    EditText editText;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_note);

        editText = findViewById(R.id.notesArea);
        sp = getSharedPreferences("com.codehours.notesapp", MODE_PRIVATE);

        Intent intent = getIntent();
        int noteID = intent.getIntExtra("note_ID", -1);

        if (noteID != -1) {
            editText.setText(MainActivity.savedNotes.get(noteID));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = String.valueOf(editText.getText());
                String[] line = text.split("\n");
                line = line[0].split(" ");
                StringBuilder noteTitle = new StringBuilder();
                if (line.length > 3) {
                    for (int j = 0; j < 3; j++) {
                        noteTitle.append(line[j]).append(" ");
                    }
                } else {
                    for (String str : line) {
                        noteTitle.append(str).append(" ");
                    }
                }
                MainActivity.titleNotes.set(noteID, String.valueOf(noteTitle));
                MainActivity.savedNotes.set(noteID, text);

                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    sp.edit().putString("saved_notes", ObjectSerializer.serialize(MainActivity.savedNotes)).apply();
                    sp.edit().putString("title_notes", ObjectSerializer.serialize(MainActivity.titleNotes)).apply();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}