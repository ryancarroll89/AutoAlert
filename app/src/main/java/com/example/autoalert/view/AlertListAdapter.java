package com.example.autoalert.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.autoalert.room_database.Alert;
import com.example.autoalert.view.ListItemLongClickListener;

public class AlertListAdapter extends ListAdapter<Alert, AlertViewHolder>
{
    private ListItemLongClickListener mListItemLongClickListener;

    public AlertListAdapter(ListItemLongClickListener listItemLongClickListener, @NonNull DiffUtil.ItemCallback<Alert> diffCallback)
    {
        super(diffCallback);
        this.mListItemLongClickListener = listItemLongClickListener;
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return AlertViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position)
    {
        Alert currentAlertObject = getItem(position);
        holder.bind(currentAlertObject.getAlertName());

        holder.itemView.setOnLongClickListener(v -> {
            mListItemLongClickListener.listItemLongClicked(currentAlertObject);
            return true;
        });
    }

    public static class AlertDiff extends DiffUtil.ItemCallback<Alert>
    {
        @Override
        public boolean areItemsTheSame(@NonNull Alert oldItem, @NonNull Alert newItem)
        {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Alert oldItem, @NonNull Alert newItem)
        {
            return oldItem.getAlertName().equals(newItem.getAlertName());
        }
    }
}


