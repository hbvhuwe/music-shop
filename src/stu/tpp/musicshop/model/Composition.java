package stu.tpp.musicshop.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Composition {
    private IntegerProperty compositionId;
    private IntegerProperty diskId;
    private StringProperty name;
    private StringProperty presentDate;
    private StringProperty duration;

    public Composition() {
        this.compositionId = new SimpleIntegerProperty();
        this.diskId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.presentDate = new SimpleStringProperty();
        this.duration = new SimpleStringProperty();
    }

    public int getCompositionId() {
        return compositionId.get();
    }

    public IntegerProperty compositionIdProperty() {
        return compositionId;
    }

    public void setCompositionId(int compositionId) {
        this.compositionId.set(compositionId);
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

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }
}
