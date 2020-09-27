package com.example.collegeapp.ui.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college_app_sdk.classes.Review;
import com.example.collegeapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListReviewsAdapter extends RecyclerView.Adapter<ListReviewsAdapter.ViewHolder> {

    private Context context;
    private DatabaseReference databaseReference;
    private ArrayList<Review> reviews;
    private String subjectId;

    public ListReviewsAdapter(Context context, String subjectId) {
        this.context = context;
        this.subjectId = subjectId;
        databaseReference = FirebaseDatabase.getInstance().getReference("reviews");
        databaseReference.addChildEventListener(new ReviewChildEventListener());
        this.reviews = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_listitem, parent, false);
        return new ListReviewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.publisher.setText(review.getPublisher());
        holder.title.setText(review.getTitle());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView publisher;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            publisher = itemView.findViewById(R.id.publisher);
            title = itemView.findViewById(R.id.title);


        }
    }

    private class ReviewChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Review review = snapshot.getValue(Review.class);
            if (review.getSubjectId().equals(subjectId)) {
                reviews.add(0, review);
                notifyDataSetChanged();
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}
