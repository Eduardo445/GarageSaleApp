package edu.csumb.esotorodriguez.garagesaleapp;

import androidx.appcompat.app.AppCompatActivity;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class ItemActivity extends AppCompatActivity {

    private TextView tvItem;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvPrice;
    private Button btnPurchase;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        tvItem = findViewById(R.id.tvItem);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        btnPurchase = findViewById(R.id.btnPurchase);

        Item item = Parcels.unwrap(getIntent().getParcelableExtra("itemPost"));
        assert item != null;
        tvItem.setText(item.getItemName());
        tvDescription.setText(item.getDescription());
        tvPrice.setText("Price: $" + item.getPrice());

        ParseFile image = item.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ItemActivity.this, "This is not implemented", Toast.LENGTH_SHORT).show();
            }
        });

    }
}