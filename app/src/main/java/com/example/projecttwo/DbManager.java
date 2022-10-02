package com.example.projecttwo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DbManager {

    private static int game_id = 1;
    public static List<Session> session =new LinkedList<Session>();
    SQLiteDatabase myDB;

    private DbManager() {
        init();
    }

    private static DbManager instance = new DbManager();

    public static DbManager getInstance() {
        return instance;
    }

    private void init() {
        try {
            File file = new File(
                    MyApp.getAppContext().
                            getExternalFilesDir(
                                    Environment.DIRECTORY_DOCUMENTS),
                    "mysession.obj");

            //open sqlite db

            this.myDB = SQLiteDatabase.openOrCreateDatabase(file, null);


            String sessionDb = "CREATE TABLE if not exists gamesessions ( id INTEGER , avgtime INTEGER, date TEXT, score INTEGER, bonus INTEGER, level varchar );";
//
            this.myDB.execSQL(sessionDb);
            populate();
        } catch (Exception ex) {
            Log.i(MyApp.appTag,"Error Retrieving:" + ex.getMessage() + "-->"+this.session.toString());}

    }


    public void populate(){
        Cursor resultSet = this.myDB.rawQuery("Select * from gamesessions", null);
        resultSet.moveToFirst(); //move to the first row
        for(int row=0;row<resultSet.getCount();row++){ //iterate thru rows
            Session d = new Session();

            //grab session information and set the object
            d.setAvgtime(resultSet.getInt(1));
            d.setDate(resultSet.getString(2));
            d.setScore(resultSet.getInt(3));
            d.setBonus(resultSet.getInt(4));
            d.setLevel(resultSet.getString(5));
            resultSet.moveToNext(); //advance the cursor
            this.session.add(d);
        }
        resultSet.close();
        Log.i(MyApp.appTag,"number of sessions read: "+this.session.size());
    }
    public boolean addGameSession(Session d){

        try {
            ContentValues values = new ContentValues();
            values.put("id",game_id++);
            values.put("avgtime",d.getAvgtime());
            values.put("date",d.getDate());
            values.put("score",d.getScore());
            values.put("bonus",d.getBonus());
            values.put("level",d.getLevel());
            //insert the doctor to the DB
            game_id++;
            long rec=  myDB.insert("gamesessions", null , values);
            if(rec==-1)
                return false;
            Log.i(MyApp.appTag, "added session" + this.session.size() + " = " + this.session.toString());
            this.session.add(d); //add game to the list

            return true; // successfully inserted
        }catch(Exception ex){
            Log.i(MyApp.appTag,"Error "+ex.getMessage());
            return false;
        }
    }
    public List<Session> getGame(){
        return session;

    }
    public List<Session> getSessionLevels(String level){
        List<Session> searched = new LinkedList<>();

        Cursor resultSet = this.myDB.rawQuery("Select * from gamesessions where level = '"+level+"'", null);
        resultSet.moveToFirst(); //move to the first row
        for(int row=0;row<resultSet.getCount();row++) { //iterate thru rows
            Session d = new Session();

            //grab session information and set the object
            d.setAvgtime(resultSet.getInt(1));
            d.setDate(resultSet.getString(2));
            d.setScore(resultSet.getInt(3));
            d.setBonus(resultSet.getInt(4));
            d.setLevel(resultSet.getString(5));
            searched.add(d);
            Log.i(MyApp.appTag, "results: " + d.toString());

            resultSet.moveToNext();
        }
        resultSet.close();
        return searched;
    }

}

