package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Challen extends GameObject {
    int x = 0;
    int y = 0;
    int scale = 25;
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(x,y,x + image.getWidth()/scale, y + image.getHeight()/scale), null);
    }

    @Override
    void update() {

    }
    Challen(Bitmap image) {
        super(image);
    }
}
