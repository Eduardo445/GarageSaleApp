package edu.csumb.esotorodriguez.garagesaleapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import edu.csumb.esotorodriguez.garagesaleapp.R;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;

public class ComposeFragment  extends Fragment {

    private String TAG = "ComposeFragment";
    private Post post;
    public ComposeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager();
        Fragment fragment = new Fragment();
        if(doesPostExist()){
            fragment = new NewItemFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(TAG, post);
            fragment.setArguments(bundle);
        }else{
            fragment = new NewPostFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.compose_fragment, fragment).commit();
    }


    private boolean doesPostExist(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo("userID", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                }
                if (!objects.isEmpty()){
                    post = objects.get(0);
                }
            }
        });
        return post != null;
    }
}
