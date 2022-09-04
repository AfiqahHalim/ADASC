package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserMenu extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button btPersonalDetails, btCommunity, btCommittee, btStatistic, btLogout;
    String eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        //toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        eMail = intent.getStringExtra("eMail");
        Log.e("email", "onCreate: " + eMail );

        btPersonalDetails = (Button) findViewById(R.id.btDetail);
        btPersonalDetails.setOnClickListener(this);

        btCommunity = (Button) findViewById(R.id.btCommunity);
        btCommunity.setOnClickListener(this);

        btCommittee = (Button) findViewById(R.id.btCommittee);
        btCommittee.setOnClickListener(this);

        btStatistic = (Button) findViewById(R.id.btStatistic);
        btStatistic.setOnClickListener(this);

        btLogout = (Button) findViewById(R.id.btLogout);
        btLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btDetail:
                Intent intent = new Intent(UserMenu.this, UserProfile.class);
                intent.putExtra("eMail", eMail);
                //redirect to user profile
                startActivity(intent);
                break;
            case R.id.btCommunity:
                startActivity(new Intent(UserMenu.this, MenuCommunity.class));
                break;
            case R.id.btCommittee:
                startActivity(new Intent(UserMenu.this, MenuCommittee.class));
                break;
            case R.id.btStatistic:
                startActivity(new Intent(UserMenu.this, BarChartActivity.class));
                break;
            case R.id.btLogout:
                btLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(UserMenu.this,Login.class));
                    }
                });
                break;
        }

    }

}