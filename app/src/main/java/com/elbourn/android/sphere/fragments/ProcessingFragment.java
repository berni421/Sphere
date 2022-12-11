package com.elbourn.android.sphere.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.elbourn.android.sphere.BuildConfig;
import com.elbourn.android.sphere.Globals;
import com.elbourn.android.sphere.R;
import com.elbourn.android.sphere.processing.Sketch;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import processing.android.PFragment;
import processing.core.PApplet;

public class ProcessingFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = Fragment.class.getSimpleName();

    PApplet sketch = null;
    View view = null;
    PFragment processingFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_processing, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sketch = new Sketch();
        Globals.getInstance().setSketch(sketch);
        processingFragment = new PFragment(sketch);
        processingFragment.setView(view.findViewById(R.id.frameLayout01), getActivity());
    }
}
