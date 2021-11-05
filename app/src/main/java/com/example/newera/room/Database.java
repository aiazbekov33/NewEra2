package com.example.newera.room;

import androidx.room.RoomDatabase;

import com.example.newera.ui.create.TaskModel;

@androidx.room.Database(entities = {TaskModel.class}, version = 1 )
public abstract class Database extends RoomDatabase {
    public abstract TaskDao taskDao();
}
