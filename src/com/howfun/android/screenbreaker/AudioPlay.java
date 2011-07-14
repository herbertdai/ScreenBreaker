package com.howfun.android.screenbreaker;

import java.io.File;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AudioPlay {
   
   private static MediaPlayer mPlayer = null;
   private static Handler mHandler;
   
   private Context mContext;
   
   public AudioPlay(Context context) {
      mContext = context;
   }
   
   public void play(String audioPath) { 
      stop();
      Utils.log("Audio play ", "in play");
      // Start music only if not disabled in preferences
      if (audioPath.equals("")) {
         Utils.log("audio path ", "is empty!!!!!!!!!!!!");
         return;
      }
      
      if (getMusic(mContext)) {
         File f = new File(audioPath);
         Uri uri = Uri.parse(f.getAbsolutePath());
         mPlayer = MediaPlayer.create(mContext, uri);
         if (mPlayer == null) {
            showErrorDialog();
            return;
         }
         
         //TODO: create handler in babycard
//         mHandler = cardContext.mHandler;
         mPlayer.setOnCompletionListener(completeListener);
         mPlayer.setOnErrorListener(playErr);
         mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
         mPlayer.start();
      }
   }

   public void play(int resId) { 
      
      stop();
      
      Utils.log("Audio play ", "in play");
      
      if (getMusic(mContext)) {
         mPlayer = MediaPlayer.create(mContext, resId);
         if (mPlayer == null) {
            showErrorDialog();
            return;
         }
         
         mPlayer.setOnCompletionListener(completeListener);
         mPlayer.setOnErrorListener(playErr);
         mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
         mPlayer.start();
      }
   }
   
   /** Stop the music */
   public void stop() {
      if (mPlayer != null) {
         mPlayer.stop();
         mPlayer.release();
         mPlayer = null;
      }
   }

   /** Get the current value of the music option */
   public static boolean getMusic(Context context) {
      return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            "", true);
   }
   private MediaPlayer.OnErrorListener playErr = new MediaPlayer.OnErrorListener() {

      @Override
      public boolean onError(MediaPlayer mp, int what, int extra) {
         
         Utils.log("AudioView", "play errorrrr..");

         if (completeListener != null) {
            completeListener.onCompletion(mPlayer);
         }
         if (mPlayer != null) {
            
            Utils.log("AudioView", "stop mplayer..");
            mPlayer.stop();// .stopPlayback();
            mPlayer.release();
            mPlayer = null;
         }
         
         showErrorDialog();

         return true;
      }

   };
   
   private void showErrorDialog() {
            
      new AlertDialog.Builder(mContext)
            .setMessage(R.string.record_play_error_text)
            .setPositiveButton(android.R.string.ok, null)
            .setCancelable(false)
            .show();
   }
   
   private OnCompletionListener completeListener = new OnCompletionListener() {

      @Override
      public void onCompletion(MediaPlayer arg0) {
         try {
            if (mPlayer != null) {
               mPlayer.stop();// .stopPlayback();
               mPlayer.release();
               mPlayer = null;
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
   };
}
