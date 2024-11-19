package com.example.laundry;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private static final String TAG = "ImageRecyclerAdapter";
    private final Context context;
    private final ArrayList<Imagedata> imagePaths;

    public ImageRecyclerAdapter(Context context, ArrayList<Imagedata> img_list) {
        this.context = context;
        this.imagePaths = img_list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_taken;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_taken = itemView.findViewById(R.id.image_taken);
        }
    }

    @NonNull
    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecyclerAdapter.ViewHolder holder, int position) {
        holder.image_taken.setImageResource(R.drawable.dummy);
        holder.image_taken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        File imageFile = new File(imagePath);
//        Uri imageUri = Uri.fromFile(imageFile);
//
//        Log.d(TAG, "Binding view for position: " + position + ", imagePath: " + imagePath);
//
//        if (imageFile.exists()) {
//            e
//            Glide.with(context)
//                    .load(imageUri)
//                    .placeholder(R.drawable.dummy)
//                    .error(R.drawable.dummy)
//                    .into(holder.image_taken);
//        } else {
//
//
//        }
    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }
}
