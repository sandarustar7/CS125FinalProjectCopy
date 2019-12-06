package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square extends GameObject {
    Rect rect = new Rect(100, 100, 400, 400);
    Paint paint = new Paint();
    public void draw(Canvas canvas) {
        canvas.drawRGB(250,250, 250);
        canvas.drawRect(rect, paint);
    }
    Square(Bitmap image) {
        super(image);
        paint.setColor(Color.rgb(250,0,0));
    }
}
