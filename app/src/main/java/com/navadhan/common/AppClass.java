package com.navadhan.common;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppClass extends Application {
    private static final String TAG = AppClass.class.getSimpleName();
    private static AppClass instance;
    private RequestQueue mRequestQueue;
    private String searchByName;

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized AppClass getInstance() {
        AppClass appClass;
        synchronized (AppClass.class) {
            appClass = instance;
        }
        return appClass;
    }

    private RequestQueue getRequestQueue() {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        return this.mRequestQueue;
    }

    public <T> void addRequestQueue(Request<T> request, String str) {
        request.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        if (TextUtils.isEmpty(str)) {
            str = TAG;
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }


}
