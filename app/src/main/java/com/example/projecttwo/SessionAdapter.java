package com.example.projecttwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SessionAdapter extends ArrayAdapter<Session>  {
    List<Session> arr;    Context context;
    public SessionAdapter(Context context,List<Session> arr){
        super(context,0,arr);
        this.context = context;
        this.arr = arr;   }

    @Override
    public Session getItem(int i) {
        return arr.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = (View) inflater.inflate(R.layout.displaytextview, null);
        }
        TextView score = view.findViewById(R.id.scores);
        TextView date = view.findViewById(R.id.date);
        TextView level =view.findViewById(R.id.lev);
        TextView averageTime =view.findViewById(R.id.averagetime);
        score.setText("Score : " + arr.get(i).getScore());
        date.setText("" + arr.get(i).getDate());
        level.setText("Level : " + arr.get(i).getLevel());
        averageTime.setText("Average time : " + arr.get(i).getAvgtime());

        return view;
    }
}

