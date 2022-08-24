package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText fName, iC, phone, email, password, address;
    private Button register, btBirthDate, btRegister,btLogin;
    private DatePickerDialog datePickerDialog;
    Map<String, Object> user = new HashMap<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String date;

    String[] roles = {"Chairman", "Secretary 1", " Secretary 2", "Treasurer", "Night Guard A", "Night Guard B", "Night Guard C", "Night Guard D"};

    AutoCompleteTextView etRole;

    ArrayAdapter<String> adapterRoles;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatePicker();

        mAuth = FirebaseAuth.getInstance();

        fName = (EditText) findViewById(R.id.etFName);
        iC = (EditText) findViewById(R.id.etIC);
        phone = (EditText) findViewById(R.id.etPhone);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        address = (EditText) findViewById(R.id.etAddress);

        register = (Button) findViewById(R.id.btRegister);
        register.setOnClickListener(this);

        btRegister = findViewById(R.id.btRegister);

        btBirthDate = (Button) findViewById(R.id.btBirthDate);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
//                startActivity(new Intent(Register.this, Login.class));
            }
        });

        etRole = findViewById(R.id.etRole);
        adapterRoles = new ArrayAdapter<String>(this,R.layout.list_role,roles);
        etRole.setAdapter(adapterRoles);
        etRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String role = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Role: " + role,Toast.LENGTH_SHORT).show();
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

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

                month = month + 1;
                date = makeDateString(day, month, year);

                btBirthDate.setText(date);

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

    public void openBirthDatePicker(View view) {

        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btRegister:
//                registerUser();
//                break;
        }
    }

    private void registerUser() {

        String fullName = fName.getText().toString().trim();
        String icNum = iC.getText().toString().trim();
        String phoneNum = phone.getText().toString().trim();
        String eMail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String uRole = etRole.getText().toString().trim();
        String uBirthDate = date;
        String uAddress = address.getText().toString().trim();

        Log.e("birthdate", " " + date);

        user.put("fullname",fullName);
        user.put("icNum",icNum);
        user.put("phoneNum",phoneNum);
        user.put("eMail",eMail);
        user.put("pass",pass);
        user.put("role",uRole);
        user.put("address",uAddress);
        user.put("birthdate",uBirthDate);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(@NonNull AuthResult authResult) {
                        // Register User add to Firebase
                        firebaseFirestore.collection("users")
                                .document(authResult.getUser().getUid())
                                .set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {
                                        Toast.makeText(Register.this, "Register Succesfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, Login.class));
//                                        Intent intent = new Intent(Register.this, Login.class);
//                                        startActivity(intent);
                                    }
                                });


                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //enter data to database
//        mAuth.createUserWithEmailAndPassword(eMail, pass)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            //Log.d(TAG, "createUserWithEmail:success");
//                            //FirebaseUser user = mAuth.getCurrentUser();
//
//                            db.collection("users")
//                                    .add(user)
//                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                                            Toast.makeText(getApplicationContext(), "Authentication succeed.",
//                                                    Toast.LENGTH_SHORT).show();
//                                            toLogin();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            //Log.w(TAG, "Error adding document", e);
//                                        }
//                                    });
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
    }

    public void toLogin(){
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

}