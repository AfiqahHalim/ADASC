package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCommittee extends AppCompatActivity implements View.OnClickListener{

    private Button btMeeting, btNightGuard, btCommitteeMembers, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_committee);

        btMeeting = (Button) findViewById(R.id.btMeeting);
        btMeeting.setOnClickListener(this);

        btNightGuard = (Button) findViewById(R.id.btNightGuard);
        btNightGuard.setOnClickListener(this);

        btCommitteeMembers = (Button) findViewById(R.id.btCommitteeMembers);
        btCommitteeMembers.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMeeting:
                startActivity(new Intent(MenuCommittee.this, MenuMeeting.class));
                break;
            case R.id.btNightGuard:
                startActivity(new Intent(MenuCommittee.this, MenuNightGuard.class));
                break;
            case R.id.btCommitteeMembers:
                startActivity(new Intent(MenuCommittee.this, CommitteeList.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(MenuCommittee.this, UserMenu.class));
                break;
        }
    }
}