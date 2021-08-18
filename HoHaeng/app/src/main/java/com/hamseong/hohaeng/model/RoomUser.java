package com.hamseong.hohaeng.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomUser {
    @PrimaryKey(autoGenerate = true)
    private int count;
    private String name;
    private String id;
    private String pasword;

    public RoomUser(String name, String id, String pasword) {
        this.name = name;
        this.id = id;
        this.pasword = pasword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
