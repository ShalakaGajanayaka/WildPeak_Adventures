/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

import gui.administrator.customerManagement.allCustomer.customersCount;
import gui.administrator.customerManagement.allCustomer.filter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class allCustomers extends javax.swing.JPanel {

    private Color color1 = new Color(46, 125, 50);    // Forest green
    private Color color2 = new Color(129, 199, 132);  // Light green
    private Color midpointColor;                     // Midpoint color
    private int cornerRadius = 20;

    /**
     * Creates new form allCustomers
     */
    public allCustomers() {
        initComponents();
//        setOpaque(false);
//        setBackground(new Color(255, 255, 255, 0));
        panelColor();
        
        filterPanel.add(new filter(this));
        customersCountPanel.add(new customersCount(this));

        loadCustomer();
      
//       

    }

    public void panelColor() {
        // Calculate the midpoint color
        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;
        midpointColor = new Color(red, green, blue);

        // Set the background to the midpoint color
        setBackground(midpointColor);
        filterPanel.setBackground(midpointColor);
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

  

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        customersCountPanel = new javax.swing.JPanel();
        filterPanel = new javax.swing.JPanel();
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

        customersCountPanel.setLayout(new java.awt.CardLayout());

        filterPanel.setLayout(new java.awt.CardLayout());

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
        jButton2.setText("Get Summery");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField1))
                                .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(customersCountPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(customersCountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String searchtext = jTextField1.getText();
        loadCustomer("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel customersCountPanel;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
