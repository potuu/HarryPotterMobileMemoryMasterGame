package com.adil.harrypotter;
import android.content.Context;
import android.media.MediaPlayer;
public class OyunMuzikleri {
    MediaPlayer mzk;
    public void bitince(Context ct1){
        this.mzk = MediaPlayer.create(ct1, R.raw.yenilgi);
        this.mzk.start();
    }
    public void eslesince(Context ct2){
        this.mzk=MediaPlayer.create(ct2, R.raw.esbulma);
        this.mzk.start();
    }
    public void yenince(Context ct3){
        this.mzk.stop();
        this.mzk=MediaPlayer.create(ct3, R.raw.yenme);
        this.mzk.start();
    }
}
