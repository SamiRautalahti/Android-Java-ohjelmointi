package com.example.my_application;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaxiFareCalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private double aloitusmaksu = 4.50;
    private double pyhapaivanLisamaksu = 3.00;
    private double matkataksa1 = 1.90;
    private double matkataksa2 = 2.50;

    private TextView hintaTextView;
    private Spinner matkanPituusSpinner;
    private Spinner matkustajienMaaraSpinner;

    private int valittuMatkanPituus;
    private int valittuMatkustajienMaara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_fare_calculator);

        hintaTextView = findViewById(R.id.hintaTextView);

        matkanPituusSpinner = findViewById(R.id.matkanPituusSpinner);
        ArrayAdapter<CharSequence> matkanPituusAdapter = ArrayAdapter.createFromResource(this,
                R.array.matkan_pituudet_array, android.R.layout.simple_spinner_item);
        matkanPituusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matkanPituusSpinner.setAdapter(matkanPituusAdapter);
        matkanPituusSpinner.setOnItemSelectedListener(this);

        matkustajienMaaraSpinner = findViewById(R.id.matkustajienMaaraSpinner);
        ArrayAdapter<CharSequence> matkustajienMaaraAdapter = ArrayAdapter.createFromResource(this,
                R.array.matkustajien_maarat_array, android.R.layout.simple_spinner_item);
        matkustajienMaaraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matkustajienMaaraSpinner.setAdapter(matkustajienMaaraAdapter);
        matkustajienMaaraSpinner.setOnItemSelectedListener(this);
    }

    private void paivitaHinta() {
        double hinta;

        if (valittuMatkanPituus == 1) {
            hinta = aloitusmaksu + pyhapaivanLisamaksu + matkataksa1 * 10;
        } else {
            hinta = aloitusmaksu + pyhapaivanLisamaksu + matkataksa2 * 10;
        }

        if (valittuMatkustajienMaara > 4) {
            hinta += (valittuMatkustajienMaara - 4) * 0.50;
        }

        hintaTextView.setText(String.format("%.2f", hinta));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == matkanPituusSpinner) {
            valittuMatkanPituus = position;
        } else if (parent == matkustajienMaaraSpinner) {
            valittuMatkustajienMaara = position;
        }

        paivitaHinta();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Ei tehdä mitään, kun mitään ei ole valittu.
    }
    public double laskeHinta(int matkanPituus, int matkustajienMaara) {
        double hinta;

        if (matkanPituus == 1) {
            hinta = aloitusmaksu + pyhapaivanLisamaksu + matkataksa1 * 10;
        } else {
            hinta = aloitusmaksu + pyhapaivanLisamaksu + matkataksa2 * 10;
        }

        if (matkustajienMaara > 4) {
            hinta += (matkustajienMaara - 4) * 0.50;
        }

        return hinta;
    }
}