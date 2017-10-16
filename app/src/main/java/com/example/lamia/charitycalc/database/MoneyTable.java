package com.example.lamia.charitycalc.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MoneyTable {
    // Database table
    public static final String TABLE_MONEY = "money";
    public static final String COLUMN_DATE = "_date";
    public static final String COLUMN_GOAL = "goal";
    public static final String COLUMN_INCOME = "income";
    public static final String COLUMN_EXPENDITURE = "expenditure";
    public static final String COLUMN_SAVINGS = "savings";
    public static final String COLUMN_CHARITY = "charity";
    public static final String COLUMN_INCOME_GOAL = "income_goal";
    public static final String COLUMN_EXPENDITURE_GOAL = "expenditure_goal";
    public static final String COLUMN_SAVINGS_GOAL = "savings_goal";
    public static final String COLUMN_CHARITY_GOAL = "charity_goal";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MONEY
            + "("
            + COLUMN_DATE + " date primary key, "
            + COLUMN_GOAL + " int not null, "
            + COLUMN_INCOME + " int not null,"
            + COLUMN_EXPENDITURE + " int not null, "
            + COLUMN_SAVINGS + " int not null, "
            + COLUMN_CHARITY + " int not null, "
            + COLUMN_INCOME_GOAL + " int,"
            + COLUMN_EXPENDITURE_GOAL + " int, "
            + COLUMN_SAVINGS_GOAL + " int, "
            + COLUMN_CHARITY_GOAL + " int "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(MoneyTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MONEY);
        onCreate(database);
    }
}
