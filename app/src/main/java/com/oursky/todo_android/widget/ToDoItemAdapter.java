package com.oursky.todo_android.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.model.Task;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class ToDoItemAdapter extends ArrayAdapter<Task> {
    private Context context;
    private LayoutInflater inflater;
    private ToDoListListener listener;

    public ToDoItemAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        try {
            listener = (ToDoListListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement ToDoListFinishedListener.");
        }
    }

    public interface ToDoListListener {
        public void setTaskFinished(int position);
        public void setEditFinished(int position, Task task);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.partial_to_do_item, parent, false);
            convertView.setTag(new ToDoItemViewHolder(convertView));
        }
        final ToDoItemViewHolder holder = (ToDoItemViewHolder) convertView.getTag();

        final Task task = getItem(position);
        holder.checkBox.setChecked(task.isFinished());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            task.setIsFinished(isChecked);
            if (isChecked) {
                listener.setTaskFinished(position);
            }
            }
        });
        if (task.getDescription() != null && task.getDescription().length() != 0) {
            holder.task.setText(task.getDescription());
            holder.editTask.setVisibility(View.GONE);
            holder.task.setVisibility(View.VISIBLE);
        } else {
            holder.editTask.setText("");
            holder.editTask.setVisibility(View.VISIBLE);
            holder.task.setVisibility(View.GONE);
        }
        holder.editTask.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if (v != null && v.getParent() != null && !TextUtils.isEmpty(((EditText)v).getText())) {
                    ((EditText) v).setOnEditorActionListener(null);
                    String taskk = ((EditText) v).getText().toString();
                    task.setDescription(taskk);
                    holder.task.setText(taskk);
                    v.setVisibility(View.GONE);
                    holder.task.setVisibility(View.VISIBLE);
                    listener.setEditFinished(position, task);
                }
            }
            }
        });
        holder.editTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String taskk = ((EditText) v).getText().toString();
                task.setDescription(taskk);
                holder.task.setText(taskk);
                v.setVisibility(View.GONE);
                holder.task.setVisibility(View.VISIBLE);
                listener.setEditFinished(position, task);
            }
            return false;
            }
        });
        return convertView;
    }

    public class ToDoItemViewHolder {
        public TextView task;
        public EditText editTask;
        public CheckBox checkBox;

        public ToDoItemViewHolder(View view) {
            task = (TextView) view.findViewById(R.id.partial_to_do_task);
            checkBox = (CheckBox) view.findViewById(R.id.partial_to_do_checkbox);
            editTask = (EditText) view.findViewById(R.id.partial_to_do_edit_task);
        }
    }
}
