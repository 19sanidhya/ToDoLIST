package com.utilities.sanidhya.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sanidhya on 23/6/16.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    public HashMap<String, ArrayList<Task>> map;
    Context context;
    String[] mKeys;
    public static final int FINISH_DATE_VIEW = 0;
    public static final int TASK_VIEW = 1;

    @Override
    public int getCount() {
        return map.size();
    }

    public TaskAdapter(Context context, HashMap<String, ArrayList<Task>> constructor_map) {
        super(context,0,0);
        map = constructor_map;
        this.context = context;
        mKeys = constructor_map.keySet().toArray(new String[constructor_map.size()]);
    }


    private static class FinishtvHolder {

        TextView taskFinishDatetv;


        public FinishtvHolder(TextView taskFinishDatetv) {
            this.taskFinishDatetv = taskFinishDatetv;

        }
    }

    private static class TasktvHolder {


        TextView taskNametv;
        TextView taskCreateDatetv;

        public TasktvHolder(TextView taskNametv, TextView taskCreateDatetv) {

            this.taskNametv = taskNametv;
            this.taskCreateDatetv = taskCreateDatetv;
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
//        Task task=map.get(mKeys[position]).get(position);
        //  int itemviewtype=getItemViewType(position);
//if(itemviewtype==FINISH_DATE_VIEW){
//
//        if(v==null)
//        {
//           v= LayoutInflater.from(this.context).inflate(R.layout.finish_date,parent,false);
//            TextView finish_tv=(TextView)v.findViewById(R.id.finish_view);
//            FinishtvHolder finishtvHolder=new FinishtvHolder(finish_tv);
//            v.setTag(finish_tv);
//        }
//        FinishtvHolder finishtvHolder=(FinishtvHolder)v.getTag();
//    finishtvHolder.taskFinishDatetv.setText("Finish By:                      "+task.getFinish_date());
//
//
//
//    }
//        else{
        for (String key : map.keySet()) {
            Task task = map.get(key).get(0);

            if (v == null) {
                v = LayoutInflater.from(this.context).inflate(R.layout.task_view, parent, false);
                TextView task_tv = (TextView) v.findViewById(R.id.task_view_tv);
                TextView create_date_tv = (TextView) v.findViewById(R.id.create_view_tv);
                task_tv.setText("" + task.getTask_name());
                create_date_tv.setText("" + task.getCreate_date());

                TasktvHolder tasktvHolder = new TasktvHolder(task_tv, create_date_tv);
                v.setTag(tasktvHolder);
            }
            TasktvHolder tasktvHolder = (TasktvHolder) v.getTag();
            tasktvHolder.taskNametv.setText("" + task.getTask_name());
            tasktvHolder.taskCreateDatetv.setText("" + task.getCreate_date());


//}


        }
        return v;
    }
}
//if(itemviewtype==TASK_VIEW)