package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Contact_view extends AppCompatActivity {
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        resultado=findViewById(R.id.contacto);

        Intent intent2=getIntent();
        Bundle b= intent2.getExtras();
//        Agenda a=(Agenda) b.getSerializable(MainActivity.CONTACTO);
//
//        resultado.setText (a.getNombre()+"\n");
//        resultado.append (a.getTelefono()+"\n");
    }
}