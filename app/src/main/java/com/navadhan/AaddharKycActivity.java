package com.navadhan;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.navadhan.databinding.ActivityAaddharKycBinding;
import com.navadhan.model.HouseholdData;
import com.navadhan.viewmodel.AaddhharKycViewModel;


public class AaddharKycActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
HouseholdData householdData;
ActivityAaddharKycBinding binding;
AaddhharKycViewModel aaddhharKycViewModel;
String selected_type_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aaddhar_kyc);
        aaddhharKycViewModel = ViewModelProviders.of(this).get(AaddhharKycViewModel.class);
        binding.setViewModel(aaddhharKycViewModel);
        householdData = (HouseholdData) getIntent().getSerializableExtra("household_data");
        binding.usingcamerabtn.setOnClickListener(this::onClick);
        binding.usingimagebtn.setOnClickListener(this::onClick);
        binding.usingpdfbtn.setOnClickListener(this::onClick);
        binding.spinnerType.setAdapter(aaddhharKycViewModel.setlistOnSpinner());
        binding.spinnerType.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usingcamerabtn:
                aaddhharKycViewModel.startCameraActivity(selected_type_spinner,householdData);
                break;
            case R.id.usingpdfbtn:
                break;
            case R.id.usingimagebtn:
                aaddhharKycViewModel.startImageDocumentActivity(selected_type_spinner,householdData);
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_type_spinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}