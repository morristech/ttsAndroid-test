package com.tts.android.ttsandroid.util;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.tts.android.ttsandroid.objects.Transaction;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by robint on 2017/08/21.
 */

public class DataUtil {

    private static Transaction [] mTransactions;

    public static void loadTransactionData(Context context) {

        String json = null;
        try {
            InputStream is = context.getAssets().open("transactions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mTransactions = new Gson().fromJson(json, Transaction[].class);
    }

    public static Transaction[] getTransactionData() {
        return mTransactions;
    }
}
