package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText etEmail;
    private Button btResetPassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = (EditText) findViewById(R.id.etEmail);
        btResetPassword = (Button) findViewById(R.id.btResetPassword);
        auth = FirebaseAuth.getInstance();

        btResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {

        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Your email is required!");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Plase enter a valid email!");
            etEmail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "Please check your email to reset your password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPassword.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}