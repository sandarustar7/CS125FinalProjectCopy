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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Source: https://www.androidauthority.com/android-game-java-785331/
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    final float SCALE = getResources().getDisplayMetrics().density;
    static int HEIGHT_PX;
    static int WIDTH_PX;

    RenderThread thread;
    Square square;
    Background background;
    Challen challen;

    ValueAnimator pathAnimator;

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
            square.draw(canvas);
        }
    }

    int testX = 100;
    int testY = 100;
    public void update() {
        testX++;
        testY++;

        //int slope = (challenY-squareY)/(challenX-squareX);

        square.update(testX, testY);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Rect dimensions = holder.getSurfaceFrame();
        WIDTH_PX = dimensions.right;
        HEIGHT_PX = dimensions.bottom;

        square = new Square(null);
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.foellinger_auditorium_front));
        challen = new Challen(BitmapFactory.decodeResource(getResources(),R.drawable.angry_challen));

        thread.setRunning(true);
        thread.start();

        Log.d("GAME", "thread started!");
        /*Path squarePath = new Path();
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
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public static int DptoPx(float dp, float scale) {
      return (int) (dp * scale);
    }

}
