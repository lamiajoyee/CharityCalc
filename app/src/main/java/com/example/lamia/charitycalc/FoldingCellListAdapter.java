package com.example.lamia.charitycalc;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamia.charitycalc.models.Item;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

import static com.example.lamia.charitycalc.utility.UtilityClass.appendK;

public class FoldingCellListAdapter extends RecyclerView.Adapter<FoldingCellListAdapter.ViewHolder> implements
        LoaderManager.LoaderCallbacks<Cursor> {

    public static HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private View.OnClickListener foldUnfoldListener;

    private List<Item> items;
    private int itemLayout;
    private Item item;

    private final Context context;

    Typeface custom_font;

    public FoldingCellListAdapter(Context context, List<Item> items, int itemLayout) {
        //super(context, 0, objects);
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
        //this.listener = listener;

        // set font
        custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Anton-Regular.ttf");
    }

    /*
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

        final String te2 = String.valueOf(Math.round(item.getGoal() / 10));

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
    }*/

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

    public View.OnClickListener getFoldUnfoldListener() {
        return foldUnfoldListener;
    }
    public void setFoldUnfoldListener(View.OnClickListener foldUnfoldListener) {
        this.foldUnfoldListener = foldUnfoldListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        item = items.get(position);

        holder.goal.setText(String.valueOf(item.getGoal()));
        holder.goal.append("%");
        holder.goal.setTypeface(custom_font);

        holder.income.setText(String.valueOf(appendK(item.getIncome())));
        holder.expenses.setText(String.valueOf(appendK(item.getExpenses())));
        holder.charity.setText(String.valueOf(appendK(item.getCharity())));
        holder.savings.setText(String.valueOf(appendK(item.getSavings())));

        holder.date.setText(item.getTime().toString());

        //image and placeholder
        final String te2 = String.valueOf(Math.round(item.getGoal() / 10));

        StringBuilder s2 = new StringBuilder(10);
        s2.append("n");
        s2.append(te2);
        int colorResId = holder.itemView.getResources().getIdentifier(s2.toString(), "color", context.getPackageName());
        holder.bg.setBackgroundColor(holder.itemView.getResources().getColor(colorResId));
        holder.bg_extended.setBackgroundColor(holder.itemView.getResources().getColor(colorResId));

        StringBuilder s = new StringBuilder(100);
        s.append("http://lamiajoyee.com/charityimages/");
        s.append(te2);
        s.append(".png");
        Picasso.with(context).load(s.toString()).into(holder.bg);
        Picasso.with(context).load(s.toString()).into(holder.bg_extended);



        if (item.getRequestBtnClickListener() != null) {
            holder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        }
        else {
            holder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        if (item.getFoldUnfoldListener() != null) {
            holder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    // View lookup cache
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView goal;
        TextView income;
        TextView expenses;
        TextView charity;
        TextView savings;
        TextView date;
        TextView contentRequestBtn;
        ImageView bg;
        FoldingCell fc;

        TextView goal_extended;
        TextView income_extended;
        TextView expenses_extended;
        TextView charity_extended;
        TextView savings_extended;
        TextView date_extended;
        ImageView bg_extended;

        TextView week1_goal_extended;
        TextView week1_income_extended;
        TextView week1_expenses_extended;
        TextView week1_charity_extended;
        TextView week1_savings_extended;
        TextView week1_date_extended;

        TextView week2_goal_extended;
        TextView week2_income_extended;
        TextView week2_expenses_extended;
        TextView week2_charity_extended;
        TextView week2_savings_extended;
        TextView week2_date_extended;

        TextView week3_goal_extended;
        TextView week3_income_extended;
        TextView week3_expenses_extended;
        TextView week3_charity_extended;
        TextView week3_savings_extended;
        TextView week3_date_extended;

        TextView week4_goal_extended;
        TextView week4_income_extended;
        TextView week4_expenses_extended;
        TextView week4_charity_extended;
        TextView week4_savings_extended;
        TextView week4_date_extended;

        public ViewHolder(View itemView) {
            super(itemView);

            goal = (TextView) itemView.findViewById(R.id.total_goal_amount);
            income = (TextView) itemView.findViewById(R.id.income_label_value);
            expenses = (TextView) itemView.findViewById(R.id.expenditure_label_value);
            charity = (TextView) itemView.findViewById(R.id.charity_label_value);
            savings = (TextView) itemView.findViewById(R.id.savings_label_value);
            date = (TextView) itemView.findViewById(R.id.date_label);

            bg = (ImageView) itemView.findViewById(R.id.bg);

            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);

            fc = (FoldingCell)itemView.findViewById(R.id.cell);
            fc.setTag(itemView);

            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });

            goal_extended = (TextView) itemView.findViewById(R.id.total_goal_amount_extended);
            date_extended = (TextView) itemView.findViewById(R.id.date_label_extended);
            bg_extended = (ImageView) itemView.findViewById(R.id.bgExtended);
        }
    }
}