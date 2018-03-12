package stu.tpp.musicshop.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group implements Model {
    private IntegerProperty groupId;
    private StringProperty name;
    private StringProperty musician;
    private StringProperty style;

    public Group() {
        this.groupId = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.musician = new SimpleStringProperty();
        this.style = new SimpleStringProperty();
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

    public String getMusician() {
        return musician.get();
    }

    public StringProperty musicianProperty() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician.set(musician);
    }

    public String getStyle() {
        return style.get();
    }

    public StringProperty styleProperty() {
        return style;
    }

    public void setStyle(String style) {
        this.style.set(style);
    }

    @Override
    public int getId() {
        return getGroupId();
    }

    @Override
    public void setId(int id) {
        setGroupId(id);
    }
}
