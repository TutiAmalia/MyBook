package com.dicoding.mybook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private BooksAdapter adapter;
    private MainViewModel mainViewModel;
    private SearchView searchAction;
    private Button seeDetails;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new BooksAdapter(this);
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mainViewModel = ViewModelProvider.of(this).get(MainViewModel.class);
        mainViewModel.getListBook().observe(this, getBooks);
        mainViewModel.setBooks(null);

        searchAction = findViewById(R.id.search);
        searchAction.setOnQueryTextListener(searchlistener);
    }

    private Observer<ArrayList<BookItems>> getBooks = new Observer<ArrayList<BookItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<BookItems> bookItems) {
            if (bookItems != null){
                adapter.setData(bookItems);
                showLoading(false);
            }
        }
    };

    private void showLoading(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    SearchView.OnQueryTextListener searchlistener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mainViewModel.getListBook().observe(MainActivity.this, getBooks);
            mainViewModel.setBooks(query);
            showLoading(true);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
