package com.example.collegeapp.ui.review;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.example.collegeapp.ui.list_subjects.ListSubjectsActivity;
import com.example.collegeapp.ui.listfaculties.ListFacultiesFragment;

public class ReviewListActivity extends AppCompatActivity {

    private ListReviewsAdapter listReviewsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String subjectId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewlist);

        recyclerView = findViewById(R.id.recycle_view_reply);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        intent.getExtras();

        //TODO
        getId();

        listReviewsAdapter = new ListReviewsAdapter(this, subjectId);
        recyclerView.setAdapter(listReviewsAdapter);
    }

    private void getId() {
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            if (incomingIntent.hasExtra(ListSubjectsActivity.EXTRA_SUBJECT_ID)) {
                subjectId = String.valueOf(incomingIntent.getStringExtra(ListSubjectsActivity.EXTRA_SUBJECT_ID));

            }

        }
    }
}
