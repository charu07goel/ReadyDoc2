package com.example.charugoel.readydoc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class First extends AppCompatActivity {
    public ImageButton but1,but2;
    public void init(){
        but1 = (ImageButton) findViewById(R.id.button2);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(First.this , PatientLogin.class);
                startActivity(toy);
            }
        });
        but2 = (ImageButton)findViewById(R.id.button);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2 = new Intent(First.this , DoctorLogin.class);
                startActivity(toy2);
            }
        });
    }
    @Override
    public void onBackPressed(){
        //System.gc();
        //System.exit(0);
        //super.onBackPressed();
        //finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab= getSupportActionBar();
        ab.setLogo(R.drawable.logo2);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_first);
        init();

    }
}
