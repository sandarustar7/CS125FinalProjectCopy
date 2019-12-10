package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends GameObject {
    //The background is the background image that displays behind everything. It should always be drawn first.
    // It needs no update implementation as it does not move
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(0,0,canvas.getWidth(), canvas.getHeight()), null);
    }

    @Override
    void update(int x, int y) {

    }
    Background(Bitmap image) {
        super(image);
    }
}
