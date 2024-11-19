package com.example.laundry;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class LaundryListAdapter extends RecyclerView.Adapter<LaundryListAdapter.ViewHolder>{
        Context context;

        ArrayList<DayLaundry> data;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView cloth;
        TextView items;
        TextView price;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.cloth = itemView.findViewById(R.id.cloth);
            this.items = itemView.findViewById(R.id.itemtag);
            this.price = itemView.findViewById(R.id.price);

        }
    }

    LaundryListAdapter(Context context,ArrayList<DayLaundry> data){
    this.context = context;
    this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.date.setText(data.get(position).date);
        holder.items.setText(data.get(position).items);
        holder.cloth.setImageResource(data.get(position).img);
        holder.price.setText(data.get(position).price);
        holder.cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Image_taker.class);
                int x = position;
                intent.putExtra("pos",x);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
