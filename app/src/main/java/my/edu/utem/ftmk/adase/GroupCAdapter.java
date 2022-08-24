package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupCAdapter extends RecyclerView.Adapter<GroupCAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelGroupCList> modelGroupCListArrayList;

    public GroupCAdapter(Context context, ArrayList<ModelGroupCList> modelGroupCListArrayList) {
        this.context = context;
        this.modelGroupCListArrayList = modelGroupCListArrayList;
    }

    @NonNull
    @Override
    public GroupCAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.group_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupCAdapter.MyViewHolder holder, int position) {

        ModelGroupCList modelGroupCList = modelGroupCListArrayList.get(position);

        holder.tvName.setText(modelGroupCList.fullname);
        holder.tvPhone.setText(modelGroupCList.phoneNum);

    }

    @Override
    public int getItemCount() {
        return modelGroupCListArrayList.size();
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
