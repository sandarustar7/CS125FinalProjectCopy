package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button approve = findViewById(R.id.ApplyButton);
        approve.setOnClickListener(unused -> buttonPress());

    }

    public void buttonPress() {
        EditText numEnemies = findViewById(R.id.EnemyNumberBox);
        EditText enemySpeed = findViewById(R.id.EnemySpeedBox);
        int enemies = Integer.parseInt(numEnemies.getText().toString());
        int speed = Integer.parseInt(enemySpeed.getText().toString());
        Intent result = new Intent();
        result.putExtra("enemyNumber", enemies);
        result.putExtra("speed", speed);
        setResult(Activity.RESULT_OK,result);
        finish();
    }

}
