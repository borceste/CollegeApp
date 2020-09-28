package com.example.collegeapp.ui.listfaculties;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college_app_sdk.classes.Faculty;
import com.example.collegeapp.R;
import com.example.collegeapp.ui.home.HomeViewModel;
import com.example.collegeapp.ui.list_subjects.ListSubjectsActivity;

public class ListFacultiesFragment extends Fragment implements ListFacultiesAdapter.OnFacultyListener{

    public static String EXTRA_FACULTY_NAME = "faculty_name";
    public static String EXTRA_FACULTY_DESCRIPTION = "faculty_description";
    public static String EXTRA_FACULTY_CITY = "faculty_city";


    private ListFacultiesAdapter listFacultiesAdapter;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listfaculties, container, false);

        recyclerView = view.findViewById(R.id.recycle_view_faculty);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        listFacultiesAdapter = new ListFacultiesAdapter(getActivity(), this);

        recyclerView.setAdapter(listFacultiesAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onFacultyClick(int position) {
        Faculty selectedFaculty = listFacultiesAdapter.getFaculty(position);
        Intent subjectIntent = new Intent(getActivity(), ListSubjectsActivity.class);
        subjectIntent.putExtra(EXTRA_FACULTY_NAME, selectedFaculty.getName());
        subjectIntent.putExtra(EXTRA_FACULTY_CITY, selectedFaculty.getCity());
        subjectIntent.putExtra(EXTRA_FACULTY_DESCRIPTION, selectedFaculty.getDescription());
        startActivity(subjectIntent);
    }
}
