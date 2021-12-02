package com.example.autoalert.room_database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alert_table")
public class Alert {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "alert_name")
    private String mAlertName;

    public Alert(@NonNull String alertName) {
        mAlertName = alertName;
    }

    public String getAlertName() {
        return mAlertName;
    }

    public void setAlertName(String alertName) {
        mAlertName = alertName;
    }
}