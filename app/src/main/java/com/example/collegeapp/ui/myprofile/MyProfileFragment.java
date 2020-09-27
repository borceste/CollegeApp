package com.example.collegeapp.ui.myprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.college_app_sdk.classes.User;
import com.example.collegeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MyProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    private TextView mYearsOfService;
    private TextView mScore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        mYearsOfService = root.findViewById(R.id.text_myprofile_yearsofservice);
        mScore = root.findViewById(R.id.text_myprofile_score);
        getData();
        return root;
    }


    private void getData() {
        if(firebaseAuth.getCurrentUser() != null){
            String userId = firebaseAuth.getCurrentUser().getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    mScore.setText("Score: " + user.getScore());
                    mYearsOfService.setText("Years of service: " + user.getYearsOfService());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}
