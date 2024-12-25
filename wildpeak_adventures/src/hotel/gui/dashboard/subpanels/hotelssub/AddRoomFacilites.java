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
import raven.toast.Notifications;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author shalaka
 */
public class AddRoomFacilites extends javax.swing.JDialog {

    private RoomFacilitesList parent;

    /**
     * Creates new form AddRoomImages
     */
    public AddRoomFacilites(RoomFacilitesList parent, boolean modal) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Add Room Facilites");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Facility Name");

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
                        .addComponent(jButton1)))
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
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String text = jTextField1.getText().trim(); // Trim to remove leading/trailing spaces

        if (text.isEmpty()) {
//            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please enter a facility name.");
            JOptionPane.showMessageDialog(this, "Please enter a facility name","INFORMATION",JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        try {
            // Check if the facility name already exists
            String searchQuery = "SELECT * FROM `Room_Facility` WHERE `Name` = ?";
            PreparedStatement searchStmt = MYSQL2.getConnection().prepareStatement(searchQuery);
            searchStmt.setString(1, text);

            ResultSet resultSet = searchStmt.executeQuery();

            if (resultSet.next()) {
//                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Facility is already added.");
                JOptionPane.showMessageDialog(this, "Facility is already added.","INFORMATION",JOptionPane.INFORMATION_MESSAGE
              
                );
            } else {
                // Find the next id manually
                String idQuery = "SELECT COALESCE(MAX(id), 0) + 1 AS next_id FROM Room_Facility";
                Statement idStmt = MYSQL2.getConnection().createStatement();
                ResultSet idResult = idStmt.executeQuery(idQuery);

                int nextId = 1; // Default ID
                if (idResult.next()) {
                    nextId = idResult.getInt("next_id");
                }

                // Insert the new facility
                String insertQuery = "INSERT INTO `Room_Facility` (`id`, `Name`) VALUES (?, ?)";
                PreparedStatement insertStmt = MYSQL2.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, nextId);
                insertStmt.setString(2, text);
                insertStmt.executeUpdate();

//                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Facility is added successfully.");
            JOptionPane.showMessageDialog(this, "Facility is added successfully.","INFORMATION",JOptionPane.INFORMATION_MESSAGE
            );
                jTextField1.setText("");

                // Close ID result resources
                idResult.close();
                idStmt.close();
            }

            // Close search resources
            resultSet.close();
            searchStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "An error occurred while adding the facility.");
        } catch (Exception ex) {
            Logger.getLogger(AddRoomFacilites.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Clear the text field
        jTextField1.setText("");

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
