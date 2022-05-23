package com.example.myapplication;

import java.util.ArrayList;

public class List {
    public static ArrayList<List> listArrayList = new ArrayList<>();
    private int id;
    private String tanggal;
    private String matkul;
    private String jamulai;
    private String jamakhir;
    private String dosen;
    private String asisten;
    private String lab;
    private String jurusan;

    public List(int id, String tanggal, String matkul, String jamulai, String jamakhir, String dosen, String asisten, String lab, String jurusan) {
        this.id = id;
        this.tanggal = tanggal;
        this.matkul = matkul;
        this.jamulai = jamulai;
        this.jamakhir = jamakhir;
        this.dosen = dosen;
        this.asisten = asisten;
        this.lab = lab;
        this.jurusan = jurusan;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getJamulai() {
        return jamulai;
    }

    public void setJamulai(String jamulai) {
        this.jamulai = jamulai;
    }

    public String getJamakhir() {
        return jamakhir;
    }

    public void setJamakhir(String jamakhir) {
        this.jamakhir = jamakhir;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getAsisten() {
        return asisten;
    }

    public void setAsisten(String asisten) {
        this.asisten = asisten;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
