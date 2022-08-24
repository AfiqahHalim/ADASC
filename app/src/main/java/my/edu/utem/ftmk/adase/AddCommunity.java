package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCommunity extends AppCompatActivity implements View.OnClickListener{

    private EditText fName, phone, houseNo, age, work, familyMembers;
    private Button addCommunity;
    Map<String, Object> community = new HashMap<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String[] gender = {"Male", "Female"};
    String[] street = {"Jalan Melur", "Jalan Lavendar", "Jalan Matahari", "Jalan Orkid", "Jalan Jasmin"};

    AutoCompleteTextView etGender;
    AutoCompleteTextView etStreet;

    ArrayAdapter<String> adapterGender;
    ArrayAdapter<String> adapterStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_community);

        mAuth = FirebaseAuth.getInstance();

        fName = (EditText) findViewById(R.id.etFName);
        phone = (EditText) findViewById(R.id.etPhone);
        houseNo = (EditText) findViewById(R.id.etHouse);
        age = (EditText) findViewById(R.id.etAge);
        work = (EditText) findViewById(R.id.etWork);
        familyMembers = (EditText) findViewById(R.id.etFamMembers);

        addCommunity = (Button) findViewById(R.id.btAdd);
        addCommunity.setOnClickListener(this);

        etGender =findViewById(R.id.etGender);
        adapterGender = new ArrayAdapter<String>(this,R.layout.list_role, gender);
        etGender.setAdapter(adapterGender);
        etGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Gender : " + gender, Toast.LENGTH_SHORT);
            }
        });

        etStreet =findViewById(R.id.etStreet);
        adapterStreet = new ArrayAdapter<String>(this,R.layout.list_role, street);
        etStreet.setAdapter(adapterStreet);
        etStreet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String street = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Street : " + gender, Toast.LENGTH_SHORT);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                addNewCommunity();
                break;
        }
    }

    private void addNewCommunity() {

        final String timestamp = ""+System.currentTimeMillis();

        String fullName = fName.getText().toString().trim();
        String phoneNum = phone.getText().toString().trim();
        String house = houseNo.getText().toString().trim();
        String uStreet = etStreet.getText().toString().trim();
        String cAge = age.getText().toString().trim();
        String cGender = etGender.getText().toString().trim();
        String cWork = work.getText().toString().trim();
        String cFam = familyMembers.getText().toString().trim();

        community.put("Id", ""+timestamp);
        community.put("uid",""+mAuth.getUid());
        community.put("fullname",fullName);
        community.put("phone",phoneNum);
        community.put("house",house);
        community.put("street", uStreet);
        community.put("age",cAge);
        community.put("gender",cGender);
        community.put("work",cWork);
        community.put("family",cFam);

        db.collection("community")
                .add(community)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Authentication succeed.",
                                Toast.LENGTH_SHORT).show();
                        toUserMenu();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void toUserMenu() {
        Intent intent = new Intent(getApplicationContext(), MenuCommunity.class);
        startActivity(intent);
    }

}