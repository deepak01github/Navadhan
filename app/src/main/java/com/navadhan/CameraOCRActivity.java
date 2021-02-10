package com.navadhan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.navadhan.model.HouseholdData;

public class CameraOCRActivity extends AppCompatActivity {
    public String document_type;
    public HouseholdData householdData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_o_c_r);
        document_type = getIntent().getStringExtra("document_type");
        householdData = (HouseholdData) getIntent().getSerializableExtra("household_data");
        Toast.makeText(this, document_type+" "+householdData.getFamily_member_name(), Toast.LENGTH_SHORT).show();
    }
}