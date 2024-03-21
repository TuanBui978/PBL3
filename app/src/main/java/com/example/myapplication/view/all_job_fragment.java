package com.example.myapplication.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.config.MainActivityRecycleViewAdapter;
import com.example.myapplication.databinding.FragmentAllJobBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link all_job_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class all_job_fragment extends Fragment {

    private FragmentAllJobBinding binding;
    public all_job_fragment() {

    }

//    public static all_job_fragment newInstance(String param1, String param2) {
//        all_job_fragment fragment = new all_job_fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllJobBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(new MainActivityRecycleViewAdapter());
        return inflater.inflate(R.layout.fragment_all_job, container, false);
    }
}