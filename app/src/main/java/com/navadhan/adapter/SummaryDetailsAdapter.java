package com.navadhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.navadhan.R;
import com.navadhan.common.Constant;
import com.navadhan.model.SummaryData;

import java.util.List;

public class SummaryDetailsAdapter extends RecyclerView.Adapter<SummaryDetailsAdapter.ViewHolder> {

    private List<SummaryData> summaryDataList;
    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public SummaryDetailsAdapter(List<SummaryData> summaryDataList, Context mContext) {
        this.summaryDataList = summaryDataList;
        this.mContext = mContext;
    }

    public SummaryDetailsAdapter(List<SummaryData> summaryDataList, Context mContext, RecyclerView recyclerView) {
        this.summaryDataList = summaryDataList;
        this.mContext = mContext;
        this.recyclerView = recyclerView;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.summary_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SummaryData summaryData = summaryDataList.get(position);

        holder.pull_date.setText(Constant.getFormatedDate(summaryData.getReport_pulling_date()));
        holder.name.setText(summaryData.getApplicant_name());
        holder.ntc.setText(summaryData.getIs_ntc());
        holder.score.setText(summaryData.getScore_value());
        holder.total_ac.setText(summaryData.getNumber_of_account());
        holder.total_ac_ovd.setText(summaryData.getNumber_of_account_overdue());
        holder.ac_active.setText(summaryData.getNumber_of_account_active());
        holder.ac_active_ovd.setText(summaryData.getNumber_of_account_active_overdue());
        holder.alerts_ac.setText(summaryData.getNumber_of_account_with_alert());
        holder.alerts.setText(summaryData.getNumber_of_total_alert());
        holder.inquiries_24_months.setText(summaryData.getInquiries_24_months());
        holder.inquiries_3_months.setText(summaryData.getInquiries_3_months());
        holder.ac_24_months.setText(summaryData.getNew_account_24_months());
        holder.ac_3_months.setText(summaryData.getNew_account_3_months());
        holder.ac_amount_3_months.setText(Constant.getFormatedAmount(summaryData.getNew_account_amount_3_months()));
        linearLayoutManager =(LinearLayoutManager) recyclerView.getLayoutManager();

        if(position==0){
            holder.leftArrow.setVisibility(View.GONE);
        }
       int firstElementPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

        if(firstElementPosition>0&&firstElementPosition<summaryDataList.size()-1){
            holder.leftArrow.setVisibility(View.VISIBLE);
            holder.rightArrow.setVisibility(View.VISIBLE);
        }


        if(position==summaryDataList.size()-1){
            holder.rightArrow.setVisibility(View.GONE);
        }



        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstElementPosition = lManager.findFirstCompletelyVisibleItemPosition();
                if(firstElementPosition==0){
                    if(position==0){
                        holder.leftArrow.setVisibility(View.GONE);
                    }else{
                        holder.leftArrow.setVisibility(View.VISIBLE);

                    }
                }else{
                    holder.leftArrow.setVisibility(View.VISIBLE);
                }

                int lastElementPosition = lManager.findLastCompletelyVisibleItemPosition();
                if(lastElementPosition==summaryDataList.size()-1){
                    holder.rightArrow.setVisibility(View.GONE);
                }else{
                    holder.rightArrow.setVisibility(View.VISIBLE);
                }

            }
        });

     holder.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                linearLayoutManager.scrollToPositionWithOffset(pos-1,0);



            }
        });
        holder.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                linearLayoutManager.scrollToPositionWithOffset(pos+1,0);



            }
        });



    }




    @Override
    public int getItemCount() {
        return summaryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pull_date, name, ntc, score, total_ac, total_ac_ovd, ac_active, ac_active_ovd, alerts_ac, alerts, inquiries_24_months, inquiries_3_months, ac_24_months, ac_3_months, ac_amount_3_months;
        public ImageView leftArrow, rightArrow;
        View view= null;
        public ViewHolder(View itemView) {
            super(itemView);
            this.pull_date = itemView.findViewById(R.id.pull_date);
            this.name = itemView.findViewById(R.id.name);
            this.ntc = itemView.findViewById(R.id.ntc);
            this.score = itemView.findViewById(R.id.score);
            this.total_ac = itemView.findViewById(R.id.number_of_account);
            this.total_ac_ovd = itemView.findViewById(R.id.number_of_account_overdue);
            this.ac_active = itemView.findViewById(R.id.number_of_account_active);
            this.ac_active_ovd = itemView.findViewById(R.id.number_of_account_active_overdue);
            this.alerts_ac = itemView.findViewById(R.id.number_of_account_with_alert);
            this.alerts = itemView.findViewById(R.id.number_of_total_alert);
            this.inquiries_24_months = itemView.findViewById(R.id.inquiries_24_months);
            this.inquiries_3_months = itemView.findViewById(R.id.inquiries_3_months);
            this.ac_24_months = itemView.findViewById(R.id.new_account_24_months);
            this.ac_3_months = itemView.findViewById(R.id.new_account_3_months);
            this.ac_amount_3_months = itemView.findViewById(R.id.new_account_amount_3_months);
            this.leftArrow = itemView.findViewById(R.id.image_left);
            this.rightArrow = itemView.findViewById(R.id.image_right);


        }



    }
}
