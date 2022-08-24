package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ReportNightGuardListAdapter extends RecyclerView.Adapter<ReportNightGuardListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelReportNightGuardList> modelReportNightGuardListArrayList;

    public ReportNightGuardListAdapter(Context context, ArrayList<ModelReportNightGuardList> modelReportNightGuardListArrayList) {
        this.context = context;
        this.modelReportNightGuardListArrayList = modelReportNightGuardListArrayList;
    }

    @NonNull
    @Override
    public ReportNightGuardListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.report_night_guard_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportNightGuardListAdapter.MyViewHolder holder, int position) {

        ModelReportNightGuardList modelReportNightGuardList = modelReportNightGuardListArrayList.get(position);

        holder.tvDate.setText("Date : " + (modelReportNightGuardList.dateOccur));
        holder.tvCrime.setText("Crime        :  " + (modelReportNightGuardList.reportCrime));
        holder.tvStreet.setText("Street        :  " + (modelReportNightGuardList.street));
//        holder.tvGroup.setText("Group        :  " + (modelReportNightGuardList.group));
        holder.tvNickname.setText("Nickname :  " + (modelReportNightGuardList.nickname));

    }

    @Override
    public int getItemCount() {

        return modelReportNightGuardListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvCrime, tvStreet, tvGroup, tvNickname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvCrime = itemView.findViewById(R.id.tvCrime);
            tvStreet = itemView.findViewById(R.id.tvStreet);
//            tvGroup = itemView.findViewById(R.id.tvGroup);
            tvNickname = itemView.findViewById(R.id.tvNickname);
        }
    }
}
