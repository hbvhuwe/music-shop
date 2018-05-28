package com.hbvhuwe.musicshop.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Groups")
public class Group implements Serializable, Model {

    @Id
    @Column(name="GroupID")
    private int groupId;

    @Column(name="Musician")
    private String musician;

    @Column(name="Name")
    private String name;

    @Column(name="Style")
    private String style;

    public Group() {
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMusician() {
        return this.musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public int getId() {
        return this.groupId;
    }

    @Override
    public void setId(int id) {
        this.groupId = id;
    }
}
