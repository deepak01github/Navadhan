package com.navadhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.navadhan.adapter.HeatmapGridAdapter;
import com.navadhan.common.Constant;
import com.navadhan.databinding.ActivityGetHeatmapBinding;
import com.navadhan.model.AlertDetailsData;
import com.navadhan.model.HeatmapData;
import com.navadhan.viewmodel.GetHeatmapViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GetHeatmapActivity extends AppCompatActivity implements HeatmapGridAdapter.ItemClickListener{

    private ActivityGetHeatmapBinding binding;
    private GetHeatmapViewModel getHeatmapViewModel;
    private String household_no;
    private int numberOfColumns;
    HeatmapData[][] dataOfHeatmap;
    List<HeatmapData> heatmapDataList;
    HeatmapGridAdapter heatmapGridAdapter;
    AlertDetailsData alertDetailsData;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_get_heatmap);
        getHeatmapViewModel = ViewModelProviders.of(this).get(GetHeatmapViewModel.class);
        binding.setViewModel(getHeatmapViewModel);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getHeatmapViewModel.getHeatmapDetails(getIntent().getStringExtra("household_id"));
        getHeatmapViewModel.getResponseAlertDetails().observe(this,responseModel -> {
            final Dialog dialog = new Dialog(GetHeatmapActivity.this);
            dialog.setContentView(R.layout.popup_alertdialog);
            RelativeLayout custom = dialog.findViewById(R.id.custom_dialog);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
            /*String value = color;
            value = value.replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "#$1$1$2$2$3$3");
            custom.setBackgroundColor(Color.parseColor(value));*/
            //TextView detailsOfAlert = dialog.findViewById(R.id.detailsText);
        /*Button ok =  dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.cancel);*/
            Button close = dialog.findViewById(R.id.close);
            close.setOnClickListener(v -> dialog.dismiss());
            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {
                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        dialog.findViewById(R.id.alert_details).setVisibility(View.VISIBLE);
                        dialog.findViewById(R.id.newCustomer_alert).setVisibility(View.GONE);
                        jsonObject = responseModel.getJsonObject();
                        alertDetailsData = new AlertDetailsData(
                                jsonObject.getString("status"),
                                jsonObject.getString("applicant_name"),
                                jsonObject.getString("account_type"),
                                jsonObject.getString("disbursed_amount"),
                                jsonObject.getString("credit_guarantor"),
                                jsonObject.getString("disbursed_date"),
                                jsonObject.getString("account_status"),
                                jsonObject.getString("alert_description"),
                                jsonObject.getString("color_indicator")
                        );
                        String value = "#"+alertDetailsData.getColor_indicator();
                        if(value.length()<=4){
                            value = value.replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "#$1$1$2$2$3$3");

                        }
                        custom.setBackgroundColor(Color.parseColor(value));
                        TextView name = dialog.findViewById(R.id.name);
                        name.setText(alertDetailsData.getApplicant_name());
                        TextView loan_type = dialog.findViewById(R.id.loan_type);
                        loan_type.setText(alertDetailsData.getAccount_type());
                        TextView amount = dialog.findViewById(R.id.amount);
                        amount.setText(Constant.getFormatedAmount(alertDetailsData.getDisbursed_amount()));
                        TextView lender = dialog.findViewById(R.id.lender);
                        lender.setText(alertDetailsData.getCredit_guarantor());
                        TextView dod = dialog.findViewById(R.id.dod);
                        dod.setText(Constant.getFormatedDate(alertDetailsData.getDisbursed_date()));
                        TextView status = dialog.findViewById(R.id.status);
                        status.setText(alertDetailsData.getAccount_status());
                        TextView alert = dialog.findViewById(R.id.alert);
                        alert.setText(alertDetailsData.getAlert_description());
                        dialog.show();
                    }else {
                        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setMessage("New Customer");
                                alertDialogBuilder.setPositiveButton("yes",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int arg1) {
                                               dialog.dismiss();
                                            }
                                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                        alertDialog.show();*/
                        dialog.findViewById(R.id.newCustomer_alert).setVisibility(View.VISIBLE);
                        dialog.findViewById(R.id.alert_details).setVisibility(View.GONE);
                        dialog.show();
                    }

                }else if (responseModel.getVolleyError() != null) {
                    Toast.makeText(this, "Relative Master: " + Constant.VOLLEY_ERROR(responseModel.getVolleyError()),
                            Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }


        });
        getHeatmapViewModel.getResponseHeatmapDetails().observe(this,responseModel -> {
            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {

                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject = responseModel.getJsonObject();
                        JSONArray arr = jsonObject.getJSONArray("heatmap");
                        Set<Integer> rowHashSet = new LinkedHashSet<>();
                        Set<Integer> columnHashSet = new LinkedHashSet<>();
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject1 = arr.getJSONObject(i);
                            int row = Integer.parseInt(jsonObject1.getString("row"));
                            int column = Integer.parseInt(jsonObject1.getString("column"));
                            rowHashSet.add(row);
                            columnHashSet.add(column);
                            numberOfColumns = columnHashSet.size();
                        }
                        dataOfHeatmap= new HeatmapData[rowHashSet.size()][columnHashSet.size()];
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject1 = arr.getJSONObject(i);
                            HeatmapData heatmapData = new HeatmapData(jsonObject1.getString("color"),jsonObject1.getString("id"));
                            int row = Integer.parseInt(jsonObject1.getString("row"));
                            int column = Integer.parseInt(jsonObject1.getString("column"));
                            dataOfHeatmap[row-1][column-1]=heatmapData;
                        }
                        heatmapDataList = new ArrayList<HeatmapData>();
                        for (HeatmapData[] array : dataOfHeatmap) {
                            heatmapDataList.addAll(Arrays.asList(array));
                        }
                        heatmapGridAdapter = new HeatmapGridAdapter(this,heatmapDataList);
                        binding.arrayGrid.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns));
                        binding.arrayGrid.setAdapter(heatmapGridAdapter);
                        heatmapGridAdapter.setClickListener(GetHeatmapActivity.this::onItemClick);
                        //Code for Animations
                        /*int resId = R.anim.gridlayout_animations;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
                        binding.arrayGrid.setLayoutAnimation(animation);*/
                        Thread background = new Thread() {
                            public void run() {
                                try {
                                    sleep(2 * 1000);
                                    if(progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                } catch (Exception e) {
                                }
                            }
                        };
                        background.start();


                    } else {
                        Toast.makeText(this, responseModel.getJsonObject().getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } else if (responseModel.getVolleyError() != null) {
                    Toast.makeText(this, "Relative Master: " + Constant.VOLLEY_ERROR(responseModel.getVolleyError()),
                            Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }



        });


    }

    @Override
    public void onItemClick(View view, int position, HeatmapData heatmapData) {
        getHeatmapViewModel.getAlertDetails(heatmapData.getId());
    }
}