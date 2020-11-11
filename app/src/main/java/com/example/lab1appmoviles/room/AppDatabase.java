package com.example.lab1appmoviles.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.dao.PedidoDao;
import com.example.lab1appmoviles.dao.PlatoDao;

import java.util.concurrent.Executor;




@Database(entities = {Plato.class, Pedido.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public static Executor databaseWriteExecutor;


    static AppDatabase getInstance(final Context context) {

        if (INSTANCE ==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").fallbackToDestructiveMigration().build();
            databaseWriteExecutor=INSTANCE.getTransactionExecutor();
        }
        return INSTANCE;

        //agregado el codigo del ingles
    }

    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();
    /* .... */
    /*static AppDatabase getInstance(final Context context) {

        return INSTANCE;
    }*/
}


/*public abstract class AppDatabase extends RoomDatabase {
    public static Executor databaseWriteExecutor;
    private static AppDatabase INSTANCE;
    public abstract PlatoDao platoDao();


static AppDatabase getInstance(final Context context) {

    if (INSTANCE ==null){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").fallbackToDestructiveMigration().build();
    }
    return INSTANCE;

    //agregado el codigo del ingles
}
}
*/