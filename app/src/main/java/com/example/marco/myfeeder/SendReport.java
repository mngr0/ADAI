package com.example.marco.myfeeder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
//import net.gotev.*;

public class SendReport extends AppCompatActivity {


    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast toast = Toast.makeText(getApplicationContext(), "photo taken!", Toast.LENGTH_SHORT);
        toast.show();
        if(resultCode == RESULT_OK) {
            Uri image = data.getData();
            Log.d("data", data.getData().toString());
            //Bundle extras = data.getExtras();
            Bitmap mImageBitmap= null; //= (Bitmap) extras.get("data");

            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
            } catch (FileNotFoundException e) {
                Log.e("PHOTOTAKEN fnf",e.toString());
            } catch (IOException e) {
                Log.e("PHOTOTAKEN io",e.toString());
            }

            // Open a HTTP connection
            if(mImageBitmap != null){
                Log.d("LOADING", "received data not null, saving");
                String filename = "test4.jpg";
                File file = new File(getFilesDir(), filename);
                String fileContents = "Hello world!";
                FileOutputStream outputStream;
                Log.d("PATH",file.getPath());
                try {
                    outputStream = openFileOutput(filename,Context.MODE_PRIVATE);
                    outputStream.write(mImageBitmap.getRowBytes());
                    outputStream.close();
                    Log.d("SAVED", "saved of size" +file.getTotalSpace());
                    PostImage pi= new PostImage(data.getData().toString());//file.getAbsolutePath());
                    Log.d("UPLOAD", "starting");
                    pi.execute();
                } catch (Exception e) {
                    Log.e("PHOTOTAKEN",e.toString());
                }

                /*
                HttpURLConnection conn;

                try {

                    String boundary =  "*****"+Long.toString(System.currentTimeMillis())+"*****";
                    URL url = new URL("http://altaserver.ddns.net/Marco/Marco.php");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use cache
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("uploaded_file", "test.jpg");
                    Log.e("LOADING", "all ok");
                } catch (Exception e) {
                    Toast toast2 = Toast.makeText(getApplicationContext(), "BOOOOOO!", Toast.LENGTH_SHORT);
                    toast2.show();
                    Log.e("LOADING", e.toString());
                }
                */
            }
            else{
                Log.e("LOADING", "receiver data is null");
            }
        }
        else{
            Log.e("LOADING", "rc not ok");
        }
    }










    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int GET_FROM_GALLERY = 3;
    public void dispatchTakePictureIntent(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        //    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //}
    }




    public File saveBitmapToFile(File file) {
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            //Create a new image file and then return it.
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

/*
    private String uploadFile() {
        String responseString = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL);

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new ProgressListener() {

                        @Override
                        public void transferred(long num) {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });

            File sourceFile = new File(filePath);

            // Adding file data to http body
            entity.addPart("image", new FileBody(sourceFile));

            // Extra parameters if you want to pass to server
            entity.addPart("website",
                    new StringBody("www.androidhive.info"));
            entity.addPart("email", new StringBody("abc@gmail.com"));

            totalSize = entity.getContentLength();
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        return responseString;

    }
*/

}