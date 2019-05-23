package com.dicoding.mybook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private Context context;
    BookItems bData;

    public DetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(BookItems data) {
        bData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View detailView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout,detail_book_item, viewGroup, false);
        return new DetailViewHolder(detailView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {
        detailViewHolder.bind(bData);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, author,publisher, publishedDate, description;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            publisher=itemView.findViewById(publisher);
            publishedDate = itemView.findViewById(publishedDate);
            description = itemView.findViewById(description);
            image = itemView.findViewById(image);
        }

        public void bind(BookItems bData) {
            Glide.with(context).load(image)
        }
    }
}
