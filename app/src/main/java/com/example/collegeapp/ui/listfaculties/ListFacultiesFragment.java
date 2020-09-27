package com.example.collegeapp.ui.listfaculties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.example.collegeapp.ui.home.HomeViewModel;

public class ListFacultiesFragment extends Fragment {

    private ListFacultiesViewModel listFacultiesViewModel;
    private ListFacultiesAdapter listFacultiesAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listfaculties, container, false);
        /*recyclerView = root.findViewById(R.id.recycle_view_faculty);
        //recyclerView = container.getRootView().findViewById(R.id.recycle_view_faculty);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);
        listFacultiesAdapter = new ListFacultiesAdapter(getActivity());
        recyclerView.setAdapter(listFacultiesAdapter);*/

        recyclerView = view.findViewById(R.id.recycle_view_faculty);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listFacultiesAdapter = new ListFacultiesAdapter(getActivity());

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
}
