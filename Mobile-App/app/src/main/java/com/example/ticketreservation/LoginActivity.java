package com.example.ticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketreservation.managers.ContextManager;
import com.example.ticketreservation.managers.LoginManager;

public class LoginActivity extends AppCompatActivity {

    private EditText etNIC;
    private EditText etPassword;
    private Button btnLogin;
    private LoginManager loginManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNIC = findViewById(R.id.etNIC);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> login());

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        loginManager = LoginManager.getInstance();
    }

    private void login(){
        String nic = etNIC.getText().toString();
        String password = etPassword.getText().toString();
        boolean isValid = loginManager.validateCredentials(nic, password);

        if (!isValid){
            Toast.makeText(this,"Invalid NIC or Password", Toast.LENGTH_LONG).show();
            return;
        }
        loginManager.login(
                nic,
                password,
                () -> handleLoginSuccess(),
                error -> handleLoginError(error));
    }

    private void handleLoginSuccess(){
        loginManager.setLoggedInState(true);
    }

    private void handleLoginError(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}