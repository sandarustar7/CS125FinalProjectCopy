package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends GameObject {
    @Override
    void draw(Canvas canvas) {
        //canvas.drawBitmap(image, new Rect());
    }

    @Override
    void update() {

    }
    Background(Bitmap image) {
        super(image);
    }
}
