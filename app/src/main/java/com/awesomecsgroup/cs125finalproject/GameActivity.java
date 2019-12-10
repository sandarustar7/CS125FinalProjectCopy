package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    //GameActivity is essentially a wrapper class for GameView, but also handles the music for the game.
    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Lets make the app fullscreen, remove the title, and set the content to be the GameView.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

    }

    @Override
    //Pause music, end the rendering thread.
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        Log.v("GameActivity", "pause");
        GameView.setThreadRunning(false);
        try {
            GameView.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        //Resume the music, assume thread is started by surfaceCreated.
        super.onResume();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_music);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    public Intent returnIntent() {
        //Returns intent for extra-getting.
        return getIntent();
    }
}
