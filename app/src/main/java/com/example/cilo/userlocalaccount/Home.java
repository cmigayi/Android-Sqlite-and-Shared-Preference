package com.example.cilo.userlocalaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView nameTV;
    Button logoutBtn;
    LocalUserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userStorage = new LocalUserStorage(this);

        Intent intent = getIntent();
        String uname = intent.getStringExtra("username");
        logoutBtn = (Button)findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStorage.setLoggedIn(false);

                Intent intent = new Intent(Home.this,
                        Login.class);
                startActivity(intent);
            }
        });

        nameTV = (TextView) findViewById(R.id.nameTv);
        nameTV.setText(uname);
    }
}
