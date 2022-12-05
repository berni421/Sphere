package com.elbourn.android.sphere.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.elbourn.android.sphere.BuildConfig;
import com.elbourn.android.sphere.R;
import com.elbourn.android.sphere.processing.Sketch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import processing.android.PFragment;
import processing.core.PApplet;

public class ProcessingFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = Fragment.class.getSimpleName();

    private PApplet sketch;
    View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_processing, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        if (view != null) startAndSetup(view);
        Log.i(TAG, "end onResume");
    }

    void startAndSetup(View view) {
        FragmentActivity fragmentActivity = getActivity();
        FrameLayout frame = view.findViewById(R.id.frameLayout01);
        sketch = new Sketch();
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, fragmentActivity);
    }

}
