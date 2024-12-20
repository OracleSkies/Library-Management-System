/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class SIGNUP extends javax.swing.JFrame {

    /**
     * Creates new form SIGNUP
     */
    public SIGNUP() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        Cpass = new javax.swing.JPasswordField();
        Password = new javax.swing.JPasswordField();
        backButton = new javax.swing.JButton();
        ConfirmButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 310, 30));

        Cpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpassActionPerformed(evt);
            }
        });
        getContentPane().add(Cpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 310, 30));
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 310, 30));

        backButton.setBackground(new java.awt.Color(51, 51, 51));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dark_Back.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 180, 80));

        ConfirmButton.setBackground(new java.awt.Color(51, 51, 51));
        ConfirmButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ConfirmButton.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dark_Confirm.png"))); // NOI18N
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ConfirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 230, 80));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(":");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 20, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BG_SignUpWindow.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CpassActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        Start start = new Start();
        setVisible(false);
        start.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmButtonActionPerformed
        // TODO add your handling code here:
        String username = Username.getText();
        String Pass = Password.getText();
        String Cpassword = Cpass.getText();
          //Checks if userinput is accurate
         if (!Pass.equals(Cpassword)){
            
           JOptionPane.showMessageDialog(this,"Passwords don't match","Error",JOptionPane.ERROR_MESSAGE);
           return;
        }
        saveToFile(username,Pass);

    }//GEN-LAST:event_ConfirmButtonActionPerformed

    private void saveToFile(String username, String password) {
        try (var writer = new BufferedWriter(new FileWriter("ManageAccounts.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();

            JOptionPane.showMessageDialog(this, "Account created successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
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
            java.util.logging.Logger.getLogger(SIGNUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SIGNUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SIGNUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SIGNUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SIGNUP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JPasswordField Cpass;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Username;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
