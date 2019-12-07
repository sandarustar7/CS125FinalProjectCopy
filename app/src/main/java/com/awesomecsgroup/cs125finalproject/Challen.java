package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Challen extends GameObject {

    int centerXDP = 500;
    int centerYDP = 500;
    int scale = 10;
    int left = centerXDP - (image.getWidth() / (scale * 2));
    int right = centerXDP + (image.getWidth() / (scale * 2));
    int up = centerYDP - (image.getHeight() / (scale * 2));
    int down = centerYDP + (image.getHeight() / (scale * 2));

    @Override
    void draw(Canvas canvas) {
        //canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(left, up, right, down), null);
    }

    @Override
    void update() {
        /*
        left = centerX - (image.getWidth() / (scale * 2));
        right = centerX + (image.getWidth() / (scale * 2));
        up = centerY - (image.getHeight() / (scale * 2));
        down = centerY + (image.getHeight() / (scale * 2));

         */
    }
    Challen(Bitmap image) {
        super(image);
    }
}
