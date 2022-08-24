package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommitteeListAdapter extends RecyclerView.Adapter<CommitteeListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelCommitteeList> modelCommitteeListArrayList;

    public CommitteeListAdapter(Context context, ArrayList<ModelCommitteeList> modelCommitteeListArrayList) {
        this.context = context;
        this.modelCommitteeListArrayList = modelCommitteeListArrayList;
    }

    @NonNull
    @Override
    public CommitteeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.committee_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitteeListAdapter.MyViewHolder holder, int position) {

        ModelCommitteeList modelCommitteeList = modelCommitteeListArrayList.get(position);

        holder.tvFullName.setText(modelCommitteeList.fullname);
        holder.tvRole.setText(modelCommitteeList.role);

    }

    @Override
    public int getItemCount() {
        return modelCommitteeListArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullName, tvRole;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvRole = itemView.findViewById(R.id.tvRole);

        }
    }
}
