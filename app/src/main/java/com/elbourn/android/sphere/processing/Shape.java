package com.elbourn.android.sphere.processing;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.elbourn.android.sphere.BuildConfig;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

import static android.content.Context.MODE_PRIVATE;


public class Shape {

    static String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();

    PShape shape = null;
    PApplet pApplet = null;
    PImage img;

    float size;
    int x=0, y=0, z=0;
    int type = PConstants.SPHERE;
    boolean newTouch = false;

    Shape(PApplet pApplet, int type, float size, PImage img) {
        this.pApplet = pApplet;
        this.img = img;
        setType(type);
        setSize(size);
    }

    void setType(int type) {
        Context context = pApplet.getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        sharedPreferences.edit().putInt("type", type).apply();

    }

    void checkType( ) {
        Context context = pApplet.getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        int newType = sharedPreferences.getInt("type", PConstants.SPHERE);
        if (newType != type) {
                Log.i(TAG, "newType: " + newType);
                type = newType;
                setSize(size);
        }
    }

    void setSize(float size) {
        this.size = size;
        pApplet.noStroke();
        shape = pApplet.createShape(type, size);
        shape.setTexture(img);
    }

    void display(float v) {
        checkType();
        pApplet.push();
        pApplet.translate(x + pApplet.width/2, y + pApplet.height/2, 0);
        int frameCount = pApplet.frameCount;
        pApplet.rotateX(frameCount * 0.01f);
        pApplet.rotateY(frameCount * 0.01f);
        pApplet.rotateZ( 2*v/pApplet.width);
        pApplet.shape(shape);
        pApplet.pop();
    }

    boolean overEvent(float x, float y) {
        boolean covers = x > this.x - size && x < this.x + size &&
                y > this.y - size && y < this.y + size;
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
            size = pApplet.abs( PVector.dist(p0, p1)) / 2f;
            Log.i(TAG, "radius: " + size);
            setSize(size);
        }
    }
}
