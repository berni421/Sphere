package com.elbourn.android.sphere;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Globals {

   String TAG = getClass().getSimpleName();

   private static Globals instance = new Globals();
   private List<Integer> type = new ArrayList<>();
   private List<PApplet> sketch = new ArrayList<>();

   public static Globals getInstance() {
      return instance;
   }

   public int getType() {
      return type.get(type.size()-1);
   }
   public void setType(int type) {
      this.type.add(type);
   }

   public PApplet getSketch() {
      return sketch.get(sketch.size()-1);
   }
   public void setSketch(PApplet sketch) {
      this.sketch.add(sketch);
   }
}
