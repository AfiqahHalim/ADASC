package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
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
import java.util.Locale;
import java.util.Map;

public class ReportMeetings extends AppCompatActivity implements View.OnClickListener{

    private EditText etName, etPostReport;
    private Button btDate, btStarting, btEnding, btSave;
    private DatePickerDialog datePickerDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Map<String, Object> reportMeetings = new HashMap<>();

    String date;
    int hour1, minute1, hour2, minute2;
    FirebaseFirestore db;

    String[] venue = {"Balai Raya Kahang Timur", "Dewan Kahang Timur"};

    AutoCompleteTextView acVenue;

    ArrayAdapter<String> adapterVenue;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_meetings);
        initDatePicker();

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(this);

        btDate = (Button) findViewById(R.id.btDate);
        btDate.setText(meetingDate());

        etName = findViewById(R.id.etName);
        etName.setOnClickListener(this);

        etPostReport = findViewById(R.id.etPostReport);
        etPostReport.setOnClickListener(this);

        btStarting = findViewById(R.id.btStarting);
        btEnding = findViewById(R.id.btEnding);

        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("ProfileActivity", "DocumentSnapshot data: " + document.getData());

                        etName.setText(document.getData().get("fullname").toString());
                    } else {
                        Log.d("ProfileActivity", "No such document");
                    }
                } else {
                    Log.d("ProfileActivity", "get failed with ", task.getException());
                }
            }
        });

        acVenue = findViewById(R.id.acVenue);
        adapterVenue = new ArrayAdapter<String>(this, R.layout.list_role, venue);
        acVenue.setAdapter(adapterVenue);
        acVenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String report = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Venue : " + report, Toast.LENGTH_SHORT);
            }
        });

    }

    private String meetingDate() {

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

    public void openDate(View view) {

        datePickerDialog.show();
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

                month = month + 1;
                date = makeDateString(day, month, year);

                btDate.setText(date);

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

    public void startingTime(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

                hour1 = selectedHour;
                minute1 = selectedMinute;
                btStarting.setText(String.format(Locale.getDefault(), "%02d:%02d", hour1, minute1));

                Log.e("time", "" + hour1 + minute1);
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour1, minute1, true);

        timePickerDialog.setTitle("Select The Starting Time :");
        timePickerDialog.show();
    }

    public void endingTime(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

                hour2 = selectedHour;
                minute2 = selectedMinute;
                btStarting.setText(String.format(Locale.getDefault(), "%02d:%02d", hour2, minute2));

                Log.e("time", "" + hour2 + minute2);
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour2, minute2, true);

        timePickerDialog.setTitle("Select The Ending Time :");
        timePickerDialog.show();
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

        String meetingDate = date;
        String meetingVenue = acVenue.getText().toString().trim();
        String startTime = String.format("%02d", hour1) + ":" + String.format("%02d", minute1);
        String endTime = String.format("%02d", hour2) + ":" + String.format("%02d", minute2);
        String meetingReport = etPostReport.getText().toString().trim();
        String fullName = etName.getText().toString().trim();

        Log.e("date", " " + date);

        reportMeetings.put("meetingDate",meetingDate);
        reportMeetings.put("reportVenue",meetingVenue);
        reportMeetings.put("startingTime",startTime);
        reportMeetings.put("endingTime", endTime);
        reportMeetings.put("report", meetingReport);
        reportMeetings.put("reportedBy", fullName);

        db.collection("reportForMeetings")
                .add(reportMeetings)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "New data insert succeessfully.",
                                Toast.LENGTH_SHORT).show();
                        toMeetingMenu();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void toMeetingMenu() {
        Intent intent = new Intent(getApplicationContext(), MenuMeeting.class);
        startActivity(intent);
    }

}