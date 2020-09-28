package com.example.collegeapp.ui.review;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college_app_sdk.classes.Review;
import com.example.collegeapp.R;
import com.example.collegeapp.ui.list_subjects.ListSubjectsActivity;
import com.example.collegeapp.ui.listfaculties.ListFacultiesFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class ReviewListActivity extends AppCompatActivity implements Validator.ValidationListener {

    private ListReviewsAdapter listReviewsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String subjectId;

    private TextInputLayout titleLayout, contentLayout;

    @NotEmpty
    private TextInputEditText title;

    @NotEmpty
    private TextInputEditText content;

    Button confirm, cancel;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewlist);

        recyclerView = findViewById(R.id.recycle_view_reply);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        titleLayout = findViewById(R.id.enter_title_layout);
        title = findViewById(R.id.enter_title_text);
        contentLayout = findViewById(R.id.enter_content_layout);
        content = findViewById(R.id.enter_content_text);
        confirm = findViewById(R.id.button_add_review);
        cancel = findViewById(R.id.button_cancel_review);

        databaseReference = FirebaseDatabase.getInstance().getReference("reviews");

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);


        Intent intent = getIntent();
        intent.getExtras();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

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

    @Override
    public void onValidationSucceeded() {
        String title = titleLayout.getEditText().getText().toString().trim();
        String content = contentLayout.getEditText().getText().toString().trim();
        //String content, int numberOfDislikes, int numberOfLikes, String publisher, String subjectId, String title
        Review review = new Review(content, 0, 0, "Borce", subjectId, title);
        databaseReference.push().setValue(review);

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
