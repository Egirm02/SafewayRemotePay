package com.safeway.postest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.safeway.postest.Data.model.CartItem;
import com.safeway.postest.Data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {


    public List<CartItem> itemList;
//    public List<CartItem> itemList2;

    public MainRecyclerViewAdapter(){
        itemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart_layout,parent,false);
        MainRecyclerViewAdapter.ViewHolder viewHolder = new MainRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
//        Item cartItem = itemList.get(position);
//       // CartItem cart = cartList.getData().get(position;
//
//        holder.txtName.setText(cartItem.getItem().getExtDescription());
//        holder.txtPrice.setText(cartItem.getItem().getRegularPrice().toString());
//        Picasso.get().load(cartItem.getItem().getImageUrl())
//                .resize(250,250)
//                .centerCrop()
//                .into(holder.ivItem);
//
//        holder.txtName.setText(cart.getItem().getExtDescription());
//        holder.txtPrice.setText(cart.getItem().getItemPrice().toString());
//        Picasso.get().load(cart.getItem().getImageUrl())
//                .resize(250,250)
//                .centerCrop()
//                .into(holder.ivItem);

    }

    @Override
    public int getItemCount() {
        return itemList.size() ;
    }

    public void setData(List<CartItem> data){
        this.itemList = data;
//        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtPlu;
        public TextView txtPrice;
        private ImageView ivItem;
        public CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);

            ivItem= view.findViewById(R.id.ivImage);
            txtName = view.findViewById(R.id.txtName);
//            txtPlu = view.findViewById(R.id.txtPLU);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
