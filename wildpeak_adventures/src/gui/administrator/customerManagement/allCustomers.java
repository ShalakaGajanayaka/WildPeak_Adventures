/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class allCustomers extends javax.swing.JPanel {

    /**
     * Creates new form allCustomers
     */
    public allCustomers() {
        initComponents();
        loadCustomer();
        getCustomerCount();
        loadType();
        loadGender();
        loadAgeRange();
        loadEvent();
//        jDateChooser1.getDateEditor().getUiComponent().setEnabled(false);

    }

    public void loadCustomer() {

        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `customer` "
                    + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id`");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
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
        try {
            // Query to get total customer count
            String totalQuery = "SELECT COUNT(*) AS total FROM customer";
            ResultSet totalRs = MYSQL.executeSearch(totalQuery);
            if (totalRs.next()) {
                int totalCustomers = totalRs.getInt("total");
                jLabel7.setText("All : " + String.valueOf(totalCustomers)); // Update jLabel1 with total count
            }

            // Query to get count of 'local' customers
            String localQuery = "SELECT COUNT(*) AS localCount FROM customer WHERE customer_type_id = '2'";
            ResultSet localRs = MYSQL.executeSearch(localQuery);
            if (localRs.next()) {
                int localCustomers = localRs.getInt("localCount");
                jLabel6.setText("Local : " + String.valueOf(localCustomers)); // Update jLabel2 with local count
            }

            // Query to get count of 'foreign' customers
            String foreignQuery = "SELECT COUNT(*) AS foreignCount FROM customer WHERE customer_type_id = '1'";
            ResultSet foreignRs = MYSQL.executeSearch(foreignQuery);
            if (foreignRs.next()) {
                int foreignCustomers = foreignRs.getInt("foreignCount");
                jLabel4.setText("Forign : " + String.valueOf(foreignCustomers)); // Update jLabel3 with foreign count
            }

            // Close result sets
            totalRs.close();
            localRs.close();
            foreignRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadType() {
        try {
            // Query to fetch all unique customer types
            String query = "SELECT DISTINCT `name` FROM `customer_type`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("name"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(v));

            // Close the result set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGender() {
        try {
            // Query to fetch all unique customer types
            String query = "SELECT `name` FROM `gender`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("name"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(v));

            // Close the result set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAgeRange() {
        try {
            // Query to fetch age ranges from the database
            String query = "SELECT `range` FROM `age_range`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("range"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_age_range.setModel(new javax.swing.DefaultComboBoxModel<>(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvent() {
        try {
            // Query to fetch all columns from the event table
            String query = "SELECT * FROM `event`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold event names
            Vector<String> v = new Vector<>();

            // Add a default "Select" item to the Vector
            v.add("Select");

            // Fetch each row and add the 'name' column to the Vector
            while (rs.next()) {
                String eventName = rs.getString("name"); // Assuming 'name' is the column storing event names
                v.add(eventName);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(v));
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        cmb_type = new javax.swing.JComboBox<>();
        cmb_gender = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_age_range = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmb_event = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        btn_filter = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Email", "Mobile", "Age", "Joined Date", "Gender", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("All : 5");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Forign : Count");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Local :  Count");

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel6});

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmb_type.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmb_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_typeItemStateChanged(evt);
            }
        });

        cmb_gender.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        jDateChooser1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Type");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Gender");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Date");

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Filter");

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Age");

        cmb_age_range.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_age_range.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "20 -30", "30 - 40", "40 - 60" }));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Event  :");

        cmb_event.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "20 -30", "30 - 40", "40 - 60" }));

        jButton3.setText("C");

        btn_filter.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        btn_filter.setText("Filter");
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(":");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(":");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(":");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(":");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_gender, 0, 137, Short.MAX_VALUE)
                            .addComponent(cmb_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_age_range, 0, 112, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_event, 0, 241, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_age_range, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser1, jLabel10});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_filter, cmb_age_range, jButton3});

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Search");

        jButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton1.setText("Add New Customer");

        jButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton2.setText("Get Report");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String searchtext = jTextField1.getText();
        loadCustomer("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void cmb_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_typeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_typeItemStateChanged

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        try {
            String selectedType = cmb_type.getSelectedItem().toString();
            String selectedGender = cmb_gender.getSelectedItem().toString();
            String selectedAgeRange = cmb_age_range.getSelectedItem().toString();
//            String selectedEvent = cmb_event.getSelectedItem().toString();


            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM customer "
                    + "INNER JOIN  `customer_type`  ON  `customer`.`customer_type_id` = `customer_type`.`id`"
                    + "INNER JOIN  `gender`  ON  `customer`.`gender_id` = `gender`.`id`"
                    + " WHERE 1=1");

            if (!selectedType.equals("Select")) {
                queryBuilder.append(" AND `customer_type`.`name` = '").append(selectedType).append("'");
            }
            if (!selectedGender.equals("Select")) {
                queryBuilder.append(" AND `gender`.`name` = '").append(selectedGender).append("'");
            }
            if (!selectedAgeRange.equals("Select")) {
                // Split the selected age range into lower and upper bounds
                String[] ageBounds = selectedAgeRange.split(" - ");
                int lowerBound = Integer.parseInt(ageBounds[0]);
                int upperBound = Integer.parseInt(ageBounds[1]);

                queryBuilder.append(" AND `customer`.`age` BETWEEN ").append(lowerBound).append(" AND ").append(upperBound);
            }
//            if (!selectedAgeRange.equals("Select")) {
//                queryBuilder.append(" AND `age_range`.`range` = '").append(selectedAgeRange).append("'");
//            }
//            if (!selectedEvent.equals("Select")) {
//                queryBuilder.append(" AND `event`.`name` = '").append(selectedEvent).append("'");
//            }

            System.out.println(queryBuilder);

            ResultSet resultSet = MYSQL.executeSearch(queryBuilder.toString());

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);
//
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
    }//GEN-LAST:event_btn_filterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_filter;
    private javax.swing.JComboBox<String> cmb_age_range;
    private javax.swing.JComboBox<String> cmb_event;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JComboBox<String> cmb_type;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
