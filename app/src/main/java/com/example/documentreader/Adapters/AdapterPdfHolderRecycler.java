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
import com.example.documentreader.Models.ModelPdfHolderRecycler;
import com.example.documentreader.R;

import java.util.ArrayList;

public class AdapterPdfHolderRecycler extends RecyclerView.Adapter<AdapterPdfHolderRecycler.AdapterViewHolder> {
    private Context context;
    private ArrayList<ModelPdfHolderRecycler> modelPdfHolderRecyclerList;
    private OnItemClickListener onItemClickListener;


    public AdapterPdfHolderRecycler(Context context,
                                    ArrayList<ModelPdfHolderRecycler> modelPdfHolderRecyclerList) {
        this.context = context;
        this.modelPdfHolderRecyclerList = modelPdfHolderRecyclerList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return modelPdfHolderRecyclerList.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImageView;
        TextView itemFileUriTv;
        TextView itemFileUriNameTv;

        public AdapterViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.item_view_pdfHolder_iv);
            itemFileUriTv = itemView.findViewById(R.id.item_view_pdfHolder_fileUri_tv);
            itemFileUriNameTv = itemView.findViewById(R.id.item_view_pdfHolder_fileUriName_tv);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pdfholder_recycler, parent, false);
        return new AdapterViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        ModelPdfHolderRecycler currentItem = modelPdfHolderRecyclerList.get(position);
        holder.itemImageView.setImageResource(currentItem.getImgId());
        holder.itemFileUriNameTv.setText(currentItem.getPdfFileName());
        holder.itemFileUriTv.setText(currentItem.getPdfFileUri());
    }


}
