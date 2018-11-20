package dev.eder.androidcourse;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpTextView();
        setUpImageView();
        readAssets();
        showLogs();
    }


    private void setUpTextView() {
        textView = findViewById(R.id.textView);
        textView.setBackgroundColor(ContextCompat.getColor(this,android.R.color.black));
        textView.setTextColor(ContextCompat.getColor(this,android.R.color.white));
        textView.setText(getString(R.string.lukes_dad));
    }

    private void setUpImageView() {
        imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.death_star));
    }

    private void readAssets() {
        try{
            InputStream stream = getAssets().open("sample.txt");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String text = new String(buffer);
            showLog("The text is "+text);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showLogs() {
        Log.e(TAG, "Error message");
        Log.w(TAG, "Warning Message");
        Log.i(TAG, "Info Message");
        Log.d(TAG, "Debug message");
        Log.v(TAG, "Verbose message");
    }

    public static void showLog(String message){
        Log.e(TAG,message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("On Start 🚀");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        showLog("On Resume 🚀");
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        showLog("On Pause 🚀");
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        showLog("On Stop 🚀");
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("On Destroy 🚀");
        // The activity is about to be destroyed.
    }

}
