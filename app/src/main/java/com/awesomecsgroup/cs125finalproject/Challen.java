package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Challen extends GameObject {
    //Class for Geoff, scales image down and contains position information. Geoff is represented by a rectangular hitbox.
    //Challen is pretty straight forward

    //Scales bitmap down to proper size
    private int imageScale = 15;
    //Place challen at the bottom center of the screen
    private int centerX = GameView.WIDTH_PX/2;
    private int centerY = GameView.HEIGHT_PX - (image.getHeight() / (imageScale * 2));

    //Position information
    private int left = centerX - (image.getWidth() / (imageScale * 2));
    private int right =  centerX + (image.getWidth() / (imageScale * 2));
    private int up = centerY - (image.getHeight() / (imageScale * 2));
    private int down = centerY + (image.getHeight() / (imageScale * 2));

    //Hitbox for checking collisions
    private Rect hitbox = new Rect(left, up, right , down);

    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, down), null);
    }

    //No update required, challen does not move.
    @Override
    void update(int x, int y) {

    }
    Challen(Bitmap image) {
        super(image);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    //Return hitbox object if requested.
    public Rect getHitbox() {
        return hitbox;
    }
}
