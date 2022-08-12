//package com.safeway.postest.scanner.view_model;
//
//import static androidx.constraintlayout.widget.Constraints.TAG;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.text.TextUtils;
//
//import androidx.databinding.ObservableBoolean;
//import androidx.databinding.ObservableField;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.safeway.core.component.data.DataWrapper;
//import com.safeway.scanandgo.data.models.CartLookupResponse;
//import com.safeway.scanandgo.data.models.CustomException;
//import com.safeway.scanandgo.data.models.Item;
//import com.safeway.scanandgo.data.models.UiState;
//import com.safeway.scanandgo.data.repository.CartRepository;
//import com.safeway.scanandgo.data.repository.SuperSmartRepository;
//import com.safeway.scanandgo.utils.LoginUtils;
//import com.safeway.scanandgo.utils.SNGLogger;
//import com.scandit.barcodepicker.ScanSession;
//import com.scandit.recognition.Barcode;
//
//import java.util.Locale;
//
//import javax.inject.Inject;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.schedulers.Schedulers;
//
////import static androidx.constraintlayout.Constraints.TAG;
//
//public class ScannerActivityViewModel extends ViewModel {
//
//    public ObservableBoolean showScreen = new ObservableBoolean(false);
//    public final ObservableField<String> pluCodes = new ObservableField<>("");
//    public ObservableBoolean weightItem = new ObservableBoolean(false);
//    public ObservableBoolean showMessage = new ObservableBoolean(false);
//    private final MutableLiveData<Boolean> openScaleFragment = new MutableLiveData<>();
//    public ObservableBoolean isQRCodeEnabled = new ObservableBoolean(false);
//
//    private CartRepository repository;
//    private CompositeDisposable compositeDisposable = new CompositeDisposable();
//
//    private AlertDialog.Builder builder;
//    @Inject
//    public ScannerActivityViewModel(CartRepository repository) {
//        super();
//        this.repository = repository;
//
//    }
//
//    public ObservableField<UiState<Item>> uiState = new ObservableField<>();
//
//    private UiState setLoading(String message) {
//        return new UiState<Item>(true, false, false, message, null);
//    }
//
//    private UiState setSuccess(Item item) {
//        return new UiState<Item>(false, true, false, "", item);
//    }
//
//    private UiState setError(String message, CustomException customException) {
//        return new UiState<Item>(false, false, true, message, uiState.get().getData(), customException);
//    }
//
//    public void afterCodeDetected(ScanSession scanSession,String gs1) {
//        String message = "";
//        String upcType = "";
//
//        String storeId = "";
//        if(LoginUtils.getStore()!=null){
//            storeId = LoginUtils.getStore().getStoreId();
//        }
//        for (Barcode code : scanSession.getNewlyRecognizedCodes()) {
//            String data = code.getData();
//            // Truncate code to certain length.
//            String cleanData = data;
//         /*
//            if (data.length() > 30) {
//                cleanData = data.substring(0, 25) + "[...]";
//            }
//            */
//            if (message.length() > 0) {
//                message += "\n\n\n";
//            }
//
//            if(!TextUtils.isEmpty(gs1) && (TextUtils.equals(code.getSymbologyName().toUpperCase(Locale.US),"DATA-MATRIX") || TextUtils.equals(code.getSymbologyName().toUpperCase(Locale.US),"CODE128"))){
//                cleanData=gs1;
//                 message += cleanData;
//                upcType += "plu".toUpperCase(Locale.US);
//                }else {
//                message += cleanData;
//                upcType += code.getSymbologyName().toUpperCase(Locale.US);
//
//            }
//
//            if (upcType.equals("DATABAR")){
//                SNGLogger.debug(TAG, "afterCodeDetected: DATABAR");
//                message = message.substring(2,15);
////                onGS1Scan();
////                Boolean checkVal = onGS1Scan().getValue();
////                SNGLogger.debug(TAG, "afterCodeDetected: " + checkVal);
//               // openScaleFragment.setValue(true);
//                showScreen.set(true);
//            } else if (upcType.equals("CODE39")){
//
////                SNGLogger.debug(TAG, "afterCodeDetected: CODE39");
////                    builder.setMessage("Please scan barcode on the individual product")
////                            .setTitle("Scan Message")
////                            .setCancelable(false)
////                            .setPositiveButton("OK", (dialogInterface, i) -> {
////                                dialogInterface.cancel();
//////                            homeActivityViewModel.onScanButtonClicked();
//////
//////                            event.onNext("CLEAR_FRAGMENTS");
////
////                            });
////
////                    builder.create().show();
//
//            }
//        }
//        getScannedItemDetail(message, upcType, storeId);
//
//
//    }
//
//    public void setOpenScaleFragmentFalse() {
//        openScaleFragment.setValue(false);
//    }
//
//    public MutableLiveData<Boolean> onGS1Scan() {
//        onGS1Scan().setValue(true);
//            return openScaleFragment;
//    }
//
//    public void getScannedItemDetail(String code, String upcType,String storeId) {
////        compositeDisposable.add(repository.getItemDetail(code, upcType).toFlowable()
////                .map(this::setSuccess)
////                .onErrorReturn(error -> setError("Failed to get Item Data", new CustomException(error)))
////                .startWith(setLoading("Fetching Item Details"))
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(uiState::set, Throwable::printStackTrace));
//
//        //String storeId = "";
//        if(LoginUtils.getStore()!=null){
//            storeId = LoginUtils.getStore().getStoreId();
//        }
//
//        compositeDisposable.add(repository.getItemDetail(code, upcType, storeId).toFlowable()
//                .map(baseResponse -> {
//                    if(baseResponse.getAck().equals("1")){
//                        return setError(baseResponse.getErrorMessage().get(0).getMessage(),new CustomException());
//                    }
////
////                     if(baseResponse.getResponse().getUpcType().equals("DATABAR")){
////                       return setError("DATABAR DETECTED",new CustomException());
////                    } //
//                    pluCodes.set(baseResponse.getResponse().getScanCode());
//                    weightItem.set(baseResponse.getResponse().getWeightItem());
//                    if(baseResponse.getResponse().getSellByWeight() != null){
//                        if((baseResponse.getResponse().getSellByWeight().equals("W")&&baseResponse.getResponse().getUpcType().equals("UPCA"))||(baseResponse.getResponse().getUpcType().equals("DATABAR")&&baseResponse.getResponse().getSellByWeight().equals("W"))){
//                            showScreen.set(true);
//                            SNGLogger.debug(TAG, "getScannedItemDetail: "+ baseResponse.getResponse().getSellByWeight()+ baseResponse.getResponse());
//                            return setError(" ",new CustomException());
//                        }
//                    }
//                     return setSuccess(baseResponse.getResponse());
//
//
////                    if (!baseResponse.getRestrictedItem())
////                        return setSuccess(item);
////                    return setError("Restricted item! Please, go to regular checkout lane to buy.", new CustomException());
//
//                })
//                .onErrorReturn(error -> setError("Failed to get Item Data", new CustomException(error)))
//                .startWith(setLoading("Fetching Item Details"))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(uiState::set, Throwable::printStackTrace));
//
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
//            compositeDisposable.clear();
//        }
//    }
//
//    /**
//     * Method to fetch Scan & Pay enabled stores
//     *
//     * @param cartCode
//     */
//    public MutableLiveData<DataWrapper<CartLookupResponse>> cartCodeLookup(String cartCode, Context context) {
//        return new SuperSmartRepository().lookupCartCode(cartCode, context);
//    }
//
//}
