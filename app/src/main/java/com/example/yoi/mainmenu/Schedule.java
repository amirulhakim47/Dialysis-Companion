package com.example.yoi.mainmenu;

import android.app.AlarmManager;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.PendingIntent;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TimePicker;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.Calendar;




//DatePicker
public class Schedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    EditText chooseTime,et2;
    Button btnOK;
    int currentHour;
    int currentMinute;
    public static int alarmHour, alarmMin, alarmDay, alarmYear, alarmMonth;
    final static int RQS_1 = 1;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        chooseTime = (EditText) findViewById(R.id.etChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(Schedule.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        // get input time
                       /* Calendar calNow = Calendar.getInstance();
                        Calendar calSet = (Calendar) calNow.clone();

                        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calSet.set(Calendar.MINUTE, minutes);
                        calSet.set(Calendar.SECOND, 0);
                        calSet.set(Calendar.MILLISECOND, 0);*/
                        alarmHour = hourOfDay;
                        alarmMin = minutes;

                        /*if(calSet.compareTo(calNow) <= 0){
                            //Today Set time passed, count to tomorrow
                            calSet.add(Calendar.DATE, 1);
                        }*/

                       // go(calSet);

                        String amPm;
                        if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + "" + amPm);
                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });


        //clear button
        Button btnCancel=(Button) findViewById(R.id.buttonSchCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chooseTime=(EditText) findViewById(R.id.etChooseTime);
                et2=(EditText) findViewById(R.id.button2);
                et2.setText("");
                chooseTime.setText("");

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent myIntent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), 1, myIntent, 0);

                alarmManager.cancel(pendingIntent);

            }
        });



        // button for date picker
        // Button button = (Button) findViewById(R.id.button2);
        et2 =(EditText) findViewById(R.id.button2);
        et2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }


    /*public void stopAlarm(Context context) {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }*/


    public void setAlarm(View view) {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        calSet.set(Calendar.HOUR_OF_DAY, alarmHour);
        calSet.set(Calendar.MINUTE, alarmMin);
        calSet.set(Calendar.DAY_OF_MONTH, alarmDay);
        calSet.set(Calendar.YEAR, alarmYear);
        calSet.set(Calendar.MONTH, alarmMonth);

        go(calSet);
    }



    private void go(Calendar targetCal)
    {
        Toast.makeText(this, "Alarm is set at" + " " + targetCal.getTime(),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }




    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        /*Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.YEAR , year);
        calSet.set(Calendar.MONTH, month);
        calSet.set(Calendar.DAY_OF_MONTH, day);*/
        alarmDay = day;
        alarmMonth = month;
        alarmYear = year;
       // String currentDateString = DateFormat.getDateInstance().format(calSet.getTime());


        //go(calSet);

        et2 =(EditText) findViewById(R.id.button2);
        et2.setText(alarmDay + "-" + alarmMonth + "-" +alarmYear);

    }




}
