package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        gameOver();

        //On click listeners that allow you to go back to home or to the game
        Button home = findViewById(R.id.homebtn);
        Button game = findViewById(R.id.replaybtn);
        home.setOnClickListener(unused -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        });
        game.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameActivity.class);
            //these extras are added so that the settings the user places in options persist
            intent.putExtra("speed", getIntent().getIntExtra("speed", 5));
            intent.putExtra("enemies", getIntent().getIntExtra("enemies", 3));

            startActivity(intent);
            finish();
        });
    }

    public void gameOver() {
        //gameOver sets the score value of the text box, and formats the text box
        String scr = String.valueOf(getIntent().getIntExtra("score", -1));
        TextView scoreView1 =(TextView) findViewById(R.id.scoreview);
        scoreView1.setText("Your Score: " + scr);
        scoreView1.setTextSize(24);
        scoreView1.setTextColor(Color.rgb(255,255,255));
    }
}
