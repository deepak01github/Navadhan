package com.navadhan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.navadhan.adapter.ObligationDetailsAdapter;
import com.navadhan.common.Constant;
import com.navadhan.databinding.ActivityGetObligationBinding;
import com.navadhan.model.ObligationData;
import com.navadhan.model.ObligationDetailsData;
import com.navadhan.viewmodel.GetObligationViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetObligationActivity extends AppCompatActivity {

    private ActivityGetObligationBinding binding;
    private GetObligationViewModel getObligationViewModel;
    private List<ObligationData> obligationDataList;
    private List<ObligationDetailsData> obligationDetailsDataList;
    private ObligationDetailsAdapter obligationDetailsAdapter;
    boolean state=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_get_obligation);
        getObligationViewModel = ViewModelProviders.of(this).get(GetObligationViewModel.class);
        binding.setViewModel(getObligationViewModel);
        getObligationViewModel.getObligationDetails(getIntent().getStringExtra("household_id"));
        getObligationViewModel.getResponseObligationDetails().observe(this,responseModel -> {

            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {
                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject = responseModel.getJsonObject();
                        obligationDetailsDataList = new ArrayList<>();
                        ObligationDetailsData obligationDetailsData = new ObligationDetailsData(jsonObject.getString("total_disbursed"),
                                jsonObject.getString("total_current_balance"),jsonObject.getString("total_instalment"));
                        obligationDetailsDataList.add(obligationDetailsData);
                        obligationDataList = new ArrayList<>();
                        JSONArray arr = jsonObject.getJSONArray("obligation");
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject2 = arr.getJSONObject(i);
                                ObligationData obligationData = new ObligationData(
                                        jsonObject2.getString("credit_guarantor"),
                                        jsonObject2.getString("ownership"),
                                        jsonObject2.getString("applicant_name"),
                                        jsonObject2.getString("account_type"),
                                        jsonObject2.getString("disbursed_amount"),
                                        jsonObject2.getString("current_balance"),
                                        jsonObject2.getString("monthly_instalment"),
                                        jsonObject2.getString("in_cb_report"),
                                        jsonObject2.getString("is_dpd"),
                                        jsonObject2.getString("days"),
                                        jsonObject2.getString("start_month"),
                                        jsonObject2.getString("end_month")


                                );
                                obligationDataList.add(obligationData);
                        }
                        obligationDetailsAdapter = new ObligationDetailsAdapter(obligationDataList,obligationDetailsDataList,this,binding.recyclerView);
                        SnapHelper snapHelper = new PagerSnapHelper();
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerView.setAdapter(obligationDetailsAdapter);
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