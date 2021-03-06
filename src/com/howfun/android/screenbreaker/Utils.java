package com.howfun.android.screenbreaker;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

public final class Utils {
   public static final int NORTH = 0;
   public static final int SOUTH = 1;
   public static final int WEST = 2;
   public static final int EAST = 3;
   public static final int[] DIRECTIONS = { NORTH, SOUTH };
   public static final long[] LIFES = { 10000L };

   public static final int[] MOVE_STEPS = { 5, 10, 15 };

   public static void log(String tag, String info) {
      if (ScreenMaskApplication.DEBUG) {
         Log.e("Screen Mask>>>>>>>>>" + tag, "-------->" + info);
      }
   }

   public static void showMessageDlg(Context context, int stringId) {
      new AlertDialog.Builder(context).setIcon(R.drawable.icon).setTitle(
            R.string.app_name).setMessage(stringId).setPositiveButton(
            android.R.string.ok, null).show();
   }
   
   public static void showAbout(Context context) {
      new AlertDialog.Builder(context)
         .setIcon(R.drawable.icon)
         .setTitle(R.string.app_name)
         .setMessage(
               context.getResources().getString(R.string.version)
               + "\n" +
               context.getResources().getString(R.string.howfun)
               )
         .show().setCanceledOnTouchOutside(true);
   }

}
