package edu.upenn.cis350.hw4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static String difficulty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle the speener
        Spinner speener = (Spinner) findViewById(R.id.spinner);

        // Create an Array Adapter to pass the options into the Speener
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,
                 R.array.speener_values_array, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speener.setAdapter(aa);

        // Let the speener know that we handle its actions in this activity
        speener.setOnItemSelectedListener(this);

    }

    // The spinner methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        difficulty = (String) parent.getItemAtPosition(position);
        Log.d("Notice", difficulty);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void startQuiz(View view) {
        Intent intent  = new Intent(this, QuizActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }

}
