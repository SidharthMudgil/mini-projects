package com.codehours.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    class GetWeatherData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder result = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    result.append(line).append("\n");
                }

                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("ERROR", "doInBackground");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");
                Log.i("result", weatherInfo);

                JSONArray jsonArray = new JSONArray(weatherInfo);

                JSONObject weather = jsonArray.getJSONObject(0);

                textView.setText(weather.getString("description"));
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("ERROR", "onPostExecute");
            }
        }
    }

    public void getInfo(View view) {
        GetWeatherData getWeatherData = new GetWeatherData();

        String cityName = editText.getText().toString();
        if (cityName.isEmpty()) {
            Toast.makeText(this, "City Not Found", Toast.LENGTH_SHORT).show();
        } else {
            String url = String.format(Locale.getDefault(), "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=ce7e1b0a8d788282d8f03bfa60f27808", cityName);
            getWeatherData.execute(url);
        }

        //hide the keyboard after pressing calculate button
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.weatherInfo);
        editText = findViewById(R.id.cityName);
    }
}