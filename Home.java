package com.vjit.mysemester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton subjects, syllabus, timetable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        subjects = (ImageButton) findViewById(R.id.subject);
        syllabus = (ImageButton) findViewById(R.id.syllabus);
        timetable = (ImageButton) findViewById(R.id.timetable);

        subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, subjects.class);
                    startActivity(intent);
            }
        });

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, syllabus.class);
                startActivity(intent);
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, timetable.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
