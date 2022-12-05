package com.elbourn.android.sphere.processing;

import android.util.Log;

import processing.core.PApplet;

public class Sketch extends PApplet {
    String TAG = getClass().getSimpleName();
    Shape shape = null;
    HSlider slider = null;

    public void settings() {
        size(displayWidth, displayHeight, P3D);
    }

    public void setup() {
        Log.i(TAG, "start setup");
        // Sphere(PApplet pApplet, float radius)
        shape = new Shape(this, min(width, height) * 0.4f);
        // HSlider(float xpos, float ypos, int swidth, int sheight, int loose)
        slider = new HSlider(this, 0, (int) (height * 0.01), width, (int) (height * 0.1), 10);
        Log.i(TAG, "end setup");
        frameRate(10);
    }

    public void draw() {
        background(android.R.color.black);
        if (slider == null) return;
        slider.update();
        slider.display();
        if (shape == null) return;
        shape.update();
        shape.display(slider.getValue());
    }

    public void touchStarted() {
        slider.touched();
    }

    public void touchMoved() {
        shape.touched();
    }

}
