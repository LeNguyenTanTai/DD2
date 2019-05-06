package com.example.project_android_version_3.Data;

public class SongItem  {


    public int getnHinhAnh() {
        return nHinhAnh;
    }

    public SongItem(int nHinhAnh, String szName) {
        this.nHinhAnh = nHinhAnh;
        this.szName = szName;
    }

    public void setnHinhAnh(int nHinhAnh) {
        this.nHinhAnh = nHinhAnh;
    }

    public String getSzName() {
        return szName;
    }

    public void setSzName(String szName) {
        this.szName = szName;
    }

    private int nHinhAnh;
    private String szName;





}
