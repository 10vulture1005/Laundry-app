package com.example.laundry;

//made by vaidik saxena cs iiitl'28

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ArrayList<DayLaundry> info = new ArrayList<>();
    ImageView d;
    LaundryListAdapter adapter;
    FloatingActionButton addnew_data;
    File picturesDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String todayDate = String.format("%02d-%02d-%02d", day, month, year % 100);

        // Initialize the directory for the current date
        picturesDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), todayDate);

        // Check if the directory exists, if not, create it
        if (!picturesDir.exists()) {
            boolean isCreated = picturesDir.mkdir();
            if (!isCreated) {
                Log.e("Directory Creation", "Failed to create directory: " + picturesDir.getAbsolutePath());
                Toast.makeText(MainActivity.this, "Failed to create directory", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("Directory Creation", "Directory created: " + picturesDir.getAbsolutePath());
            }
        }

        addnew_data = findViewById(R.id.addnew_data);
        adapter = new LaundryListAdapter(this, info);

        // Load the laundry data (files) from the current date's directory
        loadLaundryData(todayDate);

        addnew_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!picturesDir.exists()) {
                    boolean isCreated = picturesDir.mkdir();
                    if (isCreated) {
                        Toast.makeText(MainActivity.this, "Directory created: " + picturesDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Directory Creation", "Failed to create directory.");

                    }
                }

                Intent intent = new Intent(MainActivity.this, Image_taker.class);
                intent.putExtra("directoryPath", picturesDir.getAbsolutePath());
                intent.putExtra("pos",info.size()-1);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    // Method to load laundry data (files) from the directory
    private void loadLaundryData(String date) {

        File[] files = picturesDir.getParentFile().listFiles();

        if (files != null && files.length > 0) {
            String items = "items: " + files.length;
            String price = "";

            for (File file : files) {
                File f[] = file.listFiles();
                 items = "items: " + f.length;
                 price = ""+f.length*10;
                date = file.getName();
                info.add(new DayLaundry(R.drawable.dummy, items, date,price));
            }
        } else {
            Log.i("LaundryData", "No files found for date: " + date);
            Toast.makeText(this, "No laundry records found for " + date, Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }

    public void click(View view) {


    }

}
