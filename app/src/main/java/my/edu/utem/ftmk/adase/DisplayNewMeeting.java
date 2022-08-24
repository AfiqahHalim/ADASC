package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DisplayNewMeeting extends AppCompatActivity implements View.OnClickListener {

    private Button btMainMenu;

    TextView tvDisplayDate, tvDisplayTime, tvDisplayPlace;

    String id;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_meeting);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("DisplayNewMeeting.java, ", "Email: " + id);

        tvDisplayDate = findViewById(R.id.tvDisplayDate);
        tvDisplayTime = findViewById(R.id.tvDisplayTime);
        tvDisplayPlace = findViewById(R.id.tvDisplayPlace);

        displayMeeting();

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMainMenu:
                startActivity(new Intent(this, UserMenu.class));
                break;
        }
    }

    private void displayMeeting() {

        String meetingId = db.collection("meeting").document("Ah4Su5MSzc6AXx8SudSW").getId();

        db.collection("meeting")
                .whereEqualTo("id" , meetingId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                tvDisplayDate.setText(document.getData().get("date").toString());
                                tvDisplayTime.setText(document.getData().get("time").toString());
                                tvDisplayPlace.setText(document.getData().get("place").toString());

                            }
                        } else {

                        }
                    }
                });


//        private void displayMeeting() {

//            db.collection("meeting")
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    tvDisplayDate.setText(document.getData().get("date").toString());
//                                    tvDisplayTime.setText(document.getData().get("time").toString());
////                                    tvDisplayPlace.setText(document.getData().get("place").toString());
//
//                                    Log.e("date", "onCreate: " + document.getData().get("date").toString());
//                                }
//                            } else {
//
//                            }
//                        }
//                    });

//            DocumentReference docRef = db.collection("meeting").document("SF");
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//
//                            Log.e("date", "onCreate: " + document.getData().get("date").toString());
//
//                            tvDisplayDate.setText(document.getData().get("date").toString());
//                            //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        } else {
//                            //Log.d(TAG, "No such document");
//                        }
//                    } else {
//                        //Log.d(TAG, "get failed with ", task.getException());
//                    }
//                }
//
    }
}
