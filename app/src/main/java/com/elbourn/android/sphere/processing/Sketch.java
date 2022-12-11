package com.elbourn.android.sphere.processing;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import processing.core.PApplet;

public class Sketch extends PApplet {

    final String TAG = getClass().getSimpleName();
    Shape shape = null;
    HSlider slider = null;
    String[] permissions = null; // {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_CALENDAR};
    float textSize = 20;

    public void settings() {
        size(displayWidth, displayHeight, P3D);
    }

    public void setup() {
        Log.i(TAG, "start setup");
        // set default text attributes
        textSize = width * 0.05f;
        textSize(textSize);
        textAlign(CENTER);
        // Check permissions
        requestPermissions(permissions);
        // Shape(PApplet pApplet, int type, float size, PImage img) {
        shape = new Shape(this,
                SPHERE ,
                min(width, height) * 0.4f,
                loadImage("rusty.jpg"));
        // HSlider(float xpos, float ypos, int swidth, int sheight, int loose)
        int sHeight = (int) (height * 0.05);
        slider = new HSlider(this,
                0,
                sHeight / 2,
                width,
                sHeight, 10);
        frameRate(10);
        Log.i(TAG, "end setup");
    }

    public void requestPermissions(String[] permissions) {
        if (permissions == null) return;
        for (String permission : permissions) {
            requestPermission(permission);
        }
    }

    public boolean hasPermissions(String[] permissions) {
        if (permissions == null) return true;
        String t = "";
        for (String permission: permissions) {
            if (!hasPermission(permission)) {
                t = t + permission.substring(permission.lastIndexOf('.') + 1).trim() + "\n";
            }
        }
        text( t + "permission required", width/2, height/2);
        return t == null;
    }

    public void draw() {
        background(android.R.color.black);
        if (!hasPermissions(permissions)) return;
        // Slider
        if (slider == null) return;
        slider.update();
        slider.display();
        text("Use slider to adjust spin axis"
                , width * 0.5f, slider.sheight + textSize);
        // Options
        text("Use menu (three dots top right)\nto select other shapes"
                , width * 0.5f, height - 2 * textSize);
        // shape
        if (shape == null) return;
        shape.update();
        float rotateZ = TWO_PI * (slider.getValue() / slider.swidth);
        shape.display(rotateZ);
    }

    public void touchStarted() {
        slider.touched();
    }

    public void touchMoved() {
        shape.touched();
    }
}
