package com.navadhan.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.navadhan.CameraOCRActivity;
import com.navadhan.ImageToTextActivity;
import com.navadhan.model.HouseholdData;

import java.util.ArrayList;
import java.util.List;

public class AaddhharKycViewModel extends AndroidViewModel {
    public Application application;

    public AaddhharKycViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }
    public ArrayAdapter setlistOnSpinner(){
        List<String> categories = new ArrayList<String>();
        categories.add("Aaddhar Front");
        categories.add("Aaddhar Back");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication().getApplicationContext(), android.R.layout.simple_spinner_item,categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrayAdapter;
    }
    public void startImageDocumentActivity(String spinner_type, HouseholdData householdData){
        Intent i = new Intent(getApplication().getApplicationContext(), ImageToTextActivity.class);
        i.putExtra("document_type",spinner_type);
        i.putExtra("household_data",householdData);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(i);
    }
    public void startCameraActivity(String spinner_type,HouseholdData householdData){
        Intent i = new Intent(getApplication().getApplicationContext(), CameraOCRActivity.class);
        i.putExtra("document_type",spinner_type);
        i.putExtra("household_data",householdData);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(i);
    }
}
