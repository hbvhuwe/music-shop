package com.hbvhuwe.musicshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Disk")
public class Disk implements Serializable, Model {

    @Id
    @Column(name="DiskID")
    private int diskID;

    @Column(name="Amount")
    private int amount;

    @Column(name="Name")
    private String name;

    @Column(name="PresentDate")
    private String presentDate;

    @Column(name="Price")
    private double price;

    @Column(name = "GroupID")
    private int groupId;

    public Disk() {
    }

    public int getDiskId() {
        return this.diskID;
    }

    public void setDiskId(int diskID) {
        this.diskID = diskID;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getId() {
        return diskID;
    }

    @Override
    public void setId(int id) {
        this.diskID = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}