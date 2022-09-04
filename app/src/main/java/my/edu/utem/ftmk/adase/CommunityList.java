package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommunityList extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    ArrayList<ModelCommunityList> modelCommunityListArrayList;
    CommunityListAdapter communityListAdapter;
    FirebaseFirestore database;

    ProgressDialog progressDialog;

    private ModelCommunityList modelCommunityList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_list);

        modelCommunityList = (ModelCommunityList) getIntent().getSerializableExtra("community");
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.rvCommunityList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseFirestore.getInstance();
        modelCommunityListArrayList = new ArrayList<ModelCommunityList>();
        communityListAdapter = new CommunityListAdapter(CommunityList.this, modelCommunityListArrayList);

        recyclerView.setAdapter(communityListAdapter);

        findViewById(R.id.delete).setOnClickListener(this);

        EventChangeListener();
    }

    private void EventChangeListener() {

        database.collection("community")
                .orderBy("fullname", Query.Direction.ASCENDING)
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

                                modelCommunityListArrayList.add(dc.getDocument().toObject(ModelCommunityList.class));
                            }

                            communityListAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }

                    }
                });
    }

    private void deleteCommunity() {

        db.collection("community").document(modelCommunityList.getUid()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CommunityList.this, "Community is successfully deleted", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(CommunityList.this, CommunityList.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete Community");
                builder.setMessage("Are your sure want to delete this profile?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCommunity();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog txt = builder.create();
                txt.show();

                break;
        }
    }

}