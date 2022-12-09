package com.elbourn.android.sphere;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Globals {

   String TAG = getClass().getSimpleName();

   private static Globals instance = new Globals();
   private List<Object> current = new ArrayList<>();

   public Object getCurrent() {
      return current.get(current.size()-1);
   }

   public void setCurrent(int type) {
      Log.i(TAG, "type: " + type);
      this.current.add(type);
   }

   public static Globals getInstance() {
      return instance;
   }
}
