package com.example.my_application.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.my_application.MainActivity2;
import com.example.my_application.R;
import com.example.my_application.databinding.FragmentHomeBinding;

import YTJ.DataActivity;

public class HomeFragment extends Fragment {

    private Button button1;
    private TextView textView2;

    private Button button2;

    private Button button5;

    private EditText editText;

    public static final String TAG = "MainActivity";

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button1 = root.findViewById(R.id.button1);

        textView2 = root.findViewById(R.id.textView2);

        button2 = root.findViewById(R.id.button4);

        button5 = root.findViewById(R.id.button5);

        editText = root.findViewById(R.id.editText);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Game started");
                openMinigame();
            }

        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Data started");
                Intent i = new Intent(view.getContext(), DataActivity.class);
                Log.i(TAG, editText.getText().toString());
                i.putExtra(editText.getText().toString(), "this is activity for value 1");
                startActivity(i);
            }
        });




        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                textView2.setText(R.string.hello);

                //kerta painallus piilottaa tekstin
                //textView2.setVisibility(View.INVISIBLE);

                //näyttää ja piilottaa TextView-elementin tervehdystekstin kun buttonia klikataan
                if (textView2.getVisibility() == View.VISIBLE) {
                    textView2.setVisibility(View.INVISIBLE);
                } else
                    textView2.setVisibility(View.VISIBLE);

                Log.i(TAG, "Start button clicked");
            }
        });

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void openMinigame(){
        Intent intent=new Intent(getActivity(),MainActivity2.class);
        startActivity(intent);
    }
}