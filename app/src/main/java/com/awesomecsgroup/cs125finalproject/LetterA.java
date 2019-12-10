package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class LetterA extends GameObject {

    //These are the variables representing the position, radius, and scaling of the letterA.
    //We try to process LetterA as an oval to allow for a reasonable hitbox
    //imageScale is because the image is much larger than we want it to be
    int centerX;
    int centerY;
    float radiusX;
    float radiusY;
    private int imageScale = 15;
    int left;
    int up;
    int right;
    int bottom;

    //Rect rect = new Rect(left, up, right, bottom);

    //draw function adds the bitmap for letterA to the passed canvas, in the location it is at. We don't want to apply coloring, so paint is null
    public void draw(Canvas canvas) {
        //First argument represents the part of the image we want to draw (we want to draw the whole image, so we set it to be those dimensions)
        //Second argument represents where on the canvas we want to draw this image
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, bottom), null);
    }

    public void update(int challenX, int challenY, int speed) {
        //Letter A's try to home in on Challen's position. We do this by calculating the slope of the line connecting the two and moving with that slope.
        double theta = Math.atan2((challenY-centerY),(challenX-centerX));

        //Speed sanitation just in case. After that, move centerX and centerY to correct locations.
        centerX += (speed >= 5 && speed <= 50 ? speed : 5)*Math.cos(theta);
        centerY += (speed >= 5 && speed <= 50 ? speed : 5)*Math.sin(theta);

        //Use the calculated centerX and centerY to update the edge locations
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        bottom = centerY + (image.getHeight() / (imageScale * 2));
    }

    //This update is because the abstract class I designed was poorly implemented
    public void update(int x, int y) {

    }

    //Constructor takes image and passes it to the superclass, then sets itself to a random position on the 3 farthest edges of the screen
    public LetterA(Bitmap image) {
        super(image);
        //Why not lets make our random really random
        Random rng = new Random(new Random().nextLong());
        centerY = 0;
        centerX = 0;
        //Lets decide which edge to place the A. After that, lets decide where on the edge to place it.
        switch (rng.nextInt(3)) {
            case 0:
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
            case 1:
                centerX = rng.nextInt(GameView.WIDTH_PX);
                break;
            case 2:
                centerX = GameView.WIDTH_PX;
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
        }
        //Lets initialize the position values based on where we placed the A
        radiusX = image.getWidth() / (imageScale*2);
        radiusY = image.getHeight() / (imageScale * 2);
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        bottom = centerY + (image.getHeight() / (imageScale * 2));

    }

    public boolean isTapped(float x, float y) {
        //Lets figure out if we were tapped. Tap location should be within an oval representing the A's hitbox
        return ((centerX-x)*(centerX-x))/(radiusX*radiusX) + ((centerY-y)*(centerY-y))/(radiusY*radiusY) <= 1;
        //return Math.sqrt((this.centerX - x)*(this.centerX - x)+(this.centerY - y)*(this.centerY - y)) < radius;
    }

    public void resetPosition() {
        //Lets regenerate a new location for the A. Follows same logic as constructor.
        Random rng = new Random(new Random().nextLong());
        centerY = 0;
        centerX = 0;
        switch (rng.nextInt(3)) {
            case 0:
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
            case 1:
                centerX = rng.nextInt(GameView.WIDTH_PX);
                break;
            case 2:
                centerX = GameView.WIDTH_PX;
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
        }
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        bottom = centerY + (image.getHeight() / (imageScale * 2));
    }

    public void setCenterX(float centerX) {
        this.centerX = (int)centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = (int)centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean collision(Rect rect) {
        //Let's figure out if we have collided with Geoff. Only need to analyze the pixels on the edge closes to Geoff.
        double theta = Math.atan2(rect.centerY() - centerY, rect.centerX() - centerX);
        return rect.contains(centerX + (int) (radiusX*Math.cos(theta)), centerY + (int) (radiusY*Math.sin(theta)));
    }
}
