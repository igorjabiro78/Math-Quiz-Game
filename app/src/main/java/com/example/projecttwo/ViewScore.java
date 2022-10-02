package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ViewScore extends AppCompatActivity {

    ListView lv;

    TextView bonus;
    Button play,choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score);
        lv=findViewById(R.id.list);
        play = findViewById(R.id.play);
        bonus= findViewById(R.id.bon);


        String value = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getString("level");
        }

        List<Session> sessionlists;
         if(value.equals("easy")){
              sessionlists = DbManager.getInstance().getSessionLevels(value);
         }else{
             sessionlists = DbManager.getInstance().getSessionLevels(value);
         }

        SessionAdapter sessionAdapter = new SessionAdapter(getApplicationContext(),sessionlists);
        lv.setAdapter(sessionAdapter);


        bonus.setText("Bonus "+getAllBonus(value));

    }

    public void playAgaim(View view) {
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void ChooseLevel(View view) {
        choose=findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChooseLevel.class);
                    startActivity(intent);
            }
        });

    }
    public int getAllBonus(String l){
        List<Session> session = DbManager.getInstance().getSessionLevels(l);
        int bonus=0;
        for(int i=1;i<session.size();i++){
            bonus+=session.get(i).getBonus();

        }

        return bonus;
    }

}