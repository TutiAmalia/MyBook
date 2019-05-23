package com.dicoding.mybook;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;

public class BookItems implements Parcelable{
    private int id;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String image;

    public BookItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String authors = object.getString("authors");
            String publisher = object.getString("publisher");
            String publishedDate = object.getString("publishedDate");
            String description = object.getString("description");
            String image = object.getString("thumbnail");
            this.id = id;
            this.title = title;
            this.authors = authors;
            this.publisher = publisher;
            this.publishedDate = publishedDate;
            this.description = description;
            this.image = image;

        } catch (JSONException e) {
            Log.d("Exception", e.getMessage());
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.authors);
        dest.writeString(this.publisher);
        dest.writeString(this.publishedDate);
        dest.writeString(this.description);
        dest.writeString(this.image);
    }

    protected BookItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.authors = in.readString();
        this.publisher = in.readString();
        this.publishedDate = in.readString();
        this.description = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<BookItems> CREATOR = new Parcelable.Creator<BookItems>() {
        @Override
        public BookItems createFromParcel(Parcel source) {
            return new BookItems(source);
        }

        @Override
        public BookItems[] newArray(int size) {
            return new BookItems[size];
        }
    };
}
