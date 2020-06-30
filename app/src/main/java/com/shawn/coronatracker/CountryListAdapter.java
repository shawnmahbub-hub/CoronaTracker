package com.shawn.coronatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryListAdapter extends ArrayAdapter<CountryListModel> {


    private Context context;
    private List<CountryListModel> countryListModels;
    private List<CountryListModel> countryListModelsFiltered;


    public CountryListAdapter(Context context, List<CountryListModel> countryListModels ) {
        super(context, R.layout.country_list_item,countryListModels);

        this.context=context;
        this.countryListModels=countryListModels;
        this.countryListModelsFiltered=countryListModels;
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
        countryName.setText(countryListModelsFiltered.get(position).getCountry());
        Glide.with(context).load(countryListModelsFiltered.get(position).getFlag()).into(flags);

        return view;
    }

    @Override
    public int getCount() {
        return countryListModelsFiltered.size();
    }

    @Nullable
    @Override
    public CountryListModel getItem(int position) {
        return countryListModelsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryListModels.size();
                    filterResults.values = countryListModels;

                }else{
                    List<CountryListModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryListModel itemsModel:countryListModels){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryListModelsFiltered = (List<CountryListModel>) results.values;
                CountryListActivity.countryListModels = (List<CountryListModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;    }
}
