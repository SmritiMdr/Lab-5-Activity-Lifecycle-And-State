package com.example.Lab5_ActivityLifecycleAndState;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.Lab5_ActivityLifecycleAndState.extra.REPLY";
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mReply = findViewById(R.id.editText_second);

        //To get the Intent that activated this Activity
        Intent intent = getIntent();

        //Get the string containing the message from the Intent extras
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);

    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }



    public void returnReply(View view) {

        //Get the text of the EditText as a string:
        String reply = mReply.getText().toString();

        //a new intent for the response
        Intent replyIntent = new Intent();

        //extras are key/value pairs,
        // here the key is EXTRA_REPLY, and the value is the reply
        replyIntent.putExtra(EXTRA_REPLY, reply);

        //Setting the result to RESULT_OK to indicate that the response was successful
        setResult(RESULT_OK, replyIntent);

        //Adding log statement to returnReply()
        Log.d(LOG_TAG, "End SecondActivity");

        // close the Activity and return to MainActivity
        finish();
    }
}