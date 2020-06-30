package com.shawn.coronatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryListAdapter extends ArrayAdapter<CountryListModel> {


    private Context context;
    private List<CountryListModel> countryListModels;

    public CountryListAdapter(Context context, List<CountryListModel> countryListModels ) {
        super(context, R.layout.country_list_item,countryListModels);

        this.context=context;
        this.countryListModels=countryListModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //generating views for layout inflater
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,null,true);

        //list items
        ImageView flags=view.findViewById(R.id.imageFlag);
        TextView countryName=view.findViewById(R.id.tvCountryName);

        //getting data of the list items through model class
        countryName.setText(countryListModels.get(position).getCountry());
        Glide.with(context).load(countryListModels.get(position).getFlag()).into(flags);

        return view;
    }
}
