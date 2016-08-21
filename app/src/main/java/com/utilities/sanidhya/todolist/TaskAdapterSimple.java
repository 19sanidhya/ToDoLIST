package com.utilities.sanidhya.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sanidhya on 24/6/16.
 */
public class TaskAdapterSimple extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> arrayListtasks;
    static final int FINISH_DATE_TYPE = 0;
    static final int NAME_CREATE_DATE_TYPE = 1;

    public TaskAdapterSimple(Context context, ArrayList<Task> objects) {
        super(context, 0, objects);

        this.context = context;
        this.arrayListtasks = objects;
    }

    public static class TaskHolder {


        TextView tv1;
        TextView tv2;
        TextView tv3;


        public TaskHolder(TextView tv1, TextView tv2, TextView tv3) {
            this.tv1 = tv1;
            this.tv2 = tv2;
            this.tv3 = tv3;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getPosition(Task item) {
        return arrayListtasks.indexOf(item);
    }

    @Override
    public Task getItem(int position) {
        return arrayListtasks.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return FINISH_DATE_TYPE;
        else
            return NAME_CREATE_DATE_TYPE;
    }

    @Override
    public int getCount() {
        return arrayListtasks.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(this.context).inflate(R.layout.task_view, parent, false);
            TextView tv1 = (TextView) v.findViewById(R.id.task_view_tv);
            TextView tv2 = (TextView) v.findViewById(R.id.create_view_tv);
            TextView tv3 = (TextView) v.findViewById(R.id.finish_view_tv);
            TaskHolder taskHolder = new TaskHolder(tv1, tv2, tv3);
            v.setTag(taskHolder);

        }
        TaskHolder taskHolder = (TaskHolder) v.getTag();

        Task currenttask = arrayListtasks.get(position);
        taskHolder.tv1.setText("" + currenttask.getTask_name());
        taskHolder.tv2.setText("" + currenttask.getCreate_date());
        taskHolder.tv3.setText("" + currenttask.getFinish_date());

        return v;
    }

}
