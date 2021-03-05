package com.example.Lab5_ActivityLifecycleAndState;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE =
            "com.example.android.Lab5_ActivityLifecycleAndState.extra.MESSAGE";

    // the key for a particular type of response
    public static final int TEXT_REQUEST = 1;

    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //using findViewById to get references from the layout
        mMessageEditText=findViewById(R.id.editText_main);
        mReplyHeadTextView=findViewById(R.id.text_header_reply);
        mReplyTextView=findViewById(R.id.text_message_reply);

        // Restore the saved state
        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");

            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.

            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

        // Log the start of the onCreate() method.
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",mReplyTextView.getText().toString());
            Log.d(LOG_TAG, "onSaveInstanceState");
        }


    }


    public void launchSecondActivity(View view) {

        //displaying this message in Logcat when send button is clicked
        Log.d(LOG_TAG, "Button clicked!");

        //Creating intent object with two parameters
        Intent intent=new Intent(this, SecondActivity.class);

        //Storing message into the variable
        String message= mMessageEditText.getText().toString();

        //The intent extras are key/value pairs in a Bundle
        //To pass information from one activity to another,
        // you put keys and values into the intent extra Bundle from the sending activity,
        // and then get them back out again in the receiving activity
        //Passing data as key value pair

        // EXTRA_MESSAGE constant as the key and the string as the value
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent,TEXT_REQUEST);

    }

    //The three arguments to onActivityResult():
    //the requestCode you set when you launched the Activity with startActivityForResult()
    //the resultCode set in the launched Activity (usually one of RESULT_OK or RESULT_CANCELED)
    //the Intent data that contains the data returned from the launch Activity

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}