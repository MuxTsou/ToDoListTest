package com.oursky.todo_android.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.DatabaseHelper;
import com.oursky.todo_android.content.model.Task;
import com.oursky.todo_android.widget.FinishedItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class FinishedListActivity extends ListActivity {
    final static String PUT_BACK = "Put Back";
    final static String CANCEL = "Cancel";

    private FinishedItemAdapter adapter;
    private int selectedPosition;

    private List<Task> tasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_list);

        adapter = new FinishedItemAdapter(this);
        tasks = getDatabaseHelper().getAllFinishedTasks();
        adapter.addAll(tasks);

        setListAdapter(adapter);
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == getListView().getId()) {
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            selectedPosition = adapterContextMenuInfo.position;
            menu.setHeaderTitle("Select An Action");
            menu.add(PUT_BACK);
            menu.add(CANCEL);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(PUT_BACK)) {
            Task task = (Task) getListAdapter().getItem(selectedPosition);
            task.setIsFinished(false);
            if (getDatabaseHelper().updateTask(task) == 1) {
                tasks.remove(selectedPosition);
                adapter.clear();
                adapter.addAll(tasks);
            }
        }
        return true;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DatabaseHelper getDatabaseHelper() {
        return ((BaseApplication) getApplication()).getDatabaseHelper();
    }
}
