package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MeetingList extends AppCompatActivity implements View.OnClickListener{

    private Button btMainMenu;

    RecyclerView recyclerView;
    ArrayList<ModelMeetingList> modelMeetingListArrayList;
    MeetingListAdapter meetingListAdapter;
    FirebaseFirestore database;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.rvMeetingList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseFirestore.getInstance();
        modelMeetingListArrayList = new ArrayList<ModelMeetingList>();
        meetingListAdapter = new MeetingListAdapter(MeetingList.this, modelMeetingListArrayList);

        recyclerView.setAdapter(meetingListAdapter);

        EventChangeListener();

    }

    private void EventChangeListener() {

        database.collection("meeting")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                modelMeetingListArrayList.add(dc.getDocument().toObject(ModelMeetingList.class));
                            }

                            meetingListAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMainMenu:
                startActivity(new Intent(MeetingList.this, UserMenu.class));
                break;
        }
    }
}