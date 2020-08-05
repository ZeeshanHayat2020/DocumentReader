package com.example.documentreader.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.documentreader.Adapters.AdapterPdfHolderRecycler;
import com.example.documentreader.Constats.Constant;
import com.example.documentreader.Interfaces.OnItemClickListener;
import com.example.documentreader.Models.ModelHomeRecycler;
import com.example.documentreader.Models.ModelPdfHolderRecycler;
import com.example.documentreader.R;

import java.io.File;
import java.util.ArrayList;

public class PdfHolderActivity extends BaseActivity {

    private static final String TAG = "PdfHolderActivity";
    private RecyclerView recyclerView;
    private AdapterPdfHolderRecycler adapterPdfHolderRecycler;
    private ArrayList<ModelPdfHolderRecycler> modelPdfHolderRecyclerList;
    private RecyclerView.LayoutManager layoutManager;
    private File externalStorageDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_holder);
//        externalStorageDir=new File(String.valueOf(Environment.getExternalStorageDirectory()));
        externalStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        modelPdfHolderRecyclerList = new ArrayList<>();
        if (!hasPermission()) {
            checkStoragePermission();
        }
        new LoadInBg().execute();
    }

    private void getAllPDFFiles(File parentFile) {
        int id = R.drawable.ic_baseline_picture_as_pdf_24;
        File[] fileList = parentFile.listFiles();
        String pdfPattern = ".pdf";

        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    getAllPDFFiles(fileList[i]);
                } else {
                    if (fileList[i].getName().endsWith(pdfPattern)) {
                        if (!modelPdfHolderRecyclerList.contains(fileList[i])) {
                            modelPdfHolderRecyclerList.add(new
                                    ModelPdfHolderRecycler(id,
                                    fileList[i].getName(),
                                    fileList[i].getAbsolutePath()
                            ));
                        }
                    } else {
                        Log.d(TAG, "No Pdf Files....");
                    }
                }
            }
        } else {
            Log.d(TAG, "Failed to get External Storage Dir");
        }
    }

    private void buildThisRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView_pdfHolder);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        if (modelPdfHolderRecyclerList != null) {
            adapterPdfHolderRecycler = new AdapterPdfHolderRecycler(this, modelPdfHolderRecyclerList);
            for (int i = 0; i < modelPdfHolderRecyclerList.size(); i++) {
                Log.d(TAG, "Checking Array...." + "Img ID=" + modelPdfHolderRecyclerList.get(i).getImgId() +
                        "File Uri=  " + modelPdfHolderRecyclerList.get(i).getPdfFileUri()
                        + "Name=" + modelPdfHolderRecyclerList.get(i).getPdfFileName());
            }
        }
        adapterPdfHolderRecycler.notifyDataSetChanged();
        recyclerView.setAdapter(adapterPdfHolderRecycler);
        adapterPdfHolderRecycler.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(PdfHolderActivity.this, PdfViewerActivity.class);
                intent.putExtra(Constant.CURRENT_PDF_ITEM_KEY, modelPdfHolderRecyclerList.get(position).getPdfFileUri());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllPDFFiles(externalStorageDir);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class LoadInBg extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getAllPDFFiles(externalStorageDir);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            buildThisRecyclerView();
        }
    }

}