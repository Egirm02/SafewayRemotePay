package com.safeway.postest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.safeway.postest.Data.model.Cart;
import com.safeway.postest.Data.model.CartItem;
import com.safeway.postest.Data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartCheckoutRecyclerViewAdapter extends RecyclerView.Adapter<CartCheckoutRecyclerViewAdapter.ViewHolder> {


    public List<Item> itemList;
//    public List<CartItem> itemList2;

    public CartCheckoutRecyclerViewAdapter(){
        itemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartCheckoutRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart_layout,parent,false);
        CartCheckoutRecyclerViewAdapter.ViewHolder viewHolder = new CartCheckoutRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartCheckoutRecyclerViewAdapter.ViewHolder holder, int position) {
        Item cartItem = itemList.get(position);
       // CartItem cart = cartList.getData().get(position;

        holder.txtName.setText(cartItem.getExtDescription());
        holder.txtPrice.setText("$ " + cartItem.getRegularPrice().toString());
        holder.etqunt.setText("1");
        Picasso.get().load(R.drawable.icon_ebt_green)
                .into(holder.ivebt);
        if(cartItem.getFoodStamp()==true){
            holder.ivebt.setVisibility(View.VISIBLE);

        }else{
            holder.ivebt.setVisibility(View.INVISIBLE);
        }

//        Picasso.get().load(cartItem.getImageUrl())
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

    public void setData(List<Item> data){
        this.itemList = data;
//        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtPlu;
        public TextView txtPrice;
        private ImageView ivItem;
        public CardView cardView;
        public ImageView ivebt;
        public EditText etqunt;

        public ViewHolder(@NonNull View view) {
            super(view);
            ivebt= view.findViewById(R.id.iv_ebt);
            ivItem= view.findViewById(R.id.ivImage);
            txtName = view.findViewById(R.id.txtName);
            etqunt = view.findViewById(R.id.et_qunt);
//            txtPlu = view.findViewById(R.id.txtPLU);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
