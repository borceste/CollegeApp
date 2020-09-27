package com.example.collegeapp.ui.list_subjects;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college_app_sdk.classes.Faculty;
import com.example.college_app_sdk.classes.Subject;
import com.example.collegeapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListSubjectsAdapter extends RecyclerView.Adapter<ListSubjectsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Subject> subjects;
    private DatabaseReference databaseReference;
    private OnSubjectListener onSubjectListener;
    private static final String TAG = "ListSubjectsAdapter";

    public ListSubjectsAdapter(Context mContext, OnSubjectListener onSubjectListener) {
        this.mContext = mContext;
        this.onSubjectListener = onSubjectListener;
        this.subjects = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("subjects");
        Log.i(TAG, "databaseReference: " + databaseReference);
        databaseReference.addChildEventListener(new SubjectChildEventListener());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_listitem, parent, false);
        return new ViewHolder(view, onSubjectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.subjectName.setText(subject.getName());
        Log.i(TAG, "onBindViewHolder: subject name = " + subject.getName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView subjectName;
        private OnSubjectListener onSubjectListener;

        public ViewHolder(@NonNull View itemView, OnSubjectListener onSubjectListener) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_name_text_view);
            this.onSubjectListener = onSubjectListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSubjectListener.onSubjectClick(getAdapterPosition());
        }
    }

    public interface OnSubjectListener{
        void onSubjectClick(int position);
    }

    Subject getSubject(int position){
        return subjects.get(position);
    }

    private class SubjectChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Log.i(TAG, "onChildAdded: subject added " + previousChildName);
            Subject upcomingSubject = snapshot.getValue(Subject.class);
            subjects.add(upcomingSubject);
            notifyDataSetChanged();
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
            Log.i(TAG, "onCancelled: " + String.valueOf(error.getCode()));
        }
    }
}
