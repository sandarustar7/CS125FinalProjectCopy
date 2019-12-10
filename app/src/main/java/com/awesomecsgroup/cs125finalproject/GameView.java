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
//GameView is a child of SurfaceView, a view that is essentially just a canvas we can draw on.
//GameView implemets SurfaceHolder.Callback so we can do stuff when the canvas is created or destroyed, such as stopping or starting the renderthread
class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //Height and width of the canvas (not the screen dimension)
    static int HEIGHT_PX;
    static int WIDTH_PX;

    //RenderThread is the thread used for updating and rendering the game
    static RenderThread thread;
    //These are the gameObjects used in the game
    Background background;
    Challen challen;
    List<LetterA> letterAs = new ArrayList<>();

    //The context we are working in, allows us to start new activities and get extras
    GameActivity context;
    //Values for lives and score,
    private int lives = 3;
    private int score = 0;

    //Sometimes because of the adjacent thread, gameOverActivity launch code runs multiple times, this boolean helps prevent that
    private boolean launched = false;

    //values we get from extras
    private int speed;
    private int numberEnemies;

    //Constructor, needs context of the activity.
    public GameView(Context ctx) {
        super(ctx);
        context =(GameActivity) ctx;

        //Adds this to the callback so that the callback functions will be triggered on their events
        getHolder().addCallback(this);
        setFocusable(true);

        //Get values from the extras
        speed = ((GameActivity) ctx).returnIntent().getIntExtra("speed", 5);
        numberEnemies = ((GameActivity) ctx).returnIntent().getIntExtra("enemies", 3);
    }

    //Draw function calls all the draw functions of all visible gameObjects. Runs once each frame
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            background.draw(canvas);
            challen.draw(canvas);
            letterAs.forEach(square -> square.draw(canvas));
        }
    }

    //Update function allows for gameObjects to move and execute game logic. Runs once each frame
    public void update() {
        //Run challen.getHitbox() and filter by result, then subtract lives for each collided object and reset its position.
        letterAs.stream().filter(letter -> letter.collision(challen.getHitbox())).forEach(letter -> {letter.resetPosition(); lives--;});
        //All colliding objects have been processed, allow letters to advance. Letters calculate direction based off Challen's position
        letterAs.forEach(letter -> letter.update(challen.getCenterX(), challen.getCenterY(), speed));
        //If we have no lives, launch the GameOverActivity
        if (lives <= 0 && !launched) {
            launched = true;
            Intent intent = new Intent(context, GameOverActivity.class);
            //Put score, speed, and enemies into intent. Speed and Enemies are added in case the user wants to play again
            intent.putExtra("score", score);
            intent.putExtra("speed", speed);
            intent.putExtra("enemies", numberEnemies);
            //Launch activity and stop this activity
            context.startActivity(intent);
            context.finish();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //We don't expect the surface to change and we don't want to do anything if it does, so we won't do anything here.
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //The canvas is created, we can now start running game code. Create a new thread that will start running all the update and render code.
        thread = new RenderThread(getHolder(), this);
        //get and store dimensions of the canvas for image scaling
        Rect dimensions = holder.getSurfaceFrame();
        WIDTH_PX = dimensions.right;
        HEIGHT_PX = dimensions.bottom;
        //Add the number of enemies to the list, based off what the user requested.
        for (int i = 0; i < numberEnemies; i++) {
            letterAs.add(new LetterA(BitmapFactory.decodeResource(getResources(),R.drawable.a_grade)));
        }
        //Create background and challen objects based on the correct image resource
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.foellinger_auditorium_front));
        challen = new Challen(BitmapFactory.decodeResource(getResources(),R.drawable.angry_challen));

        //Everything is set up, lets start the game
        thread.setRunning(true);
        thread.start();

        Log.d("GAME", "thread started!");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //Most likely, the activity has been paused or stopped by the Android System.
        //Lets reset for when surfaceCreated will be called (which is when the activity is resumed)
        letterAs.clear();
        score = 0;

        //Sometimes the thread doesn't really stop well
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

    //If we need to change the thread status from other classes, we can
    public static void setThreadRunning(boolean running) {
        thread.setRunning(running);
    }

    //Processes touch events on the screen, gets coordinate points, which we give to the letters to process collision.
    //If the user did tap on a letter, reset the letter and increase the score.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            letterAs.stream().filter(s -> s.isTapped(x,y)).forEach(s -> {s.resetPosition(); score++;});
            Log.d("GameView", x + ", " + y);
        }
        return super.onTouchEvent(event);
    }

    //If we need the score for some reason while running
    public int getScore() {
        return score;
    }

}
