package com.safeway.postest;
import androidx.appcompat.app.AppCompatActivity;
import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.Intents;
import com.clover.sdk.v3.order.LineItem;
import com.clover.sdk.v3.order.Order;
import com.clover.sdk.v3.order.OrderConnector;
import com.clover.sdk.v3.payments.Payment;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


    public class EBTpayment extends AppCompatActivity {
        private static final String TAG = "EBTTenderActivity";
        private static final int REQUEST_EBT = 0;
        private Account account;
        private Executor exec = Executors.newSingleThreadExecutor();
        private OrderConnector orderConnector;
        private boolean awaitingEBTPayment = false;  // see the comment in onResume()
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            account = CloverAccount.getAccount(this);
        }
        public void makeEBTRequest(View view) {
            /*
             * BEGIN: EBT-only sale request
             */
            exec.execute(() -> {
                try {
                    Long ebt_amount = 1000l; // hardcoded amount we'll be requesting for this sample
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
        /*
         * BEGIN: EBT-only sal response
         */
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
                    Long amount = bundle.getLong(Intents.EXTRA_AMOUNT);
                    Log.d(TAG, "Payment: " + payment + "\nAmount: " + amount);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Transaction was canceled
                    Log.d(TAG, "Transaction Canceled");
                    // From my testing this branch never actually happens, even when the user cancels the payment (back button). Instead, the EBT payment exits without a result, and android returns back to your own application.
                    // See my comment in the onResume() method for an example of a way to work around this
                }
            }
        }
        /*
         * END
         */
        @Override
        protected void onResume() {
            super.onResume();
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
    }

