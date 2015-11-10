package com.oursky.todo_android.content.model;

/**
 * Created by yuyauchun on 13/3/15.
 */

public class Task {
    private int id;
    private String description;
    private String dueAt;
    private boolean finished = false;

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, String dueAt) {
        this.description = description;
        this.dueAt = dueAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }
}
