package com.fenton.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextpassword, editTextName;
    Button button_login;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView signup;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.EmailAddress);
        editTextpassword=findViewById(R.id.Password);
        progressBar = findViewById(R.id.progressbar);
        button_login=findViewById(R.id.button_login);
        signup=findViewById(R.id.signup);
        editTextName=findViewById(R.id.FullName);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
                finish();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, fullname;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextpassword.getText());

                if (TextUtils.isEmpty(email)){

                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}