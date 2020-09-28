package com.onurbolaca.atlhoca_kennyiyakalajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity2 extends AppCompatActivity {

    RadioButton rdoEasy;
    RadioButton rdoMedian;
    RadioButton rdoHard;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rdoEasy = findViewById(R.id.rdoEasy);
        rdoMedian = findViewById(R.id.rdoMedian);
        rdoHard = findViewById(R.id.rdoHard);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen();
            }
        });




    }


    public void changeScreen() {

        Intent intent = new Intent(MainActivity2.this, MainActivity.class);

       if(rdoEasy.isChecked()){

           intent.putExtra("delayMillis",500);


       }else if(rdoMedian.isChecked()){


           intent.putExtra("delayMillis",300);


       }else if(rdoHard.isChecked()){

           intent.putExtra("delayMillis",150);
       }

        startActivity(intent);

    }
}