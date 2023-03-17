package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    private FloatingActionButton fab;

    private TextView Diamond;

    int clickCount = 4;

    int HighestSuccessCount;

    private static final String KEY_HS = "HighestScore";

    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        HighestSuccessCount = myPreferences.getInt(KEY_HS, clickCount);

        Diamond = findViewById(R.id.Diamond);
        Diamond.setVisibility(View.GONE);

        ImageView kortti1=findViewById(R.id.imageView4);
        ImageView kortti2=findViewById(R.id.imageView2);
        ImageView kortti3=findViewById(R.id.imageView3);
        ImageView kortti4=findViewById(R.id.imageView);

        fab = findViewById(R.id.fab);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anime);

        Random rand = new Random();

        int randomnumber = rand.nextInt(4);

        kortti1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti1.startAnimation(animation);
                if(randomnumber == 0){
                    kortti1.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.VISIBLE);
                    kortti3.setVisibility(View.VISIBLE);
                    kortti4.setVisibility(View.VISIBLE);

                    Diamond.setVisibility(View.VISIBLE);

                    adcount();

                }
                else {
                    kortti1.setImageResource(R.drawable.incorrect);
                    clickCount = clickCount-1;
                }
            }
        });
        kortti2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti2.startAnimation(animation);
                if(randomnumber == 1){
                    kortti2.setImageResource(R.drawable.diamond);
                    kortti1.setVisibility(View.VISIBLE);
                    kortti3.setVisibility(View.VISIBLE);
                    kortti4.setVisibility(View.VISIBLE);

                    Diamond.setVisibility(View.VISIBLE);

                    adcount();


                }
                else {
                    kortti2.setImageResource(R.drawable.incorrect);
                    clickCount = clickCount-1;
                }
            }
        });
        kortti3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti3.startAnimation(animation);
                if(randomnumber == 2){
                    kortti3.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.VISIBLE);
                    kortti1.setVisibility(View.VISIBLE);
                    kortti4.setVisibility(View.VISIBLE);

                    Diamond.setVisibility(View.VISIBLE);

                    adcount();


                }
                else {
                    kortti3.setImageResource(R.drawable.incorrect);
                    clickCount = clickCount-1;
                }
            }
        });
        kortti4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti4.startAnimation(animation);
                if(randomnumber == 3){
                    kortti4.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.VISIBLE);
                    kortti3.setVisibility(View.VISIBLE);
                    kortti1.setVisibility(View.VISIBLE);

                    Diamond.setVisibility(View.VISIBLE);

                    adcount();


                }
                else {
                    kortti4.setImageResource(R.drawable.incorrect);
                    clickCount = clickCount-1;
                }
            }
        });
        /*button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
        }
    public void adcount(){
        HighestSuccessCount = HighestSuccessCount + clickCount;

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt(KEY_HS, HighestSuccessCount);
        myEditor.commit();
    }
}