package com.wahyuade.kuisioner.model;

/**
 * Created by wahyuade on 08/08/17.
 */

public class ReferensiModel {
    private String nama_pemilik;
    private String alamat;
    private String produk;
    private String no_telp;
    private String nama_ukm;

    public ReferensiModel(String nama_pemilik, String alamat, String produk, String no_telp, String nama_ukm) {
        this.nama_pemilik = nama_pemilik;
        this.alamat = alamat;
        this.produk = produk;
        this.no_telp = no_telp;
        this.nama_ukm = nama_ukm;
    }
    public ReferensiModel(String nama_pemilik, String alamat, String produk, String no_telp) {
        this.nama_pemilik = nama_pemilik;
        this.alamat = alamat;
        this.produk = produk;
        this.no_telp = no_telp;
        this.nama_ukm = "Tanpa Nama";
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getProduk() {
        return produk;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getNama_ukm() {
        return nama_ukm;
    }
}
