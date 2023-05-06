package com.example.switchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText username, username2, password, password2;
    Button signIn, signUp;
    MyDatabaseHelper db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        username2 = findViewById(R.id.username2);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);

        db = new MyDatabaseHelper(this);

        intent = new Intent(this, MainActivity.class);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals(username2.getText().toString()) && password.getText().toString().equals(password2.getText().toString())) {
                    db.addUser(username.getText().toString(), password.getText().toString());
                    Toast.makeText(SignUp.this, "Account registered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                    Toast.makeText(SignUp.this, "Please Enter proper username and password", Toast.LENGTH_SHORT).show();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}