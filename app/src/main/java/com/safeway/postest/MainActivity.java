package com.safeway.postest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clover.connector.sdk.v3.PaymentConnector;
//import com.clover.remote.client.CloverConnectorFactory;
//import com.clover.remote.client.CloverDeviceConfiguration;
//import com.clover.remote.client.DefaultCloverConnectorListener;
//import com.clover.remote.client.ICloverConnector;
//import com.clover.remote.client.MerchantInfo;
//import com.clover.remote.client.USBCloverDeviceConfiguration;
//import com.clover.remote.client.messages.PrintRequest;
//import com.clover.remote.client.messages.RetrievePrintersRequest;
//import com.clover.remote.client.messages.RetrievePrintersResponse;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.Intents;
import com.clover.sdk.v1.printer.job.PrintJob;
import com.clover.sdk.v1.printer.job.TextPrintJob;
import com.clover.sdk.v1.printer.job.ViewPrintJob;
import com.clover.sdk.v3.connector.ExternalIdUtils;
import com.clover.sdk.v3.connector.IPaymentConnector;
import com.clover.sdk.v3.connector.IPaymentConnectorListener;
import com.clover.sdk.v3.order.LineItem;
import com.clover.sdk.v3.order.Order;
import com.clover.sdk.v3.order.OrderConnector;
import com.clover.sdk.v3.order.OrderContract;
import com.clover.sdk.v3.payments.CardTransaction;
import com.clover.sdk.v3.payments.CardType;
import com.clover.sdk.v3.payments.Payment;
import com.clover.sdk.v3.printer.Printer;
import com.clover.sdk.v3.remotepay.AuthRequest;
import com.clover.sdk.v3.remotepay.AuthResponse;
import com.clover.sdk.v3.remotepay.CapturePreAuthResponse;
import com.clover.sdk.v3.remotepay.CloseoutResponse;
import com.clover.sdk.v3.remotepay.ConfirmPaymentRequest;
import com.clover.sdk.v3.remotepay.ManualRefundResponse;
import com.clover.sdk.v3.remotepay.PreAuthResponse;
import com.clover.sdk.v3.remotepay.ReadCardDataResponse;
import com.clover.sdk.v3.remotepay.RefundPaymentRequest;
import com.clover.sdk.v3.remotepay.RefundPaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePendingPaymentsResponse;
import com.clover.sdk.v3.remotepay.SaleRequest;
import com.clover.sdk.v3.remotepay.SaleResponse;
import com.clover.sdk.v3.remotepay.TipAdded;
import com.clover.sdk.v3.remotepay.TipAdjustAuthResponse;
import com.clover.sdk.v3.remotepay.VaultCardResponse;
import com.clover.sdk.v3.remotepay.VerifySignatureRequest;
import com.clover.sdk.v3.remotepay.VoidPaymentRefundResponse;
import com.clover.sdk.v3.remotepay.VoidPaymentRequest;
import com.clover.sdk.v3.remotepay.VoidPaymentResponse;
import com.daimajia.swipe.SwipeLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.safeway.postest.Data.NetworkManager;

import com.safeway.postest.Data.RecalculateTransaction;
import com.safeway.postest.Data.Transactions;
import com.safeway.postest.Data.model.BaseResponse;
import com.safeway.postest.Data.model.COA_Request;
import com.safeway.postest.Data.model.COA_Response;
import com.safeway.postest.Data.model.FinalizeSplitTransaction;
import com.safeway.postest.Data.model.Item;
import com.safeway.postest.Data.model.SplitItem;
import com.safeway.postest.Data.model.receipt.Data;
import com.safeway.postest.Data.remote.Service;
import com.safeway.postest.fragment.SplitBalanceFragment;
import com.safeway.postest.fragment.SplitOptionListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements SplitOptionListener {

    IPaymentConnector paymentConnector;
  //  ICloverConnector cloverConnector;

    //EditText amountET;
    TextView amountET;
    TextView amountTax;
    TextView amountSubTotal;
    TextView amountAppliedDiscounts;
    TextView amountEBT;
    TextView amountNonEBT;
    EditText et_ebt_dialog;
    EditText et_eft_dialog;
    EditText et_coa_dialog;
    TextView tvEbtError, tvEftError;
    TextView tv_reciept,tv_reciept1;
    TextView tv_ebtProcessedPaymentAmount;
    TextView tv_eftProcessedPaymentAmount;
    TextView tv_voidEBT;
    TextView tv_voidEFT;
    FrameLayout fl_receipt;

    Button payButton;
    Button authorize;
    Button splitButton;
    Button printReceipt,refund;
    Button btn_processCOA_OK;

    Boolean balanceEFTRemaining = false;
    Boolean orderProcessed=false;
    Boolean orderComplete=false;
    Double EBT_PaidAmount=0.00;
    Double EFT_PaidAmount=0.00;

    RelativeLayout rl_COA;
    LinearLayout ll_processed_payment;
    ConstraintLayout loadingLayout;
    SwipeLayout ll_EBTprocessed_payment;
    SwipeLayout ll_EFTprocessed_payment;

    FrameLayout voidEBTPayment, voidEFTPayment;

    RelativeLayout EBTpaymentOption,EFTpaymentOption;

    Transactions transactionInfoListEBTnew = null;

    private List<Transactions> allTransactionsList = new ArrayList<>();
    public List<Item> itemList;

    public static List<Printer> printers;
    private Printer printer;
    private String lastPrintRequestId;

    String amountValue;
    String orderId;
    String storeId;
    String clubcard;
    private static final int KIOSK_CARD_ENTRY_METHODS = 1 << 15;
    public static final int CARD_ENTRY_METHOD_MAG_STRIPE = 0b0001 | 0b0001_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33026
    public static final int CARD_ENTRY_METHOD_ICC_CONTACT = 0b0010 | 0b0010_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33282
    public static final int CARD_ENTRY_METHOD_NFC_CONTACTLESS = 0b0100 | 0b0100_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33796
    public static final int CARD_ENTRY_METHOD_MANUAL = 0b1000 | 0b1000_00000000 | KIOSK_CARD_ENTRY_METHODS; // 34824

    public static final String SPLIT_DIALOG = "split_dialog";
    public static final String SPLIT_BALANCE = "split_balance";

    private static final String REMOTE_APPLICATION_ID = "0GKBMAW7ER9SW.F9TBFQ7XV83FW";

    double total;
    double tax;
    double SubTotal;
    double AppliedDiscounts;
    double EBT;
    double nonEBT;
    String recalculation;
    CheckBox EBT_Option, nonEBT_Option;
    RecyclerView cartRecyclerView;
    Boolean authorizeEBT=false;
    String receipt;
    String finalSubTotal,finalSavings,finalTax,finalTotal,finalEBT_Balance,final_COA;
    Long finalEBT_Balance_long;
    Boolean ebt_recalcualted= false;
    Boolean COA=false;
   // CartCheckoutRecyclerViewAdapter cartRecyclerViewAdapter;

    Payment payment;
    Payment paymentEBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountSubTotal = (TextView)findViewById(R.id.tv_subtotal);
        amountTax = (TextView)findViewById(R.id.tv_tax);
        amountET =(TextView)findViewById(R.id.amount);
        amountAppliedDiscounts = (TextView)findViewById(R.id.tv_applied_discounts);
        amountEBT = (TextView)findViewById(R.id.tv_ebt_total);
        amountNonEBT = (TextView)findViewById(R.id.tv_ebt_total1);
        authorize = (Button) findViewById(R.id.btn_PaymentAuthorize_dialog);
        EBT_Option = (CheckBox) findViewById(R.id.rb_EBT_select);
        nonEBT_Option = (CheckBox) findViewById(R.id.rb_EFT_select);
        tv_voidEBT =(TextView) findViewById(R.id.tv_voidEBT);
        tv_voidEFT =(TextView) findViewById(R.id.tv_voidEFT);

        EBTpaymentOption = findViewById(R.id.ll_EBT_payment);
        EFTpaymentOption = findViewById(R.id.ll_EFT_amaount);
        ll_processed_payment = (LinearLayout) findViewById(R.id.ll_processed_payment);
        ll_EBTprocessed_payment = findViewById(R.id.ll_EBTprocessed_payment);
        ll_EFTprocessed_payment = findViewById(R.id.ll_EFTprocessed_payment);
        loadingLayout = findViewById(R.id.loadingLayoutMain);

        tv_ebtProcessedPaymentAmount = (TextView) findViewById(R.id.tv_ebtProcessedPaymentAmount);
        tv_eftProcessedPaymentAmount = (TextView) findViewById(R.id.tv_eftProcessedPaymentAmount);

        voidEBTPayment = findViewById(R.id.remove_ebt_payment);
        voidEFTPayment = findViewById(R.id.remove_eft_payment);

       // tv_reciept = findViewById(R.id.tv_receipt);

        payButton = (Button)findViewById(R.id.btn_PayButton);
        printReceipt = (Button) findViewById(R.id.btn_Print);
        rl_COA= (RelativeLayout) findViewById(R.id.rl_COA);
        et_coa_dialog = (EditText) findViewById(R.id.et_coa_dialog);
        btn_processCOA_OK = (Button) findViewById(R.id.btn_processCOA_OK);
        refund = findViewById(R.id.btn_refund);
       // splitButton =(Button) findViewById(R.id.btn_PaySplitButton);

        //cartRecyclerView = findViewById(R.id.rv_cart2);
//        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        cartRecyclerViewAdapter = new CartCheckoutRecyclerViewAdapter();
//        cartRecyclerView.setAdapter(cartRecyclerViewAdapter);

        String sessionId = getIntent().getStringExtra("checkoutTotal");
        String subTotalId = getIntent().getStringExtra("subTotal");
        String taxId = getIntent().getStringExtra("taxTotal");
        String appliedDiscountsId = getIntent().getStringExtra("appliedDiscountsTotal");
        String ebtId = getIntent().getStringExtra("ebtTotal");
                receipt = getIntent().getStringExtra("receipt");
               recalculation = getIntent().getStringExtra("recalculation");
                 orderId = getIntent().getStringExtra("orderId");
                 storeId = getIntent().getStringExtra("storeId");
                 clubcard= getIntent().getStringExtra("clubcard");

        finalSubTotal = subTotalId;
        finalSavings = appliedDiscountsId;
        finalTax =taxId;
        finalTotal = sessionId;

         total = Double.valueOf(sessionId);
         tax = Double.valueOf(taxId);
         SubTotal =Double.valueOf(subTotalId);
         AppliedDiscounts =Double.valueOf(appliedDiscountsId);
         EBT = Double.valueOf(ebtId);

         nonEBT = SubTotal-EBT;
    //Check to see if the balance is COA eligible
//        total = total *-1;
        if(total< 0){
            payButton.setVisibility(View.GONE);
            rl_COA.setVisibility(View.VISIBLE);

            COA = true;
        }
            amountET.setText("$ "+String.format("%.2f", total));
            amountTax.setText("$ "+String.format("%.2f", tax)+" ");
            amountSubTotal.setText("$ "+String.format("%.2f",(SubTotal)));
            amountAppliedDiscounts.setText("$ "+"("+String.format("%.2f",AppliedDiscounts)+")");
            amountEBT.setText("$ "+String.format("%.2f",EBT)+" ");
            amountNonEBT.setText("$ "+String.format("%.2f",nonEBT)+" ");
           // tv_reciept.setText(receipt);



//         splitButton.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
////                 amountValue = String.valueOf(EBT);
////                 amountEBT.setText("$ "+String.format("%.2f",0.00)+" ");
////                 amountET.setText("$ "+String.format("%.2f", total-EBT));
////                 amountSubTotal.setText("$ "+String.format("%.2f",(SubTotal-EBT)));
////                 splitButton.setVisibility(View.GONE);
////                 payButton.setText("Complete Pay");
////                 SaleRequest saleRequest =  setupSaleRequest(amountValue);
////                 paymentConnector.sale(saleRequest);
//
//
//                 FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                 SplitOptionFragment dialogFragment = new SplitOptionFragment();
//                 dialogFragment.show(ft, SPLIT_DIALOG);
//
//             }
//         });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (COA) {
////                    Date currentTime = Calendar.getInstance().getTime();
////                    String time = String.valueOf(currentTime);
////                    String currentAmount = String.valueOf(total);
////                    Transactions transactionsCOA = new Transactions("NA","COA","NA",currentAmount,false,time);
////                    List<Transactions> transactions = Collections.singletonList(transactionsCOA);
////
////                    FinalizeSplitTransaction finalizeSplitTransaction = new FinalizeSplitTransaction(orderId,transactions);
////                    finalizeTransaction(finalizeSplitTransaction);
//                    COA_Request coa_request = new COA_Request( clubcard,orderId,total);
//                    processCOA(coa_request);
//                    loadingLayout.setVisibility(View.VISIBLE);
//                    payButton.setVisibility(View.GONE);
//                    COA =false;
//                } else{
                    amountValue = String.valueOf(total - EBT);

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.payment_dialog, null);

                EBTpaymentOption = mView.findViewById(R.id.ll_EBT_payment);
                EFTpaymentOption = mView.findViewById(R.id.ll_EFT_amaount);
                LinearLayout eBTEditLayout = mView.findViewById(R.id.ebtEditLayout);
                LinearLayout eFTEditLayout = mView.findViewById(R.id.eftEditLayout);
                ImageView cancelButton = mView.findViewById(R.id.cancel_button);
                EBT_Option = (CheckBox) mView.findViewById(R.id.rb_EBT_select);
                nonEBT_Option = (CheckBox) mView.findViewById(R.id.rb_EFT_select);
                et_ebt_dialog = mView.findViewById(R.id.tv_ebt_dialog);
                et_eft_dialog = mView.findViewById(R.id.tv_eft_dialog);

                tvEbtError = mView.findViewById(R.id.tv_ebt_error);
                tvEftError = mView.findViewById(R.id.tv_eft_error);
                authorize = (Button) mView.findViewById(R.id.btn_PaymentAuthorize_dialog);

                et_ebt_dialog.addTextChangedListener(textWatcher);
                et_eft_dialog.addTextChangedListener(textWatcher);
                mbuilder.setView(mView);

                AlertDialog dialog = mbuilder.create();

                dialog.show();


                if (balanceEFTRemaining) {
                    EBTpaymentOption.setVisibility(View.GONE);
                    authorizeEBT = false;
                }

                try {
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    EBTpaymentOption.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EBT_Option.toggle();
                            if (EBT_Option.isChecked()) {
                                eBTEditLayout.setVisibility(View.VISIBLE);
                                et_ebt_dialog.setText((String.format("%.2f", EBT)));
                                et_ebt_dialog.setSelection(et_ebt_dialog.getText().length());
                                et_ebt_dialog.requestFocus();
                                EBTpaymentOption.setBackgroundColor(getResources().getColor(R.color.brown_grey));
                                authorize.setEnabled(true);
                                authorize.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                authorizeEBT = true;
                                if (nonEBT_Option.isChecked()) {
                                    EFTpaymentOption.callOnClick();
                                }
                            } else {
                                eBTEditLayout.setVisibility(View.INVISIBLE);
                                et_ebt_dialog.setText(String.format("%.2f", EBT));
                                EBTpaymentOption.setBackgroundColor(getResources().getColor(R.color.colorUnSelected));
                                if (!nonEBT_Option.isChecked()) {
                                    authorize.setEnabled(false);
                                    authorize.setBackgroundColor(getResources().getColor(R.color.gray));
                                    authorizeEBT = false;
                                }

                            }
                        }
                    });

                    EFTpaymentOption.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            nonEBT_Option.toggle();
                            if (nonEBT_Option.isChecked()) {
                                eFTEditLayout.setVisibility(View.VISIBLE);
                                DecimalFormat df = new DecimalFormat("#.####");
                                df.setRoundingMode(RoundingMode.CEILING);
                                double total1 = total;
                                et_eft_dialog.setText(String.format("%.2f", total));
                                et_eft_dialog.setSelection(et_eft_dialog.getText().length());
                                et_eft_dialog.requestFocus();
                                EFTpaymentOption.setBackgroundColor(getResources().getColor(R.color.brown_grey));
                                authorize.setEnabled(true);
                                authorize.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                if (balanceEFTRemaining == false) {
                                    authorizeEBT = false;
                                } else {
                                    authorizeEBT = false;
                                }

                                if (EBT_Option.isChecked()) {
                                    EBTpaymentOption.callOnClick();
                                }
                            } else {

                                eFTEditLayout.setVisibility(View.INVISIBLE);
                                et_eft_dialog.setText(String.format("%.2f", total));
                                EFTpaymentOption.setBackgroundColor(getResources().getColor(R.color.colorUnSelected));
                                if (!EBT_Option.isChecked()) {
                                    authorize.setEnabled(false);
                                    authorize.setBackgroundColor(getResources().getColor(R.color.gray));
                                    authorizeEBT = false;
                                }
                            }
                        }
                    });

                    authorize.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ll_processed_payment.setVisibility(View.VISIBLE);

                            if (authorizeEBT) {
                                onSelectEbtPay();
                            } else if (authorizeEBT == false) {
                                onSelectEftPay();
                            }
                            dialog.dismiss();
                        }
                    });

//TODO ADD support for multiple card void
                    voidEBTPayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sync();
                            loadingLayout.setVisibility(View.VISIBLE);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    payButton.setEnabled(false);
                                    payButton.setBackgroundColor(getResources().getColor(R.color.greyish));
                                    orderProcessed = false;
                                    Log.d(TAG, "onClick: Void" + "pressed void");
                                    loadingLayout.setVisibility(View.GONE);
                                    //NewEBT_FlowFlag
                                    // voidPayment(payment.getId(), payment.getOrder().getId());
                                    try {
                                        if (paymentEBT != null) {
                                            voidPayment(paymentEBT.getId(), paymentEBT.getOrder().getId());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } //Do something after 100ms
                                }
                            }, 6000);

                        }
                    });

//                    tv_voidEBT.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            payButton.setEnabled(false);
//                            payButton.setBackgroundColor(getResources().getColor(R.color.greyish));
//                            orderProcessed = false;
//                            Log.d(TAG, "onClick: Void" + "pressed void");
//                            voidPayment(payment.getId(), payment.getOrder().getId());
//                        }
//                    });
                    voidEFTPayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payButton.setEnabled(false);
                            payButton.setBackgroundColor(getResources().getColor(R.color.greyish));
                            orderProcessed = false;
                            voidPayment(payment.getId(), payment.getOrder().getId());
                         //   Toast.makeText(MainActivity.this, "EFT transaction Voided", Toast.LENGTH_SHORT).show();
                        }
                    });

//                    tv_voidEFT.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            payButton.setEnabled(false);
//                            payButton.setBackgroundColor(getResources().getColor(R.color.greyish));
//                            orderProcessed = false;
//                            voidPayment(payment.getId(), payment.getOrder().getId());
//                            Toast.makeText(MainActivity.this, "EFT transaction Voided", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
//                SaleRequest saleRequest =  setupSaleRequest(amountValue);
//                //  AuthRequest authRequest = setupAuthRequest(amountValue);
//                paymentConnector.sale(saleRequest);
//                //   paymentConnector.auth(authRequest);
          //  }
            }
        });

        btn_processCOA_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ordernum = et_coa_dialog.getText().toString();
                try {
                 Double total2 = total*-1;
                    COA_Request coa_request = new COA_Request( clubcard,ordernum ,total2);
                    processCOA(coa_request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadingLayout.setVisibility(View.VISIBLE);

            }
        });

        tv_reciept1 = findViewById(R.id.tv_receipt1);
        tv_reciept1.setText(receipt);
        fl_receipt = findViewById(R.id.fl_receipt1);
        fl_receipt.setVisibility(View.INVISIBLE);
        printReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickPrintReceipt1(receipt);

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
                View nView = getLayoutInflater().inflate(R.layout.receipt_layout, null);
                mbuilder.setView(nView);
                ViewPager viewPager =nView.findViewById(R.id.viewpager);
                String reciptTotal = "";
                if(finalEBT_Balance_long!=null){
                  //  Double formatEBT = Double.valueOf(finalEBT_Balance);
                    double d = Math.round(Double.valueOf(finalEBT_Balance_long)/100);
                    finalEBT_Balance = ("$ "+String.format("%.2f",d));
                }
                RecieptPageAdapter recieptPageAdapter = new RecieptPageAdapter(MainActivity.this,
                        receipt,"\r\n\r\n        Subtotal              "+ String.valueOf( finalSubTotal)
                        +"\r\n\r\n        Savings                "+ String.valueOf(finalSavings)
                        +"\r\n\r\n         Tax                        "+ String.valueOf(finalTax)
                        +"\r\n\r\n\n  **** Total                 "+ String.valueOf(finalTotal)
                );

                RecieptPageAdapter recieptPageAdapterCOA = new RecieptPageAdapter(MainActivity.this,
                        receipt,"\r\n\r\n        Subtotal              "+ String.valueOf( finalSubTotal)
                        +"\r\n\r\n        Savings                "+ String.valueOf(finalSavings)
                        +"\r\n\r\n         Tax                        "+ String.valueOf(finalTax)
                        +"\r\n\r\n\n  **** Total                 "+ String.valueOf(finalTotal)
                        +"\n\r\n\r\n\n     COA                "+ String.valueOf(final_COA)
                );
                RecieptPageAdapter recieptPageAdapterEBT = null;
                try {
                    recieptPageAdapterEBT = new RecieptPageAdapter(MainActivity.this,
                            receipt,"\r\n\r\n        Subtotal              "+ String.valueOf(finalSubTotal)
                            +"\r\n\r\n        Savings                "+ String.valueOf(finalSavings)
                            +"\r\n\r\n         Tax                        "+ String.valueOf(finalTax)
                            +"\r\n\r\n\n  **** Total                 "+ String.valueOf(finalTotal)
                            +"\n\r\n\r\n\n  * EBT Balance               "+ String.valueOf(finalEBT_Balance)
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RecieptPageAdapter recieptPageAdapterEBT_COA = null;
                try {
                    recieptPageAdapterEBT_COA = new RecieptPageAdapter(MainActivity.this,
                            receipt,"\r\n\r\n        Subtotal              "+ String.valueOf(finalSubTotal)
                            +"\r\n\r\n        Savings                "+ String.valueOf(finalSavings)
                            +"\r\n\r\n         Tax                        "+ String.valueOf(finalTax)
                            +"\r\n\r\n\n  **** Total                 "+ String.valueOf(finalTotal)
                            +"\n\r\n\r\n\n  * EBT Balance               "+ String.valueOf(finalEBT_Balance)
                            +"\r\n\r\n\n  **** COA                "+ String.valueOf(final_COA)

                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(finalEBT_Balance!=null){
                    if(final_COA!=null){
                        viewPager.setAdapter(recieptPageAdapterEBT_COA);
                    }
                    viewPager.setAdapter(recieptPageAdapterEBT);
                }else{
                    try {
                        if(final_COA!=null){
                            viewPager.setAdapter(recieptPageAdapterCOA);
                        }else {
                        viewPager.setAdapter(recieptPageAdapter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//                tv_reciept = nView.findViewById(R.id.tv_receipt);
//                tv_reciept.setText(receipt);

                AlertDialog dialog = mbuilder.create();
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Print",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                         int viewPagerPos= viewPager.getCurrentItem();
                         if (viewPagerPos == 0){
                             onClickPrintReceipt3(nView);
                         }else{
//                             SpannableStringBuilder str = new SpannableStringBuilder("        SAFEWAY \r\n");
//                             str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 1, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                             if(finalEBT_Balance!=null){
                                 if(final_COA!=null){
                                     onClickPrintReceipt1(receipt+"\n\r\n\r\n\n  * EBT Balance            "+ String.valueOf(finalEBT_Balance)
                                                                      +"\n\r\n\n  * COA              "+ String.valueOf(final_COA)
                                     );
                                 }else{
                             onClickPrintReceipt1(receipt+"\n\r\n\r\n\n  * EBT Balance            "+ String.valueOf(finalEBT_Balance));
                                 }
                             }else{
                                 if(final_COA!=null){
                                     onClickPrintReceipt1(receipt +"\n\r\n\r\n\n  * COA              "+ String.valueOf(final_COA)
                                     );
                                 }else{
                                 onClickPrintReceipt1(receipt);
                                 }
                             }
                         }
                             //   dialog.dismiss();
                            }
                        });
                dialog.show();

            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(payment!=null) {
                 //  refund(payment.getAmount(),payment.getId(),payment.getOrder().getId());
                   Log.d(TAG, "onClick: Void"+ "pressed void");
                   voidPayment(payment.getId(),payment.getOrder().getId());
               }
            }
        });

       // viewCart("default","9879");
    }

    public void sync(){
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(account, OrderContract.AUTHORITY, bundle);
        ContentResolver.requestSync(account, "com.clover.transactions", bundle);

    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                try {
                    if (et_ebt_dialog.getText().hashCode() == editable.hashCode()) {
                        tvEbtError.setVisibility(View.INVISIBLE);
                        String value = editable.toString();

                        if (EBT < Double.valueOf(value)) {
                            tvEbtError.setVisibility(View.VISIBLE);
                            et_ebt_dialog.removeTextChangedListener(textWatcher);
                            et_ebt_dialog.setText(String.valueOf(EBT));
                            et_ebt_dialog.setSelection(et_ebt_dialog.getText().length());
                            et_ebt_dialog.addTextChangedListener(textWatcher);
                        }

                    }
                    else if (et_eft_dialog.getText().hashCode() == editable.hashCode()) {

                        tvEftError.setVisibility(View.INVISIBLE);
                        String value = editable.toString();
                        DecimalFormat df = new DecimalFormat("#.####");
                        df.setRoundingMode(RoundingMode.CEILING);

                         //   et_eft_dialog.setText(String.format("%.2f",total));
                            if (Double.valueOf(df.format(total)) < Double.valueOf(value)) {
                                tvEftError.setVisibility(View.VISIBLE);
                                et_eft_dialog.removeTextChangedListener(textWatcher);
                          //      amountET.setText("$ "+String.format("%.2f", total));
                                et_eft_dialog.setText(String.format("%.2f",total));
                                et_eft_dialog.setSelection(et_eft_dialog.getText().length());
                                et_eft_dialog.addTextChangedListener(textWatcher);
                            }

                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }*/
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EBT) {
            // The activity result's requestCode matches the one we supplied in our makeEBTRequest()'s startActivityForResult() call, so proceed down this branch.
            // If you're code is already expecting activity results from other intent calls, this would just be an additional branch to that logic.
            awaitingEBTPayment = false;  // see the comment in onResume()
            if (resultCode == Activity.RESULT_OK) {
                // Transaction was completed
                if (data == null) return;
                Bundle bundle = data.getExtras();
                // This extra gives you the complete Payment object, just like you would get with SaleResponse.getPayment() via PaymentConnector.
                Payment payment = bundle.getParcelable(Intents.EXTRA_PAYMENT);
                // This extra gives you the amount that was authorized (which in this case should always equal the requested amount since EBT can't partial-auth). It's populated straight from Payment.getAmount()
                paymentEBT = payment;
                Long amount = bundle.getLong(Intents.EXTRA_AMOUNT);
                Log.d(TAG, "Payment: " + payment + "\nAmount: " + amount);

                //our code


                 //   result = "Sale was successful";
                    CardTransaction cardTransaction = payment.getCardTransaction();//response.getPayment().getCardTransaction();
//                    payment = response.getPayment();
//                    response.getPayment().getCardTransaction().getEndBalance();
//                    Log.d(TAG, "onAuthResponse: response" + response);
                    Log.d(TAG, "onSaleResponse: card transaction" + cardTransaction);
                    CardType cardType = cardTransaction.getCardType();
                    Log.d(TAG, "onSaleResponse: card type "+ cardType);

                    if(cardType.toString().equals("EBT")&&cardTransaction.getEndBalance()!=null){
                        finalEBT_Balance_long =  cardTransaction.getEndBalance();
                    }

                 //   if(authorizeEBT){
                        handleEBTPaymentResponse(cardTransaction.getFirst6()+
                                        "0000"+cardTransaction.getLast4(),
                                cardTransaction.getCardType().name(),
                                cardTransaction.getAuthCode(),
                                payment.getAmount(),
                                true,
                                payment.getCreatedTime()
                        );
              //      }
//                    else if(authorizeEBT == false){
//                        handleEFTPaymentResponse(cardTransaction.getFirst6()+
//                                        "0000"+response.getPayment().getCardTransaction().getLast4(),
//                                response.getPayment().getCardTransaction().getCardType().name(),
//                                response.getPayment().getCardTransaction().getAuthCode(),
//                                response.getPayment().getAmount(),
//                                false,
//                                response.getPayment().getCreatedTime()
//                        );
//                    }






            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Transaction was canceled
                Log.d(TAG, "Transaction Canceled");
                // From my testing this branch never actually happens, even when the user cancels the payment (back button). Instead, the EBT payment exits without a result, and android returns back to your own application.
                // See my comment in the onResume() method for an example of a way to work around this
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPaymentConnector();
        //for Make EBT Call
        orderConnector = new OrderConnector(this, account, null);
        Log.d(TAG, "onResume");
        //
        // Example of how you might recognize canceled transactions and handle them.
        //
        // Since the app is resuming and we were awaiting a payment, we can assume we just came back from the EBT tender and so can safely assume our sale request was canceled. Thus we manually call onActivityResult and set the resultCode to be RESULT_CANCELED.
        if(awaitingEBTPayment) onActivityResult(REQUEST_EBT, Activity.RESULT_CANCELED, null);
    }

    @Override
    protected void onPause() {
        if(orderConnector != null) {
            orderConnector.disconnect();
            orderConnector = null;
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (paymentConnector != null) {
            paymentConnector.dispose();
        }
    }

//    private void initCloverConnector(){
//        CloverDeviceConfiguration config = new USBCloverDeviceConfiguration(this, REMOTE_APPLICATION_ID);
////        cloverConnector = CloverConnectorFactory.createICloverConnector(config);
////        cloverConnector.addCloverConnectorListener(new CloverConnectorListener(cloverConnector));
////        cloverConnector.initializeConnection();
//    }

//    private final class CloverConnectorListener extends DefaultCloverConnectorListener {
//        public CloverConnectorListener(ICloverConnector cloverConnector) {
//            super(cloverConnector);
//        }
//
//        @Override
//        public void onConfirmPaymentRequest(com.clover.remote.client.messages.ConfirmPaymentRequest request) {
//
//        }
//
//        @Override
//        public void onDeviceReady(MerchantInfo merchantInfo) {
//            super.onDeviceReady(merchantInfo);
//            Log.d(TAG, "device ready");
//            RetrievePrintersRequest printerRequest = new RetrievePrintersRequest();
//            cloverConnector.retrievePrinters(printerRequest);
//
//        }
//
//        @Override
//        public void onRetrievePrintersResponse(RetrievePrintersResponse response) {
//            super.onRetrievePrintersResponse(response);
//            printers = response.getPrinters();
//            if(printers != null){
//                printer = printers.get(0);
//            }
//        }
//    }

//    private void onClickPrintReceipt(String textView) {
//        String[] textLines = textView.split("\n");
//        List<String> lines = Arrays.asList(textLines);
//        if (printer != null) {
//            PrintRequest pr = new PrintRequest(lines);
//            lastPrintRequestId = String.valueOf(System.currentTimeMillis());
//            pr.setPrintRequestId(lastPrintRequestId);
//            pr = new PrintRequest(lines, lastPrintRequestId, printer.getId());
//            cloverConnector.print(pr);
//        }
//    }

    private void onClickPrintReceipt1(String text) {
        PrintJob textPrintJob = new TextPrintJob.Builder().text(text).build();
        textPrintJob.print(this, CloverAccount.getAccount(getApplicationContext()));

        //This is server end a bitmap of receipt
       // ImagePrintJob2 imagePrintJob2 = new ImagePrintJob2.Builder(this).bitmap();
    }

    private void onClickPrintReceipt3(View reciept) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // DO your work here
                ViewPrintJob viewPrintJob = new ViewPrintJob.Builder().view(reciept).build();
                viewPrintJob.print(MainActivity.this, CloverAccount.getAccount(getApplicationContext()));
            }
        }).start();

    }

    private SaleRequest setupSaleRequest(String amount) {
        // Create a new SaleRequest and populate the required request field
        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setExternalId(ExternalIdUtils.generateNewID()); //required, but can be any string
        double d = Math.round(Double.valueOf(amount)*100);
       // double d = Double.valueOf(amount)*100;
      //  saleRequest.setCardEntryMethods(CARD_ENTRY_METHOD_MANUAL);
      //  saleRequest.setDisableReceiptSelection(true);
        saleRequest.setAutoAcceptSignature(true);
        saleRequest.setDisablePrinting(true);
        saleRequest.setDisableReceiptSelection(true);
        saleRequest.setDisableDuplicateChecking(true);



        saleRequest.setAmount((long) d);

        return saleRequest;
    }

    private static final String TAG = "EBTTenderActivity";
    private static final int REQUEST_EBT = 0;
    private Account account;
    private Executor exec = Executors.newSingleThreadExecutor();
    private OrderConnector orderConnector;
    private boolean awaitingEBTPayment = false;  // see the comment in onResume()

    public void makeEBTRequest(String amount) {
        /*
         * BEGIN: EBT-only sale request
         */
        exec.execute(() -> {
            try {
                double d = Math.round(Double.valueOf(amount)*100);

                Long ebt_amount = (long) d; // hardcoded amount we'll be requesting for this sample
                // The EBT tender currently requires a valid order ID for valid processing, so we build a barebones one here with a single custom line item named "EBT Sale" and a price equal to the requested amount.
                // Unfortunately, this does mean your app will need ORDERS_W
                Order createdOrder = orderConnector.createOrder(new Order());
                Log.d(TAG, "Created Order ID: " + createdOrder.getId());
                // Add a custom line item with a price equal to the amount we will be requesting (otherwise the order will be for $0, but we'll be requesting a payment of > $0 which is not good practice).
                // Name of the line-item isn't critical.
                // Note that this call does _not_ update the createdOrder object with the new line-item. It only updates the order saved in the device's database, and in turn the order saved on clover servers. You would need to
                orderConnector.addCustomLineItem(createdOrder.getId(), new LineItem("{ \"name\": \"EBT Sale\", \"price\": "+ebt_amount+" }"), false);
                // Since a canceled payment would leave this order open, you may want to more tightly manage the orders to prevent a build up of open orders.
                Intent ebtIntent = new Intent(Intents.ACTION_MERCHANT_TENDER); // This intent action specifies that we want to start a merchant tender
                ebtIntent.setPackage("com.clover.ebt"); //This calls the ebt app directly
                ebtIntent.putExtra(Intents.EXTRA_AMOUNT, ebt_amount); // The amount you're requesting for the transaction
                ebtIntent.putExtra(Intents.EXTRA_ORDER_ID, createdOrder.getId()); // The order the successful transaction should be associated with
                ebtIntent.putExtra(Intents.EXTRA_CUSTOMER_TENDER, "EBT"); // This specifies the tender to be used. In our case, EBT
                Log.d(TAG, "Making EBT sale request for amount: " + ebt_amount);
                awaitingEBTPayment = true; // see the comment in onResume()
                startActivityForResult(ebtIntent, REQUEST_EBT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        /*
         * END
         */
    }

    private AuthRequest setupAuthRequest(String amount) {
        // Create a new SaleRequest and populate the required request field
        AuthRequest saleRequest = new AuthRequest();
        saleRequest.setExternalId(ExternalIdUtils.generateNewID()); //required, but can be any string
      //  saleRequest.setCardEntryMethods(CARD_ENTRY_METHOD_MANUAL);
        double d = Math.round(Double.valueOf(amount)*100);
        saleRequest.setAutoAcceptSignature(true);
        saleRequest.setDisablePrinting(true);
        saleRequest.setDisableReceiptSelection(true);
        // saleRequest.setCardEntryMethods(CARD_ENTRY_METHOD_MANUAL);
        saleRequest.setAmount((long) d);
       // saleRequest.setAmount(Long.parseLong(amount));

        return saleRequest;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void handleEBTPaymentResponse(String card_pan_print,String cardType,String approval_number, long amount, Boolean ebtFlag,long transaction_complete_time){

//        if(recalculation.equals("true")){
//            loadingLayout.setVisibility(View.VISIBLE);
//            Double EBTpaid = Double.valueOf(amount)/100;
//            round(EBTpaid, 2);
//            String  amountNew =  String.valueOf(EBTpaid);
//            String time = String.valueOf(transaction_complete_time);
//            TransactionInfoList transactionInfoList = new TransactionInfoList(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
//
//            transactionInfoListEBTnew = transactionInfoList;
//            List<TransactionInfoList> transactions = Collections.singletonList(transactionInfoList);
//            RecalculateTransaction recalculate = new RecalculateTransaction(orderId,transactions);
//            recalculateEBT(recalculate);
//
//        }
        sync();
        Double EBTpaid = Double.valueOf(amount)/100;
        round(EBTpaid, 2);
        String strDouble = String.format("%.2f",total);
        total = Double.valueOf(strDouble);
        //EBTCHANGE
     //   if(ebt_recalcualted){ }else
            {EBT = EBT- EBTpaid; }

        total = total -EBTpaid;
        EBT_PaidAmount = EBTpaid + EBT_PaidAmount;
        amountEBT.setText("$ "+String.format("%.2f",EBT)+" ");
        amountET.setText("$ "+String.format("%.2f", total));
        amountSubTotal.setText("$ "+String.format("%.2f",(SubTotal-EBT)));
        //TODO this assumes that the full teador for EBT will be deducted
        ll_EBTprocessed_payment.setVisibility(View.VISIBLE);
        tv_ebtProcessedPaymentAmount.setText("$ "+String.format("%.2f",Double.valueOf(EBT_PaidAmount))+" ");

        if(total>0){
            if(EBT==0) {
                payButton.setText("Complete Pay");
                balanceEFTRemaining = true;
                processTransactionEBT(card_pan_print, cardType, approval_number, ebtFlag, transaction_complete_time, EBT_PaidAmount,EBTpaid);
                orderProcessed=true;
            }
//                String  amountNew =  String.valueOf(EBTpaid);
//                String time = String.valueOf(transaction_complete_time);
//                Transactions EBTtransaction = new Transactions(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
//                //transactionInfoListEBTnew = EBTtransaction;
             //   allTransactionsList.add(EBTtransaction);
//                try {
//                    if(recalculation.equals("true")){
//
//                  //  TransactionInfoList EBTtransaction = new TransactionInfoList(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
//                    List<Transactions> transactions = Collections.singletonList(EBTtransaction);
//                    RecalculateTransaction recalculate = new RecalculateTransaction(orderId,transactions);
//                    recalculateEBT(recalculate);
//                    loadingLayout.setVisibility(View.VISIBLE);
//                }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
          //  }

            else{
                payButton.setText("Complete Pay");
                processTransactionEBT(card_pan_print, cardType, approval_number, ebtFlag, transaction_complete_time, EBT_PaidAmount,EBTpaid);
                orderProcessed=true;
                //EBTCHANGE FLAG
                balanceEFTRemaining = true;
//                //TODO ADD THIS AMOUNT TO ARRAY
//
//                String  amountNew =  String.valueOf(EBTpaid);
//                String time = String.valueOf(transaction_complete_time);
//                if(recalculation.equals("true")) {
//                    Transactions transactionInfoList = new Transactions(card_pan_print, cardType, approval_number, amountNew, ebtFlag, time);
//                    List<Transactions> transactions = Collections.singletonList(transactionInfoList);
//                    RecalculateTransaction recalculate = new RecalculateTransaction(orderId, transactions);
//                    loadingLayout.setVisibility(View.VISIBLE);
//                    recalculateEBT(recalculate);
                }
//
//
//            }
        }else if(total<0){
//            Date currentTime = Calendar.getInstance().getTime();
//            String time = String.valueOf(currentTime);
//            String currentAmount = String.valueOf(total);
//            Transactions transactionsCOA = new Transactions("NA","COA","NA",currentAmount,false,time);
//            List<Transactions> transactions = Collections.singletonList(transactionsCOA);
//
//            FinalizeSplitTransaction finalizeSplitTransaction = new FinalizeSplitTransaction(orderId,transactions);
//            finalizeTransaction(finalizeSplitTransaction);
//            loadingLayout.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.GONE);
            rl_COA.setVisibility(View.VISIBLE);
            COA = true;
        }
        else {
//            String  amountNew =  String.valueOf(EBTpaid);
//            String time = String.valueOf(transaction_complete_time);
//            if(recalculation.equals("true")) {
//                Transactions transactionInfoList = new Transactions(card_pan_print, cardType, approval_number, amountNew, ebtFlag, time);
//                List<Transactions> transactions = Collections.singletonList(transactionInfoList);
//                RecalculateTransaction recalculate = new RecalculateTransaction(orderId, transactions);
//                loadingLayout.setVisibility(View.VISIBLE);
//                recalculateEBT(recalculate);
//            }
            //TODO ADD FINAL RECIPT
            processTransactionEBT(card_pan_print,  cardType,  approval_number, ebtFlag, transaction_complete_time,EBT_PaidAmount,EBTpaid);
            payButton.setVisibility(View.GONE);
            amountTax.setText("$ "+String.format("%.2f", 0.00)+" ");
            amountAppliedDiscounts.setText("$ "+"("+String.format("%.2f",0.00)+")");
            printReceipt.setVisibility(View.VISIBLE);
        }
    }

    public void processTransactionEBT(String card_pan_print,String cardType,String approval_number, Boolean ebtFlag,long transaction_complete_time,Double EBTpaidRecalculate, Double EBTpaidFinalize){
        String  amountNewRecalculate =  String.valueOf(EBTpaidRecalculate);
        String time = String.valueOf(transaction_complete_time);
        Transactions EBTtransactionRecalculate = new Transactions(card_pan_print, cardType, approval_number, amountNewRecalculate, ebtFlag, time);

        if(recalculation.equals("true")) {
            List<Transactions> transactions = Collections.singletonList(EBTtransactionRecalculate);
            RecalculateTransaction recalculate = new RecalculateTransaction(orderId, transactions);
            loadingLayout.setVisibility(View.VISIBLE);
            recalculateEBT(recalculate);
        }

        String  amountNewFinalize =  String.valueOf(EBTpaidFinalize);
        Transactions EBTtransactionFinalize = new Transactions(card_pan_print, cardType, approval_number, amountNewFinalize, ebtFlag, time);

        allTransactionsList.add(EBTtransactionFinalize);
    }

    private void handleEFTPaymentResponse(String card_pan_print,String cardType,String approval_number, long amount, Boolean ebtFlag,long transaction_complete_time){
        Double EFTpaid = Double.valueOf(amount)/100;
        round(EFTpaid, 2);
        String strDouble = String.format("%.2f",total);
        total = Double.valueOf(strDouble);
        nonEBT = nonEBT- EFTpaid;
            ///where we total out to 0
        total = total -EFTpaid;
        EFT_PaidAmount = EFTpaid + EFT_PaidAmount;
        if(nonEBT<0){
            nonEBT= 0.00;
        }
        amountNonEBT.setText("$ "+String.format("%.2f",nonEBT)+" ");
        amountET.setText("$ "+String.format("%.2f", total));
        amountSubTotal.setText("$ "+String.format("%.2f",(SubTotal-EBT)));
        //TODO this assumes that the full teador for EBT will be deducted
        ll_EFTprocessed_payment.setVisibility(View.VISIBLE);
        tv_eftProcessedPaymentAmount.setText("$ "+String.format("%.2f",Double.valueOf(EFT_PaidAmount))+" ");

        if(total>0){


            if(EBT==0||EBT>0){
                payButton.setText("Complete Pay");
                balanceEFTRemaining = true;
            }
            //TODO Complete flow for multiple cards

            processTransactionEFT(card_pan_print,  cardType, approval_number, ebtFlag,transaction_complete_time,EFTpaid);
//            String  amountNew =  String.valueOf(EFTpaid);
//            String time = String.valueOf(transaction_complete_time);
//
//            Transactions transactionsEFT = new Transactions(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
//            allTransactionsList.add(transactionsEFT);

            orderProcessed = true;
        }else {
            processTransactionEFT(card_pan_print,  cardType, approval_number, ebtFlag,transaction_complete_time,EFTpaid);
//            String  amountNew =  String.valueOf(EFTpaid);
//
//            String time = String.valueOf(transaction_complete_time);
//
//
//           // Transactions transactionsEBT =  transactionInfoListEBTnew;
//            Transactions transactionsEFT = new Transactions(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
//
////            if(balanceEFTRemaining){
////                allTransactionsList.add(transactionsEBT);
////            }
//            allTransactionsList.add(transactionsEFT);

           // List<Transactions> transactions = allTransactionsList;
            FinalizeSplitTransaction finalizeSplitTransaction = new FinalizeSplitTransaction(orderId,allTransactionsList);
            finalizeTransaction(finalizeSplitTransaction);
            loadingLayout.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.GONE);
            amountTax.setText("$ "+String.format("%.2f", 0.00)+" ");
            amountAppliedDiscounts.setText("$ "+"("+String.format("%.2f",0.00)+")");
            amountEBT.setText("$ "+String.format("%.2f",0.00)+" ");
            orderProcessed = false;
            printReceipt.setVisibility(View.VISIBLE);
        }
    }

    public void processTransactionEFT(String card_pan_print,String cardType,String approval_number, Boolean ebtFlag,long transaction_complete_time, Double EFTpaid){
        String  amountNew =  String.valueOf(EFTpaid);
        String time = String.valueOf(transaction_complete_time);
        Transactions transactionsEFT = new Transactions(card_pan_print,cardType,approval_number,amountNew,ebtFlag,time);
        allTransactionsList.add(transactionsEFT);
    }


    public void voidPayment(String paymentID, String orderId){
        VoidPaymentRequest vpr = new VoidPaymentRequest();
        vpr.setPaymentId(paymentID);
        vpr.setOrderId(orderId);
        vpr.setVoidReason("USER_CANCEL");
        Log.d(TAG, "VoidPaymentRequest: " + vpr.toString());
        paymentConnector.voidPayment(vpr);
    }

    private void initPaymentConnector() {
        if (paymentConnector == null) {
            String remoteApplicationId = REMOTE_APPLICATION_ID;
            paymentConnector = new PaymentConnector(getApplicationContext(), CloverAccount.getAccount(getApplicationContext()), ccListener, remoteApplicationId);
            paymentConnector.initializeConnection();
        }
    }

    private void showMessage(final String msg, final int duration) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, duration).show();
            }
        });
    }

    final IPaymentConnectorListener ccListener = new IPaymentConnectorListener(){

        @Override
        public void onDeviceDisconnected() {

        }

        @Override
        public void onDeviceConnected() {

        }

        @Override
        public void onPreAuthResponse(PreAuthResponse response) {

        }

        @Override
        public void onAuthResponse(AuthResponse response) {
            String result = null;
            if(response.getSuccess()) {
                result = "Sale was successful";
                CardTransaction cardTransaction = response.getPayment().getCardTransaction();
                            payment = response.getPayment();
                Log.d(TAG, "onAuthResponse: response" + response);
                Log.d(TAG, "onSaleResponse: card transaction" + cardTransaction);
                CardType cardType = cardTransaction.getCardType();
                Log.d(TAG, "onSaleResponse: card type "+ cardType);

                if(authorizeEBT){
                    handleEBTPaymentResponse(response.getPayment().getCardTransaction().getFirst6()+"0000"+response.getPayment().getCardTransaction().getLast4(),
                            response.getPayment().getCardTransaction().getCardType().name(),
                            response.getPayment().getCardTransaction().getAuthCode(),
                            response.getPayment().getAmount(),
                            true,
                            response.getPayment().getCreatedTime()
                            );
                    sync();
                }else if(authorizeEBT == false){
                    handleEFTPaymentResponse(response.getPayment().getCardTransaction().getFirst6()+"0000"+response.getPayment().getCardTransaction().getLast4(),
                            response.getPayment().getCardTransaction().getCardType().name(),
                            response.getPayment().getCardTransaction().getAuthCode(),
                            response.getPayment().getAmount(),
                            false,
                            response.getPayment().getCreatedTime()
                    );
                }

            }else{
                result = "Sale was unsuccessful" + response.getReason() + ":" + response.getMessage();
                Log.d(TAG, "onAuthResponse: "+ response);
            }
            Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onTipAdjustAuthResponse(TipAdjustAuthResponse response) {

        }

        @Override
        public void onCapturePreAuthResponse(CapturePreAuthResponse response) {

        }

        @Override
        public void onVerifySignatureRequest(VerifySignatureRequest request) {

        }

        @Override
        public void onConfirmPaymentRequest(ConfirmPaymentRequest request) {
            Log.d(TAG, "onConfirmPaymentRequest: "+ request);
        }

        @Override
        public void onSaleResponse(SaleResponse response) {
            //  Callback with Sales Response
            String result = null;
//            if(response.getSuccess()) {
//                result = "Sale was successful";
//          CardTransaction cardTransaction = response.getPayment().getCardTransaction();
//                Log.d(TAG, "onSaleResponse: card transaction" + cardTransaction);
//          CardType cardType = cardTransaction.getCardType();
//                Log.d(TAG, "onSaleResponse: card type "+ cardType);
//
//                result = "Sale was unsuccessful" + response.getReason() + ":" + response.getMessage();
//            }
//            Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            if(response.getSuccess()) {
                result = "Sale was successful";
                CardTransaction cardTransaction = response.getPayment().getCardTransaction();
                payment = response.getPayment();
                response.getPayment().getCardTransaction().getEndBalance();
                Log.d(TAG, "onAuthResponse: response" + response);
                Log.d(TAG, "onSaleResponse: card transaction" + cardTransaction);
                CardType cardType = cardTransaction.getCardType();
                Log.d(TAG, "onSaleResponse: card type "+ cardType);

                if(cardType.toString().equals("EBT")&&cardTransaction.getEndBalance()!=null){
                    finalEBT_Balance_long =  cardTransaction.getEndBalance();
                }

                if(authorizeEBT){
                    handleEBTPaymentResponse(response.getPayment().getCardTransaction().getFirst6()+
                                    "0000"+response.getPayment().getCardTransaction().getLast4(),
                            response.getPayment().getCardTransaction().getCardType().name(),
                            response.getPayment().getCardTransaction().getAuthCode(),
                            response.getPayment().getAmount(),
                            true,
                            response.getPayment().getCreatedTime()
                    );
                }else if(authorizeEBT == false){
                    handleEFTPaymentResponse(response.getPayment().getCardTransaction().getFirst6()+
                                    "0000"+response.getPayment().getCardTransaction().getLast4(),
                            response.getPayment().getCardTransaction().getCardType().name(),
                            response.getPayment().getCardTransaction().getAuthCode(),
                            response.getPayment().getAmount(),
                            false,
                            response.getPayment().getCreatedTime()
                    );
                }

            }else{
                result = "Sale was unsuccessful" + response.getReason() + ":" + response.getMessage();
                Log.d(TAG, "onAuthResponse: "+ response);
            }

        }

        @Override
        public void onManualRefundResponse(ManualRefundResponse response) {
            Log.d(TAG, "onManualRefundResponse: response" + response);
        }

        @Override
        public void onRefundPaymentResponse(RefundPaymentResponse response) {

            Log.d(TAG, "onRefundPaymentResponse: refund" + response);
        }

        @Override
        public void onTipAdded(TipAdded tipAdded) {

        }

        @Override
        public void onVoidPaymentResponse(VoidPaymentResponse response) {

            Log.d(TAG, "onVoidPaymentResponse: response" + response);
            showMessage(getClass().getName() + ":Got VoidPaymentResponse of " + response.getResult(), Toast.LENGTH_LONG);
          //  Toast.makeText(MainActivity.this, "EBT transaction Voided", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVaultCardResponse(VaultCardResponse response) {

        }

        @Override
        public void onRetrievePendingPaymentsResponse(RetrievePendingPaymentsResponse retrievePendingPaymentResponse) {

        }

        @Override
        public void onReadCardDataResponse(ReadCardDataResponse response) {
            Log.d(TAG, "onReadCardDataResponse: "+ response);

        }

        @Override
        public void onCloseoutResponse(CloseoutResponse response) {
            Log.d(TAG, "onCloseoutResponse: "+response);
        }

        @Override
        public void onRetrievePaymentResponse(RetrievePaymentResponse response) {
            Log.d(TAG, "onRetrievePaymentResponse: retrievePayment" + response);
        }

        @Override
        public void onVoidPaymentRefundResponse(VoidPaymentRefundResponse response) {
            Log.d(TAG, "onVoidPaymentRefundResponse: response" + response);
        }
    };

    @Override
    public void onSelectEbtPay() {
     //   amountValue = String.valueOf(EBT);

        amountValue = et_ebt_dialog.getText().toString();

      //  splitButton.setVisibility(View.GONE);

//        if(total>0){
//            payButton.setText("Complete Pay");
//            balanceEFTRemaining = true;
//        }else {
//            payButton.setVisibility(View.GONE);
//            printReceipt.setVisibility(View.VISIBLE);
//        }

            //SALE REQUEST
        //Commented out to use the MakeEBT call
        /*SaleRequest saleRequest =  setupSaleRequest(amountValue);
        paymentConnector.sale(saleRequest);*/
        makeEBTRequest(amountValue);

        //AUTH REQUEST
//        AuthRequest authRequest = setupAuthRequest(amountValue);
//        paymentConnector.auth(authRequest);
    }

    public void onSelectEftPay() {
     //   amountValue = String.valueOf(total);
        amountValue = et_eft_dialog.getText().toString();

//        amountEBT.setText("$ "+String.format("%.2f",0.00)+" ");
//        amountNonEBT.setText("$ "+String.format("%.2f",0.00)+" ");
//        amountAppliedDiscounts.setText("$ "+String.format("%.2f",0.00)+" ");
//        amountTax.setText("$ "+String.format("%.2f",0.00)+" ");
//        amountET.setText("$ "+String.format("%.2f", total-total));
//        amountSubTotal.setText("$ "+String.format("%.2f",(SubTotal-total)));
//
//        //  splitButton.setVisibility(View.GONE);
//        //TODO this assumes that the full teador for EBT will be deducted
//        ll_EFTprocessed_payment.setVisibility(View.VISIBLE);
//        tv_eftProcessedPaymentAmount.setText("$ "+String.format("%.2f",total)+" ");


//        payButton.setVisibility(View.GONE);
//        printReceipt.setVisibility(View.VISIBLE);

        //SALE REQUEST
        SaleRequest saleRequest =  setupSaleRequest(amountValue);
        paymentConnector.sale(saleRequest);
        //AUTH REQUEST
//        AuthRequest authRequest = setupAuthRequest(amountValue);
//        paymentConnector.auth(authRequest);

    }

    public void recalculateEBT(RecalculateTransaction recalculate) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.recalculateCall(recalculate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {

                        try {
                            Toast.makeText(MainActivity.this, "recalculate", Toast.LENGTH_SHORT).show();

                            getReceipt(orderId,storeId);

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        loadingLayout.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error add", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void finalizeTransaction(FinalizeSplitTransaction  finalizeSplitTransaction) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.finalizeCall(finalizeSplitTransaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {

                        try {
                            Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();

                            getReceipt2(orderId,storeId);

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        loadingLayout.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error Finalise, please go back and checkout again", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void getReceipt(String orderId, String storeId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getReceipt(orderId, storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> Observable.interval(2, TimeUnit.SECONDS))
                .takeUntil(receiptResponse -> receiptResponse.getErrorMessage() == null&& (receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION RECALCULATED")||
                        receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION COMPLETED")))
                .subscribe(new Observer<BaseResponse<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data> receiptResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if
                                // check for recalculcate and update the balance for Total, (Balance due) EFT
                            if(receiptResponse.getResponse()!=null){
                                //      Data receipt = receiptResponse.getResponse();
                               /* Toast.makeText(CartActivity.this, "Terminal: "+receiptResponse.getResponse().getData().getTerminalNumber(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                intent.putExtra("checkoutTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotal().toString());
                                intent.putExtra("subTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getSubTotal().toString());
                                intent.putExtra("taxTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTax().toString());
                                intent.putExtra("appliedDiscountsTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString());
                                intent.putExtra("ebtTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getEbt().toString());
                                startActivity(intent);*/
//                                loadingLayout.setVisibility(View.GONE);
//                                Toast.makeText(MainActivity.this, "Terminal: "+receiptResponse.getResponse().getTerminalNumber(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getBaseContext(),CartActivity.class);
//                                intent.putExtra("guid",receiptResponse.getResponse().getGuid());
//                                intent.putExtra("storeId",receiptResponse.getResponse().getStoreId());
//                                intent.putExtra("orderId",orderId);
//
//                                startActivity(intent);
                                if(receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION COMPLETED")){
                                    finalSubTotal = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getSubTotal().toString();
                                    finalSavings = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString();
                                    finalTax = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTax().toString();
                                    finalTotal = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotal().toString();
                                    loadingLayout.setVisibility(View.GONE);
                                    loadingLayout.setVisibility(View.GONE);
                                    orderProcessed =false;
                                    orderComplete= true;
                                    Toast.makeText(MainActivity.this, "Receipt updated", Toast.LENGTH_SHORT).show();
                                    receipt =  receiptResponse.getResponse().getReceipt();
                                    payButton.setVisibility(View.GONE);
                                    printReceipt.setVisibility(View.VISIBLE);

                                }else{
                                    if(total>0){
                                        payButton.setVisibility(View.VISIBLE);
//                                    amountTax.setText("$ "+String.format("%.2f", 0.00)+" ");
//                                    amountAppliedDiscounts.setText("$ "+"("+String.format("%.2f",0.00)+")");
                                        printReceipt.setVisibility(View.GONE);
                                        orderProcessed=true;
                                        payButton.setText("Complete Pay");
                                        //EBTCHANGE FLAG
                                       // if(EBT==0){balanceEFTRemaining = true;}else {}
                                        //EBTCHANGE
                                        balanceEFTRemaining = true;
                                    }
                                }
                                receipt =  receiptResponse.getResponse().getReceipt();

                                total = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getBalanceDue();
                                amountET.setText("$ "+String.format("%.2f", total));
                                recalculation = String.valueOf(receiptResponse.getResponse().getIsRecalculationNeeded());
                                EBT = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getEbt();
                                ebt_recalcualted = true;
                                Toast.makeText(MainActivity.this, "Receipt updated", Toast.LENGTH_SHORT).show();
                                if((receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION RECALCULATED")||
                                        receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION COMPLETED"))){
                                loadingLayout.setVisibility(View.GONE);
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(MainActivity.this, "Error Receipt "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                    }
                });
    }

    public void getReceipt2(String orderId, String storeId) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.getReceipt(orderId, storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> Observable.interval(2, TimeUnit.SECONDS))
                .takeUntil(receiptResponse -> receiptResponse.getErrorMessage() == null&& receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION COMPLETED"))
                .subscribe(new Observer<BaseResponse<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<Data> receiptResponse) {

                        try {
                            //  Log.d(TAG, "onNext: Button press"+data.getData().getItemCount());
//                            itemCount = data.getData().getItemCount();
//                            tv_TotalQuantity.setText(String.valueOf(itemCount));if

                            if(receiptResponse.getResponse()!=null){
                                //      Data receipt = receiptResponse.getResponse();
                               /* Toast.makeText(CartActivity.this, "Terminal: "+receiptResponse.getResponse().getData().getTerminalNumber(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                intent.putExtra("checkoutTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotal().toString());
                                intent.putExtra("subTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getSubTotal().toString());
                                intent.putExtra("taxTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTax().toString());
                                intent.putExtra("appliedDiscountsTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString());
                                intent.putExtra("ebtTotal",receiptResponse.getResponse().getData().getReceiptJson().getReceiptTotalResult().getEbt().toString());
                                startActivity(intent);*/
//                                loadingLayout.setVisibility(View.GONE);
//                                Toast.makeText(MainActivity.this, "Terminal: "+receiptResponse.getResponse().getTerminalNumber(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getBaseContext(),CartActivity.class);
//                                intent.putExtra("guid",receiptResponse.getResponse().getGuid());
//                                intent.putExtra("storeId",receiptResponse.getResponse().getStoreId());
//                                intent.putExtra("orderId",orderId);
//
//                                startActivity(intent);
                                receipt =  receiptResponse.getResponse().getReceipt();
                                if(receiptResponse.getResponse().getTransactionStatus().equals("TRANSACTION COMPLETED")){
                                    finalSubTotal = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getSubTotal().toString();
                                    finalSavings = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotalSavings().toString();
                                    finalTax = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTax().toString();
                                    finalTotal = receiptResponse.getResponse().getReceiptJson().getReceiptTotalResult().getTotal().toString();
                                    loadingLayout.setVisibility(View.GONE);
                                loadingLayout.setVisibility(View.GONE);
                                    orderProcessed =false;
                                    orderComplete= true;
                                    printReceipt.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "Receipt updated", Toast.LENGTH_SHORT).show();
                                    payButton.setVisibility(View.GONE);
                                    rl_COA.setVisibility(View.GONE);
                                    if(COA){
                                        COA = false;
                                        printReceipt.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity.this, "COA ADDED TO CUSTOMERS ACCOUNT", Toast.LENGTH_SHORT).show();
                                        final_COA= finalTotal;
                                        loadingLayout.setVisibility(View.GONE);

                                        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        //Find the currently focused view, so we can grab the correct window token from it.
                                        View view2 = MainActivity.this.getCurrentFocus();
                                        //If no view currently has focus, create a new one, just so we can grab a window token from it
                                        if (view2 == null) {
                                            view2 = new View(MainActivity.this);
                                        }
                                        imm.hideSoftInputFromWindow(view2.getWindowToken(), 0);
                                        rl_COA.setVisibility(View.GONE);

                                    }
                                }


                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onNext: countError"+ e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
                        loadingLayout.setVisibility(View.GONE);
                        Log.d(TAG, "handleError: error"+ t.toString());
                        Toast.makeText(MainActivity.this, "Error Receipt "+ t.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void processCOA(COA_Request coa_request) {
        Service apiService = NetworkManager.createRetrofit().create(Service.class);
        apiService.processCOA(coa_request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse <COA_Response>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResponse<COA_Response> COAbaseResponse) {


                        try {
                            Toast.makeText(MainActivity.this, "Processing COA", Toast.LENGTH_SHORT).show();
                            try {
                                if(COAbaseResponse.getAck()!=null) {
                                    if (COAbaseResponse.getAck().equals("1")) {
                                        Toast.makeText(MainActivity.this, "Error COA, try again: " + COAbaseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                        loadingLayout.setVisibility(View.GONE);
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                            try {
                                if (COAbaseResponse.getResponse() !=null) {
                                    Date currentTime = Calendar.getInstance().getTime();
                                    String time = String.valueOf(currentTime);
                                    String currentAmount = String.valueOf(total);
                                    Transactions transactionsCOA = new Transactions("NA","COA","NA",currentAmount,false,time);
                                    List<Transactions> transactions = Collections.singletonList(transactionsCOA);

                                    FinalizeSplitTransaction finalizeSplitTransaction = new FinalizeSplitTransaction(orderId,transactions);
                                    finalizeTransaction(finalizeSplitTransaction);
                                }else{
                                    Toast.makeText(MainActivity.this, "Error COA, Order Id incorrect please try again", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            loadingLayout.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onNext: countError" + e.getMessage());
                        }
//
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtil.printLogMessage("error response", t.getMessage());
                        Log.d(TAG, "handleError: error"+ t.toString());
                        loadingLayout.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error COA, try again "+ t, Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    // not used
    public void refund(Long amountRef, String paymentID, String orderId){
        RefundPaymentRequest refund = new RefundPaymentRequest();

        //  double d = Double.valueOf(amountRef)*100;
        // refund.setAmount((long)d);
        refund.setAmount(amountRef);
        refund.setPaymentId(paymentID);
        refund.setOrderId(orderId);
        //true for full and if  false then partial
        refund.setFullRefund(true);

        Log.d(TAG, "RefundPaymentRequest - Full: " + refund.toString());
        paymentConnector.refundPayment(refund);
    }
    // not currently used
    @Override
    public void onSelectSplitBalance() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SplitBalanceFragment splitBalanceFragment = SplitBalanceFragment.newInstance(total);
        splitBalanceFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentTheme);
        splitBalanceFragment.show(ft, SPLIT_BALANCE);


    }
    // not currently used
    @Override
    public void onChargeBalance(SplitItem splitItem) {
        SaleRequest saleRequest =  setupSaleRequest(splitItem.amount.toString());
        paymentConnector.sale(saleRequest);
    }
    // not currently used
//    public void viewCart(String clientId, String storeid,String orderId) {
//        Service apiService = NetworkManager.createRetrofit().create(Service.class);
//        apiService.getCart(clientId,storeid,orderId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BaseResponse<Cart>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: ");
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse<Cart> cartBaseResponse) {
//
//                        try {
//                            //checkoutLayout.setVisibility(View.VISIBLE);
//                           // cartTotal.setText("Estimated total: $"+String.valueOf(cartBaseResponse.getResponse().getTotalPrice()));
//                          //  estTotal = String.valueOf(cartBaseResponse.getResponse().getTotalPrice());
//                            Toast.makeText(MainActivity.this, "ViewCart", Toast.LENGTH_SHORT).show();
//                            handleResultsCart(cartBaseResponse.getResponse().getData());
//                        } catch (NullPointerException e) {
//                            e.printStackTrace();
//                            Log.d(TAG, "onNext: countError"+ e.getMessage());
//                        }
////
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
////                        LogUtil.printLogMessage("error response", t.getMessage());
//                        Log.d(TAG, "handleError: error"+ t.toString());
//                        Toast.makeText(MainActivity.this, "Error add", Toast.LENGTH_SHORT).show();
////                        progressDialog.dismiss();
////                        tvName.setText("error :   " + t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }
//                });
//    }

    private void handleResultsCart(List<Item> items) {
        this.itemList = items;

//        cartRecyclerViewAdapter.setData(items);
//        cartRecyclerViewAdapter.notifyDataSetChanged();
        Toast.makeText(this, "ViewCarts", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed()
    {
        if(orderProcessed){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Order Processed")
                    .setMessage("Please void existing payment to make changes to this order.")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })
                    // A null listener allows the button to dismiss the dialog and take no further action.
                   // .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else if(orderComplete){
            Intent intent = new Intent(getBaseContext(), Home.class);
            startActivity(intent);
        }else{
            finish();
        }

    }
}
