package com.tts.android.ttsandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tts.android.ttsandroid.objects.Money;
import com.tts.android.ttsandroid.objects.Transaction;
import com.tts.android.ttsandroid.util.DataUtil;
import com.tts.android.ttsandroid.views.TransactionVisualisation;

import mehdi.sakout.fancybuttons.FancyButton;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        ImageButton close = (ImageButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent i = getIntent();

        TextView date = (TextView) findViewById(R.id.transactionDate);
        TextView amount = (TextView) findViewById(R.id.transactionAmount);
        TextView account = (TextView) findViewById(R.id.transactionAccount);
        TextView description = (TextView) findViewById(R.id.transactionDescription);
        TextView note = (TextView) findViewById(R.id.transactionNote);
        TextView noteLbl = (TextView) findViewById(R.id.transactionNoteLbl);
        FancyButton category = (FancyButton) findViewById(R.id.transactionCategory);
        FancyButton spendingGroup = (FancyButton) findViewById(R.id.transactionSpendingGroup);

        date.setText(i.getStringExtra("date"));
        note.setText(i.getStringExtra("note"));
        amount.setText(i.getStringExtra("amount"));
        account.setText(i.getStringExtra("accountName"));
        category.setText(i.getStringExtra("categoryName"));
        description.setText(i.getStringExtra("description"));
        spendingGroup.setText(i.getStringExtra("spendingGroup"));

        if (note.getText().toString().isEmpty()) {
            note.setText("No notes available");
            noteLbl.setVisibility(View.GONE);
        }

        int color = i.getIntExtra("color", Color.RED);
        category.setBackgroundColor(color);
        spendingGroup.setBackgroundColor(color);

        TransactionVisualisation vis = (TransactionVisualisation)findViewById(R.id.visualisation);
        double[] incomeAndExpense = getIncomeAndExpense();
        vis.setAmounts(incomeAndExpense[0], incomeAndExpense[1]);
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
