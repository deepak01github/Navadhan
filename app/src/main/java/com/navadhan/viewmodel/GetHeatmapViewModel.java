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
import com.navadhan.model.AlertDetailsData;
import com.navadhan.model.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

public class GetHeatmapViewModel extends AndroidViewModel {

    private Application application;
    private RequestQueue mRequestQueue;
    public MutableLiveData<ResponseModel> responseHeatmapDetails;
    public MutableLiveData<ResponseModel> responseAlertDetails;
    private AlertDetailsData alertDetailsData;
    public GetHeatmapViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }
    public MutableLiveData<ResponseModel> getResponseHeatmapDetails() {
        if (responseHeatmapDetails == null)
            responseHeatmapDetails = new MutableLiveData<>();
        return responseHeatmapDetails;
    }
    public MutableLiveData<ResponseModel> getResponseAlertDetails() {
        if (responseAlertDetails == null)
            responseAlertDetails = new MutableLiveData<>();
        return responseAlertDetails;
    }

    public void getAlertDetails(String alert_id){
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
        JSONObject postData = new JSONObject();
        try {
            postData.put("alert_no", alert_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseModel responseModel = new ResponseModel();
      JsonObjectRequest mStringRequest = new JsonObjectRequest(Request.Method.POST, Constant.ALERT_DETAILS,postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.toString());
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    responseAlertDetails.setValue(responseModel);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);            }
        });

        mRequestQueue.add(mStringRequest);



    }

    public void getHeatmapDetails(String household_no){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("household_id",household_no);
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, Constant.HEATMAP_DETAILS,
                    jsonObject,response -> {
                JSONObject jsonObject1= null;
                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    responseHeatmapDetails.setValue(responseModel);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, error -> {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);
            });
            mRequestQueue.add(jsonObjectRequest);

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
