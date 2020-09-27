package com.example.collegeapp.ui.myprofile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.college_app_sdk.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileViewModel extends ViewModel {

    private MutableLiveData<Integer> mYearsOfService;

    private MutableLiveData<Integer> mScore;



    public MyProfileViewModel() {
        mYearsOfService = new MutableLiveData<>();
        mScore = new MutableLiveData<>();
        //firebaseAuth = FirebaseAuth.getInstance();
        //getData();
    }

    /*private void getData() {
        if(firebaseAuth.getCurrentUser() != null){
            String userId = firebaseAuth.getCurrentUser().getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mScore.setValue(snapshot.getValue(User.class).getScore());
                    mYearsOfService.setValue(snapshot.getValue(User.class).getYearsOfService());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }*/

    public LiveData<Integer> getYearsOfService() {
        return mYearsOfService;
    }
    public LiveData<Integer> getScore(){
        return mScore;
    }
}
