package com.example.aditya.notesproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login, register;
    private EditText editTextEmail, editTextPassword;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.RegisterButton);
        editTextEmail = (EditText)findViewById(R.id.editText1);
        editTextPassword = (EditText)findViewById(R.id.editText2);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,NotesActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginButton:
                login();
                break;
            case R.id.RegisterButton:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:

        }
    }


    private void login() {
        String email = editTextEmail.getText().toString();
        String pass = editTextPassword.getText().toString();

        if(db.getUser(email,pass)) {
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, NotesActivity.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Incorrect email/password", Toast.LENGTH_SHORT).show();
        }

    }
}
