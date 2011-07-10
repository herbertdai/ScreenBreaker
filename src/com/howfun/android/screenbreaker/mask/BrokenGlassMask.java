/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenbreaker.mask;

import com.howfun.android.screenbreaker.R;
import com.howfun.android.screenbreaker.Sound;
import com.howfun.android.screenbreaker.Utils;

import android.content.Context;
public class BrokenGlassMask extends StaticMask {

   public static final String TAG = "BrokenGlassMask";

   public static final int RECT_WIDTH = 100;
   public static final int RECT_HEIGHT = 100;

   private Sound mSound = null; 
   
   private int[] mGlasses = { R.drawable.broken_glass0,
         R.drawable.broken_glass1};
   private int[] mSoundRes = { R.raw.glass0, R.raw.glass1};

   public BrokenGlassMask(Context context, int x, int y, Sound sound) {
      super(context);
      
      mSound = sound; 
      
      init(x, y);
   }


   protected void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = RECT_WIDTH;
      mRectHeight = RECT_HEIGHT;
      setRect();
      int whichIcon = (int) (Math.random() * mGlasses.length);
      int resId = mGlasses[whichIcon];
      setImageResource(resId);
      setClickable(false);
      playSound(0);
   }


   @Override
   protected void playSound(int resId) {
      int whichSound = (int) (Math.random() * mSoundRes.length);
      mSound.play(mSoundRes[whichSound]); 
   }

}
