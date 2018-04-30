package com.example.marco.myfeeder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRActivity extends AppCompatActivity {


    private BarcodeDetector detector;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QRA", "on create started");
        setContentView(R.layout.activity_qr);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        message = (TextView) findViewById(R.id.barcodeText);

        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        if (!detector.isOperational()) {
            Log.e("QRA", "Detector di codici a barre non attivabile");
            return;
        }

        cameraSource = new CameraSource
                .Builder(this, detector)
                .setAutoFocusEnabled(true)
                .build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                activateCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> items = detections.getDetectedItems();

                if (items.size() != 0)
                    runOnUiThread(new Runnable() {
                        public void run() {
                            String barcode = "Rilevato: " + items.valueAt(0).displayValue;
                            Log.d("QRA",barcode);
                            // message.setText(barcode);
                        }
                    });

            }
        });

    }

    private void activateCamera() {

        // verifichiamo che sia stata concessa la permission CAMERA

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);


            }

        }

        try {
            cameraSource.start(surfaceView.getHolder());
        } catch (IOException e) {
            Log.e("QRA", "Errore nell'avvio della fotocamera");
        }


    }
}