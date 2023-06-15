package com.adil.harrypotter.music;
import android.app.Service;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import com.adil.harrypotter.R;


public class Muzik extends Service {
    MediaPlayer arkaplanMzk;

    @Override
    public void onCreate() {
        super.onCreate();
        arkaplanMzk = MediaPlayer.create(getApplicationContext(), R.raw.arkaplan);
        arkaplanMzk.setLooping(true);
        arkaplanMzk.setVolume(100f, 100f);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        arkaplanMzk.start();
    }

    @Override
    public void onDestroy() {
        arkaplanMzk.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
