package dev.eder.androidcourse.intentssamples;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.eder.androidcourse.KeysConstants;
import dev.eder.androidcourse.R;

public class FirstActivity extends AppCompatActivity {

    private Button mButtonSend;

    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mEditTextMessage = findViewById(R.id.editTextMessage);
        mButtonSend = findViewById(R.id.buttonSend);
    }

    public void sendMessage(View view){
        if (!mEditTextMessage.getText().toString().isEmpty()){
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra(KeysConstants.message,mEditTextMessage.getText().toString());
            startActivity(intent);
            finish();
        }else{
            createSimpleDialog(FirstActivity.this);
        }
    }

    public void createSimpleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.empty_input_message)
                .setMessage(getString(R.string.inster_a_message_to_send))
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.show();
    }
}
