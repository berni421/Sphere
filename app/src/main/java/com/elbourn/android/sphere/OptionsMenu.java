package com.elbourn.android.sphere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.elbourn.android.sphere.fragments.IntroFragment;
import com.elbourn.android.sphere.processing.Sketch;

import androidx.appcompat.app.AppCompatActivity;
import processing.core.PConstants;

public class OptionsMenu extends AppCompatActivity {

    static String APP = BuildConfig.APPLICATION_ID;
    private String TAG = "OptionsMenu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        Context context = getApplicationContext();
        MenuItem introCheckBox = menu.findItem(R.id.menuIntroOff);
        introCheckBox.setChecked(IntroFragment.getIntroCheckBox(context));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sphere:
                setShapeType(PConstants.SPHERE);
                return true;
            case R.id.cube:
                setShapeType(PConstants.BOX);
                return true;
            case R.id.menuIntroOff:
                setIntroductionOff(item);
                return true;
            case R.id.menuDonate:
                startDonationWebsite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setShapeType(int type) {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        sharedPreferences.edit().putInt("type", type).apply();
    }

    void setIntroductionOff(MenuItem item) {
        Context context = getApplicationContext();
        Boolean subscriptionsIntroOff = !item.isChecked();
        item.setChecked(subscriptionsIntroOff);
        IntroFragment.setIntroCheckBox(context, subscriptionsIntroOff);
        Log.i(TAG, "subscriptionsIntroOff: " + subscriptionsIntroOff);
    }

    void startDonationWebsite() {
        Log.i(TAG, "start startDonationWebsite");
        Context context = getApplicationContext();
        runOnUiThread(new Runnable() {
            public void run() {
                String msg = "Starting browser to feed the cat ...";
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
        String url = "https://www.elbourn.com/feed-the-cat/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
        Log.i(TAG, "end startDonationWebsite");
    }
}