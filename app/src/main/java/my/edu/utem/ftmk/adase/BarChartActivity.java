package my.edu.utem.ftmk.adase;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        setTitle("Bar Chart");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1,0));
        entries.add(new BarEntry(2,0));
        entries.add(new BarEntry(3,0));
        entries.add(new BarEntry(4,0));
        entries.add(new BarEntry(5,3));
        entries.add(new BarEntry(6,15));
        entries.add(new BarEntry(7,12));
        entries.add(new BarEntry(8,3));

        BarDataSet barDataSet = new BarDataSet(entries, "Total of the community");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(3000);
    }
}