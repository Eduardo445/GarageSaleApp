package edu.csumb.esotorodriguez.garagesaleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import edu.csumb.esotorodriguez.garagesaleapp.R;
import edu.csumb.esotorodriguez.garagesaleapp.models.ItemsActivity;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.garage_sale_item, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView garageCard;
        private TextView tvGarage;
        private TextView tvUsername;
        private TextView tvLocation;
        private TextView tvCreated;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            garageCard = itemView.findViewById(R.id.garageCard);
            tvGarage = itemView.findViewById(R.id.tvGarage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvCreated = itemView.findViewById(R.id.tvCreated);
        }

        public void bind(final Post post){
            tvGarage.setText(post.getSaleName());
            tvUsername.setText(post.getUser().getUsername());
            tvLocation.setText(post.getLocation());
            tvCreated.setText(post.getCreatedAt().toString());

            // 1. Register click listener on the whole row
            garageCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, ItemsActivity.class);
                    i.putExtra("garagePost", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });
        }
    }
}
