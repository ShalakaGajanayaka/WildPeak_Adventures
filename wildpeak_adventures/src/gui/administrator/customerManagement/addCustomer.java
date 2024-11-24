/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class addCustomer extends javax.swing.JPanel {

    HashMap<String, String> CustomerGenderMap = new HashMap<>();
    HashMap<String, String> CustomerTypeMap = new HashMap<>();

    /**
     * Creates new form addCustomer
     */
    public addCustomer() {
        initComponents();
        loadGender();
        loadType();
        loadCustomer();
        getLocalCustomerCount();
        getForignCustomerCount();       
//        loadCustomer("id","ASC", jTextField2.getText(), jTextField2.getText(), jTextField2.getText(), jTextField2.getText());
//        jTable2.revalidate();
//        jTable2.repaint();
//        reset();
//        getCustomerCount();
    }

    private void loadGender() {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {

                vector.add(resultSet.getString("name"));
                CustomerGenderMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(comboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadType() {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {

                vector.add(resultSet.getString("name"));
                CustomerTypeMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(comboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomer() {

        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer` "
                    + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id`");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("customer.fname"));
                vector.add(resultSet.getString("customer.lname"));
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
        public void getLocalCustomerCount() {
        try {
            String query = "SELECT COUNT(*) AS customer_count FROM  customer WHERE customer_type_id = 2";

            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int localCustomerCount = resultSet.getInt("customer_count");
                jLabel8.setText("Total Local Customers: " + localCustomerCount);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void getForignCustomerCount() {
        try {
            String query = "SELECT COUNT(*) AS customer_count FROM  customer WHERE customer_type_id = 1";

            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int localCustomerCount = resultSet.getInt("customer_count");
                jLabel5.setText("Total Forign Customers: " + localCustomerCount);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        


    private void loadCustomer(String column, String orderby, String fname, String lname, String email, String mobile) {
        try {
//            String query = "SELECT * FROM customer "
//                    + "INNER JOIN gender ON customer.gender_id = gender.id "
//                    + "INNER JOIN customer_type ON customer.customer_type_id = customer_type.id "
//                    + "WHERE customer.fname LIKE ? "
//                    + "OR customer.lname LIKE ? "
//                    + "OR customer.email LIKE ? "
//                    + "OR customer.mobile LIKE ? "
//                    + "OR customer_type.name LIKE ? "
//                    + "ORDER BY customer." + column + " " + orderby;
//
//            PreparedStatement statement = MYSQL.getConnection().prepareStatement(query);
//            String searchPattern = "%" + fname + "%";
//
//            statement.setString(1, searchPattern);
//            statement.setString(2, searchPattern);
//            statement.setString(3, searchPattern);
//            statement.setString(4, searchPattern);
//            statement.setString(5, searchPattern);
//
//            ResultSet resultSet = statement.executeQuery();
//            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
//            defaultTableModel.setRowCount(0);
//
//            while (resultSet.next()) {
//                Vector<String> vector = new Vector<>();
//                vector.add(resultSet.getString("customer.fname"));
//                vector.add(resultSet.getString("customer.lname"));
//                vector.add(resultSet.getString("customer.email"));
//                vector.add(resultSet.getString("customer.mobile"));
//                vector.add(resultSet.getString("customer.age"));
//                vector.add(resultSet.getString("customer.register_date"));
//                vector.add(resultSet.getString("gender.name"));
//                vector.add(resultSet.getString("customer_type.name"));
//                defaultTableModel.addRow(vector);
//
//
//            }

            String searchText = jTextField2.getText().trim(); // search textfield එකේ text එක ගන්නවා
            String query = "SELECT * FROM `customer` "
                    + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id` "
                    + "WHERE `customer`.`fname` LIKE ? OR `customer`.`lname` LIKE ? OR `customer`.`email` LIKE ?";

            PreparedStatement preparedStatement = MYSQL.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%"); // fname
            preparedStatement.setString(2, "%" + searchText + "%"); // lname
            preparedStatement.setString(3, "%" + searchText + "%"); // email

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("customer.fname"));
                vector.add(resultSet.getString("customer.lname"));
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

    public void getCustomerCount() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Search");

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Email", "Mobile", "Age", "Registered Date", "Gender", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Local : Count");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Forign : Count ");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("You can add, edit or delete customer");

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("First Name");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Last Name");

        jTextField5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Mobile");

        jTextField7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Email");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Type");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Gender");

        jButton1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Age");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Password");

        jButton5.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addComponent(jLabel23))
                            .addGap(38, 38, 38)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(jPasswordField1)))))
                .addGap(0, 20, Short.MAX_VALUE))
            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jTextField1, jTextField5, jTextField6, jTextField7, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel21});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(16, 16, 16)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox2, jLabel1, jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel24, jTextField1, jTextField5, jTextField6, jTextField7, jTextField8});

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String FirstName = jTextField5.getText();
            String LastName = jTextField6.getText();
            String Email = jTextField8.getText();
            String Mobile = jTextField7.getText();
            String Age = jTextField1.getText();
            String Password = String.valueOf(jPasswordField1.getPassword());
            String Gender = String.valueOf(jComboBox1.getSelectedItem());
            String Type = String.valueOf(jComboBox2.getSelectedItem());

            if (FirstName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (LastName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!Email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                    + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                JOptionPane.showMessageDialog(this, "Invalid email", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your mobile", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!Mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid mobile", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your password", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!Password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                JOptionPane.showMessageDialog(this, "Please enter minimum eight characters, at least one letter and one number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Gender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Type.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `employee` WHERE `Email` = '" + Email + "' OR  `mobile` = '" + Mobile + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Customer is already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MYSQL.executeIUD("INSERT INTO customer(fname, lname, email, mobile, age, register_date, password, gender_id, customer_type_id)"
                            + "VALUES('" + FirstName + "','" + LastName + "','" + Email + "','" + Mobile + "','" + Age + "',"
                            + "'" + sdf.format(date) + "','" + Password + "','" + CustomerGenderMap.get(Gender) + "','" + CustomerTypeMap.get(Type) + "')");

                    loadCustomer();
                    reset();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fname = jTextField5.getText();
        String lname = jTextField6.getText();
        String email = jTextField8.getText();
        String mobile = jTextField7.getText();
        String age = jTextField1.getText();
        String gender = String.valueOf(jComboBox1.getSelectedItem());
        String type = String.valueOf(jComboBox2.getSelectedItem());

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's First Name", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's Last Name", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's Email", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
         } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's Mobile", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (age.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's age", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer's Valid Mobile", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Customer's gender", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else if (type.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Customer's Type", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {

                boolean update = false;

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer` WHERE `email` = '" + email + "' AND `mobile` = '" + mobile + "'");

                if (resultSet.next()) {
                    if (resultSet.getString("mobile").equals(mobile)) {
                        update = true;
                    } else {

                        JOptionPane.showMessageDialog(this, "mobile Number or Email already added", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } else {

                    update = true;
                }

                if (update) {
                    MYSQL.executeIUD("UPDATE `customer` SET `fname` = '" + fname + "',`lname` = '" + lname + "',`email` = '" + email + "',`mobile` = '" + mobile + "',`age` = '" + age + "',"
                            + "`gender_id` = '" + CustomerGenderMap.get(gender) + "',`customer_type_id` = '" + CustomerTypeMap.get(type) + "' WHERE `mobile` = '" + mobile + "'");

                    reset();
                    
                }
                
                loadCustomer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String mobile = jTextField7.getText();  
    
    if (mobile.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Customer's Mobile to Delete", "Information", JOptionPane.INFORMATION_MESSAGE);
    } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
        JOptionPane.showMessageDialog(this, "Please Enter a Valid Mobile Number", "Information", JOptionPane.INFORMATION_MESSAGE);
    } else {
        try {
            
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer` WHERE `mobile` = '" + mobile + "'");               
            MYSQL.executeIUD("DELETE FROM `customer` WHERE `mobile` = '" + mobile + "'");
            JOptionPane.showMessageDialog(this, "Customer Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            reset();  
            loadCustomer(); 
                
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String searchtext = jTextField2.getText();
        loadCustomer("id", "ASC", searchtext, searchtext, searchtext, searchtext);
    }//GEN-LAST:event_jTextField2KeyReleased

    private int clickCount = 0;
    
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
    try {
        clickCount++;
       
        if (clickCount == 1 ) {
            jButton1.setEnabled(false);  
            jButton2.setEnabled(false); 
            jButton3.setEnabled(true);  
        } else if (clickCount == 3) {
            jButton1.setEnabled(false);  
            jButton2.setEnabled(true);  
            jButton3.setEnabled(false); 
            clickCount = 0; 
        }

        int row = jTable2.getSelectedRow();
        String email = String.valueOf(jTable2.getValueAt(row, 2)); 

    
        String query = "SELECT * FROM `customer` "
                + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id` "
                + "WHERE customer.email = ?";

        PreparedStatement pst = MYSQL.getConnection().prepareStatement(query);
        pst.setString(1, email);  
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            
            jTextField5.setText(rs.getString("fname"));
            jTextField6.setText(rs.getString("lname"));
            jTextField8.setText(rs.getString("email"));
            jTextField8.setEditable(false);
            jTextField7.setText(rs.getString("mobile"));
            jTextField7.setEditable(false);
            jTextField1.setText(rs.getString("age"));
            jPasswordField1.setText(rs.getString("password"));
            jComboBox1.setSelectedItem(rs.getString("gender.name"));
            jComboBox2.setSelectedItem(rs.getString("customer_type.name"));
        }

        rs.close();
        pst.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     reset();
 
    jButton1.setEnabled(true); 
    jTextField8.setEditable(true);
    jTextField7.setEditable(true);
    jButton2.setEnabled(false);
    jButton3.setEnabled(false); 

    
    }//GEN-LAST:event_jButton5ActionPerformed

    public void reset() {
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField8.setText("");
        jTextField7.setText("");
        jTextField1.setText("");
        jPasswordField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
