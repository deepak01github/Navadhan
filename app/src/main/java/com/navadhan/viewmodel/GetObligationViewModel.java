package com.navadhan.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.navadhan.common.Constant;
import com.navadhan.model.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

public class GetObligationViewModel extends AndroidViewModel {
    private Application application;
    private RequestQueue mRequestQueue;
    public MutableLiveData<ResponseModel> responseObligationDetails;

    public MutableLiveData<ResponseModel> getResponseObligationDetails() {
        if (responseObligationDetails == null)
            responseObligationDetails = new MutableLiveData<>();
        return responseObligationDetails;
    }

    public GetObligationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void getObligationDetails(String household_no){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("household_id",household_no);
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, Constant.OBLIGATION_DETAILS,
                    jsonObject,response -> {

                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    responseObligationDetails.setValue(responseModel);

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
