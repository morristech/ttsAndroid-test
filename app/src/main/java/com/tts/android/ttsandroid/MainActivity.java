package com.tts.android.ttsandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.android.ttsandroid.objects.Money;
import com.tts.android.ttsandroid.objects.Transaction;
import com.tts.android.ttsandroid.util.DataUtil;
import com.tts.android.ttsandroid.views.TransactionVisualisation;

import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataUtil.loadTransactionData(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TransactionAdapter(this, DataUtil.getTransactionData()));
    }

    public void goToTransaction(Transaction transaction){
        Intent i = new Intent(this, TransactionActivity.class);

        Bundle extras = new Bundle();
        extras.putString("date", getLongDate(transaction.transactionDate));
        extras.putString("amount", transaction.amount.amount + " " + transaction.amount.currencyCode);
        extras.putString("accountName", transaction.accountName);
        extras.putString("categoryName", transaction.categoryName);
        extras.putString("description", transaction.description);
        extras.putString("spendingGroup", transaction.spendingGroupName);
        extras.putString("note", transaction.note);
        extras.putInt("color", transaction.getColorForGroup(transaction.spendingGroupName));

        i.putExtras(extras);
        startActivity(i);
    }

    private String getLongDate(long milliseconds) {
        return DateFormat.format("dd-MMM-yyyy", new Date(milliseconds)).toString();
    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionItem> {

        private Transaction[] transactions = new Transaction[0];
        private Context context;

        public TransactionAdapter(Context ctx, Transaction[] transactions) {
            this.context = ctx;
            this.transactions = transactions;
        }

        public class TransactionItem extends RecyclerView.ViewHolder {

            TextView description;
            TextView date;
            TextView account;
            FancyButton spendingGroup;
            FancyButton category;
            TextView amount;
            CardView card;

            public TransactionItem(View itemView) {
                super(itemView);

                this.date = (TextView) itemView.findViewById(R.id.transactionDate);
                this.card = (CardView) itemView.findViewById(R.id.card);
                this.amount = (TextView) itemView.findViewById(R.id.transactionAmount);
                this.account = (TextView) itemView.findViewById(R.id.transactionAccount);
                this.description = (TextView) itemView.findViewById(R.id.transactionDescription);
                this.category = (FancyButton) itemView.findViewById(R.id.transactionCategory);
                this.spendingGroup = (FancyButton) itemView.findViewById(R.id.transactionSpendingGroup);
            }
        }

        @Override
        public TransactionItem onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
            return new TransactionItem(view);
        }

        @Override
        public void onBindViewHolder(TransactionItem holder, int position) {
            final Transaction transaction = transactions[position];

            holder.date.setText(getLongDate(transaction.transactionDate));
            holder.amount.setText(transaction.amount.amount + " " + transaction.amount.currencyCode);
            holder.account.setText(transaction.accountName);
            holder.category.setText(transaction.categoryName);
            holder.description.setText(transaction.description);
            holder.spendingGroup.setText(transaction.spendingGroupName);

            holder.category.setBackgroundColor(transaction.getColorForGroup(transaction.spendingGroupName));
            holder.spendingGroup.setBackgroundColor(transaction.getColorForGroup(transaction.spendingGroupName));

            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).goToTransaction(transaction);
                }
            });
        }

        @Override
        public int getItemCount() {
            return transactions.length;
        }
    }
}
