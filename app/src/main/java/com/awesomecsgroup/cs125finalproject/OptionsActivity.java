package com.awesomecsgroup.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        if (enemies > 30 || enemies < 3 || speed > 50 || speed < 5) {
            Toast toast = Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Intent result = new Intent();
        result.putExtra("enemyNumber", enemies);
        result.putExtra("speed", speed);
        setResult(Activity.RESULT_OK,result);
        finish();
    }

}
