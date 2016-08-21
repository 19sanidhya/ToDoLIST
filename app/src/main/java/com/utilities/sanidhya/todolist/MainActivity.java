package com.utilities.sanidhya.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView todoListView;
    ArrayList<Task> taskArrayList = new ArrayList<>(1);
    TaskAdapter taskAdapter;
    HashMap<String, ArrayList<Task>> map = new HashMap<>();
//    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        todoListView = (ListView) findViewById(R.id.todo_lv);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        taskArrayList.add(new Task("chap1", "25-06-2016"));
        map.put("25-06-2016", taskArrayList);
        taskAdapter = new TaskAdapter(MainActivity.this, map);
        todoListView.setAdapter(taskAdapter);
        Log.i("String", "asfdf");
        //task_list.add(new Task("chap1",new LocalDate(23,06,2016),new LocalDate(25,06,2016)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if(id == R.id.add_menu) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
            dialogBuilder.setTitle("ADD A TASK");
            View v = getLayoutInflater().inflate(R.layout.alert_dialog, null);
            final EditText task_name_et = (EditText) v.findViewById(R.id.alert_task_name_et);
            final EditText task_finish_date_et=(EditText)v.findViewById(R.id.alert_task_finish_date_et);

            dialogBuilder.setView(v);
            dialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ArrayList<Task> arrayList=new ArrayList<Task>(1);
                    arrayList.add(new Task(""+task_name_et.getText(),""+task_finish_date_et.getText()));
                   map.put(""+task_finish_date_et.getText(),arrayList);
                    taskAdapter.notifyDataSetChanged();
                }
            });
            dialogBuilder.create().show();

        }

        return super.onOptionsItemSelected(item);
    }
}
