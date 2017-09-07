package com.example.windows10timt.myweather.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windows10timt.myweather.R;
import com.example.windows10timt.myweather.adapter.AdapterSearchQuery;
import com.example.windows10timt.myweather.listener.ApiSearchService;
import com.example.windows10timt.myweather.listener.ItemSearchListener;
import com.example.windows10timt.myweather.model.model.SearchRespone;
import com.example.windows10timt.myweather.network.ApiClientSearch;
import com.example.windows10timt.myweather.util.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private SearchView mSearch;
    private Button btnSearch;
    private ImageView back;
    private RecyclerView mListSearchRespone;
    private AdapterSearchQuery adapterSearchQuery;
    private List<SearchRespone> searchRespones;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearch = (SearchView) findViewById(R.id.mSearch);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        back = (ImageView) findViewById(R.id.back);

        mListSearchRespone = (RecyclerView) findViewById(R.id.mListSearch);
        mListSearchRespone.setHasFixedSize(true);

        
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ApiSearchService apiSearchService = ApiClientSearch.searchService();
                Call<List<SearchRespone>> searchResponeCall = apiSearchService.getSearchQuery(newText + Constant.API_SEARCH_AFTER);
                searchResponeCall.enqueue(new Callback<List<SearchRespone>>() {
                    @Override
                    public void onResponse(Call<List<SearchRespone>> call, Response<List<SearchRespone>> response) {
                        if (response.isSuccessful()) {
                            searchRespones = response.body();
                            adapterSearchQuery = new AdapterSearchQuery(searchRespones);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mListSearchRespone.setLayoutManager(linearLayoutManager);
                            mListSearchRespone.setAdapter(adapterSearchQuery);

                            adapterSearchQuery.setItemSearchListener(new ItemSearchListener() {
                                @Override
                                public void click(final String city) {
                                    mSearch.setQuery(city, false);

                                    btnSearch.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (!city.isEmpty()) {
                                                Intent intent = new Intent();
                                                intent.putExtra("city", city);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                            if (mSearch.getQuery() == null) {
                                                Toast.makeText(SearchActivity.this, "Don't find location", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<List<SearchRespone>> call, Throwable t) {
                    }
                });

                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
