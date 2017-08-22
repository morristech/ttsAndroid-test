package com.tts.android.ttsandroid.objects;

import android.graphics.Color;

/**
 * Created by robint on 2017/08/21.
 */

public class Transaction {

    public static final int SPENDING_GROUP_INCOME = Color.parseColor("#ff94b93d");
    public static final int SPENDING_GROUP_DAYTODAY = Color.parseColor("#ff32b1c8");
    public static final int SPENDING_GROUP_RECURRING = Color.parseColor("#fffec44a");
    public static final int SPENDING_GROUP_EXCEPTION = Color.parseColor("#ffec7c31");
    public static final int SPENDING_GROUP_INVESTMENT = Color.parseColor("#ffbc4d9b");

    public String description;
    public String accountName;
    public long transactionDate;
    public String categoryName;
    public String spendingGroupName;
    public Money amount;
    public String note;
}