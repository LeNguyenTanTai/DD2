package com.example.project_android_version_3.Data;

public class Song  {

    private String szName;
    private String nFile;
    private int nImage;

    public Song(String szName, String nFile, int nImage) {
        this.szName = szName;
        this.nFile = nFile;
        this.nImage = nImage;
    }

    public String getSzName() {
        return szName;
    }

    public void setSzName(String szName) {
        this.szName = szName;
    }

    public String getnFile() {
        return nFile;
    }

    public void setnFile(String nFile) {
        this.nFile = nFile;
    }

    public int getnImage() {
        return nImage;
    }

    public void setnImage(int nImage) {
        this.nImage = nImage;
    }




}
