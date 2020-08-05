package com.example.documentreader.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.documentreader.Interfaces.OnItemClickListener;
import com.example.documentreader.Models.ModelHomeRecycler;
import com.example.documentreader.R;

import java.util.ArrayList;

public class AdapterHomeRecycler extends RecyclerView.Adapter<AdapterHomeRecycler.AdapterViewHolder> {
    private Context context;
    private ArrayList<ModelHomeRecycler> modelHomeRecyclerList;
    private OnItemClickListener onItemClickListener;


    public AdapterHomeRecycler(Context context,
                               ArrayList<ModelHomeRecycler> modelHomeRecyclerList) {
        this.context = context;
        this.modelHomeRecyclerList = modelHomeRecyclerList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return modelHomeRecyclerList.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemTextView;

        public AdapterViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.item_view_homeRcy_iv);
            itemTextView = itemView.findViewById(R.id.item_view_homeRcy_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClicked(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_home_recycler, parent, false);
        return  new AdapterViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        final ModelHomeRecycler currentItem = modelHomeRecyclerList.get(position);
        holder.itemTextView.setText(currentItem.getBtnText());
        holder.itemImageView.setImageResource(currentItem.getBtnImgId());
    }
}
