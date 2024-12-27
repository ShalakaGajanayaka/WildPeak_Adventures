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
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import raven.toast.Notifications;

/**
 *
 * @author shalaka
 */
public class AddItems extends javax.swing.JDialog {

    private ItemList parent;
    private static HashMap<String, String> UnitNameMap = new HashMap<>();
    private static HashMap<String, String> CategoryNameMap = new HashMap<>();
    private static HashMap<String, String> LoundryItemMap = new HashMap<>();
    private static HashMap<String, String> StatusMap = new HashMap<>();
    private static HashMap<String, String> itemUsedMap = new HashMap<>();

    /**
     * Creates new form AddRoomImages
     */
    public AddItems(ItemList parent, boolean modal) {
//        super(parent, modal);
        this.parent = parent;
        initComponents();
        unitName();
        CategoryName();
        LoundryItem();
        itemUsed();
        Status();
        setLocationRelativeTo(null);
    }

    private void unitName() {
        try {
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `item_unit`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Unit");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                UnitNameMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void CategoryName() {
        try {
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `item_category`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Category");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                CategoryNameMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void LoundryItem() {
        try {
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `laundry_item`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Laundry Item");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoundryItemMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void Status() {
        try {
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Status");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                StatusMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void itemUsed() {
        try {
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `item_used`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Item Used");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                itemUsedMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Add Items");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Unit Name");

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

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setText("Category Name");

        jTextField3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setText("Item Name");

        jTextField4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Total Item");

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setText("Item used");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setText("Loundry Item");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel8.setText("Status Type");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get inputs
        String unitName = String.valueOf(jComboBox1.getSelectedItem());
        String category = String.valueOf(jComboBox2.getSelectedItem());
        String itemName = jTextField3.getText().trim();
        String total = jTextField4.getText().trim();
        String itemUsed = String.valueOf(jComboBox5.getSelectedItem());
        String laundry = String.valueOf(jComboBox3.getSelectedItem());
        String status = String.valueOf(jComboBox4.getSelectedItem());

        // Validation
        if (unitName.equals("Select Unit") || unitName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a valid unit.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (category.equals("Select Category") || category.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a valid category.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Item name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isNumeric(total)) {
            JOptionPane.showMessageDialog(null, "Total must be a valid number (integer or decimal).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (itemUsed.equals("Select Item Used") || itemUsed.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a valid item used option.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (laundry.equals("Select Laundry Item") || laundry.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a valid laundry item.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (status.equals("Select Status") || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a valid status.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Check if the item already exists in the database
            String checkQuery = "SELECT * FROM `item` WHERE `name` = ? AND `item_category_id` = ? AND `item_used_id` = ? "
                    + "AND `item_unit_id` = ? AND `laundry_item_id` = ?";
            PreparedStatement checkStatement = MYSQL2.getConnection().prepareStatement(checkQuery);
            checkStatement.setString(1, itemName);
            checkStatement.setString(2, CategoryNameMap.get(category)); // Replace with your category mapping logic
            checkStatement.setString(3, itemUsedMap.get(itemUsed)); // Replace with your item used mapping logic
            checkStatement.setString(4, UnitNameMap.get(unitName)); // Replace with your unit mapping logic
            checkStatement.setString(5, LoundryItemMap.get(laundry)); // Replace with your laundry mapping logic

            ResultSet rs = checkStatement.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "This Item is already added.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert the item into the database
                String query = "INSERT INTO item (item_unit_id, item_category_id, name, total_items, item_used_id, laundry_item_id, status_id) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = MYSQL2.getConnection().prepareStatement(query);

                preparedStatement.setString(1, UnitNameMap.get(unitName)); // Replace with unit mapping logic
                preparedStatement.setString(2, CategoryNameMap.get(category)); // Replace with category mapping logic
                preparedStatement.setString(3, itemName);
                preparedStatement.setFloat(4, Float.parseFloat(total));
                preparedStatement.setString(5, itemUsedMap.get(itemUsed)); // Replace with item used mapping logic
                preparedStatement.setString(6, LoundryItemMap.get(laundry)); // Replace with laundry mapping logic
                preparedStatement.setString(7, StatusMap.get(status)); // Replace with status mapping logic

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Item successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    resetFields(); // Reset fields after successful insertion
                    parent.item("item.name", "ASC", "");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
// Helper method to validate numeric input

    private boolean isNumeric(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
// Reset fields method

    private void resetFields() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField3.setText("");
        jTextField4.setText("");
//        jTextField5.setText("");
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
