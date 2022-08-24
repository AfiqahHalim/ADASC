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

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelCommunityList> modelCommunityListArrayList;

    public CommunityListAdapter(Context context, ArrayList<ModelCommunityList> modelCommunityListArrayList) {
        this.context = context;
        this.modelCommunityListArrayList = modelCommunityListArrayList;
    }

    @NonNull
    @Override
    public CommunityListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.community_row, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CommunityListAdapter.MyViewHolder holder, int position) {

        ModelCommunityList modelCommunityList = modelCommunityListArrayList.get(position);

        final String uid = modelCommunityList.getUid();

        holder.tvFullName.setText(modelCommunityList.fullname);
        holder.tvHouse.setText("House No : " + (modelCommunityList.house));
        holder.tvAge.setText("Age : " + (modelCommunityList.age));
        holder.tvStreet.setText("Age : " + (modelCommunityList.street));
        holder.tvPhone.setText("Age : " + (modelCommunityList.phone));
        holder.tvWork.setText("Age : " + (modelCommunityList.work));
        holder.tvGender.setText("Age : " + (modelCommunityList.gender));
        holder.tvFamily.setText("Age : " + (modelCommunityList.family));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                detailsBottomSheet(modelCommunityList);

            }
        });
    }

    private void detailsBottomSheet(ModelCommunityList modelCommunityList) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        //inflate view for bottomsheet
        View view = LayoutInflater.from(context).inflate(R.layout.activity_bs_community_profile, null);
        //set view to bottomsheet
        bottomSheetDialog.setContentView(view);

        //show dialog
        bottomSheetDialog.show();

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvAge = view.findViewById(R.id.tvAge);
        TextView tvHouse = view.findViewById(R.id.tvHouse);
        TextView tvStreet = view.findViewById(R.id.tvStreet);
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        TextView tvWork = view.findViewById(R.id.tvWork);
        TextView tvGender = view.findViewById(R.id.tvGender);
        TextView tvFamily = view.findViewById(R.id.tvFamily);

        String name = modelCommunityList.getFullname();
        String age = modelCommunityList.getAge();
        String house = modelCommunityList.getHouse();
        String street = modelCommunityList.getStreet();
        String phone = modelCommunityList.getPhone();
        String work = modelCommunityList.getWork();
        String gender = modelCommunityList.getGender();
        String family = modelCommunityList.getFamily();

        tvName.setText(name);
        tvAge.setText(age);
        tvHouse.setText(house);
        tvStreet.setText(street);
        tvPhone.setText(phone);
        tvWork.setText(work);
        tvGender.setText(gender);
        tvFamily.setText(family);

//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);
//        TextView tvEditName = view.findViewById(R.id.tvEditName);

//
//        tvName = findViewById(R.id.tvName);
//        tvAge = findViewById(R.id.tvAge);
//        tvHouse = findViewById(R.id.tvHouse);
//        tvStreet = findViewById(R.id.tvStreet);
//        tvGender = findViewById(R.id.tvGender);
//        tvPhone = findViewById(R.id.tvPhone);
//        tvWork = findViewById(R.id.tvWork);
//        tvFamily = findViewById(R.id.tvFamily);
//
//
//        tvEditAge = findViewById(R.id.tvEditAge);
//        tvEditAge.setOnClickListener(this);
//
//        tvEditHouse = findViewById(R.id.tvEditHouse);
//        tvEditHouse.setOnClickListener(this);
//
//        tvEditStreet = findViewById(R.id.tvEditStreet);
//        tvEditStreet.setOnClickListener(this);
//
//        tvEditPhone = findViewById(R.id.tvEditPhone);
//        tvEditPhone.setOnClickListener(this);
//
//        tvEditWork = findViewById(R.id.tvEditWork);
//        tvEditWork.setOnClickListener(this);
//
//        tvEditFamily = findViewById(R.id.tvEditFamily);
//        tvEditFamily.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {

        return modelCommunityListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullName, tvHouse, tvAge, tvStreet, tvPhone, tvWork, tvGender, tvFamily;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvHouse = itemView.findViewById(R.id.tvHouse);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvStreet = itemView.findViewById(R.id.tvStreet);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvWork = itemView.findViewById(R.id.tvWork);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvFamily = itemView.findViewById(R.id.tvFamily);
        }
    }
}
