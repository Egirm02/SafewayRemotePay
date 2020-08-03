package com.safeway.postest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.safeway.postest.R;

public class SplitOptionFragment extends DialogFragment implements View.OnClickListener {

    Button mEbtPayButton, mSplitBalanceButton, mCancelButton;

    SplitOptionListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener =  (SplitOptionListener) context;
    }

    @Override
 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 }
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_split_option, container, false);

        mEbtPayButton = (Button)v.findViewById(R.id.ebt_pay);
        mSplitBalanceButton= (Button)v.findViewById(R.id.split_balance);
        mCancelButton = (Button)v.findViewById(R.id.cancel_button);
        mEbtPayButton.setOnClickListener(this);
        mSplitBalanceButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {

     switch (v.getId()){

         case R.id.ebt_pay:
             listener.onSelectEbtPay();
             dismiss();
             break;

        case R.id.split_balance:
             listener.onSelectSplitBalance();
             dismiss();
             break;


        case R.id.cancel_button:
             dismiss();
             break;

     }

    }
}