package dev.eder.androidcourse;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.eder.androidcourse.Util.Util;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.textInputLayoutMail)
    TextInputLayout mTextInputMail;

    @BindView(R.id.editTextMail)
    EditText mEtMail;

    @BindView(R.id.editTextPassword)
    EditText mEtPass;

    @BindView(R.id.parent_layout)
    ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin) public void logIn(View view){
        mEtPass.setError(null);
        mTextInputMail.setError(null);
        if (areEmptyInputs()){
            if(areValidInputs()){
                successLogin();
            }
        }else{
            Snackbar.make(parentLayout,getString(R.string.empty_fields),Snackbar.LENGTH_SHORT).show();
        }
    }

    private void successLogin() {
        Toast.makeText(this,getString(R.string.successs_login),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SecondActivity.this,ProfileActivity.class);
        intent.putExtra(KeysConstants.PROFILE,mEtMail.getText().toString());
        startActivity(intent);
        SecondActivity.this.finish();
    }

    private boolean areValidInputs() {
        return validEmail() && validPassword();
    }

    private boolean validPassword() {
        if(mEtPass.getText().toString().length()<8){
            mEtPass.setError(getString(R.string.invalid_password));
            return false;
        }else{
            return true;
        }
    }

    private boolean validEmail() {
        if (!Util.isValidEmailAddress(mEtMail.getText().toString())){
            mEtMail.setError(getString(R.string.invalid_mail));
            return false;
        }else{
            return true;
        }
    }

    private boolean areEmptyInputs() {
        return !mEtMail.getText().toString().isEmpty() &&
                !mEtPass.getText().toString().isEmpty();
    }


}
