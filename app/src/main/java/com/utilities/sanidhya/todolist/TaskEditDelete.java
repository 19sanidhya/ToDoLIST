package com.utilities.sanidhya.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class TaskEditDelete extends AppCompatActivity implements View.OnClickListener {
    EditText taskNameEdEt;
    EditText taskFinishEdEt;
    EditText taskCreateEdEt;
    Button taskSaveButton;
    Button taskDeleteButton;
    Intent intent;
    int position;
    ArrayList<Task> taskArrayList;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit_delete);
        taskNameEdEt=(EditText)findViewById(R.id.ed_task_name_et);
        taskFinishEdEt=(EditText)findViewById(R.id.ed_task_finish_date_et);
        taskCreateEdEt=(EditText)findViewById(R.id.ed_task_create_date_et);
        intent=getIntent();
        task=(Task)intent.getSerializableExtra("taskobj");
        position=intent.getIntExtra("position of task",0);
        taskNameEdEt.setText(task.getTask_name());
        taskCreateEdEt.setText(task.getCreate_date());
        taskFinishEdEt.setText(task.getFinish_date());
        taskSaveButton=(Button)findViewById(R.id.ed_task_save_button);
        taskDeleteButton=(Button)findViewById(R.id.ed_task_delete_button);
        taskSaveButton.setOnClickListener(this);
        taskDeleteButton.setOnClickListener(this);
        intent.putExtra("posret",position);

    }

    @Override
    public void onClick(View v) {
        if(v==taskSaveButton)
        {
            task.setTask_name(""+taskNameEdEt.getText());
            task.setFinish_date(""+taskFinishEdEt.getText());
            intent.putExtra("taskobjret",task);
            setResult(1,intent);
            finish();

        }
        else if(v==taskDeleteButton)
        {
            setResult(2,intent);
            finish();
        }

    }
}
