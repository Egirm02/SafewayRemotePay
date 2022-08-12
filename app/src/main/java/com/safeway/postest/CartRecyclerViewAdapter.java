package com.safeway.postest;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.safeway.postest.Data.NetworkManager;
import com.safeway.postest.Data.model.Cart;
import com.safeway.postest.Data.model.CartItem;
import com.safeway.postest.Data.model.Item;
import com.safeway.postest.Data.model.ItemIdRequest;
import com.safeway.postest.Data.model.ItemRequest;
import com.safeway.postest.Data.model.Items;
import com.safeway.postest.Data.remote.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    public static final int TIME_TO_WAIT = 950;

    private CartActivity cartActivity;
    public List<Items> itemList;
    private List<CartItem> mcartItemList;
    private OnItemClickListener mOnItemClickListener;
    Boolean deleteItem= false;
    Integer count;
    Boolean quant = true;



    public CartRecyclerViewAdapter(OnItemClickListener onItemClickListener){
        itemList = new ArrayList<>();
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setCartActivity(CartActivity cartActivity) {
        this.cartActivity = cartActivity;
    }

    @NonNull
    @Override
    public CartRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart_layout,parent,false);
        CartRecyclerViewAdapter.ViewHolder viewHolder = new CartRecyclerViewAdapter.ViewHolder(view,mOnItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdapter.ViewHolder holder, int position) {
        Item cartItem = itemList.get(position).getItem();
        Items cartItem2 = itemList.get(position);
        Items cartGuid = itemList.get(0);
      //  CartItem cartItemList =  mcartItemList.get(position);
      //  CartItem cart = cartList.getData().get(position;

        holder.txtName.setText(cartItem.getPosDescription());
        holder.txtPrice.setText("$ " + cartItem.getSellPrice().toString());
        holder.etqunt.setText(cartItem.getSellMultiple().toString());

//        if(cartItem.getPosDescription().toString().equals("NEAR EAST TOASTED")){
//            holder.txtPrice.setText("$ " + cartItem.getSellPrice().toString());
//            holder.txtPriceDiscounted.setText("$ 3.79");
//            holder.txtPriceDiscounted.setPaintFlags(holder.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.txtPriceDiscounted.setTextColor(Color.parseColor("#dedede"));
//            holder.txtPriceDiscounted.setVisibility(View.VISIBLE);
//        }else{
//            holder.txtPriceDiscounted.setVisibility(View.GONE);
//        }

        //stepper view for  weight items
        if (cartItem.getWeightItem().equals(true)) {
            Double count1 = cartItem2.getWeight();
            holder.etqunt.getLayoutParams().width = 150;
            holder.minus.setVisibility(View.GONE);
            holder.plus.setVisibility(View.GONE);
            holder.minusDisabled.setVisibility(View.VISIBLE);
            holder.plusDisabled.setVisibility(View.VISIBLE);
             count= Integer.valueOf(count1.intValue());
            holder.etqunt.setText(count1.toString());
        } else {
            // stepper view for upc type2 wight embedded items
            if (cartItem.getItemId().startsWith("02")||cartItem.getItemId().startsWith("2")||cartItem.getItemId().startsWith("002")) {
                Double count1= cartItem2.getWeight();
                holder.etqunt.getLayoutParams().width =150;
                holder.minus.setVisibility(View.GONE);
                holder.plus.setVisibility(View.GONE);
                holder.minusDisabled.setVisibility(View.VISIBLE);
                holder.plusDisabled.setVisibility(View.VISIBLE);
//                count= Integer.valueOf(count1.intValue());
//                holder.etqunt.setText(count1.toString());
                count = cartItem2.getQuantity();
                holder.etqunt.setText(count.toString());
            } else {
                holder.minus.setVisibility(View.VISIBLE);
                holder.plus.setVisibility(View.VISIBLE);
                holder.minusDisabled.setVisibility(View.GONE);
                holder.plusDisabled.setVisibility(View.GONE);
                count = cartItem2.getQuantity();
                holder.etqunt.setText(count.toString());
            }
        }

            if(cartItem.getRemovedItem()!= null &&cartItem.getRemovedItem()){
           holder.removeText.setText("Recover");
           holder.txtName.setPaintFlags(holder.txtName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
           holder.txtPrice.setPaintFlags(holder.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
           holder.txtName.setTextColor(Color.parseColor("#dedede"));
           holder.txtPrice.setTextColor(Color.parseColor("#dedede"));
            }
            else {
                holder.removeText.setText("Remove");
                holder.txtName.setPaintFlags(holder.txtName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                holder.txtPrice.setPaintFlags(holder.txtPrice.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                holder.txtName.setTextColor(Color.parseColor("#000000"));
                holder.txtPrice.setTextColor(Color.parseColor("#000000"));
            }
        Picasso.get().load(R.drawable.icon_ebt_green)
                .into(holder.ivebt);
        if(cartItem.getFoodStamp()==true){
            holder.ivebt.setVisibility(View.VISIBLE);

        }
        else{
            holder.ivebt.setVisibility(View.INVISIBLE);
        }

        Handler myHandler = new Handler();
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ItemRequest itemRequest;
               //     itemRequest = new ItemRequest(mItem.getItem().getItemId(), count, mItem.getUpcType(), mItem.getScanCode(), false, mItem.getItem().getClubPrice(), mItem.getItem().getJfuOffers());

                   // homeActivityViewModel.updateHandler(itemRequest, false);

                    itemRequest=   new ItemRequest(cartItem.getItemId(),count,cartItem2.getUpcType(),cartItem2.getScanCode(),false);

                cartActivity.addItemToCart("default",itemRequest,cartItem.getStoreId(),cartGuid.getGuid());
                } catch (Exception e) {
                    cartActivity.loadingLayout.setVisibility(View.GONE);
                }
                count = cartItem2.getQuantity();
                quant = true;
            }
        };

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quant) {
                    count = cartItem2.getQuantity();
                    quant = false;
                }
                count++;
//                ItemRequest itemRequest = new ItemRequest(mItem.getItem().getItemId(),count,mItem.getUpcType(),mItem.getScanCode(),false,mItem.getItem().getClubPrice());
//                homeActivityViewModel.updateHandler(itemRequest,false);
                //  homeActivityViewModel.addItemToCart(mItem.getItem().getItemId(),mItem.getUpcType(),mItem.getScanCode(),mItem.isWeightItem(),count,false,mItem.getItem().getClubPrice());  // updateItem(count,true,true);
//                holder.cartItemBinding.minus.setVisibility(View.VISIBLE);
//                holder.cartItemBinding.minusDisabled.setVisibility(View.GONE);
                holder.etqunt.setText(count.toString());
                myHandler.removeCallbacks(myRunnable);
                myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
                cartActivity.loadingLayout.setVisibility(View.VISIBLE);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quant) {
                    count = cartItem2.getQuantity();
                    quant = false;
                }
//                if(count ==2){
//                    holder.cartItemBinding.minus.setVisibility(View.GONE);
//                    holder.cartItemBinding.minusDisabled.setVisibility(View.VISIBLE);
//                }else {
//                    holder.cartItemBinding.minus.setVisibility(View.VISIBLE);
//                    holder.cartItemBinding.minusDisabled.setVisibility(View.GONE);
//                }
                if (count > 1) {
                    count--;
//                ItemRequest itemRequest = new ItemRequest(mItem.getItem().getItemId(),count,mItem.getUpcType(),mItem.getScanCode(),false,mItem.getItem().getClubPrice());
//                homeActivityViewModel.updateHandler(itemRequest,false);
                    //  homeActivityViewModel.addItemToCart(mItem.getItem().getItemId(),mItem.getUpcType(),mItem.getScanCode(),mItem.isWeightItem(),count,false,mItem.getItem().getClubPrice());  // updateItem(count,true,true);
                    holder.etqunt.setText(count.toString());
                    myHandler.removeCallbacks(myRunnable);
                    myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
                    cartActivity.loadingLayout.setVisibility(View.VISIBLE);
                }
//                else{
//                    holder.cartItemBinding.minus.setVisibility(View.GONE);
//                    holder.cartItemBinding.minusDisabled.setVisibility(View.VISIBLE);
//                }
            }
        });

        Picasso.get().load(
              //  "https://images.albertsons-media.com/is/image/ABS/0"+cartItem.getScanCode()+"?$ecom-product-card-desktop$&defaultImage=Not_Available"
                cartItem.getImageUrl()
        )
                .resize(250,250)
                .centerCrop()
                .into(holder.ivItem);
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

    public void setData(List<Items> data){
        this.itemList = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtName;
        public TextView txtPlu;
        public TextView txtPrice;
        public TextView txtPriceDiscounted;
        private ImageView ivItem;
        public CardView cardView;
        public ImageView ivebt;
        public EditText etqunt;
        public LinearLayout removerIcon;
        public TextView removeText;
        public ImageView minus;
        public ImageView minusDisabled;
        public ImageView plus;
        public ImageView plusDisabled;

        OnItemClickListener onItemClickListener;


        public ViewHolder(@NonNull View view,  OnItemClickListener onItemClickListener) {
            super(view);
            ivebt= view.findViewById(R.id.iv_ebt);
            ivItem= view.findViewById(R.id.ivImage);
            txtName = view.findViewById(R.id.txtName);
            etqunt = view.findViewById(R.id.et_qunt);
//            txtPlu = view.findViewById(R.id.txtPLU);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtPriceDiscounted = view.findViewById(R.id.txtPriceDiscounted);
            cardView = view.findViewById(R.id.cardView);
            removerIcon = view.findViewById(R.id.ll_removeIcon);
            removeText = view.findViewById(R.id.remove_text);
            minus = view.findViewById(R.id.minus);
            minusDisabled = view.findViewById(R.id.minus_disabled);
            plus = view.findViewById(R.id.plus);
            plusDisabled = view.findViewById(R.id.plus_disabled);
            removerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition(),deleteItem);
                }
            });
            this.onItemClickListener = onItemClickListener;
        //    view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.ll_removeIcon:
                    onItemClickListener.onItemClick(getAdapterPosition(),deleteItem);
                    break;
            }
           // Item cartItem = itemList.get(getAdapterPosition());
//            removerIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    deleteItem = true;
//                }
//            });
           // onItemClickListener.onItemClick(getAdapterPosition(),deleteItem);
           // notifyItemChanged(getAdapterPosition());
          //  deleteItem=false;

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position,Boolean delete);
    }

//    public void removeItems(ItemIdRequest itemIdRequest, String storeId){
//        Service apiService = NetworkManager.createRetrofit().create(Service.class);
//        apiService.deleteItemFromCart(itemIdRequest,storeId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//
//    }
}
