package com.navadhan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navadhan.ListFamilyMemberActivity;
import com.navadhan.R;
import com.navadhan.model.HouseholdData;

import java.util.List;

public class HouseholdDetailsAdapter extends RecyclerView.Adapter<HouseholdDetailsAdapter.ViewHolder> {
    private List<HouseholdData> householdDataList;
    private Context mContext;

    public HouseholdDetailsAdapter(List<HouseholdData> householdDataList, Context mContext) {
        this.householdDataList = householdDataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.household_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HouseholdData householdData = householdDataList.get(position);
        holder.textView.setText(householdData.household_name+" - "+householdData.family_member_name+" - "+
                householdData.mobile_no+" - "+householdData.household_number);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for heatmap, obligation and summry
                //Intent i = new Intent(mContext, SelectOptionsActivity.class);
                // i.putExtra("household_id",householdData.getHousehold_id());
                Intent i =new Intent(mContext, ListFamilyMemberActivity.class);
                i.putExtra("household_id", householdData.getHousehold_id());
                mContext.startActivity(i);
                ((Activity)  mContext).overridePendingTransition(R.anim.slide_up,R.anim.slide_down);

            }
        });

    }

    @Override
    public int getItemCount() {
        return householdDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textHouseholdDetails);
        }
        }
}
