package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView kortti1=findViewById(R.id.imageView4);
        ImageView kortti2=findViewById(R.id.imageView2);
        ImageView kortti3=findViewById(R.id.imageView3);
        ImageView kortti4=findViewById(R.id.imageView);

        Button button3 = findViewById(R.id.button3);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anime);

        Random rand = new Random();

        int randomnumber = rand.nextInt(5);

        kortti1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti1.startAnimation(animation);
                if(randomnumber == 1){
                    kortti1.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.INVISIBLE);
                    kortti3.setVisibility(View.INVISIBLE);
                    kortti4.setVisibility(View.INVISIBLE);

                }
            }
        });
        kortti2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti2.startAnimation(animation);
                if(randomnumber == 2){
                    kortti2.setImageResource(R.drawable.diamond);
                    kortti1.setVisibility(View.INVISIBLE);
                    kortti3.setVisibility(View.INVISIBLE);
                    kortti4.setVisibility(View.INVISIBLE);

                }
            }
        });
        kortti3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti3.startAnimation(animation);
                if(randomnumber == 3){
                    kortti3.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.INVISIBLE);
                    kortti1.setVisibility(View.INVISIBLE);
                    kortti4.setVisibility(View.INVISIBLE);

                }
            }
        });
        kortti4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kortti4.startAnimation(animation);
                if(randomnumber == 4){
                    kortti4.setImageResource(R.drawable.diamond);
                    kortti2.setVisibility(View.INVISIBLE);
                    kortti3.setVisibility(View.INVISIBLE);
                    kortti1.setVisibility(View.INVISIBLE);

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
}