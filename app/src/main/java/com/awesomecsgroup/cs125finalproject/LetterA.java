package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class LetterA extends GameObject {

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

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, bottom), null);
    }

    public void update(int challenX, int challenY) {
        double theta = Math.atan2((challenY-centerY),(challenX-centerX));

        centerX += 5*Math.cos(theta);
        centerY += 5*Math.sin(theta);

        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        bottom = centerY + (image.getHeight() / (imageScale * 2));
    }

    public LetterA(Bitmap image) {
        super(image);
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
        //radius = (image.getWidth())/(imageScale * 2);
        radiusX = image.getWidth() / (imageScale*2);
        radiusY = image.getHeight() / (imageScale * 2);
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        bottom = centerY + (image.getHeight() / (imageScale * 2));

    }

    public boolean isTapped(float x, float y) {
        return ((centerX-x)*(centerX-x))/(radiusX*radiusX) + ((centerY-y)*(centerY-y))/(radiusY*radiusY) <= 1;
        //return Math.sqrt((this.centerX - x)*(this.centerX - x)+(this.centerY - y)*(this.centerY - y)) < radius;
    }

    public void resetPosition() {
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
        double theta = Math.atan2(rect.centerY() - centerY, rect.centerX() - centerX);
        return rect.contains(centerX + (int) (radiusX*Math.cos(theta)), centerY + (int) (radiusY*Math.sin(theta)));
    }
}
