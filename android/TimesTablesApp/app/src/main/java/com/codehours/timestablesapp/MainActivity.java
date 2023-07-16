package com.codehours.timestablesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void generateTable(int n) {
        ListView listView = findViewById(R.id.listView);
        ArrayList<Integer> table = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            table.add(n * i);
        }

        listView.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, table));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar2);

        seekBar.setMin(1);
        seekBar.setMax(100);
        seekBar.setProgress(15);

        generateTable(15);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                generateTable(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}