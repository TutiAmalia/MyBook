package com.dicoding.mybook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{
    private Context context;
    ArrayList<BookItems> bData = new ArrayList<>();
    private static final String IMAGE = "http://books.google.com/books/content?id=Ve-NDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";

    public void setData(ArrayList<BookItems> bookItems){
        bData.clear();
        bData.addAll(bookItems);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View booksView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_book_item, viewGroup, false);
        return new BooksViewHolder(booksView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BooksViewHolder booksViewHolder, int i) {
        booksViewHolder.bind(bData.get(i));
        booksViewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBookActivity.class);
                intent.putExtra("bookItem", bData.get(booksViewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bData.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {
        TextView title, authors, publisher, publishedDate, description;
        ImageView image;
        Button details;

        public BooksViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            authors = itemView.findViewById(R.id.author);
            publisher = itemView.findViewById(R.id.publisher);
            publishedDate = itemView.findViewById(R.id.published_date);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            details = itemView.findViewById(R.id.see_details);
        }

        public void bind(BookItems bookItems){
            title.setText(bookItems.getTitle());
            authors.setText(bookItems.getAuthors());
            publisher.setText(bookItems.getPublisher());
            publishedDate.setText(bookItems.getPublishedDate());
            Glide.with(context).load(IMAGE + bookItems.getImage()).apply(new RequestOptions().override(150,200)).into(image);
        }
    }
}
