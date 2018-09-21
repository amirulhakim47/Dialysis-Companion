package com.example.yoi.mainmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.CardView;
import android.content.Intent;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //set navigation to go to other pages/layout
        CardView cardView = (CardView) findViewById(R.id.reminder);
        CardView cardView1 = (CardView) findViewById(R.id.bankcardId);
        CardView cardView2 = (CardView) findViewById(R.id.dietCalculator);
        CardView cardView3 = (CardView) findViewById(R.id.map_locator);


        //on click function
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, Reminder.class));
            }

            });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  startActivity(new Intent(MainMenu.this, Schedule.class));
                startActivity(new Intent(MainMenu.this, Schedule.class));
            }

        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, Calculator.class));
            }

        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, activity_maps.class));
            }

        });
    }
}
