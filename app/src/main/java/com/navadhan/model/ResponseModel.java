package com.navadhan.model;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class ResponseModel {

    private JSONObject jsonObject;
    private VolleyError volleyError;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public VolleyError getVolleyError() {
        return volleyError;
    }

    public void setVolleyError(VolleyError volleyError) {
        this.volleyError = volleyError;
    }
}
