package com.example.todoprojectv2.model.shared;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todoprojectv2.model.database.DateConverter;

import java.util.Date;

@Entity(tableName = "todo_table")
@TypeConverters(DateConverter.class)
public class ToDoModelEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Priority")
    private int priority;

    @ColumnInfo(name = "Date")
    private Date date;

    @ColumnInfo(name = "Description")
    private String description;

    public ToDoModelEntity(String title, int priority, Date date, String description) {
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
