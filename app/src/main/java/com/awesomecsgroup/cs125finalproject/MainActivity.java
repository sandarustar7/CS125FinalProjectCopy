package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TITLE = "MAIN_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame = findViewById(R.id.startGame);
        Button options = findViewById(R.id.options);

        startGame.setOnClickListener(unused -> {
            Log.d(TITLE, "Start Game!");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        });
        options.setOnClickListener(unused -> {
            Log.d(TITLE, "OptionsActivity");
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivity(intent);
        });

    }
}
