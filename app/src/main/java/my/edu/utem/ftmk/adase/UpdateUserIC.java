package my.edu.utem.ftmk.adase;

import      androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserIC extends AppCompatActivity {

    Button btUpdate, btCancel;
    EditText etUpdateIC;
    FirebaseFirestore db;
    Map<String, Object> user = new HashMap<>();

    String eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_ic);

        db = FirebaseFirestore.getInstance();

        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        etUpdateIC = findViewById(R.id.etUpdateIC);

        displayUserIC();

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateUserIC();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void displayUserIC()
    {
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    if (doc.exists())
                    {
                        Log.d("Display ic", "DocumentSnapshot data: " + doc.getData());
                        etUpdateIC.setText(doc.getData().get("icNum").toString());

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Failed to get your ic number", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Log.d("Failed", "get failed with ", task.getException());
                }
            }
        });
    }

    private void updateUserIC() {

        if (!etUpdateIC.getText().toString().isEmpty()) {
            DocumentReference nameRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

            nameRef
                    .update("icNum", etUpdateIC.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("EditIC", "DocumentSnapshot successfully updated!");

                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            startActivity(intent);

                            Toast.makeText(UpdateUserIC.this, "IC updated successfully.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateUserIC.this, "Failed to update.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(UpdateUserIC.this, "This field cannot be blank.", Toast.LENGTH_SHORT).show();
        }

    }

//        db.collection("users")
//                .document("fullname")
//                .update("fullname", user.get(etUpdateName))
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                        Toast.makeText(UpdateUserName.this, "Successfully updated your name", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(UpdateUserName.this, "Some error occured. Please try again", Toast.LENGTH_SHORT).show();
//                    }
//        });

//        user.put("updateName", etUpdateName.getText().toString());
//
//        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//        documentReference.update("fullname", user.get("updateName"))
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                        Toast.makeText(getApplicationContext(), "Your name is successfully updates", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(getApplicationContext(), "Failed to update your name", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        Intent intent = new Intent(UpdateUserName.this, UserProfile.class);
//        startActivity(intent);


}

//    public void displayUserProfile(){
//
//        db.collection("users")
//                .whereEqualTo("eMail" + "", eMail)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                etCurrentName.setText(document.getData().get("fullname").toString());
//                                //Log.d(TAG, document.getId() + " => " + document.getData());
//
//                                Log.e("fullname", "onCreate: " +  document.getData().get("fullname").toString());
//                            }
//                        } else {
//                            //Log.d(TAG, "Error getting documents: ", task.getException());
//                            Toast.makeText(getApplicationContext(), "Failed to get your name", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//
//    }
//    private void UpdateUsername(String fullname, String newName) {
//
//        Map<String,Object> userDetail = new HashMap<>();
//
//        userDetail.put("fullname",""+fullname);
//
//        db.collection("users")
//                .whereEqualTo("eMail" + "", eMail)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                etCurrentName.setText(document.getData().get("fullname").toString());
//
//                                Log.e("fullname", "onCreate: " +  document.getData().get("fullname").toString());
//                            }
//                        } else {
//                            //Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });

//        db.collection("users")
//                .whereEqualTo("eMail", eMail)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                if (task.isSuccessful()&& !task.getResult().isEmpty()) {
//
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Toast.makeText(UpdateUserName.this, eMail, Toast.LENGTH_SHORT).show();
//
//                        etCurrentName.setText(document.getData().get("fullname").toString());
//                    }
//
