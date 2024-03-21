package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentLoginBinding;

public class login extends Fragment {

    private FragmentLoginBinding loginFragmentBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent a = new Intent(this.getContext(), MainActivity.class);
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false);

        loginFragmentBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(a);
            }
        });

        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginFragmentBinding = null;
    }
}