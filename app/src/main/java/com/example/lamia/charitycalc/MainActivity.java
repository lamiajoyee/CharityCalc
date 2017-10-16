package com.example.lamia.charitycalc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.lamia.charitycalc.models.Item;

import java.util.ArrayList;

import static com.example.lamia.charitycalc.utility.UtilityClass.SetTranslucentSystemTray;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setupWindowAnimations();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SetTranslucentSystemTray(this);
        }

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items, R.layout.cell);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setFoldUnfoldListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // set elements to adapter
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("All Months");

    }

    public void fab_click(View v)
    {
        Intent intent = new Intent(this, MonthWeekActivity.class);
        this.startActivity(intent);
    }
}
