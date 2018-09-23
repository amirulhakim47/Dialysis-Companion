package com.example.yoi.mainmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.CircleProgress;

public class calimage extends AppCompatActivity {


    private static final String TAG = "calimage";


    final Context context = this;
    private ViewPager mViewPager;

    ProgressBar mProgress;
    TextView tv4, tv13, tv14;
    ImageView im1,im2,im3, im4, im5;
    LinearLayout lin;
    CircleProgress circularProgress;

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calimage);



        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        tv4 = (TextView) findViewById(R.id.textView5);
        circularProgress =(CircleProgress)findViewById(R.id.circle_progress);
        circularProgress.setProgress(0);
        circularProgress.setMax(100);

        im1 = (ImageView) findViewById(R.id.cup);
        im2 = (ImageView) findViewById(R.id.can);
        im3 = (ImageView) findViewById(R.id.plus);
        im4 = (ImageView) findViewById(R.id.bottle);
        im5 = (ImageView) findViewById(R.id.jug);
        lin = (LinearLayout) findViewById(R.id.anything);
        lin.removeAllViews();
        /*mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        mProgress.setProgress(0);
        mProgress.setMax(1200);*/




        if (circularProgress.getProgress() == circularProgress.getMax())
        {

            circularProgress.setProgress(0);
            lin.removeAllViews();
            Toast.makeText(calimage.this, "Warning! you have exceeded the water level limit", Toast.LENGTH_SHORT).show();

        }


       /* BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);*/

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // mProgress.setProgress(mProgress.getProgress()+100);
                circularProgress.setProgress(circularProgress.getProgress()+8);
                TextView txtName = new TextView(calimage.this);
                txtName.setId(View.generateViewId());
                txtName.setText("Tea cup - 100 ml");

                lin.addView(txtName);


            }

        });


        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  mProgress.setProgress(mProgress.getProgress()+250);
                circularProgress.setProgress(circularProgress.getProgress()+30);
                TextView txtName = new TextView(calimage.this);
                txtName.setId(View.generateViewId());
                txtName.setText("Soft drinks - 355 ml");

                lin.addView(txtName);

            }
        });

        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog_calimage, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                userInput.setRawInputType(Configuration.KEYBOARD_12KEY);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        int spin3 = Integer.parseInt(userInput.getText().toString()) ;
                                        int spin2 = 1200;
                                        int total = (spin3 * 100 + (spin2 >> 1)) / spin2;

                                        circularProgress.setProgress(circularProgress.getProgress()+ total);
                                        TextView txtName = new TextView(calimage.this);
                                        txtName.setId(View.generateViewId());
                                        txtName.setText("Custom - " + userInput.getText() + " ml");
                                        lin.addView(txtName);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   mProgress.setProgress(mProgress.getProgress()+500);
                circularProgress.setProgress(circularProgress.getProgress()+42);
                TextView txtName = new TextView(calimage.this);
                txtName.setId(View.generateViewId());
                txtName.setText("Small bottle - 500 ml");

                lin.addView(txtName);


            }
        });

        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   mProgress.setProgress(mProgress.getProgress()+800);
                circularProgress.setProgress(circularProgress.getProgress()+67);
                TextView txtName = new TextView(calimage.this);
                txtName.setId(View.generateViewId());
                txtName.setText("Jug - 800 ml");

                lin.addView(txtName);


            }
        });

    }


}