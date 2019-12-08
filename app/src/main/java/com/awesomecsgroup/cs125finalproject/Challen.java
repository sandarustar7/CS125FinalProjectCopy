package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Challen extends GameObject {

    private int imageScale = 15;
    private int centerX = GameView.WIDTH_PX/2;
    private int centerY = GameView.HEIGHT_PX - (image.getHeight() / (imageScale * 2));

    private int left = centerX - (image.getWidth() / (imageScale * 2));
    private int right =  centerX + (image.getWidth() / (imageScale * 2));
    private int up = centerY - (image.getHeight() / (imageScale * 2));
    private int down = centerY + (image.getHeight() / (imageScale * 2));

    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, down), null);
    }

    @Override
    void update(int x, int y) {
        /*
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        down = centerY + (image.getHeight() / (imageScale * 2));

         */
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
}
