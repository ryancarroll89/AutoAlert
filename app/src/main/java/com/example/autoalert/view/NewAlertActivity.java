package com.example.autoalert.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.autoalert.R;

public class NewAlertActivity extends AppCompatActivity
{
    public static final String NEW_MAR_ART_KEY = "com.example.autoalert.GET_BACK_ALERT";
    private EditText edtMA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alert);

        edtMA = findViewById(R.id.edtAlert);

        Button btnSave = findViewById(R.id.btnSaveAlert);
        btnSave.setOnClickListener(v ->
        {
            Intent getBackIntent = new Intent();

            if (TextUtils.isEmpty(edtMA.getText())) {
                setResult(RESULT_CANCELED, getBackIntent);
            }
            else
            {
                String favMA = edtMA.getText().toString();
                getBackIntent.putExtra(NEW_MAR_ART_KEY, favMA);
                setResult(RESULT_OK, getBackIntent);
            }

            finish();
        });
    }
}
