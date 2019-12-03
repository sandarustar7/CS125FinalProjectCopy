package com.awesomecsgroup.cs125finalproject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Source: https://www.androidauthority.com/android-game-java-785331/
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    RenderThread thread;
    public GameView(Context ctx) {
        super(ctx);
        getHolder().addCallback(this);
        thread = new RenderThread(getHolder(), this);
        setFocusable(true);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void update() {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
