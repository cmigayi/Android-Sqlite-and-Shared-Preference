package com.example.cilo.userlocalaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText emailET,usernameET,passET;
    TextView dbTV;
    Button regBtn,loginBtn,dbBtn;
    LocalUserStorage userStorage;
    LocalUserDatabase ldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userStorage = new LocalUserStorage(this);

        ldb = new LocalUserDatabase(this, "",null,1);

        boolean status = userStorage.getLoggedInStatus();
        if(status == true){

            ArrayList<String> arrayList = userStorage.getData();
            String usern = arrayList.get(0);

            Intent intent = new Intent(MainActivity.this,
                    Home.class);
            intent.putExtra("username",usern);
            startActivity(intent);
        }

        emailET = (EditText) findViewById(R.id.email);
        usernameET = (EditText) findViewById(R.id.username);
        passET = (EditText) findViewById(R.id.password);

        dbTV = (TextView) findViewById(R.id.dbTV);

        loginBtn = (Button)findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        Login.class);
                startActivity(intent);
            }
        });

        regBtn = (Button)findViewById(R.id.registerBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = usernameET.getText().toString();
                String email = emailET.getText().toString();
                String pass = passET.getText().toString();

//                userStorage.storeData(uname,email, pass);
//
//                Toast.makeText(getApplicationContext(),
//                        "Data Stored!",
//                        Toast.LENGTH_LONG).show();
                ldb.insertUser(uname,email,pass);

            }
        });

        dbBtn = (Button) findViewById(R.id.dbBtn);
        dbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ldb.listAllUsers(dbTV);
            }
        });


    }
}











