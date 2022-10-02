package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);


    }

    public void handleEasy(View view) {
        Intent intent = new Intent(this,EasyQ.class);
        intent.putExtra("easy","Easy");

        startActivity(intent);
    }

    public void handleDifficult(View view) {
        Intent intent = new Intent(this,DifficultQ.class);
        intent.putExtra("difficult","Difficult");
        startActivity(intent);
    }
}