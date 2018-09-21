package com.example.yoi.mainmenu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

public class Reminder extends AppCompatActivity {

    EditText chooseTime;
    int currentHour;
    int currentMinute;
    final static int RQS_1 = 1;
    public static int alarmHour, alarmMin, alarmDay, alarmYear, alarmMonth;

    //spinner dropdown
    Spinner spinnerDropDown;
    String[] volume = {
            "",
            "Erythropoietin",
            "Phosphorus binders",
            "Active Vitamin D",
            "B-complex Vitamin",
            "Vitamin E",
            "Antihistamines"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);


        // Get reference of SpinnerView from layout/activity_calculator.xml
        spinnerDropDown =(Spinner)findViewById(R.id.spinner4);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,volume);

        spinnerDropDown.setAdapter(adapter);

        spinnerDropDown.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                if
                        (parent.getItemAtPosition(position).equals(""))
                {
                    // do nothing
                }
                else {
                    int sid = spinnerDropDown.getSelectedItemPosition();
                    Toast.makeText(getBaseContext(), "You have selected : " + volume[sid],
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


                // TODO Auto-generated method stub
            }
        });


        chooseTime = (EditText) findViewById(R.id.reChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        // get input time
                        /*Calendar calNow = Calendar.getInstance();
                        Calendar calSet = (Calendar) calNow.clone();

                        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calSet.set(Calendar.MINUTE, minutes);
                        calSet.set(Calendar.SECOND, 0);
                        calSet.set(Calendar.MILLISECOND, 0);

                        if(calSet.compareTo(calNow) <= 0){
                            //Today Set time passed, count to tomorrow
                            calSet.add(Calendar.DATE, 1);
                        }

                        setAlarm(calSet);*/

                        alarmHour = hourOfDay;
                        alarmMin = minutes;

                        //set text value
                        /*chooseTime.setText(hourOfDay + ":" + minutes);
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));*/

                        String amPm;
                        if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);


                    }
                }, 0, 0, false);

                timePickerDialog.show();






            }
        });

        //clear button
        Button btnCancel=(Button) findViewById(R.id.buttonRemCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText re=(EditText) findViewById(R.id.reChooseTime);
                Spinner tvDate = (Spinner) findViewById(R.id.spinner4);
                tvDate.setAdapter(null);
                re.setText("");

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent myIntent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), 1, myIntent, 0);

                alarmManager.cancel(pendingIntent);
            }
        });

        // OK button in schedule layout

       /* Button btnOK = (Button) findViewById(R.id.buttonRemOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {



                Toast.makeText(getBaseContext(), "Your reminder has been set!" , Toast.LENGTH_SHORT ).show();

            }});*/
    }

    /*private void setAlarm(Calendar targetCal){



        Intent intent = new Intent(getBaseContext(), ReminderReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }*/

    public void setAlarm(View view) {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        calSet.set(Calendar.HOUR_OF_DAY, alarmHour);
        calSet.set(Calendar.MINUTE, alarmMin);


        go(calSet);
    }

    private void go(Calendar targetCal)
    {
        Toast.makeText(this, "Reminder is set at" + " " + targetCal.getTime(),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), ReminderReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

}