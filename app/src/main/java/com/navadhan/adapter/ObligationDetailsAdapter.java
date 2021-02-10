package com.navadhan.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.navadhan.R;
import com.navadhan.common.Constant;
import com.navadhan.model.ObligationData;
import com.navadhan.model.ObligationDetailsData;

import java.util.List;

public class ObligationDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  List<ObligationData> obligationDataList;
    List<ObligationDetailsData> obligationDetailsDataList;
    Context context;
    RecyclerView recyclerView;
    final int VIEW_TYPE_OBLIGATIONDATA = 0;
    final int VIEW_TYPE_OBLIGATIONDETAILSDATA = 1;
    LinearLayoutManager linearLayoutManager;



    public ObligationDetailsAdapter(List<ObligationData> obligationDataList, List<ObligationDetailsData> obligationDetailsDataList, Context context, RecyclerView recyclerView) {
        this.obligationDataList = obligationDataList;
        this.obligationDetailsDataList = obligationDetailsDataList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());


        if(viewType == VIEW_TYPE_OBLIGATIONDATA){
            View listItem= layoutInflater.inflate(R.layout.obligation_list_item, parent, false);
            return new ObligationDataHolder(listItem);
        }

        if(viewType == VIEW_TYPE_OBLIGATIONDETAILSDATA){
            View listItem= layoutInflater.inflate(R.layout.obligation_details_list_item, parent, false);
            return new ObligationDetailsDataHolder(listItem);
        }

        return null;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ObligationDetailsDataHolder){
            linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            ObligationDetailsData obligationDetailsData = obligationDetailsDataList.get(position);
            ((ObligationDetailsDataHolder) holder).disbursed_amount_total.setText(Constant.getFormatedAmount(obligationDetailsData.getTotal_disbursed()));
            ((ObligationDetailsDataHolder) holder).current_balance_total.setText(Constant.getFormatedAmount(obligationDetailsData.getTotal_current_balance()));
            ((ObligationDetailsDataHolder) holder).installment.setText(Constant.getFormatedAmount(obligationDetailsData.getTotal_installment()));
           if(position==0){
                ((ObligationDetailsDataHolder) holder).left_arrow.setVisibility(View.GONE);
            }

            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstElementPosition = lManager.findFirstCompletelyVisibleItemPosition();
                    if(firstElementPosition==0){
                        ((ObligationDetailsDataHolder) holder).left_arrow.setVisibility(View.GONE);
                    }else{
                        ((ObligationDetailsDataHolder) holder).left_arrow.setVisibility(View.VISIBLE);
                    }

                    int lastElementPosition = lManager.findLastCompletelyVisibleItemPosition();
                    if(lastElementPosition==(obligationDataList.size()+obligationDetailsDataList.size())-1){
                        ((ObligationDetailsDataHolder) holder).right_arrow.setVisibility(View.GONE);
                    }else{
                        ((ObligationDetailsDataHolder) holder).right_arrow.setVisibility(View.VISIBLE);
                    }

                }
            });
            ((ObligationDetailsDataHolder) holder).left_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    linearLayoutManager.scrollToPositionWithOffset(pos-1,0);



                }
            });
            ((ObligationDetailsDataHolder) holder).right_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    linearLayoutManager.scrollToPositionWithOffset(pos+1,0);



                }
            });


        }

        if(holder instanceof ObligationDataHolder){
            linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int updated_position = position - obligationDetailsDataList.size();
            ObligationData obligationData = obligationDataList.get(updated_position);
            ((ObligationDataHolder) holder).credit_guarantor.setText(obligationData.getCredit_guarantor());
            ((ObligationDataHolder) holder).ownership.setText(obligationData.getOwnership());
            ((ObligationDataHolder) holder).applicant_name.setText(obligationData.getApplicant_name());
            ((ObligationDataHolder) holder).account_type.setText(obligationData.getAccount_type());
            ((ObligationDataHolder) holder).disbursed_amount.setText(Constant.getFormatedAmount(obligationData.getDisbursed_amount()));
            ((ObligationDataHolder) holder).current_balance.setText(Constant.getFormatedAmount(obligationData.getCurrent_balance()));
            ((ObligationDataHolder) holder).monthly_instalment.setText(Constant.getFormatedAmount(obligationData.getMonthly_instalment()));
            ((ObligationDataHolder) holder).in_cb_report.setText(obligationData.getIn_cb_report());
            ((ObligationDataHolder) holder).is_dpd.setText(obligationData.getIs_dpd());
            ((ObligationDataHolder) holder).days.setText(obligationData.getDays());
            ((ObligationDataHolder) holder).start_month.setText(Constant.getFormatedDate(obligationData.getStart_month()));
            ((ObligationDataHolder) holder).end_month.setText(Constant.getFormatedDate(obligationData.getEnd_month()));
            /*if(position==0){
                ((ObligationDataHolder) holder).left_arrow.setVisibility(View.GONE);
            }
            int firstElementPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            if(firstElementPosition>0&&firstElementPosition<obligationDetailsDataList.size()+obligationDataList.size()-1){
                ((ObligationDataHolder) holder).left_arrow.setVisibility(View.VISIBLE);
                ((ObligationDataHolder) holder).right_arrow.setVisibility(View.VISIBLE);
            }
            if(position==obligationDetailsDataList.size()+obligationDataList.size()-1){
                ((ObligationDataHolder) holder).right_arrow.setVisibility(View.GONE);
            }*/
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstElementPosition = lManager.findFirstCompletelyVisibleItemPosition();
                    if(firstElementPosition==0){
                        ((ObligationDataHolder) holder).left_arrow.setVisibility(View.VISIBLE);
                    }else{
                        ((ObligationDataHolder) holder).left_arrow.setVisibility(View.VISIBLE);
                    }

                    int lastElementPosition = lManager.findLastCompletelyVisibleItemPosition();
                    if(lastElementPosition==(obligationDataList.size()+obligationDetailsDataList.size())-1){
                        ((ObligationDataHolder) holder).right_arrow.setVisibility(View.GONE);
                    }else{
                        ((ObligationDataHolder) holder).right_arrow.setVisibility(View.VISIBLE);
                    }

                }
            });
            ((ObligationDataHolder) holder).left_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    linearLayoutManager.scrollToPositionWithOffset(pos-1,0);



                }
            });
            ((ObligationDataHolder) holder).right_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    linearLayoutManager.scrollToPositionWithOffset(pos+1,0);



                }
            });
        }


            }


    @Override
    public int getItemCount() {
        return (obligationDetailsDataList.size()+obligationDataList.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(position < obligationDetailsDataList.size()){
            return VIEW_TYPE_OBLIGATIONDETAILSDATA;
        }

        if(position - obligationDetailsDataList.size() < obligationDataList.size()){
            return VIEW_TYPE_OBLIGATIONDATA;
        }

        return -1;
    }

   public class ObligationDataHolder extends RecyclerView.ViewHolder {
       public TextView credit_guarantor,ownership, applicant_name, account_type, disbursed_amount, current_balance, monthly_instalment, in_cb_report,is_dpd,days, start_month, end_month;
        public TableLayout obligationDataTable, obligationDetailsDataTable;
       public ImageView left_arrow, right_arrow;

       public ObligationDataHolder(View itemView){
           super(itemView);
           this.obligationDataTable = itemView.findViewById(R.id.tableObligationData);
           this.obligationDetailsDataTable = itemView.findViewById(R.id.tableObligationDetailsData);
           this.credit_guarantor = itemView.findViewById(R.id.credit_guarantor);
           this.ownership = itemView.findViewById(R.id.ownership);
           this.applicant_name = itemView.findViewById(R.id.applicant_name);
           this.account_type = itemView.findViewById(R.id.account_type);
           this.disbursed_amount = itemView.findViewById(R.id.disbursed_amount);
           this.current_balance = itemView.findViewById(R.id.current_balance);
           this.monthly_instalment = itemView.findViewById(R.id.monthly_instalment);
           this.in_cb_report = itemView.findViewById(R.id.in_cb_report);
           this.is_dpd = itemView.findViewById(R.id.is_dpd);
           this.days = itemView.findViewById(R.id.days);
           this.start_month = itemView.findViewById(R.id.start_month);
           this.end_month = itemView.findViewById(R.id.end_month);
           this.left_arrow = itemView.findViewById(R.id.image_left);
           this.right_arrow = itemView.findViewById(R.id.image_right);
       }

   }

    public class ObligationDetailsDataHolder extends RecyclerView.ViewHolder {
       public TextView disbursed_amount_total, current_balance_total, installment;
        public TableLayout obligationDataTable, obligationDetailsDataTable;
        public ImageView left_arrow, right_arrow;


        public ObligationDetailsDataHolder(View itemView){
            super(itemView);
            this.obligationDataTable = itemView.findViewById(R.id.tableObligationData);
            this.obligationDetailsDataTable = itemView.findViewById(R.id.tableObligationDetailsData);
            this.disbursed_amount_total = itemView.findViewById(R.id.total_disbursed);
            this.current_balance_total = itemView.findViewById(R.id.total_current_balance);
            this.installment= itemView.findViewById(R.id.total_instalment);
            this.left_arrow = itemView.findViewById(R.id.image_left);
            this.right_arrow = itemView.findViewById(R.id.image_right);


        }


    }

}