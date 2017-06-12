package com.example.lamia.charitycalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Lamia on 6/11/2017.
 */

public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
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
            viewHolder.goal = (TextView) cell.findViewById(R.id.goal_label);
            viewHolder.income = (TextView) cell.findViewById(R.id.income_label_value);
            viewHolder.expenses = (TextView) cell.findViewById(R.id.expenditure_label_value);
            viewHolder.charity = (TextView) cell.findViewById(R.id.charity_label_value);
            viewHolder.savings = (TextView) cell.findViewById(R.id.savings_label_value);
            viewHolder.date = (TextView) cell.findViewById(R.id.date_label);

            viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
            cell.setTag(viewHolder);
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
        viewHolder.income.setText(String.valueOf(item.getIncome()));
        viewHolder.expenses.setText(String.valueOf(item.getExpenses()));
        viewHolder.charity.setText(String.valueOf(item.getCharity()));
        viewHolder.savings.setText(String.valueOf(item.getSavings()));
        viewHolder.date.setText(item.getDate());

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

    // View lookup cache
    private static class ViewHolder {
        TextView goal;
        TextView income;
        TextView expenses;
        TextView charity;
        TextView savings;
        TextView date;
        TextView contentRequestBtn;
    }
}
