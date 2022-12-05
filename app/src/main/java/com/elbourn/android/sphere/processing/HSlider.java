package com.elbourn.android.sphere.processing;

import android.util.Log;

import processing.core.PApplet;

class HSlider {
    String TAG = getClass().getSimpleName();
    PApplet pApplet;
    int swidth, sheight;    // width and height of bar
    float xpos, ypos;       // x and y position of bar
    float spos, newspos;    // x position of slider
    float sposMin, sposMax; // max and min values of slider
    int loose;              // how loose/heavy
    float ratio;
    boolean newTouch;

    HSlider(PApplet pApplet, float xpos, float ypos, int swidth, int sheight, int loose) {
        this.pApplet = pApplet;
        this.swidth = swidth;
        this.sheight = sheight;
        int widthtoheight = swidth - sheight;
        ratio = (float) swidth / (float) widthtoheight;
        this.xpos = xpos;
        this.ypos = ypos - this.sheight / 2f;
        spos = this.xpos + this.swidth / 2f - this.sheight / 2f;
        newspos = spos;
        sposMin = this.xpos;
        sposMax = this.xpos + this.swidth - this.sheight;
        this.loose = loose;
    }

    void update() {
        if (!newTouch) return;
        newTouch = false;
        if (pApplet.touches.length != 1) return;
        if (!overEvent()) return;
        newspos = pApplet.constrain(pApplet.mouseX - sheight / 2f, sposMin, sposMax);
        if (pApplet.abs(newspos - spos) > 1) {
            spos = spos + (newspos - spos) / loose;
        }
    }

    public boolean overEvent() {
        return pApplet.mouseX > xpos &&
                pApplet.mouseX < xpos + swidth &&
                pApplet.mouseY > ypos &&
                pApplet.mouseY < ypos + sheight;
    }

    void display() {
        pApplet.push();
        pApplet.translate(0,0,0);
        pApplet.fill(50);
        pApplet.rect(xpos, ypos, swidth, sheight);
        pApplet.fill(172);
        pApplet.rect(spos, ypos, sheight, sheight);
        float textSize = pApplet.width * 0.05f;
        pApplet.textSize(textSize);
        String text = "Use slider at top to adjust spin axis";
        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(text,
                pApplet.width * 0.5f ,
                pApplet.height - sheight * 0.5f);
        pApplet.pop();
    }

    void touched() {
        Log.i(TAG, "start touched");
        newTouch = true;
        Log.i(TAG, "mouseX: " + pApplet.mouseX);
        Log.i(TAG, "mouseY: " + pApplet.mouseY);
        Log.i(TAG, "end touched");
    }

    float getValue() {
        // Convert spos to be values between
        // 0 and the total width of the scrollbar
        return spos * ratio;
    }
}
