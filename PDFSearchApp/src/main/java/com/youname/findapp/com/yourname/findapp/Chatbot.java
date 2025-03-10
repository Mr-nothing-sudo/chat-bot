package com.yourname.findapp;

import java.util.Scanner;

public class Chatbot {
    private PDFSearcher pdfSearcher;

    // Constructor: Initialize PDFSearcher with folder path
    public Chatbot(String /home/mc/pdf) {
        pdfSearcher = new PDFSearcher(/home/mc/pdf);  // Ensure the folderPath is correctly passed
    }

    // Start method to interact with the user
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the PDF Search Chatbot! Type 'exit' to quit.");
            // Infinite loop to handle user input
            while (true) {
                System.out.print("Ask your question: ");
                String question = scanner.nextLine();
                
                // Exit condition
                if (question.equalsIgnoreCase("exit")) {
                    break;
                }
                
                // Check for empty question input
                if (question.trim().isEmpty()) {
                    System.out.println("Please ask a valid question.");
                    continue;
                }
                
                // Search the question in the PDFs
                String response = pdfSearcher.search(question);  // Ensure this returns the correct result
                System.out.println(response);
            }
            // Ensure scanner is closed to prevent resource leak
        }
    }
}