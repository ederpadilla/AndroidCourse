package dev.eder.androidcourse;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ComponentsActivity extends AppCompatActivity {

    private ScrollView parentLayout;

    private Button buttonOne;

    private Button buttonTwo;

    private ImageView imageView;

    private EditText editText;

    private CheckBox checkBox;

    private RadioGroup radioGroup;

    private Switch aSwitch;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
        initViewsValues();
        setUpButtonOne();
        setUpImageView();
        setUpCheckBox();
        setUpRadioGroup();
        setUpSwitch();
    }

    private void initViewsValues() {
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup);
        aSwitch = findViewById(R.id.switchComponent);
        progressBar = findViewById(R.id.progressBar);
        parentLayout = findViewById(R.id.parent);
    }


    private void setUpButtonOne() {
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideProgressBar();
                showToast("Click on Button One");
            }
        });
    }

    public void setUpButtonTwo(View view){
        if (!editText.getText().toString().isEmpty()){
            showToast("User type "+editText.getText().toString());
        }else{
            showToast("User type nothing");
        }
    }

    private void setUpImageView() {
        Glide.with(this)
                .load("https://e.rpp-noticias.io/normal/2018/02/28/571558giphy-1gif.gif")
                .centerCrop()
                .into(imageView);
    }

    private void setUpCheckBox() {
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            showSnackBar("Check the box "+isChecked);
        }
    };

    private void setUpRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonGood:
                        showSnackBar("You are good");
                        break;
                    case R.id.radioButtonVeryGood:
                        showSnackBar("You are very good");
                        break;
                }
            }
        });
    }


    private void setUpSwitch() {
     aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showToast("Switch has turn on");
                }else{
                    showToast("Switch has turn off");
                }
            }
        });
    }

    private void showHideProgressBar(){
        if (progressBar.getVisibility()==View.VISIBLE){
            progressBar.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }

    private void showSnackBar(String message){
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
