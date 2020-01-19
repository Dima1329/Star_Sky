package com.example.myapplication;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    MyThread myThread;
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread(getHolder());
        myThread.setRunning(true);
        myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean r= true;
        myThread.setRunning(false);
        while (r){
            try {
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = false;
        }
    }
}