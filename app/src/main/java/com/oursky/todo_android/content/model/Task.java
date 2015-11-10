package com.oursky.todo_android.content.model;

/**
 * Created by yuyauchun on 13/3/15.
 */

public class Task {
    private int id;
    private String task;
    private String dueAt;
    private boolean finished = false;

    public Task() {}

    public Task(String task) {
        this.task = task;
    }

    public Task(String task, String dueAt) {
        this.task = task;
        this.dueAt = dueAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public String getDueAt() {
        return dueAt;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setIsFinished(boolean finished) {
        this.finished = finished;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }
}
