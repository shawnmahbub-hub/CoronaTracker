package com.shawn.coronatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class CountryDetails extends AppCompatActivity {

    private int countryPosition;

    //finding components
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvTests,tvPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        tvTests=findViewById(R.id.tvTests);
        tvPopulation=findViewById(R.id.tvPopulation);


        Intent intent=getIntent();
        countryPosition=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Data of "+CountryListActivity.countryListModels.get(countryPosition).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry.setText(CountryListActivity.countryListModels.get(countryPosition).getCountry());
        tvCases.setText(CountryListActivity.countryListModels.get(countryPosition).getCases());
        tvRecovered.setText(CountryListActivity.countryListModels.get(countryPosition).getRecovered());
        tvCritical.setText(CountryListActivity.countryListModels.get(countryPosition).getCritical());
        tvActive.setText(CountryListActivity.countryListModels.get(countryPosition).getActive());
        tvTodayCases.setText(CountryListActivity.countryListModels.get(countryPosition).getTodayCases());
        tvTotalDeaths.setText(CountryListActivity.countryListModels.get(countryPosition).getDeaths());
        tvTodayDeaths.setText(CountryListActivity.countryListModels.get(countryPosition).getTodayDeaths());
        tvTests.setText(CountryListActivity.countryListModels.get(countryPosition).getTests());
        tvPopulation.setText(CountryListActivity.countryListModels.get(countryPosition).getPopulation());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
