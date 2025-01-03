/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.administrator.employeeManagement;

import java.awt.Frame;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.PreparedStatement;
import javax.swing.JDialog;

public class AddressView extends javax.swing.JDialog {

    private HashMap<String, String> cityMap = new HashMap<>();
    private HashMap<String, String> DistrictMap = new HashMap<>();

    private String Nic;
    private String UserId;

    public AddressView(java.awt.Frame parent, boolean modal, String UserId, String nic) {
        super(parent, modal);
        initComponents();
        jLabel10.setText(nic);
        this.UserId = UserId;
        this.Nic = nic;
        loadCities();
        loadAddress();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable3.setDefaultRenderer(Object.class, renderer);
        loadDistrict();
    }

    private void loadAddress() {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `address` INNER JOIN `city` "
                    + "ON `address`.`city_id` = `city`.`id` INNER JOIN `district`"
                    + "ON `address`.`district_id` = `district`.`id` INNER JOIN `employee`"
                    + "ON `address`.`employee_id` = `employee`.`id`"
                    + "WHERE `employee_id` ='" + this.UserId + "' ");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.fname") + " " + resultSet.getString("employee.lname"));
                vector.add(resultSet.getString("line_1"));
                vector.add(resultSet.getString("line_2"));
                vector.add(resultSet.getString("line_3"));
                vector.add(resultSet.getString("district.name"));
                vector.add(resultSet.getString("city.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in loading address data.");
//            logger.log(Level.WARNING, "Exception In Address View in loadAddress", e);
        }
    }

    private void loadCities() {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `city`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                cityMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
//            logger.log(Level.WARNING, "Exception In Address View in loadCities", e);
        }
    }

    private void loadDistrict() {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `district`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                DistrictMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
//            logger.log(Level.WARNING, "Exception In Address View in loadCities", e);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Address View");
        setAlwaysOnTop(true);

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(242, 242, 242));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("ADD TO ADDRESS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(242, 242, 242));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("UPDATE TO ADDRESS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(242, 242, 242));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("REMOVE TO ADDRESS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ADDRESS VIEW");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Employee");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Employee Name");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("Line 01");

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("Line 02");

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("City");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setText("Line 03");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setText("District");

        jComboBox2.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTable3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Employee Name", "Line 1", "Line 2", "Line 3", "District", "City"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton4.setBackground(new java.awt.Color(242, 242, 242));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("ADD TO CITY");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(242, 242, 242));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("ADD TO DISTRICT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String line1 = jTextField1.getText();
            String line2 = jTextField2.getText();
            String line3 = jTextField3.getText();
            String city = String.valueOf(jComboBox1.getSelectedItem());
            String district = String.valueOf(jComboBox2.getSelectedItem());

            if (line1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter address line 1", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (line2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter address line 2", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (city.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a city", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (district.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a District", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `address` WHERE `employee_id` = '" + UserId + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Address already assigned to this employee", "Warning", JOptionPane.WARNING_MESSAGE);
                    return; // Exit the method if an address already exists
                }

                boolean isFound = false;

                for (int i = 0; i < jTable3.getRowCount(); i++) {

//                    String name = String.valueOf(jTable3.getValueAt(i, 1));
                    String getLine1 = String.valueOf(jTable3.getValueAt(i, 2));
                    String getLine2 = String.valueOf(jTable3.getValueAt(i, 3));
                    String getLine3 = String.valueOf(jTable3.getValueAt(i, 4));
                    String getCity = String.valueOf(jTable3.getValueAt(i, 5));
                    String getDistrict = String.valueOf(jTable3.getValueAt(i, 6));
//                    String getid = String.valueOf(jTable3.getValueAt(i, 0));

                    if (getLine1.equals(line1) && getLine2.equals(line2) && getLine3.equals(line3) && getCity.equals(city) && getDistrict.equals(district)) {
                        JOptionPane.showMessageDialog(this, "Address already added", "Warning", JOptionPane.WARNING_MESSAGE);
                        isFound = true;

                        break;
                    }
                }

                if (!isFound) {
                    MYSQL.executeIUD("INSERT INTO `address`(`line_1`,`line_2`,`city_id`,`district_id`,`line_3`,`employee_id`)"
                            + "VALUES('" + line1 + "','" + line2 + "','" + cityMap.get(city) + "','" + DistrictMap.get(district) + "','" + line3 + "','" + this.UserId + "')");

                    loadAddress();
                    reset();
                }

//                    MySQL2.executeSearch("SELECT * FROM `employee_address` WHERE `employee_id` = '" + UserId + "'");
            }

        } catch (Exception e) {
            e.printStackTrace();
//            logger.log(Level.WARNING, "Exception In Address View in Add Button", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        int row = jTable3.getSelectedRow();

        jTextField1.setText(String.valueOf(jTable3.getValueAt(row, 2)));
        jTextField2.setText(String.valueOf(jTable3.getValueAt(row, 3)));
        jTextField3.setText(String.valueOf(jTable3.getValueAt(row, 4)));
        jComboBox2.setSelectedItem(String.valueOf(jTable3.getValueAt(row, 5)));
        jComboBox1.setSelectedItem(String.valueOf(jTable3.getValueAt(row, 6)));


    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = jTable3.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a address to update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String id = String.valueOf(jTable3.getValueAt(row, 0));

            try {
                String line1 = jTextField1.getText();
                String line2 = jTextField2.getText();
                String line3 = jTextField3.getText();
                String city = String.valueOf(jComboBox1.getSelectedItem());
                String district = String.valueOf(jComboBox2.getSelectedItem());

                if (line1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter address line 1", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (line2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter address line 2", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (city.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (district.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please select a District", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    boolean isFound = false;

                    for (int i = 0; i < jTable3.getRowCount(); i++) {

//                    String name = String.valueOf(jTable3.getValueAt(i, 1));
                        String getLine1 = String.valueOf(jTable3.getValueAt(i, 2));
                        String getLine2 = String.valueOf(jTable3.getValueAt(i, 3));
                        String getLine3 = String.valueOf(jTable3.getValueAt(i, 4));
                        String getCity = String.valueOf(jTable3.getValueAt(i, 5));
                        String getDistrict = String.valueOf(jTable3.getValueAt(i, 6));
//                    String getid = String.valueOf(jTable3.getValueAt(i, 0));

                        if (getLine1.equals(line1) && getLine2.equals(line2) && getLine3.equals(line3) && getCity.equals(city) && getDistrict.equals(district)) {
                            JOptionPane.showMessageDialog(this, "Address already added", "Warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;

                            break;
                        }
                    }

                    if (!isFound) {

                        MYSQL.executeIUD("UPDATE `address` SET `line_1` = '" + line1 + "', `line_2` = '" + line2 + "', `line_3` = '" + line3 + "',"
                                + "`city_id` = '" + cityMap.get(city) + "', `district_id` = '" + DistrictMap.get(district) + "' WHERE `id` = '" + id + "'");

                        loadAddress();
                        reset();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
//                logger.log(Level.WARNING, "Exception In Address View in Update Button", e);
            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String line1 = jTextField1.getText();
        String line2 = jTextField2.getText();
        String city = String.valueOf(jComboBox1.getSelectedItem());
        String district = String.valueOf(jComboBox2.getSelectedItem());

        if (line1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address line 1", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (line2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address line 2", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (district.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a District", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                String query = "SELECT COUNT(*) AS count FROM `address` "
                        + "INNER JOIN `employee` ON `address`.`employee_id` = `employee`.`id` "
                        + "WHERE `employee`.`id` = ?";
                try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                    statement.setString(1, UserId);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next() && resultSet.getInt("count") > 0) {
                        JOptionPane.showMessageDialog(this, "Cannot delete Address because it is associated with Employee.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        MYSQL.executeIUD("DELETE FROM `address` WHERE `employee_id` = '" + UserId + "'");
                        loadAddress();
                        reset();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
//                logger.log(Level.WARNING, "Exception In Address View in delete Button", ex);
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        city cityDialog = new city(AddressView.this, true);
        cityDialog.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        district district = new district(AddressView.this, true);
        district.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTable3.clearSelection();
    }
}
