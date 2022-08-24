package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAAdapter extends RecyclerView.Adapter<GroupAAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelGroupAList> modelGroupAListArrayList;

    public GroupAAdapter(Context context, ArrayList<ModelGroupAList> modelGroupAListArrayList) {
        this.context = context;
        this.modelGroupAListArrayList = modelGroupAListArrayList;
    }

    @NonNull
    @Override
    public GroupAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.group_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAAdapter.MyViewHolder holder, int position) {

        ModelGroupAList modelGroupAList = modelGroupAListArrayList.get(position);

        holder.tvName.setText(modelGroupAList.fullname);
        holder.tvPhone.setText(modelGroupAList.phoneNum);

    }

    @Override
    public int getItemCount() {
        return modelGroupAListArrayList.size();
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
