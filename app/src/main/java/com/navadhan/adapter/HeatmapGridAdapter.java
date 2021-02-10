package com.navadhan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navadhan.R;
import com.navadhan.model.HeatmapData;
import com.navadhan.viewmodel.GetHeatmapViewModel;

import java.util.List;

public class HeatmapGridAdapter extends RecyclerView.Adapter<HeatmapGridAdapter.ViewHolder> {
    private List<HeatmapData> dataList;
    private LayoutInflater mInflater;
    private Context mContext;
    private GetHeatmapViewModel getHeatmapViewModel;
    private ItemClickListener mClickListener;
    public HeatmapGridAdapter(Context context,List<HeatmapData> dataList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.heatmap_grid_cell, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String value ="#"+ dataList.get(position).getColor().toLowerCase();
        if(value.length()<=4){
            value = value.replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "#$1$1$2$2$3$3");
        }
        GradientDrawable bgShape = (GradientDrawable) holder.myTextView.getBackground().getCurrent();
        bgShape.setColor(Color.parseColor(value));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        public ViewHolder(View view) {

            super(view);
            myTextView = itemView.findViewById(R.id.textView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (mClickListener != null) mClickListener.onItemClick(v, position,dataList.get(position));
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position,HeatmapData heatmapData );
    }
}
