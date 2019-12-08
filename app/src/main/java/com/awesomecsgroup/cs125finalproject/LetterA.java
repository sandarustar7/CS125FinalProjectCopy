package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class LetterA extends GameObject {
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, 100, 100, null);
    }
    void update(int x, int y){}
    LetterA(Bitmap image) {
        super(image);
    }
}
