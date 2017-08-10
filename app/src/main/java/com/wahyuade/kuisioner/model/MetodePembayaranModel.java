package com.wahyuade.kuisioner.model;

/**
 * Created by wahyuade on 08/08/17.
 */

public class MetodePembayaranModel {
    private String nama_metode;
    private boolean status;

    public MetodePembayaranModel(String nama_metode) {
        this.nama_metode = nama_metode;
        this.status = false;
    }

    public String getNama_metode() {
        return nama_metode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
