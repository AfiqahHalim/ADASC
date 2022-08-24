package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.List;

public class CommunityProfile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseAuth mAuth;

    private String Id;

    String fullname;


    TextView tvName, tvAge, tvHouse, tvStreet, tvGender, tvPhone, tvWork, tvFamily, tvEditName, tvEditAge, tvEditHouse, tvEditStreet, tvEditPhone, tvEditWork, tvEditFamily;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_profile);

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fulname");
        Log.e("CommunityProfile.java, ", "Fullname: " + fullname );

        tvEditName = findViewById(R.id.tvEditName);
        tvEditName.setOnClickListener(this);

        tvEditAge = findViewById(R.id.tvEditAge);
        tvEditAge.setOnClickListener(this);

        tvEditHouse = findViewById(R.id.tvEditHouse);
        tvEditHouse.setOnClickListener(this);

        tvEditStreet = findViewById(R.id.tvEditStreet);
        tvEditStreet.setOnClickListener(this);

        tvEditPhone = findViewById(R.id.tvEditPhone);
        tvEditPhone.setOnClickListener(this);

        tvEditWork = findViewById(R.id.tvEditWork);
        tvEditWork.setOnClickListener(this);

        tvEditFamily = findViewById(R.id.tvEditFamily);
        tvEditFamily.setOnClickListener(this);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvHouse = findViewById(R.id.tvHouse);
        tvStreet = findViewById(R.id.tvStreet);
        tvGender = findViewById(R.id.tvGender);
        tvPhone = findViewById(R.id.tvPhone);
        tvWork = findViewById(R.id.tvWork);
        tvFamily = findViewById(R.id.tvFamily);

        Id = getIntent().getStringExtra("Id");

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        displayProfile();

        Log.e( "onCreate: ",id );

    }

    public void displayProfile() {

        db.collection("community")
                .whereEqualTo(
                        "Id", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String fullname = document.getString("fullname");
                                tvName.setText(fullname);

                            }
                        } else {

                        }
                    }
                });
//        db.collection("community")
//                .whereEqualTo("fullname", fullname)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                        if (!queryDocumentSnapshots.isEmpty()) {
//
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//                                tvName.setText(d.getData().get("fullname").toString());
//                            }
//
//                        } else {
//                            // if the snapshot is empty we are displaying a toast message.
//
//                            //Toast.makeText(TransactionHistoryActivity.this, "You have not made any transactions yet.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
//            }
//        });

//        DocumentReference docRef = db.collection("community").whereEqualTo("fullname", fullname).get()
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("CommunityProfile", "DocumentSnapshot data: " + document.getData());
//
//                        tvName.setText(document.getData().get("fullname").toString());
//                        tvAge.setText(document.getData().get("age").toString());
//                        tvHouse.setText(document.getData().get("house").toString());
//                        tvStreet.setText(document.getData().get("street").toString());
//                        tvGender.setText(document.getData().get("gender").toString());
//                        tvPhone.setText(document.getData().get("phoneNum").toString());
//                        tvWork.setText(document.getData().get("work").toString());
//                        tvFamily.setText(document.getData().get("family").toString());
//                        //Log.d(TAG, document.getId() + " => " + document.getData());
//
//                    } else {
//                        Log.d("CommunityProfile", "No such document");
//                    }
//                } else {
//                    Log.d("CommunityProfile", "get failed with ", task.getException());
//                }
//            }
//        });
    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvEditName:
                    startActivity(new Intent(this, UpdateUserName.class));
                    break;
                case R.id.tvEditAge:
                    startActivity(new Intent(this, UpdateUserEmail.class));
                    break;
                case R.id.tvEditHouse:
                    startActivity(new Intent(this, UpdateUserIC.class));
                    break;
                case R.id.tvEditStreet:
                    startActivity(new Intent(this, UpdateUserPhone.class));
                    break;
                case R.id.tvEditPhone:
                    startActivity(new Intent(this, UpdateUserAddress.class));
                    break;
                case R.id.tvEditWork:
                    startActivity(new Intent(this, UpdateUserAddress.class));
                    break;
                case R.id.tvEditFamily:
                    startActivity(new Intent(this, UpdateUserAddress.class));
                    break;

            }


//        db.collection("users")
//                .whereEqualTo("eMail" + "", eMail)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                tvName.setText(document.getData().get("fullname").toString());
//                                tvRole.setText(document.getData().get("role").toString());
//                                tvEmail.setText(document.getData().get("eMail").toString());
//                                tvPhoneNum.setText(document.getData().get("phoneNum").toString());
//                                tvAddress.setText(document.getData().get("address").toString());
//                                tvBirthDate.setText(document.getData().get("birthdate").toString());
//                                tvICNum.setText(document.getData().get("icNum").toString());
//                                tvWork.setText(document.getData().get("work").toString());
//                                //Log.d(TAG, document.getId() + " => " + document.getData());
//
//                                Log.e("fullname", "onCreate: " +  document.getData().get("fullname").toString());
//                            }
//                        } else {
//                            //Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }



}

//    private void displayUserProfile() {
//        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//
//                    DocumentSnapshot doc = task.getResult();
//
//                    if (doc.exists()) {
//
//                        Log.d("UserProfile", "DocumentSnapshot data: " + doc.getData());
//
//                        tvName.setText(doc.getData().get("fullname").toString());
//                        tvPosition.setText(doc.getData().get("position").toString());
//                        tvEmail.setText(doc.getData().get("eMail").toString());
//                        tvPhoneNum.setText(doc.getData().get("phoneNum").toString());
//                        tvAddress.setText(doc.getData().get("address").toString());
//                        tvBirthDate.setText(doc.getData().get("birthdate").toString());
//                        tvICNum.setText(doc.getData().get("icNum").toString());
//                        tvWork.setText(doc.getData().get("work").toString());
//
//                    } else {
//                        Log.d("UserProfile", "No data");
//                    }
//                } else {
//                    Log.d("UserProfile", "Failed", task.getException());
//                }
//            }
//        });
