package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMeeting extends AppCompatActivity implements View.OnClickListener{

    private Button btNewMeeting, btMeetingHistory, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_meeting);

        btNewMeeting = (Button) findViewById(R.id.btNewMeeting);
        btNewMeeting.setOnClickListener(this);

        btMeetingHistory = (Button) findViewById(R.id.btMeetingHistory);
        btMeetingHistory.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNewMeeting:
                startActivity(new Intent(MenuMeeting.this, DatePicker.class));
                break;
            case R.id.btMeetingHistory:
                startActivity(new Intent(MenuMeeting.this, MeetingList.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(MenuMeeting.this, UserMenu.class));
                break;
        }
    }
}