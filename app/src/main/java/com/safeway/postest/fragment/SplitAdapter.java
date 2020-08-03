package com.safeway.postest.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.safeway.postest.Data.model.SplitItem;
import com.safeway.postest.MainActivity;
import com.safeway.postest.R;

import java.util.List;

public class SplitAdapter extends RecyclerView.Adapter<SplitAdapter.MyViewHolder> {
   private List<SplitItem> balanceList;

   public interface SplitAdapterListener {
      void onClickCancel(int position);
      void onClickCharge(int position);
   }

   SplitAdapterListener mListener;
   class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, TextWatcher {
      ImageView cancelButton;
      Button chargeButton;
      EditText balanceEt;
      TextView paymentOptions;
      ImageView optionSelection;

      MyViewHolder(View view) {
         super(view);
         cancelButton = view.findViewById(R.id.cross_id);
         chargeButton = view.findViewById(R.id.charge_button);
         balanceEt = view.findViewById(R.id.balance_et);
         paymentOptions = view.findViewById(R.id.payment_options);
         optionSelection = view.findViewById(R.id.select_arrow);

         cancelButton.setOnClickListener(this);
         chargeButton.setOnClickListener(this);
         paymentOptions.setOnClickListener(this);
         optionSelection.setOnClickListener(this);
   //      balanceEt.addTextChangedListener(this);
      }

      @Override
      public void onClick(View v) {

         switch (v.getId()){

            case R.id.cross_id:
               mListener.onClickCancel(getAdapterPosition());
               break;

            case R.id.select_arrow:
            case R.id.payment_options:
               showPopup(v, getAdapterPosition());
               break;
            case R.id.charge_button:
               mListener.onClickCharge(getAdapterPosition());
               break;
         }
      }


      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        String amountText = s.toString();
        if(!amountText.isEmpty()){
           balanceList.get(getAdapterPosition()).amount = Double.valueOf(amountText);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
//         balanceEt.post(new Runnable() {
//            @Override
//            public void run() {
//               notifyDataSetChanged();
//            }
//         });
      }
   }

   public SplitAdapter(SplitAdapterListener listener , List<SplitItem> balanceList) {
      this.balanceList = balanceList;
      this.mListener = listener;
   }

   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.rview_split_item, parent, false);
      return new MyViewHolder(itemView);
   }
   @Override
   public void onBindViewHolder(MyViewHolder holder, int position) {
      SplitItem item = balanceList.get(position);

      String amountValue = String.format("%.2f", item.amount);
      holder.balanceEt.setText(amountValue);
      holder.paymentOptions.setText(item.paymentType);

//      holder.title.setText(movie.getTitle());
//      holder.genre.setText(movie.getGenre());
//      holder.year.setText(movie.getYear());


   }
   @Override
   public int getItemCount() {
      return balanceList.size();
   }



   private void showPopup(View view, int position){

      PopupMenu popup = new PopupMenu(view.getContext(), view);
      popup.getMenuInflater().inflate(R.menu.payment_menu, popup.getMenu());

      popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
         public boolean onMenuItemClick(MenuItem item) {
            balanceList.get(position).paymentType = item.getTitle().toString();
            notifyDataSetChanged();
            return true;
         }
      });

      popup.show();
   }
}