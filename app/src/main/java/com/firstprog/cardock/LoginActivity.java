package com.firstprog.cardock;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextView registerView;
    private Button btnLogin;
    private Matcher matcher;
    private Pattern pattern;
    private String password, email;
    private EditText editTextEmail, editTextPass;

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        pattern = Pattern.compile(EMAIL_REGEX);

        initView();

        //here I used sharedPreferences to keep the login user id
        sharedPreferences = getSharedPreferences("GetUserId", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign user inputs to specific variables
                email = editTextEmail.getText().toString();
                password = editTextPass.getText().toString();

                //for test
                Log.d(TAG,"onTestView " + Utils.getInstance().getAllUsers().toString());

                //check whether email and password filed inputs are correct or not
                boolean isValid = validator(email,password);
                if(isValid){
                    /**
                     * first check th email and password are matching or not
                     * if it match
                     *  get the user id their relevant
                     *  **/
                    int getUserID = Utils.getInstance().getIdFromEmailAndPassword(email,password);
                    //for test
                    Log.d(TAG,"onTestView " + getUserID);

                    //validate user given email and password are match or not with user arraylist
                    boolean isEqual = Utils.getInstance().loginValidator(email,password);
                    if(isEqual){
                        Toast.makeText(LoginActivity.this, "Welcome "+Utils.getInstance().getName(getUserID), Toast.LENGTH_SHORT).show();

                        //store a user id on sharedPreferences
                        editor.putString("USER_ID", String.valueOf(getUserID));
                        editor.commit();

                        //call Home Screen
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Email and Password does not match!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(email.isEmpty() && password.isEmpty())
                        Toast.makeText(LoginActivity.this, "Sorry Check Information Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for test
                Log.d(TAG,"onTestView inside" + Utils.getInstance().getAllUsers().toString());

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //validate user inputs
    private boolean validator(String personEmail, String password) {
        if(password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Password Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.length() < 4){
            Toast.makeText(LoginActivity.this, "Password Should Have Minimum 4 Characters!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personEmail.isEmpty()){
            Toast.makeText(LoginActivity.this, "UserEmail Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!validate(personEmail)){
            Toast.makeText(LoginActivity.this, "UserEmail Cannot be Wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    //assign id to variables
    private void initView(){
        editTextPass = findViewById(R.id.editTextPass);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnLogin = findViewById(R.id.btnLogin);
        registerView = findViewById(R.id.registerView);
    }

    //validate email address
    private boolean validate(final String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}