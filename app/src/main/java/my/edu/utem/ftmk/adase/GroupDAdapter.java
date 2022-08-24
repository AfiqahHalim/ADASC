package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupDAdapter extends RecyclerView.Adapter<GroupDAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelGroupDList> modelGroupDListArrayList;

    public GroupDAdapter(Context context, ArrayList<ModelGroupDList> modelGroupDListArrayList) {
        this.context = context;
        this.modelGroupDListArrayList = modelGroupDListArrayList;
    }

    @NonNull
    @Override
    public GroupDAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.group_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupDAdapter.MyViewHolder holder, int position) {

        ModelGroupDList modelGroupDList = modelGroupDListArrayList.get(position);

        holder.tvName.setText(modelGroupDList.fullname);
        holder.tvPhone.setText(modelGroupDList.phoneNum);

    }

    @Override
    public int getItemCount() {
        return modelGroupDListArrayList.size();
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
