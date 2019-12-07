package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Square extends GameObject {

    int centerX = 700;
    int centerY = 700;
    int height = 100;
    int width = 200;

    int left = centerX - width/2;
    int up = centerY - height/2;
    int right = centerX + width/2;
    int bottom = centerY + height/2;

    Rect rect = new Rect(left, up, right, bottom);
    Paint paint = new Paint();

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public void update() {

    }

    public void update(int centerX, int centerY) {
        this.centerY = centerY;
        this.centerX = centerX;
        rect.set(centerX - width/2,centerY - height/2,centerX + width/2,bottom = centerY + height/2);
    }

    Square(Bitmap image) {
        super(null);
        paint.setColor(Color.rgb(250,0,0));
    }

    public void setCenterX(float centerX) {
        this.centerX = (int)centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = (int)centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}
