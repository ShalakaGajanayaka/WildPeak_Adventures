/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package hotel.gui.dashboard.subpanels.hotelssub;

import hotel.model.MYSQL2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author shalaka
 */
public class AddRoomImages extends javax.swing.JDialog {

//    private static HashMap<String, String> loadRoomNoMap = new HashMap<>();
    private RoomImages parent;

    /**
     * Creates new form AddRoomImages
     */
    public AddRoomImages(RoomImages parent, boolean modal) {
//        super(parent, modal);
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        loadRoomNo();
    }

    private void loadRoomNo() {
        try {
            // Fetch data from the Room_List table
            ResultSet resultSet = MYSQL2.executeSearch("SELECT `No` FROM `Room_List`");

            // Create a vector to hold combo box items
            Vector<String> roomNumbers = new Vector<>();
            roomNumbers.add("Select"); // Default option

            // Add room numbers to the vector
            while (resultSet.next()) {
                roomNumbers.add(resultSet.getString("No"));
            }

            // Update JComboBox model
            if (jComboBox1 != null) {
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(roomNumbers);
                jComboBox1.setModel(model);
            } else {
                System.err.println("jComboBox1 is not initialized.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while loading room numbers: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Unexpected exception occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Add Room Image");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Room ID");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIP-Guest", "VIP", "Triple Room", "Twin Room", "Single Room" }));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setText("Room Images");

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

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Choose");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))))
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
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jTextField1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Open a JFileChooser with a dark theme
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this); // 'this' refers to the parent JFrame or component

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file's path
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Set the file path in jTextField1
            jTextField1.setText(selectedFilePath);

            // Notify the user
            JOptionPane.showMessageDialog(this, "Selected File: " + selectedFilePath, "File Selected", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Notify the user about cancellation
            JOptionPane.showMessageDialog(this, "No file selected.", "File Selection Cancelled", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String combo = String.valueOf(jComboBox1.getSelectedItem());
        String text = jTextField1.getText();

        // Validate combo box selection
        if (combo.equals("Select") || combo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a valid room number.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the file extension
        String[] validExtensions = {".png", ".jpg", ".jpeg", ".webp"};
        boolean isValidFile = false;
        for (String ext : validExtensions) {
            if (text.toLowerCase().endsWith(ext)) {
                isValidFile = true;
                break;
            }
        }

        if (!isValidFile) {
            JOptionPane.showMessageDialog(this, "Invalid file type. Please select a .png, .jpg, .jpeg, or .webp file.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Check if the image already exists in the database
            ResultSet resultSet = MYSQL2.executeSearch(
                    "SELECT * FROM `room_images` WHERE `image` = '" + text + "' AND `Room_List_No` = '" + combo + "'"
            );

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "This Image Already Added", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Calculate the next ID for room_images
                String idQuery = "SELECT COALESCE(MAX(id), 0) + 1 AS next_id FROM `room_images`";
                Statement idStmt = MYSQL2.getConnection().createStatement();
                ResultSet idResult = idStmt.executeQuery(idQuery);

                int nextId = 1; // Default ID
                if (idResult.next()) {
                    nextId = idResult.getInt("next_id");
                }

                // Insert the image into the database with the calculated ID
                String insertQuery = "INSERT INTO `room_images` (`id`, `image`, `Room_List_No`) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = MYSQL2.getConnection().prepareStatement(insertQuery);
                preparedStatement.setInt(1, nextId);
                preparedStatement.setString(2, text);
                preparedStatement.setString(3, combo);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Image Added Successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                jTextField1.setText("");
                jComboBox1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Clear the text field
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);

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
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
