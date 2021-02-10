package com.navadhan;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.navadhan.adapter.HouseholdDetailsAdapter;
import com.navadhan.common.Constant;
import com.navadhan.databinding.ActivityMainBinding;
import com.navadhan.model.HouseholdData;
import com.navadhan.viewmodel.SearchByNameViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private SearchByNameViewModel searchByNameViewModel;
    ArrayList<HouseholdData> householdDataList;
    HouseholdDetailsAdapter householdDetailsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        searchByNameViewModel = ViewModelProviders.of(this).get(SearchByNameViewModel.class);
        binding.setViewModel(searchByNameViewModel);
        binding.btnSearch.setOnClickListener(this);


        searchByNameViewModel.getErrorMessage().observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });

        searchByNameViewModel.getResponseHouseholdDetails().observe(this,responseModel->{
            try {
                if (responseModel.getJsonObject() != null) {
                    JSONObject jsonObject1;
                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject1 =responseModel.getJsonObject();
                        JSONArray arr = jsonObject1.getJSONArray("household_data");
                        householdDataList = new ArrayList<>();
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject2 = arr.getJSONObject(i);
                            HouseholdData householdData = new HouseholdData(jsonObject2.getString("household_name"),
                                    jsonObject2.getString("family_member_name"), jsonObject2.getString("mobile_no"),
                                    jsonObject2.getString("household_number"),jsonObject2.getString("household_id"));
                            householdDataList.add(householdData);
                        }
                        Log.i("list_length",String.valueOf(householdDataList.size()));
                        householdDetailsAdapter = new HouseholdDetailsAdapter(householdDataList,this);
                        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
                        binding.recyclerview.setAdapter(householdDetailsAdapter);
                        int resId = R.anim.layout_animation;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
                        binding.recyclerview.setLayoutAnimation(animation);
                        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));



                    } else {
                        Toast.makeText(this, responseModel.getJsonObject().getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } else if (responseModel.getVolleyError() != null) {
                    Toast.makeText(this, "Relative Master: " + Constant.VOLLEY_ERROR(responseModel.getVolleyError()),
                            Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });

    }
    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {
        }
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSearch:
                    hideKeyboard(v);
                    binding.textListContain.setVisibility(View.VISIBLE);
                    binding.textListContain.setText(getResources().getString(R.string.list_pattern_text));
                    searchByNameViewModel.getDetailsByName();
                    break;
                default:
                    break;
            }
    }

    public void showAlertDialog(String message) {
        new AlertDialog.Builder(this).setCancelable(false)
                .setMessage(message)
                .setTitle(getString(R.string.response))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }
}