package com.tts.android.ttsandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robint on 2017/08/22.
 */

public class TransactionVisualisation extends View {

    public static float PADDING = 0f;
    public static int COLOUR_INCOME = Color.GREEN;
    public static int COLOUR_EXPENSE = Color.RED;

    //The amount of income and expense in a given collection of transactions
    //The default value for both is 1 so that the visualisation will draw each as equal sizes
    private double mIncome = 1, mExpense = 1;

    public TransactionVisualisation(Context context) {
        super(context);
    }

    public TransactionVisualisation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransactionVisualisation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TransactionVisualisation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Set the income and expense amounts to be displayed by this visualisation
    public void setAmounts(double income, double expenditure) {
        mIncome = income;
        mExpense = expenditure;

        //The data has changed, so trigger the view to redraw its content
        this.invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
        //The area inside the view which should be drawn to
        RectF drawingArea = new RectF(PADDING, PADDING, c.getWidth() - PADDING, c.getHeight() - PADDING);
        Paint drawingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawingPaint.setTextSize(50);

        //The percentage of the total amount which is income
        //If income is 75 and expense is 25, 75/(75+25) = 75% income
        float incomeRatio = (float)(mIncome/(mIncome + mExpense));
        //The percentage of the total amount which is expenditure
        float expenseRatio = 1.0f - incomeRatio;

        drawingPaint.setColor(COLOUR_INCOME);
        //Draw the green rectangle representing all income
        c.drawRect(drawingArea.left, drawingArea.top, drawingArea.right * incomeRatio, drawingArea.bottom, drawingPaint);
        drawingPaint.setColor(Color.BLACK);
        //Write the amount of the income
        c.drawText((int)mIncome+"", 0, 50, drawingPaint);

        c.translate(drawingArea.right * incomeRatio, 0);

        drawingPaint.setColor(COLOUR_EXPENSE);
        //Draw the red rectangle representing all expenses
        c.drawRect(drawingArea.left, drawingArea.top, drawingArea.right * expenseRatio, drawingArea.bottom, drawingPaint);
        drawingPaint.setColor(Color.BLACK);
        //Write the amount of the expense
        c.drawText((int)mExpense+"", 0, 50, drawingPaint);
    }
}
