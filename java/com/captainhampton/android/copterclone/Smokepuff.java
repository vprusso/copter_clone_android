package com.captainhampton.android.copterclone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Smokepuff extends GameObject {

    public Smokepuff(int x, int y) {
        super.x = x;
        super.y = y;
    }

    public void update() {
        x -= 10;
    }

    public void draw(Canvas canvas) {
        final int radius = 5;

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x-radius, y-radius, radius, paint);
        canvas.drawCircle(x-radius+2, y-radius-2,radius,paint);
        canvas.drawCircle(x-radius+4, y-radius+1, radius, paint);
    }
}