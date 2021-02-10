package com.navadhan;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.navadhan.adapter.FamilyMemberAdapter;
import com.navadhan.common.Constant;
import com.navadhan.databinding.ActivityListFamilyMemberBinding;
import com.navadhan.model.FamilyMemberData;
import com.navadhan.viewmodel.ListFamilyMemberViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFamilyMemberActivity extends AppCompatActivity {
    public ActivityListFamilyMemberBinding binding;
    public ListFamilyMemberViewModel listFamilyMemberViewModel;
    public String household_id;
    public String[] family_relation_list;
    public List<String> relations;
    public List<FamilyMemberData> familyMemberDataList;
    public FamilyMemberAdapter familyMemberAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_family_member);
        listFamilyMemberViewModel = ViewModelProviders.of(this).get(ListFamilyMemberViewModel.class);
        binding.setViewModel(listFamilyMemberViewModel);
        household_id = getIntent().getStringExtra("household_id");
        listFamilyMemberViewModel.getFamilyRelationList(household_id);
        relations = new ArrayList<>();
        familyMemberDataList = new ArrayList<>();
        listFamilyMemberViewModel.getResponseRelationdDetails().observe(this,responseModel -> {
            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {

                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject = responseModel.getJsonObject();
                        JSONArray arr = jsonObject.getJSONArray("family_relation");
                        family_relation_list = new String[arr.length()];
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject1 = arr.getJSONObject(i);
                            int position = Integer.parseInt(jsonObject1.getString("family_relation_id"));
                            String relation = jsonObject1.getString("family_relation_name");
                            family_relation_list[position-1] = relation;

                        }
                        setRelationDetails(family_relation_list);

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


        listFamilyMemberViewModel.getFamilyMembersList(household_id);
        listFamilyMemberViewModel.getResponseFamilyMemberDetails().observe(this,responseModel -> {
            JSONObject jsonObject;
            try{
                if (responseModel.getJsonObject() != null) {

                    if (responseModel.getJsonObject().getString("status").equals("1")) {
                        jsonObject = responseModel.getJsonObject();
                        JSONArray arr = jsonObject.getJSONArray("family_detail");
                        for(int i=0;i<arr.length();i++){
                            JSONObject jsonObject1 = arr.getJSONObject(i);
                            FamilyMemberData familyMemberData = new FamilyMemberData(jsonObject1.getString("family_member_id"),
                                    jsonObject1.getString("family_member_name"),jsonObject1.getString("family_relation_id"),
                                    jsonObject1.getString("gender"),jsonObject1.getString("mobile_no"),
                                    jsonObject1.getString("aadhaar_no"),jsonObject1.getString("pan"),
                                    jsonObject1.getString("voter_id"),jsonObject1.getString("family_member_number"),
                                    jsonObject1.getString("date_of_birth"),jsonObject1.getString("is_aadhaar_verified"),
                                    jsonObject1.getString("is_mobile_aadhaar_linked"));
                            familyMemberDataList.add(familyMemberData);

                        }

                        familyMemberAdapter = new FamilyMemberAdapter(familyMemberDataList,this);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        binding.recyclerView.setAdapter(familyMemberAdapter);
                        int resId = R.anim.layout_animation;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
                        binding.recyclerView.setLayoutAnimation(animation);
                        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

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
    public void setRelationDetails(String[] arr){

        relations = Arrays.asList(arr);
    }
}