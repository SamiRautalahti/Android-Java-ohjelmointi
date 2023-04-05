package com.example.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private TextView textView2;

    private Button button2;

    public static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);

        textView2 = findViewById(R.id.textView2);

        button2 = findViewById(R.id.button4);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Game started");
                openMinigame();
            }

        });




        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                textView2.setText(R.string.hello);

                //kerta painallus piilottaa tekstin
                //textView2.setVisibility(View.INVISIBLE);

                //näyttää ja piilottaa TextView-elementin tervehdystekstin kun buttonia klikataan
                if (textView2.getVisibility() == View.VISIBLE){
                    textView2.setVisibility(View.INVISIBLE);
                }
                else
                    textView2.setVisibility(View.VISIBLE);

                Log.i(TAG, "Start button clicked");
            }
        });



    }

    public void openMinigame(){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}