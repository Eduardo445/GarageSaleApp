package edu.csumb.esotorodriguez.garagesaleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import edu.csumb.esotorodriguez.garagesaleapp.ItemActivity;
import edu.csumb.esotorodriguez.garagesaleapp.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.garage_sale_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Item> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView itemCard;
        private TextView tvItemName;
        private TextView tvPrice;
        private TextView tvCreated;
        private ImageView ivItem;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            itemCard = itemView.findViewById(R.id.itemCard);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCreated = itemView.findViewById(R.id.tvCreated);
            ivItem = itemView.findViewById(R.id.ivItem);
        }

        public void bind(final Item item){
            tvItemName.setText(item.getItemName());
            tvPrice.setText(String.valueOf(item.getPrice()));
            //tvCreated.setText(item.getCreatedAt().toString());
            ParseFile image = item.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivItem);
            }

            // 1. Register click listener on the whole row
            itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, ItemActivity.class);
                    i.putExtra("itemPost", Parcels.wrap(item));
                    context.startActivity(i);
                }
            });
        }
    }
}
