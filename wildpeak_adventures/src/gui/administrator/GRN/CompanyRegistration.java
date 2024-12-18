/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.administrator.GRN;

import java.sql.PreparedStatement;
import model.MYSQL;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CompanyRegistration extends javax.swing.JFrame {

    private static Supplier1 sr;

    public static void setSupplier(Supplier1 sr) {
        CompanyRegistration.sr = sr;
    }

    public CompanyRegistration() {

        initComponents();
        loadCompanies("mobile", "ASC", jTextField3.getText());
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);

    }

    private void loadCompanies(String column, String orderby, String text) {
        try {
            // Base query with JOINs
            String query = "SELECT company.mobile AS company_mobile, company.name "
                    + "FROM company ";

            // Dynamic filtering for multiple columns
            if (text != null && !text.isEmpty()) {
                query += " WHERE company.name LIKE ? OR company.mobile LIKE ?";
            }

            // Add ordering condition with whitelist validation
            List<String> allowedColumns = Arrays.asList("name", "mobile");
            List<String> allowedOrders = Arrays.asList("ASC", "DESC");
            if (allowedColumns.contains(column) && allowedOrders.contains(orderby)) {
                query += " ORDER BY company." + column + " " + orderby;
            }

            PreparedStatement statement = MYSQL.getConnection().prepareStatement(query);

            // Set parameters for the `LIKE` conditions
            if (text != null && !text.isEmpty()) {
                String searchPattern = "%" + text + "%";
                statement.setString(1, searchPattern); // For `name`
                statement.setString(2, searchPattern); // For `mobile`
            }

            ResultSet resultSet = statement.executeQuery();

            int id = 1;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Reset table

            // Populate the table with data
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                vector.add(id++);
                vector.add(resultSet.getString("company_mobile")); // Mobile
                vector.add(resultSet.getString("name")); // Name
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
//            logger.log(Level.WARNING, "Error while loading companies", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Company Name");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Company Registration");
        setAlwaysOnTop(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Company Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hotline");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField1.setToolTipText("Company Name Here");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Hotline", "Company Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-31-20.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField3.setToolTipText("SEARCH BAR");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton3)
                                .addGap(15, 15, 15)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name = jTextField1.getText();
        String hotline = jTextField2.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter company name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (hotline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter company hotline", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else if (!hotline.matches("^(?:0|94|\\+94)?7[0-9]{8}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else {

            try {

                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `company` WHERE `name`= '" + name + "' OR `mobile` = '" + hotline + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Company name or hotine already used", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    MYSQL.executeIUD("INSERT INTO `company`(`mobile`,`name`) "
                            + "VALUES('" + hotline + "','" + name + "')");
                    loadCompanies("mobile", "ASC", jTextField3.getText());
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
//                logger.log(Level.WARNING, "Exception In Company Registration in add button", e);
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        jTextField1.setText(String.valueOf(jTable1.getValueAt(row, 2)));
        jTextField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));

        jButton1.setEnabled(false);

        if (evt.getClickCount() == 2) {

            if (sr != null) {
                sr.getjTextField5().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                this.dispose();
                sr.mobileGrabFocus();
                sr.setcompanymobile(String.valueOf(jTable1.getValueAt(row, 1)));
            } else {
                JOptionPane.showMessageDialog(this, "sr object is null", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String name = jTextField1.getText();
            String hotline = jTextField2.getText();

            String selectedName = String.valueOf(jTable1.getValueAt(row, 2));
            String selectedHotline = String.valueOf(jTable1.getValueAt(row, 1));

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company name", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (hotline.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company hotline", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (!hotline.matches("^(?:0|94|\\+94)?7[0-9]{8}$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (selectedName.equals(name) && selectedHotline.equals(hotline)) {
                JOptionPane.showMessageDialog(this, "Please change name or hotline number to update", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    // Update supplier table first to ensure foreign key integrity
                    MYSQL.executeIUD("UPDATE `supplier` SET `company_mobile` = '" + hotline + "' WHERE `company_mobile` = '" + selectedHotline + "'");

                    // Now update the company table
                    MYSQL.executeIUD("UPDATE `company` SET `mobile` = '" + hotline + "', `name` = '" + name + "' WHERE `mobile` = '" + selectedHotline + "'");

                    loadCompanies("mobile", "ASC", jTextField3.getText());
                    reset();
                } catch (Exception e) {
                    e.printStackTrace();
                    //logger.log(Level.WARNING, "Exception In Company Registration in update button", e);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String company = jTextField1.getText();
        String hotline = jTextField2.getText();

        if (company.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Company Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (hotline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Hotline Number", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                // Check if the category is linked with any products
                ResultSet resultSet = MYSQL.executeSearch("SELECT COUNT(*) AS count FROM `supplier` WHERE `company_mobile` = (SELECT mobile FROM `company` WHERE `name` = '" + company + "')");

                if (resultSet.next() && resultSet.getInt("count") > 0) {
                    JOptionPane.showMessageDialog(this, "Cannot delete Company because it is associated with Supplier.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Safe to delete
                    MYSQL.executeIUD("DELETE FROM `company` WHERE `name` = '" + company + "'");
                    loadCompanies("mobile", "ASC", jTextField3.getText());
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
//                logger.log(Level.WARNING, "Exception In Company Registration in remove button", e);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        loadCompanies("mobile", "ASC", jTextField3.getText());
    }//GEN-LAST:event_jTextField3KeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField1.grabFocus();
        jButton1.setEnabled(true);
        jTable1.clearSelection();
        loadCompanies("id", "ASC", jTextField3.getText());
    }
}
