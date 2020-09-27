package com.example.collegeapp.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.collegeapp.MainActivity;
import com.example.collegeapp.R;
import com.example.collegeapp.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class SettingsFragment extends Fragment implements Validator.ValidationListener {

    private TextInputLayout oldPasswordLayout, newPasswordLayout;

    @NotEmpty
    private TextInputEditText oldPassword;

    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    private TextInputEditText newPassword;

    private Button changePassword, cancelChange;


    private FirebaseAuth firebaseAuth;

    private FirebaseUser firebaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        oldPasswordLayout = root.findViewById(R.id.old_password_layout);
        oldPassword = root.findViewById(R.id.old_password_text);

        newPasswordLayout = root.findViewById(R.id.new_password_layout);
        newPassword = root.findViewById(R.id.new_password_text);

        changePassword = root.findViewById(R.id.button_change_password);
        cancelChange = root.findViewById(R.id.button_cancel_password);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        cancelChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        return root;
    }


    @Override
    public void onValidationSucceeded() {
        String oldPassword = oldPasswordLayout.getEditText().getText().toString();
        final String newPassword = newPasswordLayout.getEditText().getText().toString();

        // Get auth credentials from the user for re-authentication.

        AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), oldPassword);

        firebaseUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Password updated", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getContext(), "Password not updated", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Error auth failed", Toast.LENGTH_LONG).show();

                        }

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages

            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
