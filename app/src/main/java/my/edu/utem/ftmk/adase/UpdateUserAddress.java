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

public class UpdateUserAddress extends AppCompatActivity {

    Button btUpdate, btCancel;
    EditText etUpdateAddress;
    FirebaseFirestore db;
    Map<String, Object> user = new HashMap<>();

    String eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_address);

        db = FirebaseFirestore.getInstance();

        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        etUpdateAddress = findViewById(R.id.etUpdateAddress);

        displayUserAddress();

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateUserAddress();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void displayUserAddress() {
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    if (doc.exists()) {
                        Log.d("Display address", "DocumentSnapshot data: " + doc.getData());
                        etUpdateAddress.setText(doc.getData().get("address").toString());

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to get your address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Failed", "get failed with ", task.getException());
                }
            }
        });
    }

    private void updateUserAddress() {

        if (!etUpdateAddress.getText().toString().isEmpty()) {
            DocumentReference nameRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

            nameRef
                    .update("address", etUpdateAddress.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("EditAddress", "DocumentSnapshot successfully updated!");

                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            startActivity(intent);

                            Toast.makeText(UpdateUserAddress.this, "Address has been successfully updated.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateUserAddress.this, "Failed to update.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(UpdateUserAddress.this, "This field cannot be blank.", Toast.LENGTH_SHORT).show();
        }

    }
}
