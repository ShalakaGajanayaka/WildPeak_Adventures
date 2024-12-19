/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class AllCustomerPanel extends javax.swing.JPanel {

  

    /**
     * Creates new form AllCustomerPanel
     */
    public AllCustomerPanel() {
        
        initComponents();

        filter.add(new Filter(this));
        customercount.add(new CustomersCount(this));

        loadCustomer();
    }

    public void loadCustomer() {

        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer` "
                    + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id`");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {
                String fname = resultSet.getString("customer.fname");
                String lname = resultSet.getString("customer.lname");
                Vector<String> vector = new Vector<>();
                vector.add(fname + " " + lname);
//                vector.add(resultSet.getString("customer.lname"));
                vector.add(resultSet.getString("customer.email"));
                vector.add(resultSet.getString("customer.mobile"));
                vector.add(resultSet.getString("customer.age"));
                vector.add(resultSet.getString("customer.register_date"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("customer_type.name"));

                defaultTableModel.addRow(vector);

                // Create a Customer object and add it to the HashMap
                int customerId = resultSet.getInt("id");
//                Customer customer = new Customer(
//                        resultSet.getString("customer.fname"),
//                        resultSet.getString("customer.lname"),
//                        resultSet.getString("customer.email"),
//                        resultSet.getString("customer.mobile"),
//                        resultSet.getString("customer.age"),
//                        resultSet.getString("customer.register_date"),
//                        resultSet.getString("gender.name"),
//                        resultSet.getString("customer_type.name")
//                );
//                LoadCustomersMap.put(customer.toString(), customerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void loadCustomer(String column, String orderby, String searchText) {
        try {
            String query = "SELECT * FROM `customer` "
                    + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id` "
                    + "WHERE `customer`.`fname` LIKE ? OR `customer`.`lname` LIKE ? OR `customer`.`email` LIKE ? OR `customer`.`mobile` LIKE ?";

            PreparedStatement preparedStatement = MYSQL.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%"); // fname
            preparedStatement.setString(2, "%" + searchText + "%"); // lname
            preparedStatement.setString(3, "%" + searchText + "%"); // email
            preparedStatement.setString(4, "%" + searchText + "%"); // mobile

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {
                String fname = resultSet.getString("customer.fname");
                String lname = resultSet.getString("customer.lname");
                Vector<String> vector = new Vector<>();
                vector.add(fname + " " + lname);
//                vector.add(resultSet.getString("customer.lname"));
                vector.add(resultSet.getString("customer.email"));
                vector.add(resultSet.getString("customer.mobile"));
                vector.add(resultSet.getString("customer.age"));
                vector.add(resultSet.getString("customer.register_date"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("customer_type.name"));

                defaultTableModel.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        filter = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        customercount = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Search");

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        filter.setLayout(new java.awt.CardLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Add New Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Get Summary");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Email", "Mobile", "Age", "Joined Date", "Gender", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        customercount.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customercount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customercount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
         String searchtext = jTextField1.getText();
        loadCustomer("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddCustomer addCustomer = new AddCustomer(this, true);
        addCustomer.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel customercount;
    private javax.swing.JPanel filter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
