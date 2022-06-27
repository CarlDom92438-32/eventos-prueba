package com.monterrey.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import providers.AuthProvider;

public class MapClientActivity extends AppCompatActivity {
    Button mButtonLogout;
    Button mButtonLogout1;
    AuthProvider mAuthProvider;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_client);

        mButtonLogout = findViewById(R.id.btnLogout);
        mButtonLogout1 = findViewById(R.id.btnLogout1);
        mAuthProvider = new AuthProvider();




        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuthProvider.logout();

                Intent intent = new Intent(MapClientActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mButtonLogout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuthProvider.logout1();

                Intent intent = new Intent(MapClientActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}


