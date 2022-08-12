/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.safeway.postest.scanner.view;

import static android.content.DialogInterface.OnClickListener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.scandit.datacapture.barcode.capture.*;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.data.SymbologyDescription;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayStyle;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinder;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyle;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BarcodeScanActivity
        extends CameraPermissionActivity implements BarcodeCaptureListener {

    // Enter your Scandit License key here.
    // Your Scandit License key is available via your Scandit SDK web account.
    public static final String SCANDIT_LICENSE_KEY = "AaqxVaWBMWwkMnS3+x0/ZWMycxOeDfuuDVASeYhvEODUIi3Z3nytTlpUCdAiEuC3lgZl9OVM8n0uWk2Dz0vQrSJnDPFKJh3WGmyVYBpWK878XGuFRkPN1i43HipdMW193wDvAHMiIMdBADHNm/W0Q6xHLBxA+b5NLwEgqM+jxgmLzJelJTBI7KpKPSAWZ625hyO9KxkHwUKHGFDRFHNYaTQKHaMASEE4n7ar+076Sw2AQgnXURRhp8GxWS19o3j3yCHzeEKYXg4kI2150A3W/iwbnjgn276KY1Q/ZSK1BLSTsPm8bq5mU92cDgP2TxR/8TnARnnEPwmQEQJAsQZHutl2oo0rfUUkjf0bDlnGbfPExv6jiiTpQVQWhhUDxBn39YYZJSxO7sWo+zbOZFdc7jaiMybgoKxFT4EikYs2l/2V0IW6GlaNgYkqB499wBp5SE7hdlzFRcjttl2UqPbY1mHhYkxjI54MrhR8HCt2wIWa/krg9tDNrNiyP/Jalh3lLjJsAMbFLIUYdVN2TedeatrU0cl9zsrldm6YwzXSqBds1trwIZD5zpKQjU9DxyTco3PLHK6xNwOdMkkAEs/zor7ilIScrwnM1dPz7XBYG63OTB6AYBaRIinbWMMTmfdUs9AkZ8P7CjRg10r7zsBmUT4hj/mfsckxISaSOe7fByGz7tyGj74YzPDIQzdgjcA/Q98Cd/Z50j0iMNcT31Dwt3JyE3uo7u2KPQDxtVO2BDJdnv2jsY2/dE6sNpniGeLqKmUuc1yuO6kv2Bjv6Q9uKzwkF7doYTEaVkRQC3zN47NBvozPPRCXE47wTGk=";

    private DataCaptureContext dataCaptureContext;
    private BarcodeCapture barcodeCapture;
    private Camera camera;
    private DataCaptureView dataCaptureView;

    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize and start the barcode recognition.
        initializeAndStartBarcodeScanning();
    }

    private void initializeAndStartBarcodeScanning() {
        // Create data capture context using your license key.
        dataCaptureContext = DataCaptureContext.forLicenseKey(SCANDIT_LICENSE_KEY);

        // Use the default camera with the recommended camera settings for the BarcodeCapture mode
        // and set it as the frame source of the context. The camera is off by default and must be
        // turned on to start streaming frames to the data capture context for recognition.
        // See resumeFrameSource and pauseFrameSource below.
        camera = Camera.getDefaultCamera(BarcodeCapture.createRecommendedCameraSettings());
        if (camera != null) {
            dataCaptureContext.setFrameSource(camera);
        } else {
            throw new IllegalStateException("Sample depends on a camera, which failed to initialize.");
        }

        // The barcode capturing process is configured through barcode capture settings
        // which are then applied to the barcode capture instance that manages barcode recognition.
        BarcodeCaptureSettings barcodeCaptureSettings = new BarcodeCaptureSettings();

        // The settings instance initially has all types of barcodes (symbologies) disabled.
        // For the purpose of this sample we enable a very generous set of symbologies.
        // In your own app ensure that you only enable the symbologies that your app requires as
        // every additional enabled symbology has an impact on processing times.
        HashSet<Symbology> symbologies = new HashSet<>();
        symbologies.add(Symbology.EAN13_UPCA);
        symbologies.add(Symbology.EAN8);
        symbologies.add(Symbology.UPCE);
        symbologies.add(Symbology.QR);
      //  symbologies.add(Symbology.DATA_MATRIX);
        symbologies.add(Symbology.CODE39);
        symbologies.add(Symbology.CODE128);
     //   symbologies.add(Symbology.INTERLEAVED_TWO_OF_FIVE);

        barcodeCaptureSettings.enableSymbologies(symbologies);

        // Some linear/1d barcode symbologies allow you to encode variable-length data.
        // By default, the Scandit Data Capture SDK only scans barcodes in a certain length range.
        // If your application requires scanning of one of these symbologies, and the length is
        // falling outside the default range, you may need to adjust the "active symbol counts"
        // for this symbology. This is shown in the following few lines of code for one of the
        // variable-length symbologies.
        SymbologySettings symbologySettings =
                barcodeCaptureSettings.getSymbologySettings(Symbology.CODE39);

        HashSet<Short> activeSymbolCounts = new HashSet<>(
                Arrays.asList(new Short[] { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 }));

        symbologySettings.setActiveSymbolCounts(activeSymbolCounts);

        // Create new barcode capture mode with the settings from above.
        barcodeCapture = BarcodeCapture.forDataCaptureContext(dataCaptureContext, barcodeCaptureSettings);

        // Register self as a listener to get informed whenever a new barcode got recognized.
        barcodeCapture.addListener(this);

        // To visualize the on-going barcode capturing process on screen, setup a data capture view
        // that renders the camera preview. The view must be connected to the data capture context.
        dataCaptureView = DataCaptureView.newInstance(this, dataCaptureContext);

        // Add a barcode capture overlay to the data capture view to render the location of captured
        // barcodes on top of the video preview.
        // This is optional, but recommended for better visual feedback.
        BarcodeCaptureOverlay overlay = BarcodeCaptureOverlay.newInstance(
                barcodeCapture,
                dataCaptureView,
                BarcodeCaptureOverlayStyle.FRAME
        );
        overlay.setViewfinder(new RectangularViewfinder(RectangularViewfinderStyle.SQUARE));

        // Adjust the overlay's barcode highlighting to match the new viewfinder styles and improve
        // the visibility of feedback. With 6.10 we will introduce this visual treatment as a new
        // style for the overlay.
        Brush brush = new Brush(Color.TRANSPARENT, Color.WHITE, 3f);
        overlay.setBrush(brush);

        setContentView(dataCaptureView);
    }

    @Override
    protected void onPause() {
        pauseFrameSource();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        barcodeCapture.removeListener(this);
        dataCaptureContext.removeMode(barcodeCapture);
        super.onDestroy();
    }

    private void pauseFrameSource() {
        // Switch camera off to stop streaming frames.
        // The camera is stopped asynchronously and will take some time to completely turn off.
        // Until it is completely stopped, it is still possible to receive further results, hence
        // it's a good idea to first disable barcode capture as well.
        barcodeCapture.setEnabled(false);
        camera.switchToDesiredState(FrameSourceState.OFF, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check for camera permission and request it, if it hasn't yet been granted.
        // Once we have the permission the onCameraPermissionGranted() method will be called.
        requestCameraPermission();
    }

    @Override
    public void onCameraPermissionGranted() {
        resumeFrameSource();
    }

    private void resumeFrameSource() {
        dismissScannedCodesDialog();

        // Switch camera on to start streaming frames.
        // The camera is started asynchronously and will take some time to completely turn on.
        barcodeCapture.setEnabled(true);
        camera.switchToDesiredState(FrameSourceState.ON, null);
    }

    private void dismissScannedCodesDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private void showResult(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.setCancelable(false)
                .setTitle(result)
                .setPositiveButton(android.R.string.ok,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                barcodeCapture.setEnabled(true);
                            }
                        })
                .create();
        dialog.show();
    }

    @Override
    public void onBarcodeScanned(
            @NonNull BarcodeCapture barcodeCapture,
            @NonNull BarcodeCaptureSession session,
            @NonNull FrameData frameData
    ) {
        if (session.getNewlyRecognizedBarcodes().isEmpty()) return;

        Barcode barcode = session.getNewlyRecognizedBarcodes().get(0);

        // Stop recognizing barcodes for as long as we are displaying the result. There won't be any
        // new results until the capture mode is enabled again. Note that disabling the capture mode
        // does not stop the camera, the camera continues to stream frames until it is turned off.
        barcodeCapture.setEnabled(false);

        // If you are not disabling barcode capture here and want to continue scanning, consider
        // setting the codeDuplicateFilter when creating the barcode capture settings to around 500
        // or even -1 if you do not want codes to be scanned more than once.

        // Get the human readable name of the symbology and assemble the result to be shown.
        String symbology = SymbologyDescription.create(barcode.getSymbology()).getReadableName();
        final String result = "Scanned: " + barcode.getData() + " (" + symbology + ")";
        final String rawBarcode = barcode.getData();


        String gs1_code=null;
        Boolean weightItem = false;
        Boolean sellByEachWeightItem = false;
        String scancode = barcode.getData();
        String scanWt= barcode.getData().substring(barcode.getData().length()-6);
        String upctype = barcode.getSymbology().toString();
        if(barcode.getSymbology()==Symbology.DATA_MATRIX ||
                barcode.getSymbology()==Symbology.EAN13_UPCA ||
                (barcode.getData().length()>18 && barcode.getSymbology()==Symbology.CODE128)||
                barcode.getSymbology()==Symbology.GS1_DATABAR){

if(barcode.getData().length()>18) {
    final String rawWithoutPrefix = barcode.getData().substring(2,15);
    if (TextUtils.equals(rawBarcode.substring(0, 2), "01") || TextUtils
            .equals(rawBarcode.substring(0, 2), "00") || TextUtils.equals(rawBarcode.substring(0, 2), "02")) {

        if (TextUtils.equals(rawWithoutPrefix.substring(3, 8).substring(0, 1), "0")) {
            //get plu for GS1 Databar

            if (rawBarcode.substring(2, 9).equals("0000000")) {
                gs1_code = rawBarcode.substring(10, 15);
            } else {
                //get plu for Older GS1 barcode
                gs1_code = rawBarcode.substring(4, 8);
            }

            scancode = gs1_code;
        } //removed explicitly checking for code 9 (Organic items) and instead now checks for any unique 5 digit plu
        else if (!TextUtils.equals(rawWithoutPrefix.substring(3, 8).substring(0, 1), "0")) {
            gs1_code = rawWithoutPrefix.substring(3, 8);
            scancode = gs1_code;
        }
    }

    //To get Qnt from each item barcode on scale by looking for qnt code 30
    if (TextUtils.equals(rawBarcode.substring(16, 18), "30") && !TextUtils.equals(rawWithoutPrefix, "00")) {
//                        scanQntItem =true;
//                        scanQntCount =f.getParsed().toString();
//                        try {
//                            LoginUtils.setScanQntCount(scanQntCount);
//                            LoginUtils.setScanQntItem(scanQntItem);
//                        } catch (Exception e) {
//                            SNGLogger.debug(this.getClass().getName(), "********************"+e.toString());
//                        }
//                        SNGLogger.debug(TAG, "didScan: "+ f.getRawString());
                sellByEachWeightItem = true;
    }
}
            if (symbology.equals("Code 128")) {
                upctype = "PLU";

                if (rawBarcode.length() > 18 && !sellByEachWeightItem) {
                    weightItem = true;
                } else {
                    weightItem = false;
                }
            }
//            else if(symbology.equals("EAN8")){
//               Integer length=  rawBarcode.length();
//               scancode = rawBarcode.substring(1,length);
//
//            }
//                    if(TextUtils.equals(f.getName(),"21")){
//                        Intent intent = new Intent(this, HomeActivity.class);
//                        intent.putExtra("UPC2",true);
//                        startActivity(intent);
//                    }
                }


        if (barcode.getSymbology()==Symbology.CODE39){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please scan barcode on the individual product")
                    .setTitle("Scan Message")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        dialogInterface.cancel();
//                            homeActivityViewModel.onScanButtonClicked();
//
//                            event.onNext("CLEAR_FRAGMENTS");

                    });

            builder.create().show();
        }


        Intent intent = new Intent();
        intent.putExtra("SCANCODE", scancode);
        intent.putExtra("UPCTYPE", upctype);
        intent.putExtra("isweight", weightItem.toString());
        intent.putExtra("SCANWT",String.valueOf(Double.valueOf(scanWt)/1000));
        setResult(Activity.RESULT_OK, intent);
        finish();

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                showResult(result);
//            }
//        });
    }

    @Override
    public void onSessionUpdated(@NonNull BarcodeCapture barcodeCapture,
            @NonNull BarcodeCaptureSession session, @NonNull FrameData data) {}

    @Override
    public void onObservationStarted(@NonNull BarcodeCapture barcodeCapture) {}

    @Override
    public void onObservationStopped(@NonNull BarcodeCapture barcodeCapture) {}
}
