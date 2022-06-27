package com.monterrey.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select_option_auth_Activity extends AppCompatActivity {

    Button mButtonGoToLogin;
    Button mButtonGoToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        mButtonGoToLogin =findViewById(R.id.btnGoToLogin);
        mButtonGoToRegister=findViewById(R.id.btnGoToRegister);


        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToRegister();
            }
        });
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                goToLogin();
            }
        });

    }

    public void goToLogin(){
        Intent intent = new Intent(select_option_auth_Activity.this,LoginActivity.class);
        startActivity(intent);

    }
    public void goToRegister(){
        Intent intent = new Intent(select_option_auth_Activity.this,RegisterActivity.class);
        startActivity(intent);

    }


}