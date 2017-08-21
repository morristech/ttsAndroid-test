package com.tts.android.ttsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tts.android.ttsandroid.objects.Transaction;
import com.tts.android.ttsandroid.util.DataUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataUtil.loadTransactionData(this);
        Transaction[] m = DataUtil.getTransactionData();
        int i = m.length;
    }
}
