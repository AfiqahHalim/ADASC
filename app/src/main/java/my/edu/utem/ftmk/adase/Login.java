package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView forgotPassword;
    private EditText email, password;
    private Button login;

    private FirebaseAuth mAuth;

    String eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);

        login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        forgotPassword.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                userLogin();
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Login.this, UserMenu.class);
            intent.putExtra("eMail", eMail);
            startActivity(intent);
        }
    }

    private void userLogin() {

        eMail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (eMail.isEmpty()) {
            email.setError("Email is required !");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(eMail).matches()) {
            email.setError("Please enter a valid email !");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Password is required !");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(eMail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(Login.this, UserMenu.class);
                    intent.putExtra("eMail", eMail);
                    FirebaseUser user = mAuth.getCurrentUser();
                    //redirect to user profile
                    startActivity(intent);

                } else {
                    Toast.makeText(Login.this, "Failed to login, please insert a valid email address and password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }



    public void toRegister(View v){
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }

}