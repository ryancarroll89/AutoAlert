package com.example.autoalert.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.autoalert.R;
import com.example.autoalert.room_database.Alert;
import com.example.autoalert.view_model.AlertViewModel;

public class MainActivity extends AppCompatActivity implements ListItemLongClickListener
{
    private AlertViewModel mAlertViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerview);
        AlertListAdapter listAdapter = new AlertListAdapter(this, new AlertListAdapter.AlertDiff());
        rv.setAdapter(listAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAlertViewModel = new ViewModelProvider(this).get(AlertViewModel.class);
        mAlertViewModel.getAllAlerts().observe(this, Alerts ->
        {
            listAdapter.submitList(Alerts);
        });
        findViewById(R.id.fab).setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, NewAlertActivity.class);
            newAlertActivityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> newAlertActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    String favMA = data.getStringExtra(NewAlertActivity.NEW_MAR_ART_KEY);
                    mAlertViewModel.insertAlert(new Alert(favMA));
                }
            }
    );

    @Override
    public void listItemLongClicked(Alert Alert)
    {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Continue with delete operation
                        mAlertViewModel.deleteAlert(Alert);
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}