package edu.csumb.esotorodriguez.garagesaleapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.csumb.esotorodriguez.garagesaleapp.LoginActivity;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.ItemAdapter;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.R;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";
    TextView tvProfileUsername;
    RecyclerView rvSaleItems;
    RecyclerView rvBoughtItems;
    Button btnLogout;
    protected ItemAdapter adapter;
    protected ItemAdapter adapter2;
    protected List<Item> soldItems;
    protected List<Item> saleItems;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvProfileUsername = view.findViewById(R.id.tvProfileUsername);
        rvSaleItems = view.findViewById(R.id.rvSaleItems);
        rvBoughtItems = view.findViewById(R.id.rvBoughtItems);
        btnLogout = view.findViewById(R.id.btnLogout);

        tvProfileUsername.setText(ParseUser.getCurrentUser().getUsername());

        saleItems = new ArrayList<>();
        adapter = new ItemAdapter(getContext(), saleItems);

        soldItems = new ArrayList<>();
        adapter2 = new ItemAdapter(getContext(), soldItems);

        rvSaleItems.setAdapter(adapter);
        rvBoughtItems.setAdapter(adapter2);

        rvSaleItems.setLayoutManager(new LinearLayoutManager(getContext()));
        querySaleItems();

        rvBoughtItems.setLayoutManager(new LinearLayoutManager(getContext()));
        queryBoughtItems();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity();
            }
        });
    }

    protected void querySaleItems() {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        query.include(Item.KEY_USER);
        query.addDescendingOrder(Item.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Item item : items ) {
                     Log.i(TAG, "Item: " + item.getItemName() + ", ID: " + item.getUser().getObjectId() + ", Current: " + ParseUser.getCurrentUser().getObjectId());
                    if (item.getUser().getObjectId().equals(ParseUser.getCurrentUser().getObjectId()) && !item.getSold()) {
                        saleItems.add(item);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    protected void queryBoughtItems() {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        query.include(Item.KEY_USER);
        query.addDescendingOrder(Item.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Item item : items ) {
                     Log.i(TAG, "Item: " + item.getItemName() + ", ID: " + item.getUser().getObjectId());
                     try {
                         if (item.getBuyer().getObjectId().equals(ParseUser.getCurrentUser().getObjectId()) && item.getSold()) {
                             soldItems.add(item);
                             adapter2.notifyDataSetChanged();
                         }
                     } catch (Exception Null) {
                         Log.e(TAG, "Failed to get bought items.", Null);
                     }
                }
            }
        });
    }

    private void goLoginActivity() {
        ParseUser.logOut();
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}