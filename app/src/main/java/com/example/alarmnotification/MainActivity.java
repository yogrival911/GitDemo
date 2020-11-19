package com.example.alarmnotification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    TimePicker timePicker;
    Button okayButton, cancelButton;
    int hour, minute;
    TextView textViewTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isAlarmOn;
    String savedAlarmTime;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = (Switch)findViewById(R.id.aSwitch);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        okayButton = (Button)findViewById(R.id.okayButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        textViewTime = (TextView)findViewById(R.id.textViewTime);

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        savedAlarmTime = sharedPreferences.getString("time", "");
        isAlarmOn = sharedPreferences.getBoolean("isAlarmOn", false);
        textViewTime.setText(savedAlarmTime);
        aSwitch.setChecked(isAlarmOn);

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


        if(isAlarmOn){

            Intent alarmIntent = new Intent(this, MyBroadcasteReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 11, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//        if (pendingIntent != null && alarmManager != null) {
//            alarmManager.cancel(pendingIntent);
//        }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + (5 * 1000), 5000, pendingIntent);


//            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//            Toast.makeText(this, "Alarm active", Toast.LENGTH_SHORT).show();
//            Intent alarmIntent = new Intent(this, MyBroadcasteReceiver.class);
//            alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 11, alarmIntent, PendingIntent.FLAG_NO_CREATE);
//            if (pendingIntent != null && alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//            }
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + (5 * 1000), 5000, pendingIntent);
//
////            // Set the alarm to start at approximately 2:00 p.m.
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTimeInMillis(System.currentTimeMillis());
////            calendar.set(Calendar.HOUR_OF_DAY, 14);
////
////            // With setInexactRepeating(), you have to use one of the AlarmManager interval
////            // constants--in this case, AlarmManager.INTERVAL_DAY.
////            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES
////                    , pendingIntent);
        }
        else{
            Toast.makeText(this, "No Alarm", Toast.LENGTH_SHORT).show();
        }
//
//
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                if(b==true){
//                    Log.i("yog", "ON");
//                    timePicker.setVisibility(View.VISIBLE);
//                    okayButton.setVisibility(View.VISIBLE);
//                    cancelButton.setVisibility(View.VISIBLE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        okayButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                hour = timePicker.getHour();
//                                minute = timePicker.getMinute();
//                                savedAlarmTime = Integer.toString(hour) + ":" + Integer.toString(minute);
//                                textViewTime.setText(savedAlarmTime);
//
//                                editor.putString("time", savedAlarmTime);
//                                editor.putBoolean("isAlarmOn", true);
//                                editor.commit();
//
//                                Log.i("yog", Integer.toString(hour));
//                                timePicker.setVisibility(View.INVISIBLE);
//                                okayButton.setVisibility(View.INVISIBLE);
//                                cancelButton.setVisibility(View.INVISIBLE);
//                            }
//                        });
//                    }
//                }
//                else{
//                    Log.i("yog", "OFF");
//                    timePicker.setVisibility(View.INVISIBLE);
//                    okayButton.setVisibility(View.INVISIBLE);
//                    cancelButton.setVisibility(View.INVISIBLE);
//                    editor.putBoolean("isAlarmOn", false);
//                    editor.commit();
//                }
//            }
//        });


    }
}