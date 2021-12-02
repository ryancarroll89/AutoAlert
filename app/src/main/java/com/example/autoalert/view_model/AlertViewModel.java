package com.example.autoalert.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.autoalert.room_database.Alert;
import com.example.autoalert.room_database.AlertRepository;

import java.util.List;

public class AlertViewModel extends AndroidViewModel
{
    private AlertRepository AlertRepository;
    private final LiveData<List<Alert>> allAlerts;

    public AlertViewModel(@NonNull Application application)
    {
        super(application);
        AlertRepository = new AlertRepository(application);
        allAlerts = AlertRepository.getAllAlerts();
    }

    public LiveData<List<Alert>> getAllAlerts()
    {
        return allAlerts;
    }

    public void insertAlert(Alert Alert)
    {
        AlertRepository.insertAlert(Alert);
    }

    public void deleteAlert(Alert Alert)
    {
        AlertRepository.deleteAlert(Alert);
    }
}



