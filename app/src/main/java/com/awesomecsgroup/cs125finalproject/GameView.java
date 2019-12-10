package com.awesomecsgroup.cs125finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

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
    GameActivity context;
    List<LetterA> letterAs = new ArrayList<>();
    private int lives = 3;
    private int score = 0;
    private boolean launched = false;
    private int speed;
    private int numberEnemies;

    public GameView(Context ctx) {
        super(ctx);
        context =(GameActivity) ctx;
        getHolder().addCallback(this);
        setFocusable(true);
        speed = ((GameActivity) ctx).returnIntent().getIntExtra("speed", 5);
        numberEnemies = ((GameActivity) ctx).returnIntent().getIntExtra("enemies", 3);
    }

    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas != null) {
            background.draw(canvas);
            challen.draw(canvas);
            letterAs.forEach(square -> square.draw(canvas));
        }
    }

    public void update() {
        letterAs.stream().filter(square -> square.collision(challen.getHitbox())).forEach(letter -> {letter.resetPosition(); lives--;});
        letterAs.forEach(square -> square.update(challen.getCenterX(), challen.getCenterY(), speed));
        if (lives <= 0 && !launched) {
            launched = true;
            Intent intent = new Intent(context, GameOverActivity.class);
            intent.putExtra("score", score);
            context.startActivity(intent);
            context.finish();
        }
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
        for (int i = 0; i < numberEnemies; i++) {
            letterAs.add(new LetterA(BitmapFactory.decodeResource(getResources(),R.drawable.a_grade)));
        }
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.foellinger_auditorium_front));
        challen = new Challen(BitmapFactory.decodeResource(getResources(),R.drawable.angry_challen));

        thread.setRunning(true);
        thread.start();

        Log.d("GAME", "thread started!");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        letterAs.clear();
        score = 0;
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
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
            letterAs.stream().filter(s -> s.isTapped(x,y)).forEach(s -> {s.resetPosition(); score++;});

            /*letterAs.forEach(square -> {
                if (square.isTapped(x, y)) {
                    square.paint.setColor(Color.rgb(0,0,250));
                }
            });*/
            Log.d("GameView", x + ", " + y);
        }
        return super.onTouchEvent(event);
    }

    public int getScore() {
        return score;
    }

}
