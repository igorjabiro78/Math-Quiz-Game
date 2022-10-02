package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyScore extends AppCompatActivity {

    private TextView average,score;
    private Button play,allscores;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);
        average= findViewById(R.id.average);
        score=findViewById(R.id.myscore);
        play=findViewById(R.id.playagain);


         level = null;
        Bundle extras = getIntent().getExtras();

        if(extras.containsKey("easy")){
            level = extras.getString("easy");
          
        }
        else if (extras.containsKey("difficult")){
            level = extras.getString("difficult");
            
        }
        List<Session> sessionlists = DbManager.getInstance().getSessionLevels(level);
        int n=sessionlists.size();
        average.setText("Average Time : "+sessionlists.get(n-1).getAvgtime());
        score.setText("Score: "+sessionlists.get(n-1).getScore());


    }

    public void playAgaim (View view) {
        play=findViewById(R.id.playagain);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    public void AllScores(View view) {
        allscores =findViewById(R.id.allscores);
        allscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewScore.class);
                intent.putExtra("level",level);
                startActivity(intent);
            }
        });
    }
}