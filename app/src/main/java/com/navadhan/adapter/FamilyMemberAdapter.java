package com.navadhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navadhan.R;
import com.navadhan.model.FamilyMemberData;
import java.util.List;

public class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.ViewHolder>  {
    private List<FamilyMemberData> familyMemberDataList;
    private Context mContext;

    public FamilyMemberAdapter(List<FamilyMemberData> familyMemberDataList, Context mContext) {
        this.familyMemberDataList = familyMemberDataList;
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
        FamilyMemberData familyMemberData = familyMemberDataList.get(position);
        holder.textView.setText(familyMemberData.family_member_name+" "+familyMemberData.getFamily_relation_id());

    }

    @Override
    public int getItemCount() {
        return familyMemberDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textHouseholdDetails);
        }
    }
}
