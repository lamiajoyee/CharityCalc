package com.example.lamia.charitycalc.models;

import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class Item {

    private int goal;
    private int income;
    private int expenses;
    private int charity;
    private int savings;
    private Date time;

    private View.OnClickListener requestBtnClickListener;
    private View.OnClickListener foldUnfoldListener;

    public Item(int goal, int income, int expenses, int charity, int savings, Date time) {
        this.goal = goal;
        this.income = income;
        this.expenses = expenses;
        this.charity = charity;
        this.savings = savings;
        this.time = time;
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

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }
    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public View.OnClickListener getFoldUnfoldListener() {
        return foldUnfoldListener;
    }
    public void setFoldUnfoldListener(View.OnClickListener foldUnfoldListener) {
        this.foldUnfoldListener = foldUnfoldListener;
    }
    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(93, 200, 30, 26, 30, new Date("1 March 2017")));
        items.add(new Item(85, 25000, 3450, 28, 453,  new Date("1 March 2017")));
        items.add(new Item(68, 900, 3450, 782, 43, new Date("4 March 2017")));
        items.add(new Item(95, 25000, 3089, 25, 3, new Date("5 March 2017")));
        items.add(new Item(58, 253, 300, 2, 6773, new Date("6 March 2017")));

        items.add(new Item(78, 253, 3780, 223, 35, new Date("7 March 2017")));
        items.add(new Item(48, 253, 330, 209, 309, new Date("8 March 2017")));
        items.add(new Item(38, 253, 6730, 672, 343, new Date("9 March 2017")));

        items.add(new Item(28, 253, 390, 20, 390, new Date("1 March 2017")));
        items.add(new Item(18, 253, 130, 82, 93, new Date("1 March 2017")));

        return items;

    }

}