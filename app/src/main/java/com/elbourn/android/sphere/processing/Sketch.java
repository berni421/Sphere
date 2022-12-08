package com.elbourn.android.sphere.processing;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.elbourn.android.sphere.BuildConfig;

import processing.core.PApplet;

import static android.content.Context.MODE_PRIVATE;

public class Sketch extends PApplet {

    static String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    Shape shape = null;
    HSlider slider = null;

    public void settings() {
        size(displayWidth, displayHeight, P3D);
    }

    public void setup() {
        Log.i(TAG, "start setup");
        // Shape(PApplet pApplet, int type, float size, PImage img) {
        shape = new Shape(this, SPHERE,min(width, height) * 0.4f, loadImage("rusty.jpg"));
        // HSlider(float xpos, float ypos, int swidth, int sheight, int loose)
        int sHeight = (int) (height * 0.1);
        slider = new HSlider(this, 0, sHeight/2, width, sHeight, 10);
        Log.i(TAG, "end setup");
        frameRate(10);
    }

    public void draw() {
        background(android.R.color.black);
        // Slider
        if (slider == null) return;
        slider.update();
        slider.display();
        float textSize = width * 0.05f;
        textSize(textSize);
        String text = "Use slider to adjust spin axis";
        textAlign(CENTER);
        text(text, width * 0.5f, slider.sheight + textSize);
        // shape
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
