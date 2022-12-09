package com.elbourn.android.sphere;

import android.os.Bundle;

public class MainActivity extends OptionsMenu {
//    private PApplet sketch;

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

}