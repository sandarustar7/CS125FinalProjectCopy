package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    //Options Activity is where the user can set options like the number of enemies or the speed.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        //Add a listener for the approve button that just calls the below function
        Button approve = findViewById(R.id.ApplyButton);
        approve.setOnClickListener(unused -> buttonPress());
    }

    //buttonPress takes values from the editTexts and santizes them, then gives them back to MainActivity
    public void buttonPress() {
        //get EditTexts
        EditText numEnemies = findViewById(R.id.EnemyNumberBox);
        EditText enemySpeed = findViewById(R.id.EnemySpeedBox);

        //default values for speed
        int enemies = 3;
        int speed = 5;

        //try-catch to avoid numberFormatExceptions
        try {
            enemies = Integer.parseInt(numEnemies.getText().toString());
            speed = Integer.parseInt(enemySpeed.getText().toString());
        } catch (NumberFormatException e) {
            //If we get an exception, ask the user to put an input and exit function
            Toast toast = Toast.makeText(this, "Please put an input", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        //3-30 enemies, 5-50 speed. If not within these values, ask the user to put valid values and exit function
        if (enemies > 30 || enemies < 3 || speed > 50 || speed < 5) {
            Toast toast = Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        //Input is good, return values by placing in result intent sent to MainActivity and finish OptionsActivity
        Intent result = new Intent();
        result.putExtra("enemyNumber", enemies);
        result.putExtra("speed", speed);
        setResult(Activity.RESULT_OK,result);
        finish();
    }

}
