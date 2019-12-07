package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Background extends GameObject {
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(0,0,canvas.getWidth(), canvas.getHeight()), null);
    }

    @Override
    void update() {

    }
    Background(Bitmap image) {
        super(image);
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        this.image = Bitmap.createBitmap(image, 0, 0, image.getWidth(),image.getHeight(), matrix, true);
    }
}
