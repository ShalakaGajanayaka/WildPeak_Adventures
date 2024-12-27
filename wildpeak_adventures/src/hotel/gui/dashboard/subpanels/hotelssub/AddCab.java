/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package hotel.gui.dashboard.subpanels.hotelssub;

import hotel.model.MYSQL2;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import raven.toast.Notifications;

/**
 *
 * @author shalaka
 */
public class AddCab extends javax.swing.JDialog {

    private CabList parent;

    /**
     * Creates new form AddRoomImages
     */
    public AddCab(CabList parent, boolean modal) {
//        super(parent, modal);
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Add Cab");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Car");

        jButton1.setBackground(new java.awt.Color(102, 0, 102));
        jButton1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(245, 245, 245));
        jButton1.setText("Save changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setText("Car Number");

        jTextField3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setText("Car Type");

        jTextField4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Fual Type");

        jTextField5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setText("Seat Capacity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jTextField2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String car = jTextField1.getText();
        String no = jTextField2.getText();
        String type = jTextField3.getText();
        String fuel = jTextField4.getText();
        String seat = jTextField5.getText();

        // Validate the car name is not empty
        if (car.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Car name cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the car number (allow alphanumeric characters, hyphens, and spaces)
        if (no.isEmpty() || !no.matches("[A-Za-z0-9 ]+")) {
            JOptionPane.showMessageDialog(null,
                    "Car number must be alphanumeric (letters, numbers, and spaces) and cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the type is not empty
        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Car type cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the fuel type is not empty
        if (fuel.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Fuel type cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate that the seat field is a valid number
        if (!isValidSeat(seat)) {
            JOptionPane.showMessageDialog(null,
                    "Seat capacity must be a valid positive number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;  // Prevent further processing if validation fails
        }

        // Continue with further processing after successful validation
        try {
            // Step 1: Retrieve the maximum current id
            String maxIdQuery = "SELECT MAX(id) FROM cab";
            PreparedStatement maxIdStmt = MYSQL2.getConnection().prepareStatement(maxIdQuery);
            ResultSet rsMax = maxIdStmt.executeQuery();
            int newId = 1;  // Default starting ID
            if (rsMax.next()) {
                newId = rsMax.getInt(1) + 1;  // Increment the maximum ID
            }

            // Step 2: Prepare the insert statement with the new id
            String insertQuery = "INSERT INTO cab (`id`, `name`, `number`, `cab_type`, `fuel_type`, `seat_capacity`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = MYSQL2.getConnection().prepareStatement(insertQuery);
            insertStatement.setInt(1, newId);  // Set the manually generated id
            insertStatement.setString(2, car);
            insertStatement.setString(3, no);
            insertStatement.setString(4, type);
            insertStatement.setString(5, fuel);
            insertStatement.setString(6, seat);

            int rowsInserted = insertStatement.executeUpdate();  // Execute the insert

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Cab successfully added! ID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);
                resetFields();  // Call method to reset fields
                parent.cab("id", "ASC", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while processing your request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
// Method to reset input fields

    private void resetFields() {
        jTextField1.setText(""); // Clear car name field
        jTextField2.setText(""); // Clear car number field
        jTextField3.setText(""); // Clear car type field
        jTextField4.setText(""); // Clear fuel type field
        jTextField5.setText(""); // Clear seat capacity field
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetFields();

        // Show confirmation dialog before closing
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to close?",
                "Confirm Close",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose(); // Close the window
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    // Helper method to validate seat capacity as a positive integer

    private boolean isValidSeat(String seat) {
        try {
            int seatCount = Integer.parseInt(seat);
            return seatCount > 0; // Ensure it's a positive number
        } catch (NumberFormatException e) {
            return false; // Not a valid integer
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
