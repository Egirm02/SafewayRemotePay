package com.safeway.postest.fragment;

import com.safeway.postest.Data.model.SplitItem;

public interface SplitOptionListener {

    public void onSelectEbtPay();

    public  void onSelectSplitBalance();

    public void onChargeBalance(SplitItem splitItem);
}
