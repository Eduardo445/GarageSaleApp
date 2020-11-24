package edu.csumb.esotorodriguez.garagesaleapp.Adapters;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_USER = "user";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_CLOSED = "closed";
    public static final String KEY_CREATED_KEY = "createdAt";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public String getSaleName() {
        return getString(KEY_NAME);
    }

    public void setSaleName(String saleName) {
        put(KEY_NAME, saleName);
    }

    public String getLocation() {
        return getString(KEY_LOCATION);
    }

    public void setLocation(String location) {
        put(KEY_LOCATION, location);
    }

    public Boolean getClosed() {
        return getBoolean(KEY_CLOSED);
    }

    public void setClosed(Boolean closed) {
        put(KEY_CLOSED, closed);
    }


}
