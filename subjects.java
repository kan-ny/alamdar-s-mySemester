package com.vjit.mysemester;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.R.attr.value;

public class subjects extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    final String sub = "/subjects";
    private ListView mlistView;
    private ArrayList<String> mUser = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);


        database = FirebaseDatabase.getInstance();

        myRef = database.getReference();

        myRef = myRef.child(sub);


        mlistView = (ListView) findViewById(R.id.listviewid);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        if(!isNetworkAvailable())
        {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Check Network Connection",Toast.LENGTH_SHORT).show();
        }



        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUser);

        mlistView.setAdapter(arrayAdapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getKey().toString();

                //String[] temp = value.split(" ");


                mUser.add(value);


                arrayAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
