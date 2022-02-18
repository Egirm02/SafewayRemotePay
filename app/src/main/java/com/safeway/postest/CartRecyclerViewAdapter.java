package com.safeway.postest;

import android.graphics.Color;
import android.graphics.Paint;
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


    public List<Items> itemList;
    private OnItemClickListener mOnItemClickListener;
    Boolean deleteItem= false;

    public CartRecyclerViewAdapter(OnItemClickListener onItemClickListener){
        itemList = new ArrayList<>();
        this.mOnItemClickListener = onItemClickListener;
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
       // CartItem cart = cartList.getData().get(position;

        holder.txtName.setText(cartItem.getPosDescription());
        holder.txtPrice.setText("$ " + cartItem.getSellPrice().toString());
        holder.etqunt.setText(cartItem.getSellMultiple().toString());

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
//        if(cartItem.getFoodStamp()==true){
//            holder.ivebt.setVisibility(View.VISIBLE);
//
//        }
//        else{
//            holder.ivebt.setVisibility(View.INVISIBLE);
//        }

//        holder.removerIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteItem = true;
//                mOnItemClickListener.onItemClick(position,deleteItem);
////                List<String> ids = Collections.singletonList(cartItem.getItem().getItemId());
////                ItemIdRequest itemIdRequest = new ItemIdRequest(ids);
////                removeItems(itemIdRequest,"9879");
//            }
//        });

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
//        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtName;
        public TextView txtPlu;
        public TextView txtPrice;
        private ImageView ivItem;
        public CardView cardView;
        public ImageView ivebt;
        public EditText etqunt;
        public LinearLayout removerIcon;
        public TextView removeText;

        OnItemClickListener onItemClickListener;


        public ViewHolder(@NonNull View view,  OnItemClickListener onItemClickListener) {
            super(view);
            ivebt= view.findViewById(R.id.iv_ebt);
            ivItem= view.findViewById(R.id.ivImage);
            txtName = view.findViewById(R.id.txtName);
            etqunt = view.findViewById(R.id.et_qunt);
//            txtPlu = view.findViewById(R.id.txtPLU);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
            removerIcon = view.findViewById(R.id.ll_removeIcon);
            removeText = view.findViewById(R.id.remove_text);
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
//            switch (view.getId())
//            {
//                case R.id.ll_removeIcon:
//                    onItemClickListener.onItemClick(getAdapterPosition(),deleteItem);
//                    break;
//            }
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
