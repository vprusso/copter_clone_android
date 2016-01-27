package com.captainhampton.android.copterclone;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Missile extends GameObject {
    public static final int MAX_MISSILE_SPEED = 40;

    private int missile_speed;
    private Animation animation = new Animation();

    public Missile(Bitmap res, int x, int y, int w, int h, int score, int numFrames) {
        super.x = x;
        super.y = y;
        width = w;
        height = h;

        // Missile speed increases as score increases.
        Random rand = new Random();
        missile_speed = 7 + (int)(rand.nextDouble()*score/30);

        if(missile_speed > MAX_MISSILE_SPEED)
            missile_speed = MAX_MISSILE_SPEED;

        Bitmap sprite_sheet;
        Bitmap[] image = new Bitmap[numFrames];

        sprite_sheet = res;

        for(int i = 0; i<image.length; i++)
            image[i] = Bitmap.createBitmap(sprite_sheet, 0, i*height, width, height);

        animation.setFrames(image);
        animation.setDelay(100 - missile_speed);
    }

    public void update() {
        x -= missile_speed;
        animation.update();
    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(animation.getImage(),x,y,null);
        } catch(Exception e){
            System.err.println("Missile:Draw() " + e.getMessage());
        }
    }

    @Override
    public int getWidth() {
        //offset slightly for more realistic collision detection
        return width-10;
    }
}