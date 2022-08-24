package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCommunity extends AppCompatActivity implements View.OnClickListener{

    private Button btAddCommunity, btCommunityList, btMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_community);

        btAddCommunity = (Button) findViewById(R.id.btAddCommunity);
        btAddCommunity.setOnClickListener(this);

        btCommunityList = (Button) findViewById(R.id.btCommunityList);
        btCommunityList.setOnClickListener(this);

        btMainMenu = (Button) findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddCommunity:
                startActivity(new Intent(MenuCommunity.this, AddCommunity.class));
                break;
            case R.id.btCommunityList:
                startActivity(new Intent(MenuCommunity.this, StreetList.class));
                break;
            case R.id.btMainMenu:
                startActivity(new Intent(MenuCommunity.this, UserMenu.class));
                break;
        }
    }
}