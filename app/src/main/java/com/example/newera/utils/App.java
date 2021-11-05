package com.example.newera.utils;

import android.app.Application;

import androidx.room.Room;

import com.example.newera.room.Database;

public class App extends Application {
    public static App instance;
    public static Database dataBase = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getInstance();
    }

    public static Database getInstance(){
        if (dataBase == null){
            dataBase = Room.databaseBuilder(instance.getApplicationContext(),Database.class, "todo")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return dataBase;
    }

}
