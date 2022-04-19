package com.firstprog.cardock;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private RecyclerView carRecView;
    private CarRecViewAdapter carRecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("GetUserId", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String id = sharedPreferences.getString("USER_ID","");

        /**
         * first i create a recycle view inside activity_home.xml
         * then i create a card view inside single_car.xml
         * then using adapter combine both and
         * get a car arraylist using utils.java
         * print it as a cardView list
         * **/
        carRecView = findViewById(R.id.carRecView);
        carRecAdapter = new CarRecViewAdapter(this);
        carRecView.setAdapter(carRecAdapter);
        carRecView.setLayoutManager(new LinearLayoutManager(this));

        carRecAdapter.setCars(Utils.getInstance().getAllCars());

        //use fab icon to open a car registration screen
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call car adding page
                Intent intent = new Intent(HomeActivity.this, CarAddActivity.class);
                startActivity(intent);

                //for test
                Log.d(TAG,"onTestView Home " + id);
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        //if user backPressed clear sharedPreferences value
        editor.clear();
        editor.apply();
        Toast.makeText(HomeActivity.this, "User Sign Out!", Toast.LENGTH_SHORT).show();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}