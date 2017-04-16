package com.example.aditya.notesproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private Button tvLogin;
    private EditText Regemail, Regpassword;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        register = (Button) findViewById(R.id.RegisterButton1);
        tvLogin = (Button) findViewById(R.id.backToLogin);
        Regemail = (EditText) findViewById(R.id.RegEmail);
        Regpassword = (EditText) findViewById(R.id.RegPass);

        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.RegisterButton1:
                register();
                break;
            case R.id.backToLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }

    }

    private void register() {
        String email = Regemail.getText().toString();
        String pass = Regpassword.getText().toString();
        if(email.isEmpty() && pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username/password field empty", Toast.LENGTH_LONG).show();
        }
        else {
            db.addUser(email,pass);
            Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}
