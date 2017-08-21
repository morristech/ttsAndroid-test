package com.tts.android.ttsandroid.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robint on 2017/08/21.
 */

public class Money {
    public static final Map<String, String> CURRENCIES = new HashMap<String, String>(){
        {
            put("EUR","€");
            put("USD","$");
            put("ZAR","R");
        }
    };
    public double amount;
    public String currencyCode;
    public String debitOrCredit;
}
