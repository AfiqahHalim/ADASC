package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
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

public class UserProfile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    String eMail;

    Button btBack;

    TextView tvName, tvRole, tvEmail, tvPhoneNum, tvAddress, tvBirthDate, tvICNum, tvEditName, tvEditEmail, tvEditIC, tvEditPhoneNum, tvEditAddress;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        eMail = intent.getStringExtra("eMail");
        Log.e("UserProfile.java, ", "Email: " + eMail );


        tvEditName = findViewById(R.id.tvEditName);
        tvEditName.setOnClickListener(this);

        tvEditEmail = findViewById(R.id.tvEditEmail);
        tvEditEmail.setOnClickListener(this);

        tvEditIC = findViewById(R.id.tvEditICNum);
        tvEditIC.setOnClickListener(this);

        tvEditPhoneNum = findViewById(R.id.tvEditPhoneNum);
        tvEditPhoneNum.setOnClickListener(this);

        tvEditAddress = findViewById(R.id.tvEditAddress);
        tvEditAddress.setOnClickListener(this);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvRole = findViewById(R.id.tvRole);
        tvAddress = findViewById(R.id.tvAddress);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvICNum = findViewById(R.id.tvICNum);

        btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(this);

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        displayProfile();

        Log.e( "onCreate: ",id );

    }

    public void displayProfile() {

        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("ProfileActivity", "DocumentSnapshot data: " + document.getData());

                                tvName.setText(document.getData().get("fullname").toString());
                                tvRole.setText(document.getData().get("role").toString());
                                tvEmail.setText(document.getData().get("eMail").toString());
                                tvPhoneNum.setText(document.getData().get("phoneNum").toString());
                                tvAddress.setText(document.getData().get("address").toString());
                                tvBirthDate.setText(document.getData().get("birthdate").toString());
                                tvICNum.setText(document.getData().get("icNum").toString());
                                //Log.d(TAG, document.getId() + " => " + document.getData());

                    } else {
                        Log.d("ProfileActivity", "No such document");
                    }
                } else {
                    Log.d("ProfileActivity", "get failed with ", task.getException());
                }
            }
        });

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEditName:
                startActivity(new Intent(this, UpdateUserName.class));
                break;
            case R.id.tvEditEmail:
                startActivity(new Intent(this, UpdateUserEmail.class));
                break;
            case R.id.tvEditICNum:
                startActivity(new Intent(this, UpdateUserIC.class));
                break;
            case R.id.tvEditPhoneNum:
                startActivity(new Intent(this, UpdateUserPhone.class));
                break;
            case R.id.tvEditAddress:
                startActivity(new Intent(this, UpdateUserAddress.class));
                break;
            case R.id.btBack:
                startActivity(new Intent(this, UserMenu.class));
                break;

        }
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
