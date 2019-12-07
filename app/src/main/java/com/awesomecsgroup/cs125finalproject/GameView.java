package com.awesomecsgroup.cs125finalproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Source: https://www.androidauthority.com/android-game-java-785331/
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    final float SCALE = getResources().getDisplayMetrics().density;
    static float HEIGHT_PX;
    static float WIDTH_PX;
    static float HEIGHT_DP;
    static float WIDTH_DP;

    RenderThread thread;
    Square square;
    Background background;
    Challen challen;

    public GameView(Context ctx) {
        super(ctx);
        getHolder().addCallback(this);
        thread = new RenderThread(getHolder(), this);
        setFocusable(true);
    }

    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas != null) {
            background.draw(canvas);
            challen.draw(canvas);
        }
    }

    public void update() {
        challen.update();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Rect dimensions = holder.getSurfaceFrame();
        HEIGHT_PX = dimensions.bottom;
        WIDTH_PX = dimensions.right;
        square = new Square(null);
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.foellinger_auditorium_front));
        challen = new Challen(BitmapFactory.decodeResource(getResources(),R.drawable.angry_challen));
        thread.setRunning(true);
        thread.start();
        Log.d("GAME", "thread started!");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public float getSCALE() {
        return SCALE;
    }


}
