package com.dicoding.mybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DetailBookActivity extends AppCompatActivity {
    private DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailAdapter = new DetailAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailAdapter);

        Intent intent = getIntent();
        BookItems data = intent.getParcelableExtra("movieItem");
        detailAdapter.setData(data);

        setTitle(R.string.detail_title);
    }
}
