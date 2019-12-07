package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Challen extends GameObject {

    int centerX = 500;
    int centerY = 500;
    int imageScale = 20;
    int left = centerX - (image.getWidth() / (imageScale * 2));
    int right =  centerX + (image.getWidth() / (imageScale * 2));
    int up = centerY - (image.getHeight() / (imageScale * 2));
    int down = centerY + (image.getHeight() / (imageScale * 2));

    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, down), null);
    }

    @Override
    void update() {
        left = centerX - (image.getWidth() / (imageScale * 2));
        right =  centerX + (image.getWidth() / (imageScale * 2));
        up = centerY - (image.getHeight() / (imageScale * 2));
        down = centerY + (image.getHeight() / (imageScale * 2));
    }
    Challen(Bitmap image) {
        super(image);
    }
}
