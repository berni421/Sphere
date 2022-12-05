package com.elbourn.android.sphere;

import android.os.Bundle;

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