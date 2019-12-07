package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TITLE = "MAIN_ACTIVITY";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Button startGame = findViewById(R.id.startGame);
        Button options = findViewById(R.id.options);

        startGame.setOnClickListener(unused -> {
            Log.d(TITLE, "Start Game!");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
        options.setOnClickListener(unused -> {
            Log.d(TITLE, "OptionsActivity");
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
