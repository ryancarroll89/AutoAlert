package com.example.autoalert.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlertDAO {

    @Insert
    void insertAlert(Alert Alert);

    @Query("Delete FROM alert_table")
    void deleteAllAlerts();

    @Delete
    void deleteAlert(Alert Alert);

    @Query("Select * FROM alert_table ORDER BY alert_name ASC")
    LiveData<List<Alert>> getAllAlertsInAlphabeticalOrder();
}
