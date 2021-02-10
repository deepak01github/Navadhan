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
import com.navadhan.model.HouseholdData;
import com.navadhan.model.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SearchByNameViewModel extends AndroidViewModel {

    public String search_by_name="";
    private static final String TAG = SearchByNameViewModel.class.getSimpleName();
    private Application application;
    private MutableLiveData<String> errorMutableLiveData;
    public MutableLiveData<ResponseModel> responseHouseholdDetails;
    private RequestQueue mRequestQueue;
    List<HouseholdData> householdDataList;
    public SearchByNameViewModel(@NonNull Application application) {
        super(application);
        this.application = application;

    }

    public MutableLiveData<String> getErrorMessage() {
        if (errorMutableLiveData == null)
            errorMutableLiveData = new MutableLiveData<>();
        return errorMutableLiveData;
    }
    public MutableLiveData<ResponseModel> getResponseHouseholdDetails() {
        if (responseHouseholdDetails == null)
            responseHouseholdDetails = new MutableLiveData<>();
        return responseHouseholdDetails;
    }

    public void getDetailsByName(){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("search_string",search_by_name);
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST,Constant.SEARCH_STRING,
                    jsonObject,response -> {
                JSONObject jsonObject1= null;
                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    responseHouseholdDetails.setValue(responseModel);
                    /*jsonObject1 = new JSONObject(response.toString());
                    JSONArray arr = jsonObject1.getJSONArray("household_data");
                    householdDataList = new ArrayList<>();
                    for(int i=0;i<arr.length();i++){
                        JSONObject jsonObject2 = arr.getJSONObject(i);
                        HouseholdData householdData = new HouseholdData(jsonObject2.getString("household_name"),
                                jsonObject2.getString("family_member_name"), jsonObject2.getString("mobile_no"),
                                jsonObject2.getString("household_number"));
                        householdDataList.add(householdData);
                    }
                    Log.i("list_length",String.valueOf(householdDataList.size()));
*/
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, error -> {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);
            });
            mRequestQueue.add(jsonObjectRequest);
            //AppClass.getInstance().addRequestQueue(jsonObjectRequest, TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
