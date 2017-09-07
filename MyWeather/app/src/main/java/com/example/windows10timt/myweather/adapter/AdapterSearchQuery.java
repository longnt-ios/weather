package com.example.windows10timt.myweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.listener.ItemSearchListener;
import com.example.windows10timt.myweather.model.model.SearchRespone;

import java.util.List;

/**
 * Created by Nguyen Tien Long on 9/6/2017.
 */

public class AdapterSearchQuery extends RecyclerView.Adapter<AdapterSearchQuery.MyViewHolder> {

    private List<SearchRespone> responeArrayList;
    private ItemSearchListener itemSearchListener;

    public ItemSearchListener getItemSearchListener() {
        return itemSearchListener;
    }

    public void setItemSearchListener(ItemSearchListener itemSearchListener) {
        this.itemSearchListener = itemSearchListener;
    }

    public AdapterSearchQuery(List<SearchRespone> responeArrayList) {
        this.responeArrayList = responeArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_respone, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final SearchRespone searchRespone = responeArrayList.get(position);
        holder.mCity.setText(searchRespone.getCity() + " , " + searchRespone.getCountry());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemSearchListener != null) {
                    itemSearchListener.click(searchRespone.getCity() + " , " + searchRespone.getCountry());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (responeArrayList == null) return 0;
        return responeArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mCity;

        private MyViewHolder(View itemView) {
            super(itemView);
            mCity = (TextView) itemView.findViewById(R.id.mCity);
        }
    }
}
