package edu.csumb.esotorodriguez.garagesaleapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.csumb.esotorodriguez.garagesaleapp.R;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.ItemAdapter;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.PostAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarageItemFragment extends Fragment {

    public static final String TAG = "GarageItemFragment";
    private RecyclerView rvItems;
    protected ItemAdapter adapter;
    protected List<Item> allItems;
    private Post post;
    SwipeRefreshLayout swipeContainerItem;

    public GarageItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        post = getArguments().getParcelable("ItemsActivity");
        return inflater.inflate(R.layout.fragment_garage_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvItems = view.findViewById(R.id.rvItem);

        swipeContainerItem = view.findViewById(R.id.swipeContainerItem);
        // Configure the refreshing colors
        swipeContainerItem.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainerItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data!");
                adapter.clear();
                queryItems();
                swipeContainerItem.setRefreshing(false);
            }
        });

        // Steps to use the recycler view:
        // 0. create layout for one row in the list
        // 1. create the adapter
        // 2. create the data source
        allItems = new ArrayList<>();
        adapter = new ItemAdapter(getContext(), allItems);

        // 3. set the adapter on the recycler view
        rvItems.setAdapter(adapter);

        // 4. set the layout manager on the recycler
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));

        queryItems();
    }

    protected void queryItems() {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        query.whereEqualTo("postID", post);
        query.include(Item.KEY_POST);
        query.setLimit(20);
        query.addDescendingOrder(Item.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Item item: items) {
                    Log.i(TAG, "Item: " + item.getItemName() + ", price: " + item.getPrice());
                }

                allItems.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }
}