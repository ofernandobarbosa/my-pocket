package com.fob.mypocket.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fob.mypocket.R;

public class TransactionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.category_spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"Categoria", "Supermercado", "Roupas", "Regalias", "Lancheria", "Viagem", "Casa"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) { // When user clicked on "Choose One" hint and the initial call.
            return;
        }
        position--;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}