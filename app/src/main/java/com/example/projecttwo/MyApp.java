package com.example.projecttwo;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application{
    public static String appTag="myApp";
    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyApp.context =
                getApplicationContext();  }
    public static Context getAppContext()
    {   return MyApp.context;  }
}
