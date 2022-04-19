package com.firstprog.cardock;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPersonName, editTextEmailAddress, editTextPhone, editTextPostalAddress, editTextPassword;
    private Button btnLogin;
    private static int id = 0;
    private String personName, personEmail, personLocation, personPhone, password;
    private Pattern pattern;
    private Matcher matcher;
    private TextView btnLoginView;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        pattern = Pattern.compile(EMAIL_REGEX);

        initView();

        /**set login button to clickable
        * assign user details
        * validate user details
        * add user details to array list
        * call second screen**/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign user inputs to specific variables
                personName = editTextPersonName.getText().toString();
                personEmail = editTextEmailAddress.getText().toString();
                personPhone = editTextPhone.getText().toString();
                personLocation = PhoneNumberUtils.formatNumber(editTextPostalAddress.getText().toString());
                password = editTextPassword.getText().toString();

                //check whether Person name,email,phone,address and password filed inputs are correct or not
                boolean isValid = validator(personName,personEmail,personPhone,personLocation,password);
                if(isValid){
                    //check whether user input email previously sign up or not
                    if(Utils.getInstance().checker(personEmail)){
                        Toast.makeText(MainActivity.this, "This Email Already Registered Try Again!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, personName+ " Registered Successfully!", Toast.LENGTH_SHORT).show();
                        //add user details to allUsers arrayList
                        Utils.getInstance().initUserData(id, personName, personEmail, personLocation, personPhone, password);

                        //call Login Screen
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);

                        //for test
                        Log.d(TAG,"onTestView " + Utils.getInstance().getAllUsers().toString());

                        //increase user id one by one
                        id++;
                    }
                }else{
                    if(personLocation.isEmpty() && personPhone.isEmpty() && personEmail.isEmpty() && personName.isEmpty())
                        Toast.makeText(MainActivity.this, "Sorry Check Information Again!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                //for test
                Log.d(TAG,"onTestView " + Utils.getInstance().getAllUsers().toString());
            }
        });
    }

    //validate user inputs
    private boolean validator(String personName, String personEmail, String personPhone, String personLocation, String password) {
        if(personName.isEmpty()){
            Toast.makeText(MainActivity.this, "UserName Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personEmail.isEmpty()){
            Toast.makeText(MainActivity.this, "UserEmail Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!validate(personEmail)){
            Toast.makeText(MainActivity.this, "UserEmail Cannot be Wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personPhone.isEmpty()){
            Toast.makeText(MainActivity.this, "UserPhone Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personPhone.length() != 10){
            Toast.makeText(MainActivity.this, "UserPhone Should be 10 Numbers!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personLocation.isEmpty()){
            Toast.makeText(MainActivity.this, "UserAddress Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.isEmpty()){
            Toast.makeText(MainActivity.this, "Password Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.length() < 4){
            Toast.makeText(MainActivity.this, "Password Should Have Minimum 4 Characters!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    //assign id to variables
    private void initView(){
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPostalAddress = findViewById(R.id.editTextPostalAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginView = findViewById(R.id.btnLoginView);
    }

    //validate email address
    private boolean validate(final String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}