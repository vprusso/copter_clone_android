package com.captainhampton.android.copterclone;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends GameObject {

    private int score;
    private int best;

    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames) {

        x = 100;
        y = GamePanel.HEIGHT / 2;
        dy = 0;
        score = 0;
        best = 0;
        height = h;
        width = w;

        Bitmap sprite_sheet;
        Bitmap[] image = new Bitmap[numFrames];
        sprite_sheet = res;

        for (int i = 0; i < image.length; i++)
            image[i] = Bitmap.createBitmap(sprite_sheet, i * width, 0, width, height);

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update() {

        final int top_border_max = 14;
        final int bot_border_min = -14;

        final double acceleration = 0.5;
        final double deceleration = 1;

        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if(up)
            dy -= acceleration;
        else
            dy += acceleration;

        if(dy > top_border_max)
            dy = top_border_max;

        if(dy < bot_border_min)
            dy = bot_border_min;

        y += dy*deceleration;

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public int getScore() {
        return score;
    }

    public int getBest() {
        return best;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void setBest(int b) {
        best = b;
    }

    public void resetDY() {
        dy = 0;
    }

    public void resetScore() {
        score = 0;
    }
}