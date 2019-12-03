package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameObject {
    private Bitmap image;

    public GameObject(Bitmap image) {

    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 100, 100, null);
    }
}
