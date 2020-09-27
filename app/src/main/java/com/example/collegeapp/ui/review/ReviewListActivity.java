package com.example.collegeapp.ui.review;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;

public class ReviewListActivity extends AppCompatActivity {

    private ListReviewsAdapter listReviewsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


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
        String subjectId = "";

        listReviewsAdapter = new ListReviewsAdapter(this, subjectId);
        recyclerView.setAdapter(listReviewsAdapter);
    }
}
