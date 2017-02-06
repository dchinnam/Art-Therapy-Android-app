package com.example.deepank.art_therapy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    BackgroundSound mBackgroundSound = new BackgroundSound();
    Thread mythread;
    Intent intent;
    NotificationCompat.Builder notification;
    private static final int uniqueID=15661;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TouchEventView t=new TouchEventView(getApplicationContext(),null);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //sending broadcast
        Intent intent1 = new Intent();
        intent1.setAction("com.myaction");
        sendBroadcast(intent1);

        //notificaiton
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        intent=new Intent(this,MyIntentService.class);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {

                startService(intent);
                Toast.makeText(MainActivity.this, "Erased when shook!", Toast.LENGTH_SHORT).show();
                TouchEventView touchView = (TouchEventView) findViewById(R.id.view);
                touchView.clear();



            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.eraser);

            player.setVolume(100,100);
            player.start();
            return null;
        }
    }
    public void send(View view){
        Intent intent1 = new Intent();
        intent1.setAction("com.myaction");
        sendBroadcast(intent1);
    }

}

