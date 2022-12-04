package com.example.learnenglishtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.github.barteksc.pdfviewer.PDFView;


public class PdfViewer extends AppCompatActivity {
    PDFView pdfView;
    DBPdfHelper db;
    private int child_id, group_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        db = new DBPdfHelper(this);
        pdfView = findViewById(R.id.pdfView);
        child_id = getIntent().getIntExtra("child_id", 0);
        group_id = getIntent().getIntExtra("group_id", 0);
        String filePath = db.getFileNamePdf(group_id, child_id);
        pdfView.fromAsset(filePath).load();
    }
}