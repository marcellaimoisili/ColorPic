package com.cs1998.colorpic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class CustomAdapter extends ArrayAdapter<PicModel> {
//        public CustomAdapter(Context context, ArrayList<PicModel> picList) {
//        super(context, 0, picList);
//    }
//}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<PicModel> picList;
    private static AdapterOnClickHandler mAdapterOnClickHandler;


    public CustomAdapter(ArrayList<PicModel> picList, AdapterOnClickHandler ach) {
        this.picList = picList;
        this.mAdapterOnClickHandler = ach;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView picIV;
        public TextView closestTV;
        public TextView distanceTV;
        public CustomViewHolder(View view) {
            super(view);
            picIV = view.findViewById(R.id.picView);
            closestTV =view.findViewById(R.id.closestColor);
            distanceTV = view.findViewById(R.id.colorDist);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mAdapterOnClickHandler.onClick(adapterPosition);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cell, parent, false);

//        Picasso.setSingletonInstance(new Picasso.Builder(this).build());

        CustomViewHolder cvh = new CustomViewHolder(view);
        return cvh;


    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        holder.picIV.setImageURI(picList.get(position).named);
        Picasso.get()
                .load(picList.get(position).image.named)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.picIV);
        holder.closestTV.setText(picList.get(position).name.closest_named_hex);
        holder.distanceTV.setText(Integer.toString(picList.get(position).name.distance));
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    public interface AdapterOnClickHandler {
        void onClick(int position);

    }
}