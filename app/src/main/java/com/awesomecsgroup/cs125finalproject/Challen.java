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
        x = canvas.getHeight()/2 - image.getWidth()/(2*scale);
        y++;
        canvas.drawBitmap(image, new Rect(0,0,image.getWidth(),image.getHeight()), new Rect(y,x,y + image.getWidth()/scale, x + image.getHeight()/scale), null);
    }

    @Override
    void update() {

    }
    Challen(Bitmap image) {
        super(null);
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        this.image = Bitmap.createBitmap(image, 0, 0, image.getWidth(),image.getHeight(), matrix, true);
    }
}
