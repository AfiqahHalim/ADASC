package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuGroupNightGuard extends AppCompatActivity implements View.OnClickListener {

    private Button btGroupA, btGroupB, btGroupC, btGroupD, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_group_night_guard);

        btGroupA = (Button) findViewById(R.id.btGroupA);
        btGroupA.setOnClickListener(this);

        btGroupB = (Button) findViewById(R.id.btGroupB);
        btGroupB.setOnClickListener(this);

        btGroupC = (Button) findViewById(R.id.btGroupC);
        btGroupC.setOnClickListener(this);

        btGroupD = (Button) findViewById(R.id.btGroupD);
        btGroupD.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGroupA:
                startActivity(new Intent(MenuGroupNightGuard.this, GroupA.class));
                break;
            case R.id.btGroupB:
                startActivity(new Intent(MenuGroupNightGuard.this, GroupB.class));
                break;
            case R.id.btGroupC:
                startActivity(new Intent(MenuGroupNightGuard.this, GroupC.class));
                break;
            case R.id.btGroupD:
                startActivity(new Intent(MenuGroupNightGuard.this, GroupD.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(MenuGroupNightGuard.this, UserMenu.class));
                break;
        }
    }
}