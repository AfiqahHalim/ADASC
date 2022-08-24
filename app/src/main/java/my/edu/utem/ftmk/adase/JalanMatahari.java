package my.edu.utem.ftmk.adase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class JalanMatahari extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelCommunityList> modelCommunityListArrayList;
    CommunityListAdapter communityListAdapter;
    FirebaseFirestore database;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jalan_matahari);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        recyclerView = findViewById(R.id.rvCommunityList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseFirestore.getInstance();
        modelCommunityListArrayList = new ArrayList<ModelCommunityList>();
        communityListAdapter = new CommunityListAdapter(JalanMatahari.this, modelCommunityListArrayList);

        recyclerView.setAdapter(communityListAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        database.collection("community")
                .whereEqualTo("street", "Jalan Matahari")
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
}