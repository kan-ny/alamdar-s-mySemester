package com.vjit.mysemester;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Comparator;

import static android.R.attr.key;

public class syllabus extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef, myRef3;

    final String stu = "/subjects";
    private ListView mlistView;
    private ArrayList<String> mUser = new ArrayList<>();

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference();

        myRef = myRef.child(stu);


        mlistView = (ListView) findViewById(R.id.listviewid);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUser);

        mlistView.setAdapter(arrayAdapter);


        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        if(!isNetworkAvailable())
        {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Check Network Connection",Toast.LENGTH_SHORT).show();
        }


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getKey().toString();

                //String[] temp = value.split(" ");


                mUser.add(value);

                arrayAdapter.sort(new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareTo(rhs);
                    }
                });

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


        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                {

                    //   myRef3.child(""+value2).removeValue();

                    String value = (String) adapterView.getItemAtPosition(position);

                    //  String[] temp = value.split(" ");

                    //   String key = temp[1];

                    // Toast.makeText(getApplicationContext(),""+value,Toast.LENGTH_SHORT).show();

                    displayFun(value);

                }
            }
        });

    }



    void displayFun(final String subSyll) {

        myRef3 = database.getReference();

        myRef3 = myRef3.child(stu);

        myRef3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);


                if (subSyll.equals( dataSnapshot.getKey().toString())) {


                    //     Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(syllabus.this);

                    builder.setTitle(subSyll+" Syllabus:");
                    builder.setMessage(""+value);


                    AlertDialog alert = builder.create();
                    alert.show();

                }


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