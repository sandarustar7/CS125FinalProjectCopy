package com.awesomecsgroup.cs125finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Main activity is where the user starts. It should have the menu options and so on.

    private static final String TITLE = "MAIN_ACTIVITY";
    private MediaPlayer mediaPlayer;
    int speed = 1;
    int enemyNumber = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start music
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //Get buttons for on click listeners
        Button startGame = findViewById(R.id.startGame);
        Button options = findViewById(R.id.options);

        startGame.setOnClickListener(unused -> {
            //Lets start the game, make a new intent and store speed and enemies that we might have gotten from the options activity.
            Log.d(TITLE, "Start Game!");
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("speed", speed);
            intent.putExtra("enemies", enemyNumber);
            startActivity(intent);
            finish();
        });
        options.setOnClickListener(unused -> {
            //Lets start the options activity. We want to get the options back, so startActivityForResult.
            Log.d(TITLE, "OptionsActivity");
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivityForResult(intent, 42069); //Random request code because I don't know what codes the superclass handles and don't want to cross wires.
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        //Stop music if the screen is paused.
    }

    @Override
    protected void onResume() {
        //Restart music, activity has been restarted.
        super.onResume();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Lets get the information from OptionsActivity, check if its an ok result and if it is the request code we expect.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42069) {
            if (resultCode == RESULT_OK) {
                //Set values, input is good.
                enemyNumber = data.getIntExtra("enemyNumber", 3);
                speed = data.getIntExtra("speed", 5);
            }
        }
    }
}
