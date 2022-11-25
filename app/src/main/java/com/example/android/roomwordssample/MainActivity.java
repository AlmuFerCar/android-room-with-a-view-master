package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static com.example.android.roomwordssample.NewContactActivity.EXTRA_REPLY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    public static final int DELETE_CONTACT_ACTIVITY_REQUEST_CODE = 2;
    public static ContactViewModel mContactViewModel;
    public Contact contacto;
    public static String CONTACTO;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Get a new or existing ViewModel from the ViewModelProvider.
        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mContactViewModel.getAllContact().observe(this, contacts -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(contacts);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
            startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
        });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Mi App", "Pulsado el elemento"+recyclerView.getChildAdapterPosition(v));

             //   adapter.getContacto(recyclerView.getChildAdapterPosition(v));
                contacto= adapter.getContacto(recyclerView.getChildAdapterPosition(v));
//                Log.i("Mi App", "Pulsado el elemento"+ contacto.getmName());

                paginaSiguiente();
            }
        });
    }

   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Contact contact = (Contact) data.getSerializableExtra(EXTRA_REPLY);
            mContactViewModel.insert(contact);
            //mContactViewModel.delete(contact);
        }
        else if(resultCode == -2)
       {
           Contact contact = (Contact) data.getSerializableExtra(EXTRA_REPLY);
           mContactViewModel.delete(contact);
       }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void paginaSiguiente()
    {
        Bundle b=new Bundle();
        b.putSerializable(CONTACTO, contacto);
        Intent intent=new Intent(this, Contact_view.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
