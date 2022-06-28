package com.codehours.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private double toUsd(double inr) {
        return inr/74.45;
    }
    public void convertCurrency(View view) {
        Log.i("Info","Button Pressed");

        EditText editText = findViewById(R.id.indianRupee);
        Log.i("Info",editText.getText().toString());

        String amountInRupees = editText.getText().toString();

        double INR = Double.parseDouble(amountInRupees);
        Log.i("Info","" + INR);

        double USD = toUsd(INR);
        Log.i("Info","" + USD);

        TextView textView = findViewById(R.id.usdText);
        //convert NN.NN + USD -> to string
        String amountInDollars = String.format(Locale.getDefault(),"%.2f USD",USD);
        textView.setText(amountInDollars);
        Toast.  makeText(this, "currency converted", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}