package com.awesomecsgroup.cs125finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;

public class LetterA extends GameObject {
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(image, 100, 100, null);
    }
    LetterA(Bitmap image) {
        super(image);
    }
}
