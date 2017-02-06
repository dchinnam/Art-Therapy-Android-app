package com.example.deepank.art_therapy;


import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class MyIntentService extends IntentService {



    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("intent", "service intent has started");
        MediaPlayer mp = MediaPlayer.create(MyIntentService.this, R.raw.eraser);
        mp.start();

    }
}
