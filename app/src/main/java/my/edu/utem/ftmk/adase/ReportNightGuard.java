package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReportNightGuard extends AppCompatActivity implements View.OnClickListener {

    private EditText etNickName;
    private Button btSave, btDateOccur;
    private DatePickerDialog datePickerDialog;
    private TextView tvName;
    Map<String, Object> reportNightGuard = new HashMap<>();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    String date;
    FirebaseFirestore db;

    String[] report = {"House Robbery", "Theft"};
//    String[] group = {"Group A", "Group B", " Group C", "Group D"};
    String[] street = {"Jalan Melur", "Jalan Lavendar", "Jalan Matahari", "Jalan Orkid", "Jalan Jasmin"};

    AutoCompleteTextView acReport, acStreet, acGroup;

    ArrayAdapter<String> adapterReport, adapterStreet, adapterGroup;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_night_guard);
        initDatePicker();

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        etNickName = (EditText) findViewById(R.id.etNickName);
        tvName = (TextView) findViewById(R.id.tvName);

        btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(this);

        btDateOccur = (Button) findViewById(R.id.btDateOccur);

//        String nickname = etNickName.toString();

        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("ProfileActivity", "DocumentSnapshot data: " + document.getData());

                        etNickName.setText(document.getData().get("fullname").toString());
                    } else {
                        Log.d("ProfileActivity", "No such document");
                    }
                } else {
                    Log.d("ProfileActivity", "get failed with ", task.getException());
                }
            }
        });

        acReport = findViewById(R.id.acReport);
        adapterReport = new ArrayAdapter<String>(this, R.layout.list_role, report);
        acReport.setAdapter(adapterReport);
        acReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String report = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Report : " + report, Toast.LENGTH_SHORT);
            }
        });

        acStreet = findViewById(R.id.acStreet);
        adapterStreet = new ArrayAdapter<String>(this, R.layout.list_role, street);
        acStreet.setAdapter(adapterStreet);
        acStreet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String street = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Street : " + street, Toast.LENGTH_SHORT);
            }
        });

//        acGroup = findViewById(R.id.acGroup);
//        adapterGroup = new ArrayAdapter<String>(this, R.layout.list_role, group);
//        acGroup.setAdapter(adapterGroup);
//        acGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String group = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "Group : " + group, Toast.LENGTH_SHORT);
//            }
//        });

    }

    private String dateReport() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {

        if(month == 1)
            return "January";
        if(month == 2)
            return "February";
        if(month == 3)
            return "March";
        if(month == 4)
            return "April";
        if(month == 5)
            return "May";
        if(month == 6)
            return "June";
        if(month == 7)
            return "July";
        if(month == 8)
            return "August";
        if(month == 9)
            return "September";
        if(month == 10)
            return "October";
        if(month == 11)
            return "November";
        if(month == 12)
            return "December";

        //default should never happpen
        return "January";
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

                month = month + 1;
                date = makeDateString(day, month, year);

                btDateOccur.setText(date);

                Log.e("date", date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDateOccur(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSave:
                addReport();
                break;
        }
    }

    private void addReport() {



        String dateOccur = date;
        String reportCrime = acReport.getText().toString().trim();
//        String group = acGroup.getText().toString().trim();
        String street = acStreet.getText().toString().trim();
        String nickname = etNickName.getText().toString().trim();

        Log.e("dateoccur", " " + date);

        reportNightGuard.put("dateOccur",dateOccur);
        reportNightGuard.put("reportCrime",reportCrime);
//        reportNightGuard.put("group",group);
        reportNightGuard.put("street",street);
        reportNightGuard.put("nickname", nickname);

        db.collection("report")
                .add(reportNightGuard)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "New data insert succeessfully.",
                                Toast.LENGTH_SHORT).show();
                        toNightGuardMenu();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void toNightGuardMenu() {
        Intent intent = new Intent(getApplicationContext(), MenuNightGuard.class);
        startActivity(intent);
    }
}