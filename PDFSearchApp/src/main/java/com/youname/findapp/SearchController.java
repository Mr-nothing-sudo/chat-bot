package com.yourname.findapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;

public class SearchController {

    @FXML
    private TextField queryField;

    @FXML
    private TextArea resultArea;

    @FXML
    private Button searchButton;

    private PDFSearcher pdfSearcher;

    public void initialize() {
        String folderPath = "/home/mc/pdf"; // Change this to your PDF folder path
        pdfSearcher = new PDFSearcher(folderPath);
        
        searchButton.setOnAction(event -> performSearch());
    }

    private void performSearch() {
        String query = queryField.getText();
        if (query.isEmpty()) {
            resultArea.setText("Please enter a query.");
            return;
        }

        resultArea.setText("Searching...");

        Task<String> searchTask = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return pdfSearcher.search(query);
            }
        };

        searchTask.setOnSucceeded(event -> {
            String result = searchTask.getValue();
            resultArea.setText(result);
        });

        searchTask.setOnFailed(event -> {
            resultArea.setText("Search failed.");
        });

        new Thread(searchTask).start();
    }
}