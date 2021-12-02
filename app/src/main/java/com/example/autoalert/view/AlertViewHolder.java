package com.example.autoalert.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.autoalert.R;

public class AlertViewHolder extends RecyclerView.ViewHolder
{
    private final TextView txtAlert;

    private AlertViewHolder(View itemView)
    {
        super(itemView);
        txtAlert = itemView.findViewById(R.id.txtAlert);
    }

    public void bind(String text)
    {
        txtAlert.setText(text);
    }

    public static AlertViewHolder create(ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_alert, parent, false);
        return new AlertViewHolder(view);
    }
}

