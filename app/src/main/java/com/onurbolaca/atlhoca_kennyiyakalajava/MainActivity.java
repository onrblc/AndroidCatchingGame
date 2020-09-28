package com.onurbolaca.atlhoca_kennyiyakalajava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView twTime;
    TextView twScore;
    ImageView ivKenny;
    int scoreNumb;
    Handler handler;
    Runnable runnable;
   // SharedPreferences sharedPreferences;
    int setDelayMillis;
    int lastScoreNumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twTime = findViewById(R.id.twTime);
        twScore = findViewById(R.id.twScore);
        ivKenny = findViewById(R.id.imageView);
        scoreNumb = 0;
        setDelayMillis = 0;

        ivKenny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreNumb++;
                twScore.setText("Score: " + scoreNumb);
            }
        });


       // sharedPreferences();
        getDelayMilliseconds();
        putRandomlyScreen();
        countDownTimeLeft();

    }

    public void putRandomlyScreen() {

        {
            handler = new Handler();

            runnable = new Runnable() {
                @Override
                public void run() {

                    Random random = new Random();

                    int randomX = random.nextInt(900);
                    int randomY = random.nextInt(900);

                    ivKenny.setX(randomX);
                    ivKenny.setY(randomY);

                    handler.postDelayed(runnable, setDelayMillis);
                }
            };
            handler.post(runnable);
        }
    }


    public void countDownTimeLeft() {

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                twTime.setText("Time: " + l / 1000);
            }

            @Override
            public void onFinish() {

                twTime.setText("Time OFF");

                handler.removeCallbacks(runnable);

                //sharedPreferences.edit().putInt("scoreNumb",scoreNumb);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Restart?");
                builder.setMessage("Your time is Off. You wanna restart? \n " +
                        "Your score is: " + scoreNumb
                        +"\nLast Score was: " + lastScoreNumb);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        scoreNumb = 0;
                        twScore.setText("Score: " + scoreNumb);
                        countDownTimeLeft();
                        putRandomlyScreen();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        }.start();
    }


    public void getDelayMilliseconds(){

        Intent intent = getIntent();
        setDelayMillis = intent.getIntExtra("delayMillis",0);
    }


//    public void sharedPreferences(){
//
//        sharedPreferences = this.getSharedPreferences("com.onurbolaca.atlhoca_kennyiyakalajava",
//                MODE_PRIVATE);
//
//        lastScoreNumb =  sharedPreferences.getInt("scoreNumb",0);
//
//
//    }
}
