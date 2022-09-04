package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DatePicker extends AppCompatActivity {

    private EditText etMeetingPlace;
    private DatePickerDialog datePickerDialog;
    private Button btDatePicker, btTimePicker, btSave;
    private FirebaseAuth mAuth;
    Map<String, Object> meeting = new HashMap<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    int hour, minute, id;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        initDatePicker();

        mAuth = FirebaseAuth.getInstance();

        etMeetingPlace = findViewById(R.id.etMeetingPlace);

        btDatePicker = findViewById(R.id.btDatePicker);
        btDatePicker.setText(meetingDate());

        btTimePicker = findViewById(R.id.btTimePicker);

        btSave = findViewById(R.id.btSave);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Meeting Notification", "Meeting Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Notification code
                NotificationCompat.Builder builder = new NotificationCompat.Builder(DatePicker.this, "Meeting Notification");
                builder.setContentTitle("Meeting Notification");
                builder.setContentText("New Meeting Was Created, Check The Meeting Details...");
                builder.setSmallIcon(R.drawable.logo);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(DatePicker.this);
                managerCompat.notify(1,builder.build());

                addMeeting();
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

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

                month = month + 1;
                date = makeDateString(day, month, year);

                btDatePicker.setText(date);

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

    public void openDatePicker(View view) {

        datePickerDialog.show();
    }

    public void openTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

                hour = selectedHour;
                minute = selectedMinute;
                btTimePicker.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

                Log.e("time", "" + hour + minute);
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Please Select A Time :");
        timePickerDialog.show();
    }

    private void addMeeting() {

        Random rand = new Random();
        int num = rand.nextInt();

        String meetingPlace = etMeetingPlace.getText().toString().trim();
        String meetingDate = date;
        String time = String.format("%02d", hour) + ":" + String.format("%02d", minute);
        String id = String.format("%02d", num);

        Log.e("date", "" + date);

        meeting.put("id", id);
        meeting.put("date",meetingDate);
        meeting.put("time",time);
        meeting.put("place", meetingPlace);

        String meetingId = db.collection("meeting").document().getId();

        db.collection("meeting")
        .add(meeting)
        .addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                Toast.makeText(getApplicationContext(), "ID: " + meetingId,
                        Toast.LENGTH_SHORT).show();

                toLogin();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Failed to add new meeting.",
                                Toast.LENGTH_SHORT).show();
                        //Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public void toLogin(){
        Intent intent = new Intent(getApplicationContext(),MenuMeeting.class);
        startActivity(intent);
    }
}



