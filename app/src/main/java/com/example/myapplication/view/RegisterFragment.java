package com.example.myapplication.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRegisterBinding;

import java.util.Calendar;


public class register extends Fragment {
    FragmentRegisterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(binding.getRoot().getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        binding.selectDate.setText(i2 + "-" + i1 + "-" + i);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource( binding.getRoot().getContext(), R.array.gender_string, R.layout.list_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.selectGender.setAdapter(genderAdapter);
//        binding.selectGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                binding.selectGender.set
//            }
//        });
        return binding.getRoot();
    }
}