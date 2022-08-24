package my.edu.utem.ftmk.adase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AgeChart extends AppCompatActivity {

    BarChart barChart;

    //private FirebaseFirestore firebaseFirestore;
    private DatabaseReference reference;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_chart);

        db = FirebaseFirestore.getInstance();

        barChart = findViewById(R.id.barChart);

        ageBarChart();



        //DatabaseReference databaseReference = FirebaseFirestore.getReference("community");

//        ArrayList<BarEntry> barEntries = new ArrayList<>();
//
//        for (int i=1; i<8; i++) {
//
//            float value = (float) (i*10.0);
//            BarEntry barEntry = new BarEntry(i,value);
//            barEntries.add(barEntry);
//        }


//        BarDataSet barDataSet = new BarDataSet(barEntries, "Age");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        barDataSet.setDrawValues(false);
//        barChart.setData(new BarData(barDataSet));
//        barChart.animateY(5000);
//        barChart.getDescription().setText("Community's Age");
//        barChart.getDescription().setTextColor(Color.BLACK);
//        barChart.getDescription().setTextSize(20);
    }

    private void ageBarChart() {

        db.collection("community").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    }
                });
    }
}