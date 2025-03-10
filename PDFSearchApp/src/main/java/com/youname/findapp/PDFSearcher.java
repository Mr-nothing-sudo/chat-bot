package com.yourname.findapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFSearcher {
    private List<String> pdfFiles;

    public PDFSearcher(String folderPath) {
        pdfFiles = new ArrayList<>();
        loadPDFFiles(folderPath);
    }

    private void loadPDFFiles(String folderPath) {
        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".pdf")) {
                pdfFiles.add(file.getAbsolutePath());
            }
        }
    }

    public String search(String query) {
        StringBuilder results = new StringBuilder();
        for (String pdfFile : pdfFiles) {
            try (PDDocument document = PDDocument.load(new File(pdfFile))) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String text = pdfStripper.getText(document);
                if (text.toLowerCase().contains(query.toLowerCase())) {
                    results.append("Found in: ").append(pdfFile).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results.length() > 0 ? results.toString() : "No results found.";
    }
}