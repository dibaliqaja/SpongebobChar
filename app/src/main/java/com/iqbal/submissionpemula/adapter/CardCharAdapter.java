package com.iqbal.submissionpemula.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.iqbal.submissionpemula.R;
import com.iqbal.submissionpemula.model.Char;

import java.util.ArrayList;

public class CardCharAdapter extends RecyclerView.Adapter<CardCharAdapter.CardViewViewHolder> {
    private ArrayList<Char> listChar;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public CardCharAdapter(ArrayList<Char> list) {
        this.listChar = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_char, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        Char ch = listChar.get(position);

        Glide.with(holder.itemView.getContext())
                .load(ch.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.tvName.setText(ch.getName());
        holder.tvDesc.setText(ch.getDesc());

        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Favorite " +
                       listChar.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT ).show();
            }
        });

        holder.btnSha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Share " +
                        listChar.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT ).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listChar.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChar.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDesc;
        Button btnFav, btnSha;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            btnFav = itemView.findViewById(R.id.btn_set_fav);
            btnSha = itemView.findViewById(R.id.btn_set_sha);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Char ch);
    }
}
