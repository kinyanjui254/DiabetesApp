package com.example.fbcon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.helper.GraphViewXML;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class chart extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private EditText enter_sugar;
    private Button submit_value;
    private GraphViewXML graph;
    FirebaseDatabase database;
    DatabaseReference reference;
    SimpleDateFormat sdf  = new SimpleDateFormat("hh:mm:ss");
    LineGraphSeries series;


    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        enter_sugar = findViewById(R.id.enter_sugar);
        submit_value = findViewById(R.id.submit_value);
        graph = findViewById(R.id.graph);
        series = new LineGraphSeries();
        graph.addSeries (series);

        database = FirebaseDatabase.getInstance ();
        reference= database.getReference ("chartTable");

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setListners();
        graph.getGridLabelRenderer().setLabelFormatter (new DefaultLabelFormatter (){

            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format (new Date ((long) value));
                }else {
                    return super.formatLabel (value, isValueX);
                }

            }
        });
    }

    private void setListners() {
        submit_value.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String id = reference.push().getKey ();
                long x = new Date ().getTime();
                int y = Integer.parseInt (enter_sugar.getText ().toString ());

                PointValue pointValue = new PointValue (x,y);

                reference.child (id).setValue (pointValue);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.food_info:
                Intent intent = new Intent(chart.this,FoodInformation.class);
                startActivity(intent);
                break;

            case R.id.info:
                Intent intent1 = new Intent(chart.this, information.class );
                startActivity(intent1);

            case R.id.chat:
                Intent intent2 = new Intent(chart.this, chat.class);
                startActivity(intent2);

            case R.id.menu:
                Intent intent3 = new Intent(chart.this, Logbook.class );
                startActivity(intent3);
            case R.id.emergency:
                startActivity(new Intent(this,EmergencyTestActivity.class));
        }


        return false;
    }
}
