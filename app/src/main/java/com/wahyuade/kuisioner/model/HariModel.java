package com.wahyuade.kuisioner.model;

/**
 * Created by wahyuade on 08/08/17.
 */

public class HariModel {
    private String hari;
    private boolean checked;

    public HariModel(String hari) {
        this.hari = hari;
        this.checked = false;
    }

    public String getHari() {
        return hari;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
