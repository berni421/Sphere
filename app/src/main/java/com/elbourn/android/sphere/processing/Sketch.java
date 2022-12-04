package com.elbourn.android.sphere.processing;

import android.util.Log;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Sketch extends PApplet {
    String TAG = getClass().getSimpleName();
    PImage img = null;
    PShape sphere = null;
    HSlider slider = null;

    public void settings() {
        size(displayWidth, displayHeight, P3D);
    }

    public void setup() {
        Log.i(TAG, "start setup");
        img = loadImage("rusty.jpg");
        noStroke();
        sphere = createShape(SPHERE, width * 0.6f);
        sphere.setTexture(img);
        frameRate(10);
        // HSlider(float xpos, float ypos, int swidth, int sheight, int loose)
        slider = new HSlider(this,0, (int) (height * 0.01), width, (int) (height * 0.1), 10);
        Log.i(TAG, "end setup");
    }

    public void draw() {
        background(android.R.color.black);
        if (slider != null ) {
            slider.update();
            slider.display();
        }
        translate(width / 2, height / 2, 0);
        rotateZ(-TWO_PI * 0.25f + 0.01f * slider.getValue() / TWO_PI);
        rotateX(frameCount * 0.01f);
        rotateY(frameCount * 0.01f);
        shape(sphere);
    }

    public void touchEnded() {
        slider.touched();
    }

}
