package com.rumeysagulluce.yakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textTime;
    TextView textSkor;
    Handler handler;
    Runnable runnable;
    int skor;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime=findViewById(R.id.textTime);
        textSkor=findViewById(R.id.textSkor);
        skor=0;

        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);

        imageArray=new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        Sakla();
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                textTime.setText("Time: "+l/1000);

            }

            @Override
            public void onFinish() {
                textTime.setText("Oyun bitti");
                handler.removeCallbacks(runnable);
                for(ImageView image:imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("DEVAM");
                alert.setMessage("OYUNA DEVAM ETMEK İSTİYORMUSUNUZ?");

                alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intend=getIntent();
                        finish();
                        startActivity(intend);

                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Oyun Bitti", Toast.LENGTH_LONG).show();

                    }
                });
                alert.show();

            }
        }.start();
    }
    public void Arttır(View view){

        skor++;
        textSkor.setText("Skor: "+skor);

    }
    public void Sakla(){
        handler=new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);

                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,300);

            }
        };
        handler.post(runnable);


    }
}