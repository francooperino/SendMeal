package com.example.lab1appmoviles.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.dao.PlatoDao;

import java.util.concurrent.Executor;

@Database(entities = {Plato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static Executor databaseWriteExecutor;
    private static AppDatabase INSTANCE;

    public abstract PlatoDao platoDao();
    /* .... */
    static AppDatabase getInstance(final Context context) {
        /* .... */
        return INSTANCE;
    }
}
