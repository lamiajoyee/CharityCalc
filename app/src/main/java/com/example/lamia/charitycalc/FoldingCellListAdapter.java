package com.example.lamia.charitycalc;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.annotation.Resource;

/**
 * Created by Lamia on 6/11/2017.
 */

public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    private final Context context;

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    Typeface custom_font;

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
        this.context = context;
        // set font
        custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Anton-Regular.ttf");

    }

    public int getImageId(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);

            // binding view parts to view holder
            viewHolder.goal = (TextView) cell.findViewById(R.id.total_goal_amount);
            viewHolder.income = (TextView) cell.findViewById(R.id.income_label_value);
            viewHolder.expenses = (TextView) cell.findViewById(R.id.expenditure_label_value);
            viewHolder.charity = (TextView) cell.findViewById(R.id.charity_label_value);
            viewHolder.savings = (TextView) cell.findViewById(R.id.savings_label_value);
            viewHolder.date = (TextView) cell.findViewById(R.id.date_label);

            viewHolder.bg = (ImageView) cell.findViewById(R.id.bg);

            viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
            cell.setTag(viewHolder);

            //set font
            viewHolder.goal.setTypeface(custom_font);

        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.goal.setText(String.valueOf(item.getGoal()));
        viewHolder.goal.append("%");

        viewHolder.income.setText(String.valueOf(appendK(item.getIncome())));
        viewHolder.expenses.setText(String.valueOf(item.getExpenses()));
        viewHolder.charity.setText(String.valueOf(item.getCharity()));
        viewHolder.savings.setText(String.valueOf(item.getSavings()));

        viewHolder.date.setText(item.getDate());

        final String te2 = String.valueOf(Math.round(item.getGoal()/10));

        StringBuilder s = new StringBuilder(100);
        s.append("n");
        s.append(te2);

        viewHolder.bg.setImageResource(getImageId(s.toString()));

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    public static String appendK(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return appendK(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + appendK(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    // View lookup cache
    private static class ViewHolder {
        TextView goal;
        TextView income;
        TextView expenses;
        TextView charity;
        TextView savings;
        TextView date;
        TextView contentRequestBtn;
        ImageView bg;
    }
}
