package com.example.autoalert.room_database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alert.class}, version = 1, exportSchema = false)
public abstract class AlertRoomDatabase extends RoomDatabase
{
    public abstract AlertDAO AlertDAO();

    private static volatile AlertRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AlertRoomDatabase getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AlertRoomDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context, AlertRoomDatabase.class, "alert_database").addCallback(databaseCallback).build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {
                // Delete all database data
                AlertDAO dao = INSTANCE.AlertDAO();
                dao.deleteAllAlerts();

                // Add some default data
                Alert Alert = new Alert("Boxing");
                dao.insertAlert(Alert);

                Alert = new Alert("Judo");
                dao.insertAlert(Alert);
            });
        }
    };
}

