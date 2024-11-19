package com.example.laundry;

import android.widget.TextView;

public class DayLaundry {
    int img;
    String items;
    String date;
    String price;
    DayLaundry(int img, String items, String date,String price){
        this.date = date;
        this.img = img;
        this.items = items;
        this.price = price;
    }
}
