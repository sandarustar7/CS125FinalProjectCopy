package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        gameOver();
    }

    public void gameOver() {
        String scr = String.valueOf(getIntent().getIntExtra("score", -1));
        TextView scoreView1 =(TextView) findViewById(R.id.scoreview);
        scoreView1.setText("Your Score: " + scr);
        //scoreView1.setTextSize();
        scoreView1.setTextColor(Color.rgb(255,255,255));
    }
}
