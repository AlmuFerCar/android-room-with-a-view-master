package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact_view extends AppCompatActivity {
    public static final String EXTRA_REPLY = "REPLY";

    TextView resultado;
    Button boton_borrar;
    Button boton_editar;
    public Contact contacto;
    public static String CONTACTO;
    public static boolean recibir=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        resultado=findViewById(R.id.contacto);
        boton_borrar=findViewById(R.id.delete);
        boton_editar=findViewById(R.id.edit);

        MainActivity.mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        Intent intent2=getIntent();
        Bundle b = intent2.getExtras();
        Contact c=(Contact) b.getSerializable(MainActivity.CONTACTO);

        resultado.setText (c.getmName()+"\n");
        resultado.append (c.getmPhone()+"\n");

        contacto=new Contact(c.getmName(), c.getmPhone());

        boton_borrar.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(View view){
                MainActivity.mContactViewModel.delete(c);
                volver(view);
            }
        });

        boton_editar.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View view)
            {
                paginaEditar(view);
            }
            });
    }

    public void volver (View view)
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void paginaEditar(View view)
    {
        Intent intent=new Intent(this, NewContactActivity.class);
        Contact p= new Contact(contacto.getmName().toString(), contacto.getmPhone().toString());
        Bundle b= new Bundle();
        b.putSerializable(CONTACTO, p);
        intent.putExtras(b);
        recibir=true;
        startActivity(intent);
    }
}