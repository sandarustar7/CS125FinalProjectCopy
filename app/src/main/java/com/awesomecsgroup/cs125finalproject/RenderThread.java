package com.awesomecsgroup.cs125finalproject;

//Render thread allows us to separate the game code from the main thread
//Android won't be happy if we overload the main thread

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class RenderThread extends Thread {
    //Surface holder is a wrapper for the canvas, handles locking and stuff.
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    //Are we running the game?
    private boolean running;
    public static Canvas canvas;
    //The target frames we want to reach.
    private int targetFPS = 60;
    private double averageFPS;

    public RenderThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        //Frame calculation variables, use the nanoTime to figure out the proper time to create a frame
            long startTime;
            long timeMillis;
            long waitTime;
            long totalTime = 0;
            int frameCount =0;
            long targetTime = 1000/targetFPS;
            //Only run when we are supposed to run, stop when false.
            while(running) {
                startTime = System.nanoTime();
                canvas = null;
                //Lets try doing stuff with the canvas, shouldn't be locked but it could be
                try {
                    canvas = this.surfaceHolder.lockCanvas(); //We are trying to draw on the canvas, let's lock it so other stuff can't try to draw on it.
                    synchronized (surfaceHolder) { //Prevents thread interference, only one thread access surfaceHolder.
                        //Update and draw the frame
                        this.gameView.update();
                        this.gameView.draw(canvas);
                    }
                } catch (Exception e) {
                }
                finally{
                    if(canvas!=null)
                    {
                        try {
                            //Lets display the frame, and unlock the canvas
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                        catch(Exception e){e.printStackTrace();}
                    }
                }
                //Frame pacing code. Lets wait until we should draw the frame
                timeMillis = (System.nanoTime() - startTime) / 1000000;
                waitTime = targetTime-timeMillis;
                try{
                    this.sleep(waitTime);
                }catch(Exception e){}
                totalTime += System.nanoTime()-startTime;
                frameCount++;
                if(frameCount == targetFPS)
                {
                    averageFPS = 1000/((totalTime/frameCount)/1000000);
                    frameCount =0;
                    totalTime = 0;
                    System.out.println(averageFPS);
                }
            }
        }
        //Allows other classes to change the running status of the thread.
    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

}
