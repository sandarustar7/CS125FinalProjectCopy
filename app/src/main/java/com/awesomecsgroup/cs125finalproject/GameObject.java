package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObject {
    //Abstract class that contains information most GameObjects need: an image, an update function, and a draw function.
    //Problem: Classes can have different update implementations, and I don't know how to support different method signatures.
    Bitmap image;

    abstract void draw(Canvas canvas);

    abstract void update(int x, int y);

    GameObject(Bitmap image) {
        this.image = image;
    }
}
