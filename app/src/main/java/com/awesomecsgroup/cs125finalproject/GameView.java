package com.awesomecsgroup.cs125finalproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//Source: https://www.androidauthority.com/android-game-java-785331/
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    final float SCALE = getResources().getDisplayMetrics().density;
    static int HEIGHT_PX;
    static int WIDTH_PX;

    static RenderThread thread;
    Background background;
    Challen challen;
    List<Square> squares = new ArrayList<>();

    public GameView(Context ctx) {
        super(ctx);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas != null) {
            background.draw(canvas);
            challen.draw(canvas);
            squares.forEach(square -> square.draw(canvas));
        }
    }


    public void update() {
        squares.forEach(square -> square.update(challen.getCenterX(), challen.getCenterY()));
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new RenderThread(getHolder(), this);
        Rect dimensions = holder.getSurfaceFrame();
        WIDTH_PX = dimensions.right;
        HEIGHT_PX = dimensions.bottom;
        squares.add(new Square(BitmapFactory.decodeResource(getResources(),R.drawable.a_grade)));
        squares.add(new Square(BitmapFactory.decodeResource(getResources(),R.drawable.a_grade)));
        squares.add(new Square(BitmapFactory.decodeResource(getResources(),R.drawable.a_grade)));
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.foellinger_auditorium_front));
        challen = new Challen(BitmapFactory.decodeResource(getResources(),R.drawable.angry_challen));

        thread.setRunning(true);
        thread.start();

        Log.d("GAME", "thread started!");
        /*
        Path squarePath = new Path();
        squarePath.moveTo(square.getCenterX(), square.getCenterY());
        squarePath.lineTo(challen.getCenterX(),challen.getCenterY());
        pathAnimator = ObjectAnimator.ofFloat(square, "centerX", "centerY", squarePath);
        pathAnimator.setDuration(1000);
        pathAnimator.setRepeatCount(100);
        pathAnimator.start();*/
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                /*new Thread() {
                    @Override
                    public void run() {
                        while(!);
                        thread.setRunning(false);
                    }
                }.run(); */
                thread.join();
                retry = false;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static int DptoPx(float dp, float scale) {
      return (int) (dp * scale);
    }

    public static void setThreadRunning(boolean running) {
        thread.setRunning(running);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            squares.stream().filter(s -> s.isTapped(x,y)).forEach(s -> s.resetPosition());

            /*squares.forEach(square -> {
                if (square.isTapped(x, y)) {
                    square.paint.setColor(Color.rgb(0,0,250));
                }
            });*/
            Log.d("GameView", x + ", " + y);
        }
        return super.onTouchEvent(event);
    }
}
