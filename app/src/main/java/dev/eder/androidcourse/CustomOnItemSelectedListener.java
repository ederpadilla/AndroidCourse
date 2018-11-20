package dev.eder.androidcourse;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Log.e("Select","Select "+parent.getItemAtPosition(pos).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
    }

}
