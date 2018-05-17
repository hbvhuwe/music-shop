package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.Main;
import com.hbvhuwe.musicshop.model.Model;
import com.hbvhuwe.musicshop.providers.DataProvider;

public abstract class BaseController<T extends Model> implements Controller {
    private Main mainApp;
    protected DataProvider<T> provider;

    @Override
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    abstract void onSelectSingle();

    abstract void onSelectAll();

    abstract void onAdd();

    abstract void onDelete();
}
