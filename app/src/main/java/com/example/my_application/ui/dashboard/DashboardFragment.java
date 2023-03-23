package com.example.my_application.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.my_application.R;
import com.example.my_application.databinding.FragmentDashboardBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    LocationManager locationManager;
    LocationListener locationListener;

    TextInputLayout latitude;
    TextInputEditText latitudeField;
    TextInputLayout longitude;
    TextInputEditText longitudeField;

    TextInputEditText addressField;
    TextInputLayout address;

    Button locationButton;

    Location lastLocation;



    private String getAddress(Location location) {
        String currentAddress = "";
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            Address address = addresses.get(0);
            currentAddress = address.getAddressLine(0);
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return currentAddress;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //LatitudeField = binding.textFieldLatitudeField;
        latitudeField = root.findViewById(R.id.textFieldLatitudeField);
        //LongitudeField = binding.textFieldLongitudeField;
        longitudeField = root.findViewById(R.id.textFieldLongitudeField);
        addressField = root.findViewById(R.id.textFieldAddress);
        locationButton = root.findViewById(R.id.locationButton);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:" + lastLocation.getLatitude() + "," + lastLocation.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        });

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latValue = location.getLatitude();
                String latConv = String.valueOf(latValue);
                double lonValue = location.getLongitude();
                String lonConv = String.valueOf(lonValue);
                latitudeField.setText(latConv);
                longitudeField.setText(lonConv);
                addressField.setText(getAddress(location));
                lastLocation = location;


            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return root;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            latitudeField.setText(Double.toString(lastLocation.getLatitude()));
            longitudeField.setText(Double.toString(lastLocation.getLongitude()));
            addressField.setText(getAddress(lastLocation));
        }

        return root;
    }




    public void onResume(){

        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}