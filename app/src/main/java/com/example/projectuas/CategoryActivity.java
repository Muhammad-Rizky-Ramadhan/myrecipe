package com.example.projectuas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.Api.ApiClient;
import com.example.projectuas.Api.ApiInterface;
import com.example.projectuas.Model.category.Category;
import com.example.projectuas.Model.category.DataItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<DataItem> dataItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataItemList = new ArrayList<>();
        adapter = new Adapter(this, dataItemList);
        recyclerView.setAdapter(adapter);

        fetchCategories();
    }

    private void fetchCategories() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Category> call = apiInterface.CategoryResponse();

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("CategoryActivity", "Data received: " + response.body().getData().size());
                    for (DataItem item : response.body().getData()) {
                        Log.d("CategoryActivity", "Item: " + item.getNameCategory());
                    }
                    dataItemList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CategoryActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Failed to connect to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
