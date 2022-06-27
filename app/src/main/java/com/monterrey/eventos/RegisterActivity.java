package com.monterrey.eventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

import dmax.dialog.SpotsDialog;
import models.Client;
import providers.AuthProvider;
import providers.ClientProvider;

public class RegisterActivity extends AppCompatActivity {
    //SharedPreferences mPref;
    //FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;

    //vistas
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputIne;
    TextInputEditText mTextInputDireccion;
    TextInputEditText mTextInputTelefono;




    AlertDialog mDialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //mAuth = FirebaseAuth.getInstance();

        //mDatabase= FirebaseDatabase.getInstance().getReference();

        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();


        //mPref = getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);


        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("ESPERE UN MOMENTO PORFAVOR").build();

        mButtonRegister=findViewById(R.id.btnRegister);
        mTextInputName=findViewById(R.id.textInputName);
        mTextInputEmail=findViewById(R.id.textInputEmail);
        mTextInputPassword=findViewById(R.id.textInputPassword);
        mTextInputIne=findViewById(R.id.textInputIne);
        mTextInputDireccion=findViewById(R.id.textInputDireccion);
        mTextInputTelefono=findViewById(R.id.textInputTelefono);




        //Toast.makeText(this, "El valor que selecciono fue"+ selectedUser, Toast.LENGTH_SHORT).show();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickregister();
            }
        });
    }
    void clickregister(){
        final String name= mTextInputName.getText().toString();
        final String email= mTextInputEmail.getText().toString();
        final String password= mTextInputPassword.getText().toString();
        final String ine=mTextInputIne.getText().toString();
        final String direccion= mTextInputDireccion.getText().toString();
        final String telefono=mTextInputTelefono.getText().toString();




        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !ine.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty()){

            if (telefono.length() ==10 ){
                mDialog.show();
                register(name,email,password,ine,direccion,telefono);


            }

            else{

                Toast.makeText(RegisterActivity.this,"El telefono debe ser a 10 digitos",Toast.LENGTH_SHORT).show();



            }

        }
        else{

            Toast.makeText(RegisterActivity.this,"Llenar los espacios vacios",Toast.LENGTH_SHORT).show();



        }



    }








    void register(final String name,String email, String password,String ine,String direccion, String telefono){
        mAuthProvider.register(email,telefono).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if (task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id,name,email,ine,telefono,direccion,password);
                    create(client);


                    //saveUser(id,name,email,ine,direccion,telefono,password);
                }

            }
        });
    }

    void create(Client client){
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this,MapClientActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //Toast.makeText(RegisterActivity.this,"El registro se ralizo correctamente",Toast.LENGTH_SHORT).show();

                }


            }
        });

    }


    public Context getActivity() {
        return null;
    }



    /*

    void saveUser(String id,String name,String email,String ine,String direccion, String telefono, String password){
        String selectedUser = mPref.getString("user","");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setIne(ine);
        user.setDireccion(direccion);
        user.setTelefono(telefono);
        user.setPassword(password);





        if (selectedUser.equals("client")){
            mDatabase.child("User").child("client").child(id).push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
                else {
                        Toast.makeText(RegisterActivity.this, "Fallo el Registro", Toast.LENGTH_SHORT).show();

                    }}
            });
        }

    }

     */



}
