package edu.csumb.esotorodriguez.garagesaleapp;

import androidx.appcompat.app.AppCompatActivity;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class ItemActivity extends AppCompatActivity {

    public static final String TAG = "ItemActivity";
    private TextView tvItem;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvPrice;
    private Button btnPurchase;
    private Item item;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        tvItem = findViewById(R.id.tvItem);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        btnPurchase = findViewById(R.id.btnPurchase);

        item = Parcels.unwrap(getIntent().getParcelableExtra("itemPost"));
        assert item != null;
        tvItem.setText(item.getItemName());
        tvDescription.setText(item.getDescription());
        double price = item.getPrice();
        tvPrice.setText(String.format("Price: $%.2f", price));

        ParseFile image = item.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }

        if (item.getUser().getObjectId().contentEquals(ParseUser.getCurrentUser().getObjectId())) {
            btnPurchase.setVisibility(View.GONE);
        } else if (item.getBuyer() != null) {
            if (item.getBuyer().getObjectId().contentEquals(ParseUser.getCurrentUser().getObjectId())) {
                btnPurchase.setVisibility(View.GONE);
            }
        }

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getUser().getObjectId().contentEquals(ParseUser.getCurrentUser().getObjectId())) {
                    Toast.makeText(ItemActivity.this, "Cannot buy your own Items!", Toast.LENGTH_SHORT).show();
                } else if (item.getBuyer() != null) {
                    if (item.getBuyer().getObjectId().contentEquals(ParseUser.getCurrentUser().getObjectId())) {
                        Toast.makeText(ItemActivity.this, "You already bought this item!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    purchasedItem();
                    purchasedComplete();
                }
            }
        });
    }

    private void purchasedComplete() {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(ItemActivity.this, "Your purchase is complete!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    protected void purchasedItem() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
        query.getInBackground(item.getObjectId(), new GetCallback<ParseObject>() {
            public void done(ParseObject item, ParseException e) {
                if (e == null) {
                    item.put("sold", true);
                    item.put("buyerID", ParseUser.getCurrentUser());
                    item.saveInBackground();
                } else {
                    Log.e(TAG, "Purchasing the item failed.", e);
                }
            }
        });
    }
}