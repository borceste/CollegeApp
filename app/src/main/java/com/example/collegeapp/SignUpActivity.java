package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.example.college_app_sdk.classes.User;
import com.example.college_app_sdk.classes.User;
import com.example.college_app_sdk_impl.SignUpHelperImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.w3c.dom.Text;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {


    private TextInputLayout regUsernameLayout, regEmailLayout, regPasswordLayout;
    @NotEmpty
    private TextInputEditText regUsername;

    @NotEmpty
    @Email
    private TextInputEditText regEmail;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    private TextInputEditText regPassword;

    Button signUpButton, haveAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regUsernameLayout = (TextInputLayout) findViewById(R.id.sign_up_username_layout);
        regUsername = (TextInputEditText) findViewById(R.id.sign_up_username_text);

        regEmailLayout = (TextInputLayout) findViewById(R.id.sign_up_email_layout);
        regEmail = (TextInputEditText) findViewById(R.id.sign_up_email_text);

        regPasswordLayout  = (TextInputLayout) findViewById(R.id.sign_up_password_layout);
        regPassword = (TextInputEditText) findViewById(R.id.sign_up_password_text);

        signUpButton = (Button) findViewById(R.id.button_sign_up);
        haveAccount = (Button) findViewById(R.id.button_have_account);
        mAuth = FirebaseAuth.getInstance();


        final Validator validator = new Validator(this);
        validator.setValidationListener(this);


        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            // TODO user already signed in
        } else {

        }
    }

    @Override
    public void onValidationSucceeded() {
        SignUpHelperImpl signUpHelper = new SignUpHelperImpl();
        String userName = regUsernameLayout.getEditText().getText().toString();
        String email = regEmailLayout.getEditText().getText().toString();
        String password = regPasswordLayout.getEditText().getText().toString();
        final User user = signUpHelper.registerNewUser(userName, email,0,0);

        Log.i("email: ", regEmail.toString().trim());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // store additional fields
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, getString(R.string.registration_successful), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignUpActivity.this, getString(R.string.registration_failed), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //databaseReference.child("users").setValue(user);
        //Toast.makeText(this, "You have successfully logged in!", Toast.LENGTH_LONG).show();
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