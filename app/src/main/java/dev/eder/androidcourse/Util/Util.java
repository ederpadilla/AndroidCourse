package dev.eder.androidcourse.Util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class Util {

    public static void showLog(String TAG, String message){
        Log.e(TAG,message);
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void changeActivityAndFinis(Activity activity, Class aClass){
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
        activity.finish();
    }

    public static int getRandomNumber(){
        return  (int)(Math.random()*((3-1)+1))+1;
    }

    public static int getRandomID(){
        return  (int)(Math.random()*((30000-1)+1))+1;
    }
}
