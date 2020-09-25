package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private FirebaseAuth mAuth;

    private TextInputLayout regEmailLayout, regPasswordLayout;

    @NotEmpty
    @Email
    private TextInputEditText regEmail;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    private TextInputEditText regPassword;

    private Button loginButton, createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regEmailLayout = (TextInputLayout) findViewById(R.id.log_in_email_layout);
        regEmail = (TextInputEditText) findViewById(R.id.log_in_email_text);

        regPasswordLayout = (TextInputLayout) findViewById(R.id.log_in_password_layout);
        regPassword = (TextInputEditText) findViewById(R.id.log_in_password_text);

        loginButton = (Button) findViewById(R.id.button_log_in);
        createAccount = (Button) findViewById(R.id.button_create_account);

        mAuth = FirebaseAuth.getInstance();

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onValidationSucceeded() {
        String email = regEmailLayout.getEditText().getText().toString().trim();
        String password = regPasswordLayout.getEditText().getText().toString().trim();

        Log.i("email: ", email);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // GO TO HOME ACTIVITY
                    // CLEAR ACTIVITY STACK
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages

            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}