package com.navadhan;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.navadhan.databinding.ActivityDocumentOCRBinding;
import com.navadhan.model.HouseholdData;
import com.navadhan.viewmodel.DocumentOCRViewModel;

public class DocumentOCR extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    HouseholdData householdData;
    ActivityDocumentOCRBinding binding;
    DocumentOCRViewModel documentOCRViewModel;
    String selected_item_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_document_o_c_r);
        documentOCRViewModel = ViewModelProviders.of(this).get(DocumentOCRViewModel.class);
        binding.setViewModel(documentOCRViewModel);
        householdData = (HouseholdData) getIntent().getSerializableExtra("household_data");
        binding.usingcamerabtn.setOnClickListener(this::onClick);
        binding.usingimagebtn.setOnClickListener(this::onClick);
        binding.usingpdfbtn.setOnClickListener(this::onClick);
        binding.spinnerType.setAdapter(documentOCRViewModel.setlistOnSpinner());
        binding.spinnerType.setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.usingcamerabtn:
                documentOCRViewModel.startCameraActivity(selected_item_spinner,householdData);
                break;
            case R.id.usingpdfbtn:
                break;
            case R.id.usingimagebtn:
                documentOCRViewModel.startImageDocumentActivity(selected_item_spinner,householdData);
                break;

        }



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       selected_item_spinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}