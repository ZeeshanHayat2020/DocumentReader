package com.example.documentreader.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.documentreader.Constats.Constant;
import com.example.documentreader.R;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;

public class ScanDocumentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView scannedIv;
    private Button btnGallery, btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_document);
        scannedIv=(ImageView) findViewById(R.id.scannedImage);
        btnGallery=(Button) findViewById(R.id.btnGallery);
        btnCamera=(Button) findViewById(R.id.btnCamera);

        btnGallery.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }


    private void openFromGallery(){
        int preference= ScanConstants.OPEN_MEDIA;
        Intent intent= new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, Constant.RESULT_KEY_FROM_SCANNED_ACTIVITY);
    }
    private void openFromCamera(){
        int preference= ScanConstants.OPEN_CAMERA;
        Intent intent= new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, Constant.RESULT_KEY_FROM_SCANNED_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == Constant.RESULT_KEY_FROM_SCANNED_ACTIVITY) {
                Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    getContentResolver().delete(uri, null, null);
                    scannedIv.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGallery:{
                openFromGallery();
            }break;
            case R.id.btnCamera:{
                openFromCamera();
            }break;
        }
    }
}