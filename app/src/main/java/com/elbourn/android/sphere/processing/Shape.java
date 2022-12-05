package com.elbourn.android.sphere.processing;

import android.util.Log;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

class Shape {

    String TAG = getClass().getSimpleName();

    PShape sphere = null;
    PApplet pApplet = null;
    PImage img;

    float radius;
    int x=0, y=0, z=0;
    boolean newTouch = false;

    Shape(PApplet pApplet, float radius) {
        this.pApplet = pApplet;
        img = pApplet.loadImage("rusty.jpg");
        this.radius = radius;
        resize();
    }

    void resize() {
        pApplet.noStroke();
        sphere = pApplet.createShape(pApplet.SPHERE, radius);
        sphere.setTexture(img);
    }

    void display(float v) {
        pApplet.push();
        pApplet.translate(x + pApplet.width/2, y + pApplet.height/2, 0);
        int frameCount = pApplet.frameCount;
        pApplet.rotateX(frameCount * 0.01f);
        pApplet.rotateY(frameCount * 0.01f);
        pApplet.rotateZ( 2*v/pApplet.width);
        pApplet.shape(sphere);
        pApplet.pop();
    }

    boolean overEvent(float x, float y) {
        boolean covers = x > this.x - radius && x < this.x + radius &&
                y > this.y - radius && y < this.y + radius;
        return covers;
    }

    void touched() {
        Log.i(TAG, "start touched");
        newTouch = true;
        Log.i(TAG, "end touched");
    }

    public void update() {
        if (!newTouch) return;
        newTouch = false;
        int touches = pApplet.touches.length;
        Log.i(TAG, "touches: " + touches);
        if (touches == 1) {
            // move sphere to this place
            float x0 = pApplet.touches[0].x - pApplet.width/2;
            float y0 = pApplet.touches[0].y - pApplet.height/2;
            if (!overEvent(x0, y0)) return;
            x = (int) x0;
            y = (int) y0;
        }
        if (touches == 2) {
            // resize based on distance between touches
            float x0 = pApplet.touches[0].x;
            float y0 = pApplet.touches[0].y;
            PVector p0 = new PVector(x0, y0);
//            if (!overEvent(x0, y0)) return;
            float x1 = pApplet.touches[1].x;
            float y1 = pApplet.touches[1].y;
            PVector p1 = new PVector(x1, y1);
//            if (!overEvent(x1, y1)) return;
            radius = pApplet.abs( PVector.dist(p0, p1)) / 2f;
            Log.i(TAG, "radius: " + radius);
            resize();
        }
    }
}
