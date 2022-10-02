package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyQ extends AppCompatActivity {
    Button btn_start,btn_answer0,btn_answer1,btn_answer2,btn_answer3,viewscore,levels;
    TextView tv_score, tv_questions, tv_timer, tv_bottommessage;

    Game g = new Game();
    int totalTimer=0;


    // variables for sqlite
    private int total_Score=0;
    private int avgtime=0, bonus=0;
    private String date,level="easy";




    CountUpTimer timer = new CountUpTimer(300000) {
        public void onTick(int second) {
            // totaltime++;// total time
            tv_timer.setText(String.valueOf(second));
        }
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);

        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_q);
        btn_start = findViewById(R.id.btn_start);
        btn_answer0= findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2= findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        tv_score = findViewById(R.id.tv_score);
        tv_timer= findViewById(R.id.tv_timer);
        tv_questions = findViewById(R.id.tv_questions);
        tv_bottommessage =  findViewById(R.id.tv_bottommessage);


        tv_timer.setText("0 Sec");
        tv_questions.setText("Calculate : ");
        tv_bottommessage.setText("Press Easy To Start");
        tv_score.setText("0 points");


        viewscore = findViewById(R.id.score);
        levels = findViewById(R.id.level);

        viewscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyScore.class);
                intent.putExtra("easy","easy");
                startActivity(intent);
            }
        });
        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to choose level activity
                Intent intent = new Intent(getApplicationContext(),ChooseLevel.class);
                startActivity(intent);
            }
        });



        // button listener
        View.OnClickListener startButtonclicklistener= new View.OnClickListener() {     // when user click easy button
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);

                NextTurn();
                timer.start(); // start timer each when clicking the start button
            }

        };

        View.OnClickListener answerButtonListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonclicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonclicked.getText().toString());
                // check the correct answer
                g.checkAnswer(answerSelected);

                tv_score.setText(Integer.toString(g.getScore()));
                if(g.getTotalQuestions()<=10){
                    totalTimer+= Integer.parseInt(tv_timer.getText().toString());
                    Log.d("timer","time"+totalTimer);
                    NextTurn();
                    timer.start(); }//start timer after answering the question
                else{
                    //timer.onFinish();
                    tv_bottommessage.setText("Easy Level Completed: " + g.getNumberCorrect() + "/" +(g.getTotalQuestions() - 1));
                    timer.cancel();
                    tv_timer.setText("0");

                    addData();
                }
            }

        };
        btn_start.setOnClickListener(startButtonclicklistener);

        btn_answer0.setOnClickListener(answerButtonListener);
        btn_answer1.setOnClickListener(answerButtonListener);
        btn_answer2.setOnClickListener(answerButtonListener);
        btn_answer3.setOnClickListener(answerButtonListener);
    }
    private void NextTurn() {      // for the next question
        // create new question
        // set text on answer buttons
        // enable answer buttons
        // start the timer
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerarray();
        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionphrase());
        // set text message in the bottom textview
        tv_bottommessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));
    }
    public void addData(){
        Session s= new Session();
        //sqlite
        Date dates =new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date=formatter.format(dates);

        //sqlite insert data

        avgtime=totalTimer;
        total_Score+=g.getScore();

        //check for bonus
        List<Session> sessionList = DbManager.getInstance().getSessionLevels(level);

        int initial_score=0;
        if(!sessionList.isEmpty())
        {
            initial_score = sessionList.get(0).getScore();
        }
        int b=0;
        if(total_Score>initial_score){
            b+=10;
            initial_score=total_Score;
        }
        bonus=b;

        s.setAvgtime(avgtime);
        s.setDate(date);
        s.setScore(total_Score);
        s.setBonus(bonus);
        s.setLevel(level);


        boolean res = DbManager.getInstance().addGameSession(s);
        if(res) {
            Toast.makeText(getApplicationContext(), "Record was added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Record was not added", Toast.LENGTH_LONG).show();
        }

    }



}