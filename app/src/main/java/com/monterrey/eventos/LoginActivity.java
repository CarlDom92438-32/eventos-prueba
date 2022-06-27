package com.monterrey.eventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    Button mButtonLogin;
    SharedPreferences mPref;

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    AlertDialog mDialog;

    //AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextInputEmail =findViewById(R.id.textInputEmail);
        mTextInputPassword =findViewById(R.id.textInputPassword);
        mButtonLogin =findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("ESPERE UN MOMENTO PORFAVOR").build();
        mPref = getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);

        mButtonLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                login();
            }
        });

    }
    private void login(){

        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6){
                mDialog.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String user = mPref.getString("user","");
                            if (user.equals("client")){

                                Intent intent =new Intent(LoginActivity.this,MapClientActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);



                              /*  Intent intent =new Intent(LoginActivity.this,MapClientActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent); */
                            }}



                        //Toast.makeText(LoginActivity.this, "El Login se realizo correctamente", Toast.LENGTH_SHORT).show();

                        else{
                            Toast.makeText(LoginActivity.this, "La contraseña es incorrecto", Toast.LENGTH_SHORT).show();
                        }
                        mDialog.dismiss();
                    }
                });

            }
        }









    }}











