package com.navadhan.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.navadhan.common.Constant;
import com.navadhan.model.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ListFamilyMemberViewModel extends AndroidViewModel {

    public Application application;
    private RequestQueue mRequestQueue;
    public MutableLiveData<ResponseModel> responseRelationDetails;
    public MutableLiveData<ResponseModel> responseFamilyMemberDetails;

    public ListFamilyMemberViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public MutableLiveData<ResponseModel> getResponseRelationdDetails() {
        if (responseRelationDetails == null)
            responseRelationDetails = new MutableLiveData<>();
        return responseRelationDetails;
    }
    public MutableLiveData<ResponseModel> getResponseFamilyMemberDetails() {
        if (responseFamilyMemberDetails == null)
            responseFamilyMemberDetails = new MutableLiveData<>();
        return responseFamilyMemberDetails;
    }


    public void getFamilyRelationList(String household_id){
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
        JSONObject postData = new JSONObject();
        try {
            postData.put("household_id", household_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseModel responseModel = new ResponseModel();
        JsonObjectRequest mStringRequest = new JsonObjectRequest(Request.Method.POST, Constant.FAMILY_RELATIONS_LIST,postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseModel.setJsonObject(response);
                responseModel.setVolleyError(null);
                responseRelationDetails.setValue(responseModel);
              }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);            }
        });

        mRequestQueue.add(mStringRequest);


    }
    public void getFamilyMembersList(String household_id){
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
        JSONObject postData = new JSONObject();
        try {
            postData.put("household_id", household_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseModel responseModel = new ResponseModel();
        JsonObjectRequest mStringRequest = new JsonObjectRequest(Request.Method.POST, Constant.FAMILY_MEMBERS_LIST,postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseModel.setJsonObject(response);
                responseModel.setVolleyError(null);
                responseFamilyMemberDetails.setValue(responseModel);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);            }
        });

        mRequestQueue.add(mStringRequest);


    }
}
