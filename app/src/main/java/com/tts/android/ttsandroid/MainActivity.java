package com.tts.android.ttsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tts.android.ttsandroid.objects.Money;
import com.tts.android.ttsandroid.objects.Transaction;
import com.tts.android.ttsandroid.util.DataUtil;
import com.tts.android.ttsandroid.views.TransactionVisualisation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataUtil.loadTransactionData(this);
        setContentView(R.layout.activity_main);

//        Uncomment this code when you're ready for task #3
//        TransactionVisualisation vis = (TransactionVisualisation)findViewById(R.id.visualisation);
//        vis.setVisibility(View.VISIBLE);
//        double[] incomeAndExpense = getIncomeAndExpense();
//        vis.setAmounts(incomeAndExpense[0], incomeAndExpense[1]);
    }

    private double[] getIncomeAndExpense() {
        //An array containing two amounts
        //0 = income
        //1 = expense
        double[] amounts = new double[2];
        for(Transaction t : DataUtil.getTransactionData()) {
            if(Money.CREDIT.equals(t.amount.debitOrCredit))
                amounts[0] += t.amount.amount;
            else
                amounts[1] += t.amount.amount;
        }
        return amounts;
    }
}
