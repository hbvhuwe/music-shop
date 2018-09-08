package com.hbvhuwe.musicshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Composition")
public class Composition implements Serializable, Model {
    @Id
    @Column(name="CompositionID")
    private int compositionId;

    @Column(name="Duration")
    private String duration;

    @Column(name="Name")
    private String name;

    @Column(name="PresentDate")
    private String presentDate;

    @Column(name = "DiskID")
    private int diskId;

    public Composition() {

    }

    public int getCompositionId() {
        return this.compositionId;
    }

    public void setCompositionId(int compositionID) {
        this.compositionId = compositionID;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentDate() {
        return this.presentDate;
    }

    public void setPresentDate(String presentDate) {
        this.presentDate = presentDate;
    }

    @Override
    public int getId() {
        return compositionId;
    }

    @Override
    public void setId(int id) {
        this.compositionId = id;
    }

    public int getDiskId() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }
}
