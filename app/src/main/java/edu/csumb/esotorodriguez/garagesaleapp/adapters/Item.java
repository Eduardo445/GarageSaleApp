package edu.csumb.esotorodriguez.garagesaleapp.adapters;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@ParseClassName("Item")
@Parcel(analyze = Item.class)
public class Item extends ParseObject {

    public static final String KEY_POST = "postID";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_SOLD = "sold";

    public static final String KEY_CREATED_KEY = "createdAt";

    public Item(){}

    public ParseObject getPost() {
        return getParseObject(KEY_POST);
    }

    public void setPost(ParseObject post) {
        put(KEY_POST, post);
    }

    public String getItemName() {
        return getString(KEY_NAME);
    }

    public void setItemName(String itemName) {
        put(KEY_NAME, itemName);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public Double getPrice() {
        return getDouble(KEY_PRICE);
    }

    public void setPrice(Double price) {
        put(KEY_PRICE, price);
    }

}
