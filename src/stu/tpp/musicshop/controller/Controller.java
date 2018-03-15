package stu.tpp.musicshop.controller;

import stu.tpp.musicshop.Main;

public abstract class Controller {
    Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    abstract void selectSingle();

    abstract void selectAll();

    abstract void add();

    abstract void delete();
}
