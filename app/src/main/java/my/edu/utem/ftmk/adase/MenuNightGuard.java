package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuNightGuard extends AppCompatActivity implements View.OnClickListener {

    private Button btGroup, btNewReport, btReport, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_night_guard);

        btGroup = (Button) findViewById(R.id.btGroup);
        btGroup.setOnClickListener(this);

        btNewReport = (Button) findViewById(R.id.btNewReport);
        btNewReport.setOnClickListener(this);

        btReport = (Button) findViewById(R.id.btReport);
        btReport.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGroup:
                startActivity(new Intent(MenuNightGuard.this, MenuGroupNightGuard.class));
                break;
            case R.id.btNewReport:
                startActivity(new Intent(MenuNightGuard.this, ReportNightGuard.class));
                break;
            case R.id.btReport:
                startActivity(new Intent(MenuNightGuard.this, ReportNightGuardList.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(MenuNightGuard.this, UserMenu.class));
                break;
        }
    }
}