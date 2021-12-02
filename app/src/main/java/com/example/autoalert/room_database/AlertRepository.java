package com.example.autoalert.room_database;

import android.app.Application;

import android.content.Context;


import androidx.lifecycle.LiveData;

import java.util.List;

public class AlertRepository
{
    private AlertDAO AlertDAO;
    private LiveData<List<Alert>> allAlerts;

    public AlertRepository(Application application)
    {
        AlertRoomDatabase alertRoomDatabase = AlertRoomDatabase.getDatabase(application);
        AlertDAO = alertRoomDatabase.AlertDAO();

        allAlerts = AlertDAO.getAllAlertsInAlphabeticalOrder(); // Room executes all queries on a separate thread so the UI thread is not blocked.
    }

    public LiveData<List<Alert>> getAllAlerts()
    {
        return allAlerts;
    }

    public void insertAlert(Alert Alert)
    {
        AlertRoomDatabase.databaseWriterExecutor.execute(() -> AlertDAO.insertAlert(Alert));
    }

    public void deleteAlert(Alert Alert)
    {
        AlertRoomDatabase.databaseWriterExecutor.execute(() -> AlertDAO.deleteAlert(Alert));
    }
}

