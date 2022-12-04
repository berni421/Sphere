package com.elbourn.android.sphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.elbourn.android.sphere.processing.Sketch;

import androidx.appcompat.app.AppCompatActivity;
import processing.android.CompatUtils;
import processing.android.PFragment;
import processing.core.PApplet;

public class MainActivity extends OptionsMenu {
//    private PApplet sketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FrameLayout frame = new FrameLayout(this);
//        frame.setId(CompatUtils.getUniqueViewId());
//        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        sketch = new Sketch();
//        PFragment fragment = new PFragment(sketch);
//        fragment.setView(frame, this);
//    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }

}