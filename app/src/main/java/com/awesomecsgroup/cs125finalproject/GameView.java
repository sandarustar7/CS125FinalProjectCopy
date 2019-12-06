package com.awesomecsgroup.cs125finalproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Source: https://www.androidauthority.com/android-game-java-785331/
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    RenderThread thread;
    Square square;
    Background background;
    Paint paint;
    ValueAnimator animator = ValueAnimator.ofInt(0, 100);
    int value;

    public GameView(Context ctx) {
        super(ctx);
        getHolder().addCallback(this);
        thread = new RenderThread(getHolder(), this);
        setFocusable(true);
    }

    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas != null) {
            Log.d("GAME", "true!");
            square.draw(canvas);
        }
    }

    public void update() {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        square = new Square(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.background));
        thread.setRunning(true);
        thread.start();
        Log.d("GAME", "thread started!");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
