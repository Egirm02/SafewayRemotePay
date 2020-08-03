package com.safeway.postest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safeway.postest.Data.model.SplitItem;
import com.safeway.postest.R;

import java.util.ArrayList;
import java.util.List;

public class SplitBalanceFragment extends DialogFragment implements View.OnClickListener, SplitAdapter.SplitAdapterListener {

    public static final int SPLIT_LIMIT = 5;
    public static final String AMOUNT = "amount";

    Button mIncrementButton, mDecrementButton;
    TextView mSplitsizeTv, mTotalAmountTv;

    SplitOptionListener listener;

    RecyclerView mPaymentReclerview;
    SplitAdapter mSplitAdapter;
    List<SplitItem> balanceList = new ArrayList<>();

    int splitNumber = 2;
    double totalAmount;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener =  (SplitOptionListener) context;
    }


    public static SplitBalanceFragment newInstance(Double totalAmount){

        SplitBalanceFragment fragment = new SplitBalanceFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(AMOUNT, totalAmount);
        fragment.setArguments(bundle);
        return  fragment;

    }

    @Override
 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 }
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_split_balance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIncrementButton = (Button)view.findViewById(R.id.increment);
        mDecrementButton= (Button)view.findViewById(R.id.decrement);
        mTotalAmountTv = (TextView)view.findViewById(R.id.total_balance);
        mSplitsizeTv = (TextView)view.findViewById(R.id.sizeValue);
        mSplitsizeTv.setText(String.valueOf(splitNumber));
        mIncrementButton.setOnClickListener(this);
        mDecrementButton.setOnClickListener(this);

        totalAmount = getArguments().getDouble(AMOUNT);

        mTotalAmountTv.setText(String.valueOf(totalAmount));
        mPaymentReclerview = view.findViewById(R.id.rv_split);
        mPaymentReclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        mSplitAdapter = new SplitAdapter(this, balanceList);
        mPaymentReclerview.setAdapter(mSplitAdapter);

        splitItems(splitNumber, totalAmount);

    }

    @Override
    public void onClick(View v) {

     switch (v.getId()){

         case R.id.increment:
             if(splitNumber < SPLIT_LIMIT) {
                 splitNumber++;
                 mSplitsizeTv.setText(String.valueOf(splitNumber));
                 splitItems(splitNumber, totalAmount);
             }
             break;

        case R.id.decrement:
            if (splitNumber > 1) {
                splitNumber--;
                balanceList.remove(balanceList.size() - 1);
                mSplitsizeTv.setText(String.valueOf(splitNumber));
                splitItems(splitNumber, totalAmount);
            }
             break;


        case R.id.cancel_button:
             dismiss();
             break;

     }

    }


//    public void splitItems(int splitNumber, double totalAmount){
//
//        balanceList.clear();
//
//        for(int i= 0; i<splitNumber; i++){
//
//            SplitItem splitItem = new SplitItem();
//            splitItem.amount = totalAmount/splitNumber;
//
//            balanceList.add(splitItem);
//        }
//
//        mSplitAdapter.notifyDataSetChanged();
//    }



    public void splitItems(int splitNumber, double totalAmount){
        SplitItem splitItem;

        for(int i= 0; i < splitNumber; i++){

            if(i < balanceList.size()){

                splitItem =  balanceList.get(i);
                splitItem.amount = totalAmount/splitNumber;
            }else {
                splitItem = new SplitItem();
                splitItem.amount = totalAmount/splitNumber;
                balanceList.add(splitItem);
            }

        }

        mSplitAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickCancel(int position) {
        if(splitNumber > 1) {
            splitNumber--;
            balanceList.remove(position);
            splitItems(splitNumber, totalAmount);
            mSplitsizeTv.setText(String.valueOf(splitNumber));
        }

    }

    @Override
    public void onClickCharge(int position) {
        listener.onChargeBalance(balanceList.get(position));
    }
}