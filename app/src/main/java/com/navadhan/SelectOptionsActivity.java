package com.navadhan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.navadhan.databinding.ActivitySelectOptionsBinding;
import com.navadhan.viewmodel.SelectOptionsViewModel;


public class SelectOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySelectOptionsBinding binding;
    private SelectOptionsViewModel selectOptionsViewModel;
    private String household_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_select_options);
        selectOptionsViewModel = ViewModelProviders.of(this).get(SelectOptionsViewModel.class);
        binding.setViewModel(selectOptionsViewModel);
        household_no = getIntent().getStringExtra("household_id");
        binding.btnHeatmap.setOnClickListener(this);
        binding.btnSummary.setOnClickListener(this);
        binding.btnObligation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHeatmap:
                selectOptionsViewModel.getHeatmap(household_no);
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;
            case R.id.btnSummary:
                selectOptionsViewModel.getSummary(household_no);
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;
            case R.id.btnObligation:
                selectOptionsViewModel.getObligation(household_no);
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;
            default:
                break;
        }

    }
}