package com.example.gamificationofscrum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button buttonSignIn;
    Button buttonLogin;
    EditText editTextMail;
    EditText editTextPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn.setPaintFlags(buttonSignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        buttonSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void openActivity2() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void login() {
        String email = editTextMail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        //progressDialog.setMessage("Registering Please Wait...");
        //progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Afterlogin.class));
                        }
                    }
                });
    }

}
