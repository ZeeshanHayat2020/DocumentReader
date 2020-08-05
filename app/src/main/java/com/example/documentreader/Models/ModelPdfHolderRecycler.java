package com.example.documentreader.Models;

public class ModelPdfHolderRecycler {
    private int imgId;
    private String pdfFileName;
    private String pdfFileUri;
    public ModelPdfHolderRecycler(int imgId,  String pdfFileName, String pdfFileUri) {
        this.imgId = imgId;
        this.pdfFileName = pdfFileName;
        this.pdfFileUri = pdfFileUri;
    }

    public int getImgId() {
        return imgId;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public String getPdfFileUri() {
        return pdfFileUri;
    }





}
