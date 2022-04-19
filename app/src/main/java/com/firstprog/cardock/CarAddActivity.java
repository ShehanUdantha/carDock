package com.firstprog.cardock;

import static android.content.ContentValues.TAG;
import static android.provider.CalendarContract.CalendarCache.URI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarAddActivity extends AppCompatActivity {

    private EditText editTextCarBrand, editTextCarModel, editTextCarFuelType, editTextCarGear, editTextCarDescription, editTextCarImgUrl, editTextCarManufactureYear, editTextPrice, editTextMileage, editTextCarEngine;
    private Button btnSubmit;
    private String carBrand, carModel, carFuelType, carGear, carDescription, carImgUrl, carManufactureYear, carPrice, carMileage, carEngine, uploadDate;
    private static int id = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);

        //set action bar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Car Registration");

        initView();

        sharedPreferences = getSharedPreferences("GetUserId", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        /**set submit button to clickable
         * assign car details
         * validate car details
         * add car details to array list
         *  then back to the home screen**/

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign car inputs to specific variables
                carBrand = editTextCarBrand.getText().toString();
                carModel = editTextCarModel.getText().toString();
                carFuelType = editTextCarFuelType.getText().toString();
                carGear = editTextCarGear.getText().toString();
                carDescription = editTextCarDescription.getText().toString();
                carImgUrl = editTextCarImgUrl.getText().toString();
                carManufactureYear = editTextCarManufactureYear.getText().toString();
                carPrice = editTextPrice.getText().toString();
                carMileage = editTextMileage.getText().toString();
                carEngine = editTextCarEngine.getText().toString();

                //get current date
                Calendar calendar = Calendar.getInstance();
                uploadDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                int userId = Integer.parseInt(sharedPreferences.getString("USER_ID",""));
                //for test
                Log.d(TAG,"onTestView " + userId);

                //check whether carBrand, carModel, carFuelType, carGear, carImgUrl, carManufactureYear, carPrice, carMileage and carEngine filed inputs are correct or not
                boolean isValid = validator(carBrand, carModel, carFuelType, carGear, carImgUrl, carManufactureYear, carPrice, carMileage, carEngine);
                if(isValid){
                    Toast.makeText(CarAddActivity.this, "Car added to the List Successfully!!", Toast.LENGTH_SHORT).show();
                    //add car details to allCar arrayList
                    Utils.getInstance().initCarData(id, userId, carBrand, carModel, carFuelType, carGear, carDescription, carImgUrl, carManufactureYear, carPrice, carMileage, carEngine, Utils.getInstance().getName(userId), Utils.getInstance().getPhone(userId), Utils.getInstance().getLocation(userId), uploadDate);
                    //increase car id one by one
                    id++;

                    //for test
                    Log.d(TAG,"onTestView " + Utils.getInstance().getAllCars().toString());

                    //back to the home screen
                    Intent intent = new Intent(CarAddActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    if(carBrand.isEmpty() && carModel.isEmpty() && carFuelType.isEmpty() && carGear.isEmpty() && carManufactureYear.isEmpty() && carImgUrl.isEmpty() && carPrice.isEmpty() && carMileage.isEmpty() && carEngine.isEmpty())
                        Toast.makeText(CarAddActivity.this, "Sorry Check Information Again!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    //assign id to variables
    private void initView(){
        editTextCarBrand = findViewById(R.id.editTextCarBrand);
        editTextCarModel = findViewById(R.id.editTextCarModel);
        editTextCarFuelType = findViewById(R.id.editTextCarFuelType);
        editTextCarGear = findViewById(R.id.editTextCarGear);
        editTextCarDescription = findViewById(R.id.editTextCarDescription);
        editTextCarImgUrl = findViewById(R.id.editTextCarImgUrl);
        editTextCarManufactureYear = findViewById(R.id.editTextCarManufactureYear);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextMileage = findViewById(R.id.editTextMileage);
        editTextCarEngine = findViewById(R.id.editTextCarEngine);

        btnSubmit = findViewById(R.id.btnSubmit);
    }

    //validate car details
    private boolean validator(String carBrand, String carModel, String carFuelType, String carGear, String carImgUrl, String carManufactureYear, String price, String carMileage, String carEngine) {
        if(carBrand.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarBrand Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carModel.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarModel Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carFuelType.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarFuelType Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carGear.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarGear Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carImgUrl.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarImgUrl Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!urlValidator(carImgUrl)){
            Toast.makeText(CarAddActivity.this, "CarImgUrl Cannot be Invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carManufactureYear.isEmpty()){
            Toast.makeText(CarAddActivity.this, "CarManufacture Year Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carManufactureYear.length() != 4){
            Toast.makeText(CarAddActivity.this, "CarManufacture Year Should be 4 Numbers!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(price.isEmpty()){
            Toast.makeText(CarAddActivity.this, "Car Price Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carMileage.isEmpty()){
            Toast.makeText(CarAddActivity.this, "Car Mileage Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(carEngine.isEmpty()){
            Toast.makeText(CarAddActivity.this, "Car Engine Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CarAddActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //check the given URL is valid or not
    private boolean urlValidator(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }

}