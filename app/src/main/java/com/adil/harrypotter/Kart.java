package com.adil.harrypotter;
import android.graphics.Bitmap;

class Kart{
    public int puan;
    public String isim;
    public String ev;
    public Bitmap resim;

    Kart(String i,String e, int p, Bitmap res){
        this.isim = i;
        this.ev = e;
        this.puan = p;
        this.resim = res;
    }

}
