package com.utilities.sanidhya.todolist;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by sanidhya on 23/6/16.
 */
public class Task implements Serializable{

    private String task_name;
    private String create_date;
    private String finish_date;
    LocalTime localTime;

    public Task(String task_name, String finish_date) {
        this.task_name = task_name;
        this.create_date =new LocalDate().now().toString();
        this.finish_date = finish_date;
       // this.localTime= localTime;

    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}

