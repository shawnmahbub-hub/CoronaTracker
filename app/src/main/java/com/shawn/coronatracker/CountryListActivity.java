package com.shawn.coronatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;


import com.shawn.coronatracker.R;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import java.util.ArrayList;
import java.util.List;

public class CountryListActivity extends AppCompatActivity {

    //declaring variables for finding the components of the CountryListActivity
    EditText searchEditText;
    ListView countryListView;
    SimpleArcLoader simpleArcLoader;

    //arrayList for REST API data
    public static List<CountryListModel> countryListModels=new ArrayList<>();
    CountryListModel countryListModel;
    CountryListAdapter countryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        searchEditText=findViewById(R.id.edtSearch);
        countryListView=findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.loader);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //method for fetching data form REST API
        fetchCountryData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchCountryData() {

        String url="https://corona.lmao.ninja/v2/countries";

        simpleArcLoader.start();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //creating JSON array to fetch JSON objects
                        try {

                            /*replace the components text fetched from the
                             * REST API in JSON format*/
                            JSONArray jsonArray=new JSONArray(response);
                            String flag,country,cases,
                                    todayCases,deaths,todayDeaths,
                                    recovered,active,critical,tests,population;

                            //loop for accessing the indexes of JSONArray
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                country=jsonObject.getString("country");
                                cases=jsonObject.getString("cases");
                                todayCases=jsonObject.getString("todayCases");
                                deaths=jsonObject.getString("deaths");
                                todayDeaths=jsonObject.getString("todayDeaths");
                                recovered=jsonObject.getString("recovered");
                                active=jsonObject.getString("active");
                                critical=jsonObject.getString("critical");
                                tests=jsonObject.getString("tests");
                                population=jsonObject.getString("population");

                                JSONObject jsonObject1=jsonObject.getJSONObject("countryInfo");
                                String flagUrl=jsonObject1.getString("flag");

                                //passing the data to the country list models by creating an object of that class
                                countryListModel=new CountryListModel(flagUrl,country,cases,todayCases,deaths,todayDeaths,
                                        recovered,active,critical,tests,population);

                                countryListModels.add(countryListModel);

                            }

                            countryListAdapter=new CountryListAdapter(CountryListActivity.this,countryListModels);
                            countryListView.setAdapter(countryListAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);




                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(CountryListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
