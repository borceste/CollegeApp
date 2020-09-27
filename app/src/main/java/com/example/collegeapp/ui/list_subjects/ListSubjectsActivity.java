package com.example.collegeapp.ui.list_subjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.collegeapp.R;
import com.example.collegeapp.ui.listfaculties.ListFacultiesFragment;

public class ListSubjectsActivity extends AppCompatActivity implements ListSubjectsAdapter.OnSubjectListener{

    private static String EXTRA_SUBJECT_NAME = "subject_name";
    private static String EXTRA_SUBJECT_DESCRIPTION = "subject_description";
    private static String EXTRA_SUBJECT_RATING = "subject_rating";

    private RecyclerView recyclerView;
    private ListSubjectsAdapter listSubjectsAdapter;
    private TextView facultyNameTextView;
    private TextView facultyDescriptionTextView;
    private TextView facultyCityTextView;

    private String facultyName;
    private String facultyDescription;
    private String facultyCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_subjects);

        initView();
        setupRecyclerView();
        setupTextFromIntent();
    }

    private void setupTextFromIntent() {
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            if(incomingIntent.hasExtra(ListFacultiesFragment.EXTRA_FACULTY_NAME)) {
                facultyName = incomingIntent.getStringExtra(ListFacultiesFragment.EXTRA_FACULTY_NAME);
                facultyNameTextView.setText(facultyName);
            }
            if(incomingIntent.hasExtra(ListFacultiesFragment.EXTRA_FACULTY_CITY)){
                facultyCity = incomingIntent.getStringExtra(ListFacultiesFragment.EXTRA_FACULTY_CITY);
                facultyCityTextView.setText(facultyCity);
            }
            if(incomingIntent.hasExtra(ListFacultiesFragment.EXTRA_FACULTY_DESCRIPTION)){
                facultyDescription = incomingIntent.getStringExtra(ListFacultiesFragment.EXTRA_FACULTY_DESCRIPTION);
                facultyDescriptionTextView.setText(facultyDescription);
            }
        }
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listSubjectsAdapter =  new ListSubjectsAdapter(this, this);
        recyclerView.setAdapter(listSubjectsAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycle_view_subject);
        facultyNameTextView = findViewById(R.id.faculty_name);
        facultyCityTextView = findViewById(R.id.faculty_city);
        facultyDescriptionTextView = findViewById(R.id.faculty_description);
    }

    @Override
    public void onSubjectClick(int position) {

    }
}