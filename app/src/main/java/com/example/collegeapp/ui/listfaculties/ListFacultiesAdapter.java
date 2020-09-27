package com.example.collegeapp.ui.listfaculties;

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
import com.example.collegeapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.security.auth.callback.Callback;

public class ListFacultiesAdapter extends RecyclerView.Adapter<ListFacultiesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Faculty> faculties;
    //private Callback callback;
    //private static final String PATH = "https://collegeappfirebase.firebaseio.com/faculty";
    private DatabaseReference databaseReference;

    public ListFacultiesAdapter(Context context){
        this.context = context;
        this.faculties = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("faculty");
        Log.i("database: ",databaseReference.toString());
        //databaseReference = new FirebaseDatabase("https://collegeappfirebase.firebaseio.com/faculty");
        databaseReference.addChildEventListener(new FacultyChildEventListener());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView facultyName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facultyName = (TextView) itemView.findViewById(R.id.faculty_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Faculty faculty = faculties.get(position);
        holder.facultyName.setText(faculty.getName());
        Log.i("fax name: ", faculty.getName());

    }

    @Override
    public int getItemCount() {
        return faculties.size();
    }

    private class FacultyChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Log.i("fax: ","nesto stigna");
            Faculty facultyAdded = snapshot.getValue(Faculty.class);
            faculties.add(0, facultyAdded);
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
            Log.i("borce: ", String.valueOf(error.getCode()));
        }
    }
}