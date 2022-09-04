package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportMeetingsListAdapter extends RecyclerView.Adapter<ReportMeetingsListAdapter.MyViewHolder>{

    Context context;
    ArrayList<ModelReportMeetingsList> modelReportMeetingsListArrayList;

    public ReportMeetingsListAdapter(Context context, ArrayList<ModelReportMeetingsList> modelReportMeetingsListArrayList) {
        this.context = context;
        this.modelReportMeetingsListArrayList = modelReportMeetingsListArrayList;
    }

    @NonNull
    @Override
    public ReportMeetingsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.report_meetings_list_row, parent, false);

        return new ReportMeetingsListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportMeetingsListAdapter.MyViewHolder holder, int position) {

        ModelReportMeetingsList modelReportMeetingsList = modelReportMeetingsListArrayList.get(position);

        holder.tvDate.setText("Date : " + (modelReportMeetingsList.meetingDate));
        holder.tvVenue.setText("Venue        :  " + (modelReportMeetingsList.reportVenue));
        holder.tvReportedBy.setText("Reported By      :  " + (modelReportMeetingsList.reportedBy));
        holder.tvStart.setText("Start at      :  " + (modelReportMeetingsList.startingTime));
        holder.tvEnd.setText("End at      :  " + (modelReportMeetingsList.endingTime));
        holder.tvAgendas.setText("Meeting's Agendas      :  " + (modelReportMeetingsList.report));

    }

    @Override
    public int getItemCount() {

        return modelReportMeetingsListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvVenue, tvReportedBy, tvStart, tvEnd, tvAgendas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvVenue = itemView.findViewById(R.id.tvVenue);
            tvReportedBy = itemView.findViewById(R.id.tvReportedBy);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);
            tvAgendas = itemView.findViewById(R.id.tvAgendas);
        }
    }
}
