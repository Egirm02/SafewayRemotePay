package com.safeway.postest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class RecieptPageAdapter extends PagerAdapter {


        private Context context;
        private String receipt;
        private String total;

        public RecieptPageAdapter(Context context, String receipt, String total) {
            this.context = context;
            this.receipt = receipt;
            this.total = total;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.receipt_text_layout, collection, false);
            TextView tv_receipt = layout.findViewById(R.id.tv_receipt_text);
            TextView tv_receipt2 = layout.findViewById(R.id.tv_receipt_big);
            if(position== 0){
                tv_receipt2.setText(total);
            }else{
                tv_receipt.setText(receipt);
            }
            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
            container.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 2;
        }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return false;
//    }

    @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
}
