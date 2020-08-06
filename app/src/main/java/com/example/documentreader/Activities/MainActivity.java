package com.example.documentreader.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.documentreader.Adapters.AdapterHomeRecycler;
import com.example.documentreader.Interfaces.OnItemClickListener;
import com.example.documentreader.Models.ModelHomeRecycler;
import com.example.documentreader.R;
import com.scanlibrary.ScanActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private RecyclerView recyclerView;
    private AdapterHomeRecycler adapterHomeRecycler;
    private ArrayList<ModelHomeRecycler> modelHomeRecyclerList;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadItemsForRecycler();
        buildRecyclerView();
    }

    private void loadItemsForRecycler(){
        int[] imgIds={
                R.drawable.ic_baseline_picture_as_pdf_24,
                R.drawable.ic_baseline_picture_as_pdf_24,
                R.drawable.ic_baseline_picture_as_pdf_24,
                R.drawable.ic_baseline_picture_as_pdf_24
        };
        String[] btnText={
                "Read PDF",
                "Scan Docs",
                "Img to Pdf",
                "Lock File"
        };
        modelHomeRecyclerList=new ArrayList<>();
        for (int i=0; i<imgIds.length; i++){
            modelHomeRecyclerList.add(new ModelHomeRecycler(imgIds[i], btnText[i]));
        }
    }
    private void buildRecyclerView(){
        recyclerView=findViewById(R.id.recyclerView_home);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this, 2);
        adapterHomeRecycler=new AdapterHomeRecycler(this, modelHomeRecyclerList);
        adapterHomeRecycler.notifyDataSetChanged();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHomeRecycler);
        adapterHomeRecycler.setOnItemClickListener(this);
    }


    @Override
    public void onItemClicked(int position) {
        switch (position){
            case 0:{
                Intent intent= new Intent(this, PdfHolderActivity.class);
                startActivity(intent);
            }break;
            case 1:{
               /* Intent intent= new Intent(this, ScanDocumentActivity.class);
                startActivity(intent);*/
                 Intent intent= new Intent(this, ScanActivity.class);
                startActivity(intent);

            }break;
            case 2:{}break;
            case 3:{}break;
        }

    }
}