/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author yan
 */
public class BookRemoveWindow extends javax.swing.JFrame {

    /**
     * Creates new form BookRemoveWindow
     */
    public BookRemoveWindow() {
        initComponents();
        // Add ActionListener to the Remove button
        jButton1.addActionListener(e -> removeBookFromFile());
    }
    
    private void removeBookFromFile() {
        String bookId = jTextField1.getText().trim(); // Get the input from the text field

        boolean isBorrowed = false;
        String borrowedFilePath = "Borrowed.txt";
        if (bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a book title.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if the book is already borrowed at Borrowed.txt
        try (BufferedReader borrowedReader = new BufferedReader(new FileReader(borrowedFilePath))) {
            String line;
            while ((line = borrowedReader.readLine()) != null) {
                String[] info = line.split(",");
                if (info[0].equalsIgnoreCase(bookId)) { // Check if the exact title exists in the borrowed list
                    isBorrowed = true;
                    borrowedReader.close();
                    break;
                }
            }
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "An error occurred while reading borrowed books: " + e.getMessage(), 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If the book is borrowed, show an error message
        if (isBorrowed) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "The book is already borrowed and is not available: " + bookId, 
                    "Book Not Available", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        File originalFile = new File("Library.txt");
        File tempFile = new File("books_temp.txt");

        boolean isRemoved = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the book ID
                if (line.contains(bookId)) {
                    isRemoved = true; // Mark as removed
                    continue; // Skip writing this line to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error processing file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         // Replace the original file with the temp file
        if (isRemoved) {
            if (originalFile.delete()) {
                tempFile.renameTo(originalFile);
                JOptionPane.showMessageDialog(this, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error updating the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete(); // Cleanup temp file
            JOptionPane.showMessageDialog(this, bookId + " is not found in the library or currently borrowed.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        backButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dark_Remove.png"))); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 230, 80));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 470, 30));

        backButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dark_Back.png"))); // NOI18N
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(backButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 200, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BG_BookRemoveWindow.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private String checkBorrowed (String title){
        //This method checks if
        String data;
        Set<String> borrowedBooks = loadBorrowedBooks("Borrowed.txt");
        
        if (borrowedBooks.contains(title)) {
            data = "BORROWED"; // Update availability to "NO" if the book is borrowed
        } else {
            data = "AVAILABLE"; // Update availability to "YES" if the book is available
        }
        
        return data;
    
    }
    
    private Set<String> loadBorrowedBooks(String filePath) {
        Set<String> borrowedBooks = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                borrowedBooks.add(line.trim()); // Assuming borrowed.txt contains one book title per line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }
    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        // TODO add your handling code here:
        DASHBOARD dash = new DASHBOARD();
        setVisible(false);
        dash.setVisible(true);
    }//GEN-LAST:event_backButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookRemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookRemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookRemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookRemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookRemoveWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
