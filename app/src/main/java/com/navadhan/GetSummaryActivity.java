package com.navadhan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.navadhan.adapter.SummaryDetailsAdapter;
import com.navadhan.common.Constant;
import com.navadhan.databinding.ActivityGetSummaryBinding;
import com.navadhan.model.SummaryData;
import com.navadhan.viewmodel.GetSummaryViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetSummaryActivity extends AppCompatActivity {

    private ActivityGetSummaryBinding binding;
    private GetSummaryViewmodel getSummaryViewmodel;
    private List<SummaryData> summaryDataList;
    private SummaryDetailsAdapter summaryDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_get_summary);
        getSummaryViewmodel = ViewModelProviders.of(this).get(GetSummaryViewmodel.class);
        binding.setViewModel(getSummaryViewmodel);
        getSummaryViewmodel.getSummaryDetails(getIntent().getStringExtra("household_id"));
        getSummaryViewmodel.getResponseSummaryDetails().observe(this,responseModel -> {
            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {
                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject =responseModel.getJsonObject();
                        JSONArray arr = jsonObject.getJSONArray("summary");
                        summaryDataList = new ArrayList<>();
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject2 = arr.getJSONObject(i);
                            SummaryData summaryData = new SummaryData(
                                    jsonObject2.getString("report_pulling_date"),
                                    jsonObject2.getString("applicant_name"),
                                    jsonObject2.getString("is_ntc"),
                                    jsonObject2.getString("score_value"),
                                    jsonObject2.getString("number_of_account"),
                                    jsonObject2.getString("number_of_account_overdue"),
                                    jsonObject2.getString("number_of_account_active"),
                                    jsonObject2.getString("number_of_account_active_overdue"),
                                    jsonObject2.getString("number_of_account_with_alert"),
                                    jsonObject2.getString("number_of_total_alert"),
                                    jsonObject2.getString("inquiries_24_months"),
                                    jsonObject2.getString("inquiries_3_months"),
                                    jsonObject2.getString("new_account_24_months"),
                                    jsonObject2.getString("new_account_3_months"),
                                    jsonObject2.getString("new_account_amount_3_months")
                            );
                            summaryDataList.add(summaryData);

                        }
                        summaryDetailsAdapter = new SummaryDetailsAdapter(summaryDataList,this,binding.recyclerView);
                        SnapHelper snapHelper = new PagerSnapHelper();
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerView.setAdapter(summaryDetailsAdapter);
                        snapHelper.attachToRecyclerView(binding.recyclerView);

                    }else {
                        Toast.makeText(this, responseModel.getJsonObject().getString("message"), Toast.LENGTH_SHORT).show();
                    }

                }else if (responseModel.getVolleyError() != null) {
                    Toast.makeText(this, "Relative Master: " + Constant.VOLLEY_ERROR(responseModel.getVolleyError()),
                            Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        });


    }
}