package com.example.andorid.userloginandregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class RegisterActivity extends AppCompatActivity {

    private EditText mFirstName, mSurname, mAge, mUsername, mPassword;
    private Button mRegisterButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = (EditText) findViewById(R.id.first_name);
        mSurname = (EditText) findViewById(R.id.surname);
        mAge = (EditText) findViewById(R.id.age);
        mUsername = (EditText) findViewById(R.id.user_name);
        mPassword = (EditText) findViewById(R.id.password);

        mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String firstName = mFirstName.getText().toString();
                String surname = mSurname.getText().toString();
                String age = mAge.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String type = "register";

                CheckLoginAndFeedData checkLoginAndFeedData = new CheckLoginAndFeedData(
                        RegisterActivity.this);

                checkLoginAndFeedData.execute(type, firstName, surname, age, username,
                        password);
            }
        });


    }


}
