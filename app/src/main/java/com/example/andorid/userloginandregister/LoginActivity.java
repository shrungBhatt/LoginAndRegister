package com.example.andorid.userloginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//This class represents the login screen

public class LoginActivity extends AppCompatActivity {
    private EditText mUserNameEditText;
    private EditText mUserPasswordEditText;
    private Button mLoginButton;
    private Button mRegisterButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //UserName EditText
        mUserNameEditText = (EditText)findViewById(R.id.username);

        //UserPassword EditText
        mUserPasswordEditText = (EditText)findViewById(R.id.password_login);

        //Login Button
        mLoginButton = (Button)findViewById(R.id.btnLogin);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String username = mUserNameEditText.getText().toString();
                String password = mUserPasswordEditText.getText().toString();

                //String to see that wether it is an login or register background task.
                String type = "login";

                CheckLoginAndFeedData checkLoginAndFeedData
                        = new CheckLoginAndFeedData(LoginActivity.this);

                //Starting the background task.
                checkLoginAndFeedData.execute(type,username,password);
            }
        });

        //Register Button.
        mRegisterButton = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }

}
