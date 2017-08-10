package com.wahyuade.kuisioner.model;

/**
 * Created by wahyuade on 08/08/17.
 */

public class KebutuhanModel {

    private String name;
    private boolean checked;

    public KebutuhanModel(String name) {
        this.name = name;
        this.checked = false;
    }
    public KebutuhanModel(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}