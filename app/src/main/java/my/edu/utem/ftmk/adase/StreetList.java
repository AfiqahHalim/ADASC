package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StreetList extends AppCompatActivity implements View.OnClickListener {

    private Button btJalanJasmin, btJalanLavendar, btJalanMatahari, btJalanMelur,btJalanOrkid, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_list);

        btJalanJasmin = (Button) findViewById(R.id.btJalanJasmin);
        btJalanJasmin.setOnClickListener(this);

        btJalanLavendar = (Button) findViewById(R.id.btJalanLavendar);
        btJalanLavendar.setOnClickListener(this);

        btJalanMatahari = (Button) findViewById(R.id.btJalanMatahari);
        btJalanMatahari.setOnClickListener(this);

        btJalanMelur = (Button) findViewById(R.id.btJalanMelur);
        btJalanMelur.setOnClickListener(this);

        btJalanOrkid = (Button) findViewById(R.id.btJalanOrkid);
        btJalanOrkid.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btJalanJasmin:
                startActivity(new Intent(StreetList.this, JalanJasmin.class));
                break;
            case R.id.btJalanLavendar:
                startActivity(new Intent(StreetList.this, JalanLavendar.class));
                break;
            case R.id.btJalanMatahari:
                startActivity(new Intent(StreetList.this, JalanMatahari.class));
                break;
            case R.id.btJalanMelur:
                startActivity(new Intent(StreetList.this, JalanMelur.class));
                break;
            case R.id.btJalanOrkid:
                startActivity(new Intent(StreetList.this, JalanOrkid.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(StreetList.this, UserMenu.class));
                break;
        }
    }
}