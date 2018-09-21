package com.example.yoi.mainmenu;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity  {

    Spinner spinnerDropDown;
    String[] volume = {
            "0",
            "100",
            "150",
            "200",
            "250",
            "300",
            "350",
            "400",
            "450",
            "500"
    };
    Spinner spinnerDropDown2;
    String[] volume2 = {
            "0",
            "100",
            "150",
            "200",
            "250",
            "300",
            "350",
            "400",
            "450",
            "500"
    };
    Spinner spinnerDropDown3;
    String[] volume3 = {
            "0",
            "100",
            "150",
            "200",
            "250",
            "300",
            "350",
            "400",
            "450",
            "500"
    };
    Button btn,clr;
    int total;
    ProgressBar mProgress;
    TextView tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Get reference of SpinnerView from layout/activity_calculator.xml
        spinnerDropDown = (Spinner) findViewById(R.id.spinner);
        spinnerDropDown2 = (Spinner) findViewById(R.id.spinner2);
        spinnerDropDown3 = (Spinner) findViewById(R.id.spinner3);
        tv4 = (TextView) findViewById(R.id.textView4);
        btn = (Button) findViewById(R.id.buttonCalculate);
        clr = (Button) findViewById(R.id.btnReset);
        mProgress = (ProgressBar) findViewById(R.id.progressBar3);
        mProgress.setProgress(0);
        mProgress.setMax(1500);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.
                R.layout.simple_spinner_dropdown_item, volume);
        spinnerDropDown.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.
                R.layout.simple_spinner_dropdown_item, volume2);
        spinnerDropDown2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.
                R.layout.simple_spinner_dropdown_item, volume3);
        spinnerDropDown3.setAdapter(adapter3);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // recheck int conversion value

                int spin1 = Integer.parseInt(spinnerDropDown.getSelectedItem().toString());
                int spin2 = Integer.parseInt(spinnerDropDown2.getSelectedItem().toString());
                int spin3 = Integer.parseInt(spinnerDropDown3.getSelectedItem().toString());

                total = spin3 + spin2 + spin1;
                mProgress.incrementProgressBy(total);
                tv4.setText(total + " ml" + " " + "/ 1500 ml");

                if  (total >= 0 && total < 599) {

                    Toast.makeText(Calculator.this, "Congratulations! you are currently on a safe water level.", Toast.LENGTH_SHORT).show();
                    mProgress.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    mProgress.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else
                {
                    Toast.makeText(Calculator.this, "Warning, you have reached the daily limit!", Toast.LENGTH_SHORT).show();
                    mProgress.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    mProgress.setProgressTintList(ColorStateList.valueOf(Color.RED));
                }


            }
        });


        spinnerDropDown.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                if
                        (parent.getItemAtPosition(position).equals("0")) {
                    // do nothing
                } else {
                    int sid = spinnerDropDown.getSelectedItemPosition();
                    Toast.makeText(getBaseContext(), "You have selected : " + volume[sid] + "ml",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinnerDropDown2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                if
                        (parent.getItemAtPosition(position).equals("0")) {
                    // do nothing
                } else {
                    int sid = spinnerDropDown2.getSelectedItemPosition();
                    Toast.makeText(getBaseContext(), "You have selected : " + volume[sid] + "ml",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinnerDropDown3.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                if
                        (parent.getItemAtPosition(position).equals("0")) {
                    // do nothing
                } else {
                    int sid = spinnerDropDown3.getSelectedItemPosition();
                    Toast.makeText(getBaseContext(), "You have selected : " + volume[sid] + "ml",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //reset button

        clr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mProgress.setProgress(0);
                spinnerDropDown.setSelection(0);
                spinnerDropDown2.setSelection(0);
                spinnerDropDown3.setSelection(0);
                tv4.setText("Max : 1500 ml");

            }
        });



    }



}
        


