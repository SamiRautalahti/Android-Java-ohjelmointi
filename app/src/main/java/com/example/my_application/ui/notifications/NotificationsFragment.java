package com.example.my_application.ui.notifications;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.my_application.R;
import com.example.my_application.databinding.FragmentNotificationsBinding;
import com.example.my_application.ui.notifications.NotificationsViewModel;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.Locale;

public class NotificationsFragment extends Fragment {




    private FragmentNotificationsBinding binding;

    public CountDownTimer cdt;

    public int timerTime;

    public static final String TAG = "Timer";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final MaterialButtonToggleGroup toggleBtn = root.findViewById(R.id.toggleButton);
        final TextView timerCountDown = root.findViewById(R.id.timer);
        final Button button4 = root.findViewById(R.id.button4);
        final Button button5 = root.findViewById(R.id.button5);
        final Button button6 = root.findViewById(R.id.button6);

        final NumberPicker picker = root.findViewById(R.id.numberpicker_main_picker);
        picker.setMaxValue(500);
        picker.setMinValue(0);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // Code here executes on main thread after user selects value
                timerTime = picker.getValue();
                Log.d(TAG, timerTime + "");
                timerTime = timerTime * 1000;
            }
        });


        String[] strArray = new String[501];
        for(int i = 0; i < strArray.length; i++){
            strArray[i] = i + "s";
        }
        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        picker.setDisplayedValues(strArray);


        toggleBtn.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.button4) {
                                // start timing here
                                cdt = new CountDownTimer(timerTime, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        timerCountDown.setText(""+millisUntilFinished / 1000);

                                    }

                                    public void onFinish() {
                                        timerCountDown.setText("done!");
                                    }
                                }.start();

                            } else if (checkedId == R.id.button6) {
                                //stop timing here
                                if (cdt != null) {
                                    cdt.cancel();
                                }
                            }else if (checkedId == R.id.button5) {
                                // pause timing here



                            }
                        }
                    }
                });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}