package com.example.collegeapp.ui.listfaculties;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListFacultiesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ListFacultiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is list of faculties fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
