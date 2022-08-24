package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateUser extends AppCompatActivity implements View.OnClickListener{

    private EditText etUserName, etUserIc, etUserPhone, etUserEmail, etUserAddress, etUserWork;
    private Button btUpdateUser, btBirthDate;
    private DatePickerDialog datePickerDialog;
    Map<String, Object> user = new HashMap<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button button;

    String date;

    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserIc = (EditText) findViewById(R.id.etUserIc);
        etUserPhone = (EditText) findViewById(R.id.etUserPhone);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserAddress = (EditText) findViewById(R.id.etUserAddress);
        etUserWork = (EditText) findViewById(R.id.etUserWork);

        btUpdateUser = (Button) findViewById(R.id.btUpdateUser);
        btUpdateUser.setOnClickListener(this);

        btBirthDate = (Button) findViewById(R.id.btBirthDate);

        documentReference = db.collection("users").document("profile");

        btUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputData();
            }
        });
    }

    private String name;
    
    private void inputData() {
        
        name = etUserName.getText().toString().trim();
        
        updateUser();
    }

    private void loadMyInfo() {
        //load user info, and set to views
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.orderByChild("uid").equalTo(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            String name = ""+ds.child("fullname").getValue();

                            etUserName.setText(" "+name);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void updateUser() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("fullname",""+name);

        //update to db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(mAuth.getUid()).updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //updated

                        Toast.makeText(UpdateUser.this, "Profile Updated...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed to update
                        Toast.makeText(UpdateUser.this, "Failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }


}