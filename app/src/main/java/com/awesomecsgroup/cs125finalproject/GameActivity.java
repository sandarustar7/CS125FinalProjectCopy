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

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("GameActivity", "pause");
        GameView.setThreadRunning(false);
        try {
            GameView.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mediaPlayer.stop();
        mediaPlayer.release();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GameView.setThreadRunning(false);
        Log.v("GameActivity", "stop");
    }
}
