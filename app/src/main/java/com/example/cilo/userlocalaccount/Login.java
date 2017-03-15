package com.example.cilo.userlocalaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText emailET, passET;
    Button loginBtn;
    LocalUserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userStorage = new LocalUserStorage(this);

        emailET = (EditText) findViewById(R.id.email);
        passET = (EditText) findViewById(R.id.password);

        loginBtn = (Button)findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arrayList = userStorage.getData();
                String savedUname = arrayList.get(0);
                String savedEmail = arrayList.get(1);
                String savedPwd = arrayList.get(2);

                String enteredEmail = emailET.getText().toString();
                String enteredPass = passET.getText().toString();

                if(enteredEmail.equals(savedEmail) &&
                        enteredPass.equals(savedPwd)){

                    userStorage.setLoggedIn(true);

                    Intent intent = new Intent(Login.this,
                            Home.class);
                    intent.putExtra("username",savedUname);
                    startActivity(intent);

                }else{

                    Toast.makeText(getApplicationContext(),
                            "Wrong email or password!",
                            Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}








