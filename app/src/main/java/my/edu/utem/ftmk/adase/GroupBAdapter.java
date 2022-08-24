package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupBAdapter extends RecyclerView.Adapter<GroupBAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelGroupBList> modelGroupBListArrayList;

    public GroupBAdapter(Context context, ArrayList<ModelGroupBList> modelGroupBListArrayList) {
        this.context = context;
        this.modelGroupBListArrayList = modelGroupBListArrayList;
    }

    @NonNull
    @Override
    public GroupBAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.group_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupBAdapter.MyViewHolder holder, int position) {

        ModelGroupBList modelGroupBList = modelGroupBListArrayList.get(position);

        holder.tvName.setText(modelGroupBList.fullname);
        holder.tvPhone.setText(modelGroupBList.phoneNum);

    }

    @Override
    public int getItemCount() {
        return modelGroupBListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }

}
