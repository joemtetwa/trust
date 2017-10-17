package com.mig062.trust;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimetableActivity extends AppCompatActivity {

    private EditText courseView;
    private EditText additionalView;
    private DatePicker beginDateView;
    private TimePicker beginTimeView;
    private DatePicker endDateView;
    private TimePicker endTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        courseView = (EditText) findViewById(R.id.course);
        additionalView = (EditText) findViewById(R.id.additionalInfo);
        beginDateView = (DatePicker) findViewById(R.id.assignmentBeginDate);
        beginTimeView = (TimePicker) findViewById(R.id.assignmentBeginTime);
        endDateView = (DatePicker) findViewById(R.id.assignmentEndDate);
        endTimeView = (TimePicker) findViewById(R.id.assignmentEndTime);
    }

    public void saveAssignment(View v) {
        long calID = 3;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(beginDateView.getYear(),
                beginDateView.getMonth(),
                beginDateView.getDayOfMonth(),
                beginTimeView.getHour(),
                beginTimeView.getMinute());
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(endDateView.getYear(),
                endDateView.getMonth(),
                endDateView.getDayOfMonth(),
                endTimeView.getHour(),
                endTimeView.getMinute());

        long endMillis = endTime.getTimeInMillis();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(Events.DTSTART, startMillis);
        values.put(Events.DTEND, endMillis);
        values.put(Events.TITLE, courseView.getText().toString());
        values.put(Events.DESCRIPTION, additionalView.getText().toString());
        values.put(Events.CALENDAR_ID, calID);
        values.put(Events.EVENT_TIMEZONE, "America/Los_Angeles");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(TimetableActivity.this, getString(R.string.calendar_permission_failed), Toast.LENGTH_LONG).show();
            return;
        }
        Uri uri = cr.insert(Events.CONTENT_URI, values);

// get the event ID that is the last element in the Uri
        long eventID = Long.parseLong(uri.getLastPathSegment());
//
        Toast.makeText(TimetableActivity.this, "Timetable event "+eventID+" added!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(TimetableActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
