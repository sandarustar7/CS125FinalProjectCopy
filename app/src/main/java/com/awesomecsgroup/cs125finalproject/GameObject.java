package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObject {
    Bitmap image;

    abstract void draw(Canvas canvas);

    abstract void update(int x, int y);

    GameObject(Bitmap image) {
        this.image = image;
    }
}
