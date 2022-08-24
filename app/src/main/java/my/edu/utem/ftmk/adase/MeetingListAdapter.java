package my.edu.utem.ftmk.adase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelMeetingList> modelMeetingListArrayList;
    FirebaseFirestore db ;

    public MeetingListAdapter(Context context, ArrayList<ModelMeetingList> modelMeetingListArrayList) {
        this.context = context;
        this.modelMeetingListArrayList = modelMeetingListArrayList;
    }

    @NonNull
    @Override
    public MeetingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.meeting_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListAdapter.MyViewHolder holder, int position) {

        ModelMeetingList modelMeetingList = modelMeetingListArrayList.get(position);

//        final String uid = modelMeetingList.getUid();

        holder.tvDate.setText(modelMeetingList.date);
        holder.tvTime.setText(modelMeetingList.time);
        holder.tvPlace.setText(modelMeetingList.place);
//        holder.etReport.setText(modelMeetingList.report);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                detailsBottomSheet(modelMeetingList);

            }
        });

    }

    private void detailsBottomSheet(ModelMeetingList modelMeetingList) {

        FirebaseFirestore db ;

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        //inflate view for bottomsheet
        View view = LayoutInflater.from(context).inflate(R.layout.activity_bs_post_meeting_report, null);
        //set view to bottomsheet
        bottomSheetDialog.setContentView(view);

        //show dialog
        bottomSheetDialog.show();


        Button btSave = view.findViewById(R.id.btSave);
        EditText etPostReport = view.findViewById(R.id.etPostReport);
        TextView etDate = view.findViewById(R.id.etDate);

        String PostReport = etPostReport.getText().toString();

        String date = modelMeetingList.getDate();
//        String report = modelMeetingList.getReport();

        etDate.setText(date);
//        etPostReport.setText(PostReport);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("reportMeeting",PostReport);

            }
        });

    }

    @Override
    public int getItemCount() {

        return  modelMeetingListArrayList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvTime, tvPlace;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPlace = itemView.findViewById(R.id.tvPlace);
//            etReport = itemView.findViewById(R.id.etPostReport);

        }
    }
}