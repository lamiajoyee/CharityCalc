package com.example.lamia.charitycalc;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Lamia on 6/11/2017.
 */

public class Item {

    private int goal;
    private int income;
    private int expenses;
    private int charity;
    private int savings;
    private String date;

    private View.OnClickListener requestBtnClickListener;

    public Item(int goal, int income, int expenses, int charity, int savings, String date) {
        this.goal = goal;
        this.income = income;
        this.expenses = expenses;
        this.charity = charity;
        this.savings = savings;
        this.date = date;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getCharity() {
        return charity;
    }

    public void setCharity(int charity) {
        this.charity = charity;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(100, 2500, 30, 2, 3, "Jan '17"));
        items.add(new Item(93, 200, 30, 2, 3, "Feb '17"));
        items.add(new Item(90, 25000, 30, 2, 3, "Mar '17"));
        items.add(new Item(100, 900, 30, 2, 3, "Apr '17"));
        items.add(new Item(95, 25000, 30, 2, 3, "May '17"));
        items.add(new Item(98, 253, 30, 2, 3, "Jun '17"));

        return items;

    }

}