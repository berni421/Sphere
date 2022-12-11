package com.elbourn.android.sphere;

import android.content.Intent;
import android.os.Bundle;

import processing.core.PApplet;

public class MainActivity extends OptionsMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PApplet sketch = Globals.getInstance().getSketch();
        if (sketch != null) {
            sketch.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PApplet sketch = Globals.getInstance().getSketch();
        if (sketch != null) {
            sketch.onNewIntent(intent);
        }
    }
}