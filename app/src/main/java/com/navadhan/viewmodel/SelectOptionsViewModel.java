package com.navadhan.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.navadhan.GetHeatmapActivity;
import com.navadhan.GetObligationActivity;
import com.navadhan.GetSummaryActivity;

public class SelectOptionsViewModel extends AndroidViewModel {
    private Application application;

    public SelectOptionsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void getHeatmap(String household_no){
        Intent i = new Intent(application.getApplicationContext(), GetHeatmapActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("household_id",household_no);
        application.startActivity(i);
    }

    public void getSummary(String household_no){
        Intent i = new Intent(application.getApplicationContext(), GetSummaryActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("household_id",household_no);
        application.startActivity(i);    }
    public void getObligation(String household_no){
        Intent i = new Intent(application.getApplicationContext(), GetObligationActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("household_id",household_no);
        application.startActivity(i);    }


}
