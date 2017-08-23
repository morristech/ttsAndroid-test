package com.tts.android.ttsandroid.objects;

import android.graphics.Color;

/**
 * Created by robint on 2017/08/21.
 */

public class Transaction {

    public static final String SPENDING_GROUP_INCOME = "Income";
    public static final String SPENDING_GROUP_DAYTODAY = "Day-to-day";
    public static final String SPENDING_GROUP_RECURRING = "Recurring";
    public static final String SPENDING_GROUP_EXCEPTION = "Exception";
    public static final String SPENDING_GROUP_INVESTMENT = "Invest-save-repay";

    public static final int SPENDING_GROUP_INCOME_COLOUR = Color.parseColor("#ff94b93d");
    public static final int SPENDING_GROUP_DAYTODAY_COLOUR = Color.parseColor("#ff32b1c8");
    public static final int SPENDING_GROUP_RECURRING_COLOUR = Color.parseColor("#fffec44a");
    public static final int SPENDING_GROUP_EXCEPTION_COLOUR = Color.parseColor("#ffec7c31");
    public static final int SPENDING_GROUP_INVESTMENT_COLOUR = Color.parseColor("#ffbc4d9b");

    public String description;
    public String accountName;
    public long transactionDate;
    public String categoryName;
    public String spendingGroupName;
    public Money amount;
    public String note;
}