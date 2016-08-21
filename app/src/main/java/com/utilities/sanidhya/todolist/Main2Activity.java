package com.utilities.sanidhya.todolist;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    ListView todoListView;
    ArrayList<Task> taskArrayList = new ArrayList<>();
    TaskAdapterSimple taskAdapterSimple;
    LocalTime localTime;
    Intent intent;
    FloatingActionButton fab;
    CheckBox alarmCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.main_add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Main2Activity.this);
                dialogBuilder.setTitle("ADD A TASK");
                View v = getLayoutInflater().inflate(R.layout.alert_dialog, null);
                final EditText task_name_et = (EditText) v.findViewById(R.id.alert_task_name_et);
                final EditText task_finish_date_et = (EditText) v.findViewById(R.id.alert_task_finish_date_et);
                final EditText task_alarm_time_et = (EditText) v.findViewById(R.id.alert_task_alarm_time_et);

                Button finishDateButton = (Button) v.findViewById(R.id.alert_task_finish_date_b);
                finishDateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(dialogBuilder.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                task_finish_date_et.setText("" + i + "-" + i1 + "-" + i2);
                            }
                        }, LocalDate.now().getYear(), LocalDate.now().getMonthOfYear(), LocalDate.now().getDayOfMonth());
                        datePickerDialog.show();
                    }
                });
                alarmCB=(CheckBox)v.findViewById(R.id.alert_task_alarm_cb);
                Button alarmTime = (Button) v.findViewById(R.id.alert_task_alarm_time_b);

                if(alarmCB.isChecked())
                {

                    alarmTime.setVisibility(View.VISIBLE);
                    task_alarm_time_et.setVisibility(View.VISIBLE);


                }
                alarmTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyTimePickerDialog localTimeDialog = new MyTimePickerDialog(dialogBuilder.getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                                task_alarm_time_et.setText("" + hourOfDay + ":" + minute + ":" + seconds);
                            }
                        }, LocalTime.now().getHourOfDay(), LocalTime.now().getMinuteOfHour(), LocalTime.now().getSecondOfMinute(), false);
                        localTimeDialog.show();
                    }
                });
                dialogBuilder.setView(v);
                dialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (task_name_et.getText().toString().equals("") || task_finish_date_et.getText().toString().equals(""))
                            Toast.makeText(Main2Activity.this, "Fields cannot be empty!!", Toast.LENGTH_LONG).show();
                        else {
                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            intent = new Intent(Main2Activity.this, AlarmActivity.class);
                            intent.putExtra("task_name", task_name_et.getText().toString());
                            PendingIntent pi;
                            pi = PendingIntent.getActivity(Main2Activity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                            LocalDate localDate = new LocalDate(task_finish_date_et.getText().toString());
                          ;

                            if(alarmCB.isChecked()) {
                                localTime = new LocalTime(task_alarm_time_et.getText().toString());
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(localDate.getYear(), localDate.getMonthOfYear() - 1, localDate.getDayOfMonth(),
                                        localTime.getHourOfDay(), localTime.getMinuteOfHour(), localTime.getSecondOfMinute());
                                long alarmTimeMilli = calendar.getTimeInMillis();
                                Log.i("currenttime", "" + System.currentTimeMillis());
                                Log.i("alarmtime", "" + alarmTimeMilli);

                                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMilli, pi);
                            }

                            taskArrayList.add(new Task("" + task_name_et.getText(), "" + task_finish_date_et.getText()));
                            taskAdapterSimple.notifyDataSetChanged();
                        }
                    }
                });
                dialogBuilder.create().show();

            }
        });
        todoListView = (ListView) findViewById(R.id.listView);
        taskAdapterSimple = new TaskAdapterSimple(Main2Activity.this, taskArrayList);
        todoListView.setAdapter(taskAdapterSimple);
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Intent i = new Intent(Main2Activity.this, TaskEditDelete.class);
                i.putExtra("taskobj", taskArrayList.get(position));
                i.putExtra("position of task", position);
                startActivityForResult(i, 1);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            if (resultCode == 1) {
                Task task = (Task) data.getSerializableExtra("taskobjret");
                taskArrayList.remove(data.getIntExtra("posret", 0));
                taskAdapterSimple.notifyDataSetChanged();
                taskArrayList.add(data.getIntExtra("posret", 0), task);
                taskAdapterSimple.notifyDataSetChanged();

            } else if (resultCode == 2) {
                taskArrayList.remove(data.getIntExtra("posret", 0));
                taskAdapterSimple.notifyDataSetChanged();
            }
        }
    }

}
