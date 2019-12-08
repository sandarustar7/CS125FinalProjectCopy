package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Square extends GameObject {

    int centerX;
    int centerY;
    int height = 100;
    int width = 200;
    float radius = 100;

    int left;
    int up;
    int right;
    int bottom;

    Rect rect = new Rect(left, up, right, bottom);
    Paint paint = new Paint();

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public void update(int challenX, int challenY) {
        double theta = Math.atan2((challenY-centerY),(challenX-centerX));

        centerX += 5*Math.cos(theta);
        centerY += 5*Math.sin(theta);

        rect.set(centerX - width/2,centerY - height/2,centerX + width/2,bottom = centerY + height/2);
    }

    public Square(Bitmap image) {
        super(null);
        Random rng = new Random(new Random().nextLong());
        centerY = 0;
        centerX = 0;
        switch (rng.nextInt(3)) {
            case 0:
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
            case 1:
                centerX = rng.nextInt(GameView.WIDTH_PX);
                break;
            case 2:
                centerX = GameView.WIDTH_PX;
                centerY = rng.nextInt(GameView.HEIGHT_PX);
                break;
        }

        paint.setColor(Color.rgb(250,0,0));

        left = centerX - width/2;
        up = centerY - height/2;
        right = centerX + width/2;
        bottom = centerY + height/2;

    }

    public boolean isTapped(float x, float y) {
        return Math.sqrt((this.centerX - x)*(this.centerX - x)+(this.centerY - y)*(this.centerY - y)) < radius;
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
