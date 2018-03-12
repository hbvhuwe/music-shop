package stu.tpp.musicshop.model;

import javafx.beans.property.*;

public class Disk implements Model {
    private IntegerProperty diskId;
    private IntegerProperty groupId;
    private StringProperty name;
    private StringProperty presentDate;
    private IntegerProperty amount;
    private DoubleProperty price;

    public Disk() {
        this.diskId = new SimpleIntegerProperty();
        this.groupId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.presentDate = new SimpleStringProperty();
        this.amount = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
    }

    public int getDiskId() {
        return diskId.get();
    }

    public IntegerProperty diskIdProperty() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId.set(diskId);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public IntegerProperty groupIdProperty() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPresentDate() {
        return presentDate.get();
    }

    public StringProperty presentDateProperty() {
        return presentDate;
    }

    public void setPresentDate(String presentDate) {
        this.presentDate.set(presentDate);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    @Override
    public int getId() {
        return getDiskId();
    }

    @Override
    public void setId(int id) {
        setDiskId(id);
    }
}
