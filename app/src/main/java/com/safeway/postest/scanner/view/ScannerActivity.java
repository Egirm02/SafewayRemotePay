//package com.safeway.postest.scanner.view;
//
//import static com.safeway.scanandgo.ui.home.view.HomeActivity.REQ_CODE;
//import static com.safeway.scanandgo.utils.Utils.showEstimatedTotal;
//
//import android.Manifest;
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffColorFilter;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.InsetDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.ColorRes;
//import androidx.annotation.DrawableRes;
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.core.view.ViewCompat;
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.Observable;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;
//
//import com.safeway.authenticator.util.NetworkErrorWrapper;
//import com.safeway.core.component.data.DataWrapper;
//import com.safeway.scanandgo.BuildConfig;
//import com.safeway.scanandgo.R;
//import com.safeway.scanandgo.data.models.Item;
//import com.safeway.scanandgo.data.models.UiState;
//import com.safeway.scanandgo.databinding.ActivityScannerBinding;
//import com.safeway.scanandgo.databinding.FragmentPluAddWeightBinding;
//import com.safeway.scanandgo.ui.Scale_Plu_Overlay_DialogFragment;
//import com.safeway.scanandgo.ui.home.view.HomeActivity;
//import com.safeway.scanandgo.ui.home.view_model.HomeActivityViewModel;
//import com.safeway.scanandgo.ui.scanner.view_model.ScannerActivityViewModel;
//import com.safeway.scanandgo.utils.Commons;
//import com.safeway.scanandgo.utils.LoginUtils;
//import com.safeway.scanandgo.utils.SNGLogger;
//import com.safeway.scanandgo.utils.Utils;
//import com.scandit.barcodepicker.BarcodePicker;
//import com.scandit.barcodepicker.OnScanListener;
//import com.scandit.barcodepicker.ScanSession;
//import com.scandit.barcodepicker.ScanSettings;
//import com.scandit.barcodepicker.ScanditLicense;
//import com.scandit.parser.DataFormat;
//import com.scandit.parser.Field;
//import com.scandit.parser.Parser;
//import com.scandit.parser.ParserResult;
//import com.scandit.recognition.Barcode;
//import com.scandit.recognition.SymbologySettings;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import dagger.android.support.DaggerAppCompatActivity;
//
//public class ScannerActivity extends DaggerAppCompatActivity implements OnScanListener {
//
//    private static final String TAG = "ScannerActivity";
//    @Inject
//    ViewModelProvider.Factory factory;
//
//    public static final String ITEM = "ITEM";
//    public static final String SCANCODE = "SCANCODE";
//    public static final String SCANWT = "SCANWT";
//    private AlertDialog.Builder builder;
//    private Activity mActivity;
//    public static final String ENABLE_QR_CODE = "ENABLE_QR_CODE";
//
//
//
//    public static  String sScanditSdkAppKey ="Ad89ydSBQFv8EvzGyRiKYKc2d4cKDU/TcRe7qtMDdcKvAvHyqUxdwesDNCb0eWfoK2c0dmUaVyfAQYYanStLGOBI1iXGQbF/DiTBwkhgubmMcHsy71pyhZ911m2OPvd7vQDtRwwiHGrBI16VCX6t+EGiO6GlJm5se9/Kn9HArhA9QuYb69A3gH1iqCiUfQGZSVP/7m/bCklB9g7+19n7cqrcqUdJIuSaZIaE3IZA+611dxHyK8+Ez1qARLw5QB7BM4PTMHQCUwFsJxcFRMUJkIB8Erc0u+1Wi9GrVLkWlphUxa2UdYqHA2qCBTjKtbscS205b+Ei4iBjU8AzRyKOeF0mi/aUUboQ1ugA+yjZc+VQYs9MhMgAjUZIO5z3YE2FA+Ix9k18b3xM7dI9AYRiGZc5acbL4s3ya/P3EtXZB+yRFlyK4RdY3TGKc8t7mmD8akAwnXylqE0gvgXz23wqB98bTijgeBVHpgGyViLE6lF7JYO4OTjZyHeej/fJkxBSKC6fk4LdOOU66X/eDOJsKJ/Kt7Kht4YD0u2svhBgrHFGynScIYrXlb05n9bzbJJ9+8wT2SFLmVrwGPz7P4KdJtYAQDOwA+vkn1/pfNgZaCvRRKPGbU4xhTG9KkWBSqzOF0lZuPq1EB/DaGvHSoEfnjAgQ5D71c4X8OtBV/r0WbmqQBRG1k6C9PfrBSrcEGBoGG0v3Uy2PdLBetLNpsuF9AFDFmPrie4f2JOZbcSEs84ryH0rAoSL20zHZ8088njzspNAR5izUy7inUbgFF7o+P7pk0xILoD5aIkl7o/EqJdJUBWbImUtJJE=";
////           AY89DTdfKonoFAi+8iVf9zE00QOpMvmJSTPzctRm8rfRTH81lVhB0zw1Q4/XU1oFFRki6GBK2cwmTYH34SbZZFlLI0/RbjaZjDvTpnomawStaroEpnprk7lEAE9QQqmM5gi2M6ImghI0NwYDQHm5C1LpGTlml1yqP366BkLLh6v5cAmKf0T2S6Q2+MlMQyZyy3giLz7QLD31xqvb/XK7LNWQBwdUm97Pe/dQQUG60gGKxtB4VY1oSZflZhSU6P93RNaBs3cwv13Xf7b7F7Csy5sNtaNMenot6l7pBJmIR4fXymnhhmB1NPujjfxY7tLTYISa8VOE4DgzkHYM9mXY9BRZcTsohnQBsjqnoeAOXjw2J3EwmuMQsbMWVKCh0fzeD2AFQgNxVBWH+D/PiZDmFfzu3sNxWYFRj3TleCiIFKh1Gs8NO7a8gv9U+cs2Xcgxw/d7cdjVp0ga6MvnVkfQySyNHwkqHMDsmseDNdcmzmzZJtRaEvVjn6Vr+2lhjSuv4s4vPgvzpShmDn/Em1y4BDJyWqPxDM13IOZ5bTvlku8jspgm0AZhSkh4266pvk/4uErHNvG0rjRFFj2hJfstzFqbtQSeMrqSmXlpf8Fp/FO8GPEZsE6pjE8xeVz4w7kLwIztzqreStf38spbYs7fRpDcf5hPCq5COeVcv6f1mBabJ2d3bR4PojEMSTYWFEaN0XuD4Ji5zIkxhUNyQ191noxMIm8uAahJjdP8H7aqbRSLJYXUmpFW2XjKlQ66AzyHCDRTaPg4aV4rsNhdnyEfwDGVLogMo3t8DP0iAjr1GoUEE1YqZZQ3IYE=
////            "AejsQ18+QvgqNMrEAkUPTw02rYwJABlE5hRcAoVR842ZUGerq17/gFEezon/IQtnVQnAeB9CQJ/fcvaqXGSwZ2RAj7CuZYmSzx4AbS8jTv0SXxUYi0s2rh15lJfbOOJ2YEQAN6cgpwnY2OZ0gPMQBPBioMSnxQ15eaTkeWFkkpTXSf8Xg+WiFf7LZhOafP5nxlb5hneNdWUnYVviuJv/EgB4GHhOClhWekDeuegXRIRcUYU7nQ32eCEreE7rEx5PjiExwNe2CFk2HTCrXgIem89h21UUCGg8wK66v9e06NVtGR3tnW7xt8oWF8JkrDFhVxICa6COx9nm0v4Sbv2NANYhdf8j1u2JyCC6FOzy0e9M89LCOABh9bL6hXYNNkX3FQhY71DLjRNtsdd8l2LJC1/R9BJDsZ+BPzvU2nd1DUGbqkP20O2BQInoNsTX7Z7hEQJtLMXTXuNb91Uq0xoRWECULtE8eelr8MFRc3nlLOJ39fyRjj1ukltfkIptfYtUxeSiB5hoJXFi4NKV+NskcWqS1Yz5N8mRkRmfGVcqEPO/8nYMPIVsSg2wCij6mXCu51B8X50NvKQsTgYVzOZgGvOWuU16HtnVM54RfLBnZlpbHTn2Gb+kkWMrCyU3VTky6H22kD1+cK3UZCejJxVB5NsfSPUsNpxNqGxxMcpBPTln+9bFaDxe9RIEI4ZyjgbnnSnRe4po/uJYV6dl1ne4ltLla7AOFkfdWorXt2WxhGH9QtBrOdJ2rnV1tTBXawDs+Vkay+h4v1YGXUP9czeClRTNCRCfE0jOL8+9CO/h+IIjX2Gyy065";
//
//    private final int CAMERA_PERMISSION_REQUEST = 0;
//    // The main object for recognizing and displaying barcodes.
//    private BarcodePicker mBarcodePicker;
//    private boolean mDeniedCameraAccess = false;
//    private boolean mPaused = true;
//    private String scancode;
//    private String scanWt;
//    private boolean scanQntItem;
//    private String scanQntCount;
//    private int scanSymbology;
//    private boolean fromScaleScreen;
//    private boolean enableQRCode = false;
//
//    private ScannerActivityViewModel scannerActivityViewModel;
//    private ActivityScannerBinding activityScannerBinding;
//    private HomeActivityViewModel homeActivityViewModel;
//
//    private FragmentPluAddWeightBinding fragmentPluAddWeightBinding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(Commons.FEATURE_IS_SUPER_SMART){
//            //reading the extra to enable or disable the QR code symbology
//            Bundle extras = getIntent().getExtras();
//            if (extras != null) {
//                enableQRCode = extras.getBoolean(ENABLE_QR_CODE);
//            }
//        }
//        sScanditSdkAppKey = ScanditBackendKey();
//        ScanditLicense.setAppKey(BuildConfig.DEBUG ? BuildConfig.DEBUG_KEY_SCAN : sScanditSdkAppKey);
//        mActivity =  ScannerActivity.this;
//
//        // Initialize and start the bar code recognition.
//        try {
//            initializeAndStartBarcodeScanning();
//        } catch (Exception e) {
//            SNGLogger.debug(this.getClass().getName(), "********************"+e.toString());
//        }
//        activityScannerBinding.scannerCloseIcon.setOnClickListener((v) -> finish());
//        setInstructions(enableQRCode? getString(R.string.qr_helper_text) : getString(R.string.barcode_helper_text),R.color.title,null);
//        Observable.OnPropertyChangedCallback uiState = new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable sender, int propertyId) {
////                if (scannerActivityViewModel.showScreen.get()){
////                 //   test();
////
////                        homeActivityViewModel.getItemByPLUCode("0780461755017","OPEN_SCALE_SCREEN", "");
////
////                }
//
//                if (scannerActivityViewModel.showScreen.get() && scannerActivityViewModel.weightItem.get()) {
//                    //Have to get the right response from viewModel to pass bellow with plucode
//                   // homeActivityViewModel.getItemByPLUCode(scannerActivityViewModel.pluCodes.get(), "OPEN_SCALE_SCREEN", "");
//                   openScaleScreen();
//                }
//                UiState<Item> state = scannerActivityViewModel.uiState.get();
//                if (state != null && state.isError()) {
//                    //Don't display error if item is weighted and going to scale
//                    if(!fromScaleScreen){
//                    setInstructions("Item not supported",R.color.red,R.drawable.ic_material_icons_ic_red_error);
//                    }
//
////                    activityScannerBinding.instructionTextView.setText();
////                    activityScannerBinding.instructionTextView.setTextColor(ContextCompat.getColor(ScannerActivity.this, R.color.red));
////                    Drawable mDrawable = ContextCompat.getDrawable(ScannerActivity.this, R.drawable.ic_material_icons_ic_red_error);
////                    mDrawable.setColorFilter(new
////                            PorterDuffColorFilter(ContextCompat.getColor(ScannerActivity.this, R.color.red), PorterDuff.Mode.MULTIPLY));
////
////                    final boolean isLTR = ViewCompat.LAYOUT_DIRECTION_LTR == ViewCompat.getLayoutDirection(activityScannerBinding.instructionTextView);
////                  //final int iconInsetLeftPadding = getResources().getDimensionPixelSize(R.dimen.icon_left_padding);
////                    final int iconInsetLeftPadding = getResources().getDimensionPixelSize(R.dimen.icon_right_padding);
////                    final Drawable insetDrawable = new InsetDrawable(mDrawable, isLTR ? iconInsetLeftPadding : 0, 0, 0, 0);
////
////                    activityScannerBinding.instructionTextView.setCompoundDrawablesWithIntrinsicBounds(insetDrawable, null, null, null);
////
////                    activityScannerBinding.instructionTextView.setCompoundDrawablePadding(-32);
////
//                    new Handler().postDelayed(() -> {
//
//                       // activityScannerBinding.instructionTextView.setCompoundDrawables(null, null, null, null);
//                       // activityScannerBinding.instructionTextView.setTextColor(ContextCompat.getColor(ScannerActivity.this, R.color.title));
//                       // activityScannerBinding.instructionTextView.setText(getString(R.string.position_barcode_in_the_frame));
//                        mBarcodePicker.resumeScanning();
//                    }, 1000);
//                    //!() is to make sure its not a weight Data bar item, because the response takes the ups as weight, not correct
//                } else if (state != null && state.isSuccess() &&!(scannerActivityViewModel.showScreen.get() && scannerActivityViewModel.weightItem.get())) {
//                    Item item = state.getData();
//                    Intent intent = new Intent();
//                    intent.putExtra(ITEM,item);
//                    intent.putExtra(SCANCODE,scancode);
//                    intent.putExtra(SCANWT,String.valueOf(Double.valueOf(scanWt)/1000));
//                    setResult(Activity.RESULT_OK, intent);
//                    homeActivityViewModel.fromScanner.set(true);
//                    SNGLogger.debug(TAG, "onPropertyChanged: ");
//                    finish();
//                }
//
//            }
//        };
//
//        scannerActivityViewModel.uiState.addOnPropertyChangedCallback(uiState);
//
////        scannerActivityViewModel.onGS1Scan().observe(this,
////                start -> {
////                    test();
////                });
//
//    }
//
////    @Override
////    public View onCreateView(String name, Context context, AttributeSet attrs) {
//// try {
////     scannerActivityViewModel.onGS1Scan().observe(this,
////             start -> {
////                 test();
////             });
//// }catch (Exception e){
////
//// }
////
////        return super.onCreateView(name, context, attrs);
////    }
//
//    public void openScaleScreen() {
//     //   if (scannerActivityViewModel.showScreen.get() == true){
//            Bundle bundle = new Bundle();
//            bundle.putBoolean(Commons.FROM_SCANNER,true);
//            Scale_Plu_Overlay_DialogFragment fragment = new Scale_Plu_Overlay_DialogFragment();
//            fragment.setArguments(bundle);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.scannerScreen, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//            mBarcodePicker.startScanning();
//            scannerActivityViewModel.showScreen.set(false);
//            fromScaleScreen = true;
//
//      //  }
//
//
//    }
//    private  String ScanditBackendKey() {
//        String key = "";
//        if (BuildConfig.DEBUG) {
//            key = BuildConfig.DEBUG_KEY_SCAN;
//        } else if (LoginUtils.getAppsettings() != null) {
//            key = LoginUtils.getAppsettings().getScanditLicenseKey();
//        }
//        return key;
//    }
//
////    // its an exisiting fragemt
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        fragmentPluAddWeightBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_plu_add_weight, container, false);
////        fragmentPluAddWeightBinding.setScannerActivityViewModel(scannerActivityViewModel);
//////        fragmentPluAddWeightBinding.setLifecycleOwner(getActivity());
//////        keyBoardListener();
//////        builder = new AlertDialog.Builder(requireContext());
////        return fragmentPluAddWeightBinding.getRoot();
////    }
//
//
//
//
//    private void setInstructions(String  text, @ColorRes int colorId, @DrawableRes Integer drawable){
//        activityScannerBinding.instructionTextView.setVisibility(View.VISIBLE);
//        if(drawable==null)
//            activityScannerBinding.instructionTextView.setCompoundDrawables(null, null, null, null);
//        else {
//            Drawable mDrawable = ContextCompat.getDrawable(ScannerActivity.this, drawable);
//            mDrawable.setColorFilter(new
//                    PorterDuffColorFilter(ContextCompat.getColor(ScannerActivity.this, R.color.red), PorterDuff.Mode.MULTIPLY));
//
//            final boolean isLTR = ViewCompat.LAYOUT_DIRECTION_LTR == ViewCompat.getLayoutDirection(activityScannerBinding.instructionTextView);
//            //final int iconInsetLeftPadding = getResources().getDimensionPixelSize(R.dimen.icon_left_padding);
//            final int iconInsetLeftPadding = getResources().getDimensionPixelSize(R.dimen.icon_right_padding);
//            final Drawable insetDrawable = new InsetDrawable(mDrawable, isLTR ? iconInsetLeftPadding : 0, 0, 0, 0);
//
//            activityScannerBinding.instructionTextView.setCompoundDrawablesWithIntrinsicBounds(insetDrawable, null, null, null);
//         //   activityScannerBinding.instructionTextView.setCompoundDrawablePadding(-32);
//
//
//        }
//        activityScannerBinding.instructionTextView.setTextColor(ContextCompat.getColor(ScannerActivity.this, colorId));
//        activityScannerBinding.instructionTextView.setText(text);
//        new Handler().postDelayed(()->{
//           activityScannerBinding.instructionTextView.setVisibility(View.GONE);
//        },4000);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.cross_button_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int resId = item.getItemId();
//        if (resId == R.id.crossButton) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mPaused = false;
//        // Handle permissions for Marshmallow and onwards...
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            grantCameraPermissionsThenStartScanning();
//        } else {
//            // Once the activity is in the foreground again, restart scanning.
//            mBarcodePicker.startScanning();
//
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // When the activity is in the background immediately stop the
//        // scanning to save resources and free the camera.
//        mBarcodePicker.stopScanning();
//
//        mPaused = true;
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    private void grantCameraPermissionsThenStartScanning() {
//        if (this.checkSelfPermission(Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (!mDeniedCameraAccess) {
//                this.requestPermissions(new String[]{Manifest.permission.CAMERA},
//                        CAMERA_PERMISSION_REQUEST);
//            }
//        } else {
//            // We already have the permission.
//            mBarcodePicker.startScanning();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[], @NonNull int[] grantResults) {
//        if (requestCode == CAMERA_PERMISSION_REQUEST) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                mDeniedCameraAccess = false;
//                if (!mPaused) {
//                    mBarcodePicker.startScanning();
//                }
//            } else {
//                mDeniedCameraAccess = true;
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    /**
//     * Initializes and starts the bar code scanning.
//     */
//    public void initializeAndStartBarcodeScanning() {
//        builder = new AlertDialog.Builder(this);
//        // Keeps the screen on while the scanner is running.
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//        ScanSettings settings = ScanSettings.create();
//
//        if (enableQRCode) {
//            //enable QR code only to scan the cart/basket. This is added to support super smart.
//            settings.setSymbologyEnabled(Barcode.SYMBOLOGY_QR, true);
//        } else {
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_EAN13, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_EAN8, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_UPCA, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_DATA_MATRIX, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_GS1_DATABAR, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_GS1_DATABAR_EXPANDED, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_GS1_DATABAR_LIMITED, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_CODE39, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_CODE128, true);
//            settings.setSymbologyEnabled( Barcode.SYMBOLOGY_UPCE, true);
//        }
//
//        // By using this preset, the decoding of the following symbologies is enabled:
//        // Barcode.SYMBOLOGY_EAN13, Barcode.SYMBOLOGY_EAN8, Barcode.SYMBOLOGY_UPCA, Barcode.SYMBOLOGY_UPCE.
//        // settings.setSymbologyEnabled(BarcodeScannerSettings.PRESET_ENABLE_RETAIL_SYMBOLOGIES, true);
//        settings.setRestrictedAreaScanningEnabled(true);
//        // Some 1d barcode symbologies allow you to encode variable-length data. By default, the
//        // Scandit BarcodeScanner SDK only scans barcodes in a certain length range. If your
//        // application requires scanning of one of these symbologies, and the length is falling
//        // outside the default range, you may need to adjust the "active symbol counts" for this
//        // symbology. This is shown in the following few lines of code.
//        SymbologySettings symSettings = settings.getSymbologySettings(Barcode.SYMBOLOGY_CODE128);
//        short[] activeSymbolCounts = new short[]{
//                7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
//                29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
//        };
//        symSettings.setActiveSymbolCounts(activeSymbolCounts);
//        settings.setCameraFacingPreference(ScanSettings.CAMERA_FACING_BACK);
//
//        // Some Android 2.3+ devices do not support rotated camera feeds. On these devices, the
//        // barcode picker emulates portrait mode by rotating the scan UI.
//        boolean emulatePortraitMode = !BarcodePicker.canRunPortraitPicker();
//        if (emulatePortraitMode) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//
//        initDataBinding();
//
//        mBarcodePicker = new BarcodePicker(this, settings);
//        mBarcodePicker.getOverlayView().setBeepEnabled(true);
//        //  mBarcodePicker.getOverlayView().setVibrateEnabled(true);
//
//        mBarcodePicker.getOverlayView().setTorchEnabled(true);
//
//        mBarcodePicker.getOverlayView().setVibrateEnabled(true);
//        mBarcodePicker.applyScanSettings(settings);
//        // Register listener, in order to be notified about relevant events
//        // (e.g. a successfully scanned bar code).
//
//        mBarcodePicker.setOnScanListener(this);
//        activityScannerBinding.pickerContainer.addView(mBarcodePicker, 0);
//
//
//    }
//
//    /**
//     * Called when a barcode
//     * has been decoded successfully.
//     */
//    @Override
//    public void didScan(ScanSession session) {
//        ParserResult result=null;
//        String gs1_code=null;
//
//        for (Barcode code : session.getNewlyRecognizedCodes()) {
//            String data = code.getData();
//            // Truncate code to certain length.
//            String cleanData = data;
//
//            if(enableQRCode){
//                /**
//                 * If the scanner enabled to scan cart/basket QR code control the flow here.
//                 * call an API to validate the scanned QR Cod.
//                 * Based on the result show error dialog or go back to the home activity for further steps.
//                 *
//                 */
//                ScannerActivity.this.runOnUiThread(() -> handleQRCodeFlow(code.getData()));
//                return;
//            }
///*
//            if (data.length() > 30) {
//                cleanData = data.substring(0, 25) + "[...]";
//            }
//*/
//            scancode = cleanData;
//            scanWt=code.getData().substring(code.getData().length()-6);
//            scanSymbology=code.getSymbology();
//            SNGLogger.debug("=== "," scanWt "+scanWt+" code "+scancode);
//            SNGLogger.debug("=== ",code.getSymbologyName()+"  "+code.getSymbology()+"  "+code.getData());
//
//            if(code.getSymbology()==Barcode.SYMBOLOGY_DATA_MATRIX ||
//                    code.getSymbology()==Barcode.SYMBOLOGY_EAN13 ||
//                    (code.getData().length()>18 && code.getSymbology()==Barcode.SYMBOLOGY_CODE128)||
//                    code.getSymbology()==Barcode.SYMBOLOGY_GS1_DATABAR){
//                Parser p = mBarcodePicker.createParserForFormat(DataFormat.GS1AI);
//                Map<String, Object> optionsmap = new HashMap<>(1);
//                optionsmap.put("outputHumanReadableString", true);
//                optionsmap.put("allowHumanReadableCodes", true);
//                p.setOptions(optionsmap);
//                try {
//                result = p.parseString(code.getData());
//                SNGLogger.debug("=== "," Result "+result.getJsonString());
//                for(Field f:result.getFields()){
//                    if(TextUtils.equals(f.getName(),"01") || TextUtils
//                            .equals(f.getName(),"00") ||TextUtils.equals(f.getName(),"02")) {
//                        if(TextUtils.equals(f.getRawString().substring(3,8).substring(0,1),Commons.STR_ZERO)){
//                            JSONObject obj = new JSONObject((Map) f.getParsed());
//                            //get plu for GS1 Databar
//                            if (obj.get("company_prefix").equals("0000000")){
//                                gs1_code=f.getRawString().substring(8,13);
//                            }else{
//                                //get plu for Older GS1 barcode
//                                gs1_code=f.getRawString().substring(4,8);
//                            }
//                           // gs1_code=f.getRawString().substring(4,8);
//                            //Only for New GS1 Data bar codes
//                           //  gs1_code=f.getRawString().substring(8,13);
//
////                            if(f.getRawString().length() == 26|| f.getRawString().length() == 19){
////                                //Only for New GS1 Data bar codes
////                                gs1_code=f.getRawString().substring(8,13);
////                            }else{
////                                //previous scale barcode
////                                gs1_code=f.getRawString().substring(4,8);
////                                //test as an alternative
////                                gs1_code=f.getRawString().substring(11,16);
////                            }
//                        scancode=gs1_code;
//                        } //removed explicitly checking for code 9 (Organic items) and instead now checks for any unique 5 digit plu
//                        else if(!TextUtils.equals(f.getRawString().substring(3,8).substring(0,1),Commons.STR_ZERO)){
//                            gs1_code=f.getRawString().substring(3,8);
//                            scancode=gs1_code;
//                        }
//                    }
//                    //To get Qnt from each item barcode on scale by looking for qnt code 30
//                    if(TextUtils.equals(f.getName(),"30")&&!TextUtils.equals(f.getRawString(),"00")){
//                        scanQntItem =true;
//                        scanQntCount =f.getParsed().toString();
//                        try {
//                            LoginUtils.setScanQntCount(scanQntCount);
//                            LoginUtils.setScanQntItem(scanQntItem);
//                        } catch (Exception e) {
//                            SNGLogger.debug(this.getClass().getName(), "********************"+e.toString());
//                        }
//                        SNGLogger.debug(TAG, "didScan: "+ f.getRawString());
//                    }
////                    if(TextUtils.equals(f.getName(),"21")){
////                        Intent intent = new Intent(this, HomeActivity.class);
////                        intent.putExtra("UPC2",true);
////                        startActivity(intent);
////                    }
//                }
//
//                }catch (Exception e){
//                    SNGLogger.debug(this.getClass().getName(), "********************"+e.toString());
//                }
//            }
//            if (code.getSymbology()==Barcode. SYMBOLOGY_CODE39){
//                builder.setMessage("Please scan barcode on the individual product")
//                        .setTitle("Scan Message")
//                        .setCancelable(false)
//                        .setPositiveButton("OK", (dialogInterface, i) -> {
//                            dialogInterface.cancel();
////                            homeActivityViewModel.onScanButtonClicked();
////
////                            event.onNext("CLEAR_FRAGMENTS");
//
//                        });
//
//                builder.create().show();
//            }
//
//
//        }
//
//        scannerActivityViewModel.afterCodeDetected(session,gs1_code);
//
////        if (scannerActivityViewModel.showScreen.get()&&scannerActivityViewModel.weightItem.get()){
////            homeActivityViewModel.getItemByPLUCode(scannerActivityViewModel.pluCodes.get(),"OPEN_SCALE_SCREEN", "");
////        }
//
//        mBarcodePicker.pauseScanning();
//    }
//
//    @Override
//    public void onBackPressed() {
//        mBarcodePicker.stopScanning();
//        finish();
//    }
//    public static void restartActivity(Activity activity){
////        if (Build.VERSION.SDK_INT >= 11) {
////            activity.recreate();
////        } else {
//            activity.finish();
//         //   activity.startActivity(activity.getIntent());
//      //  }
//    }
//    private void hideInstructionsLayout() {
//        //fragmentPluAddWeightBinding.instructions.instructionContainer.setVisibility(View.GONE);
//        //onBackPressed();
////        mBarcodePicker.resumeScanning();
////        new Handler().postDelayed(()->{
////           // activityScannerBinding.instructionTextView.setVisibility(View.GONE);
////            homeActivityViewModel.onScanButtonClicked();
////        },1000);
//        activityScannerBinding.instructions.instructionContainer.setVisibility(View.GONE);
//         //   restartActivity(mActivity);
//
//
//
//        // requireActivity().startActivityForResult(new Intent(getActivity(), ScannerActivity.class), REQ_CODE);
//
//   //     homeActivityViewModel.showScale.set(false);
////        if (!homeActivityViewModel.updatedItem.get().itemExist){
//             startActivityForResult(new Intent(this, ScannerActivity.class), REQ_CODE);
////           // fragmentPluAddWeightBinding.addBtn.setVisibility(View.VISIBLE);
////        }
////        else {
//////            fragmentPluAddWeightBinding.btnUpdate.setVisibility(View.VISIBLE);
//////            fragmentPluAddWeightBinding.btnCancel.setVisibility(View.VISIBLE);
////        }
//    }
//
//    private void initDataBinding() {
//        activityScannerBinding = DataBindingUtil.setContentView(this, R.layout.activity_scanner);
//        homeActivityViewModel = ViewModelProviders.of(this, factory).get(HomeActivityViewModel.class);
//        homeActivityViewModel.toolbarTitle.set(enableQRCode ? getResources().getString(R.string.scan_qrcode) : getResources().getString(R.string.scan_barcode));
//        homeActivityViewModel.showTitle.set(true);
//        homeActivityViewModel.showCartCount.set(!enableQRCode);
//        homeActivityViewModel.itemCount.set(Utils.cartCount);
//        showEstimatedTotal(homeActivityViewModel, Utils.estimatedTotal);
//      //  fragmentPluAddWeightBinding = DataBindingUtil.setContentView(this, R.layout.fragment_plu_add_weight);
//        scannerActivityViewModel = ViewModelProviders.of(ScannerActivity.this, factory).get(ScannerActivityViewModel.class);
//        scannerActivityViewModel.isQRCodeEnabled.set(enableQRCode);
//        activityScannerBinding.setScannerViewModel(scannerActivityViewModel);
//        activityScannerBinding.setHomeActivityViewModel(homeActivityViewModel);
//        activityScannerBinding.instructions.btnSkip.setOnClickListener((v) -> hideInstructionsLayout());
//       // fragmentPluAddWeightBinding.instructions.btnSkip.setOnClickListener((v) -> hideInstructionsLayout());
//     //   fragmentPluAddWeightBinding.setScannerActivityViewModel(scannerActivityViewModel);
//        activityScannerBinding.setLifecycleOwner(ScannerActivity.this);
//        activityScannerBinding.cartCount.cartLayout.setOnClickListener(v -> {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//            finishAffinity();
//        });
//    }
//
//
//    /**
//     * Method to handle QR code flow for super smart
//     * @param scannedData
//     */
//    private void handleQRCodeFlow(String scannedData) {
//        mBarcodePicker.pauseScanning();
//        Utils.showProgressDialog(this,false);
//        scannerActivityViewModel.cartCodeLookup(scannedData,this).observe(this, response -> {
//            Utils.dismissProgressDialog();
//            if (response.getStatus() == DataWrapper.STATUS.SUCCESS) {
//                if (response.getData() != null && response.getData().getAck().equals(Commons.STR_ZERO) && response.getData().getBasket().equalsIgnoreCase("cartFound")) {
//                    //valid cart or basket QR code. Proceed further.
//                    Intent intent = new Intent();
//                    intent.putExtra(SCANCODE, scannedData);
//                    intent.putExtra(ENABLE_QR_CODE, true);
//                    setResult(Activity.RESULT_OK, intent);
//                    finish();
//                } else {
//                    //invalid code. Show error message.
//                    if (response.getData() != null && response.getData().getErrors() != null &&
//                            response.getData().getErrors().size() > 0) {
//                        showCartErrorDialog(false, "" + response.getData().getErrors().get(0).getCode());
//                    } else {
//                        showCartErrorDialog(true, "");
//                    }
//                }
//            } else {
//                //API Failed. Show generic message
//                NetworkErrorWrapper networkError = (NetworkErrorWrapper) response.getCustomErrorObject();
//                if (networkError.isServerError()) {
//                    showCartErrorDialog(true, "");
//                } else {
//                    showCartErrorDialog(false, "Error: " + networkError.getErrorCode());
//                }
//            }
//        });
//
//    }
//
//    /**
//     * Method to show error message for lookup
//     * @param isGenericError
//     * @param errorCode
//     */
//    public void showCartErrorDialog(boolean isGenericError, String errorCode) {
//        mBarcodePicker.pauseScanning();
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (dialog.getWindow() != null) {
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.error_dialog_layout);
//        TextView txtDesc = dialog.findViewById(R.id.txt_error_desc);
//        TextView txtErrorCode = dialog.findViewById(R.id.txt_error_code);
//        ImageView close = dialog.findViewById(R.id.img_close);
//        if (isGenericError) {
//            txtDesc.setText(getString(R.string.service_problem_text));
//            dialog.findViewById(R.id.txt_heading).setVisibility(View.GONE);
//            txtErrorCode.setVisibility(View.GONE);
//            close.setVisibility(View.GONE);
//        } else {
//            txtErrorCode.setText(errorCode);
//            close.setOnClickListener(v -> {
//                dismissDialogAndResumeScanning(dialog);
//            });
//        }
//
//        dialog.findViewById(R.id.btnContinue).setOnClickListener(v -> {
//            dismissDialogAndResumeScanning(dialog);
//        });
//
//        dialog.show();
//    }
//
//    private void dismissDialogAndResumeScanning(Dialog dialog) {
//        dialog.dismiss();
//        mBarcodePicker.resumeScanning();
//    }
//}
