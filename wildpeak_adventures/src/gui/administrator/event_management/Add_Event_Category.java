package gui.administrator.event_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

public class Add_Event_Category extends javax.swing.JPanel {


    public Add_Event_Category() {
        initComponents();
        loadJobRole("id", "ASC", jTextField1.getText());
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
    }

    private void loadJobRole(String column, String orderby, String Text) {

        try {

            String query = "SELECT * FROM event_category "
                    + "WHERE event_category.name LIKE ? "
                    + "ORDER BY event_category." + column + " " + orderby;

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + Text + "%";
                for (int i = 1; i <= 1; i++) {
                    statement.setString(i, searchPattern);
                }

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("event_category.id"));
                    vector.add(resultSet.getString("event_category.name"));

                    defaultTableModel.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Category Name");

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Description");

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("You can add Or edit Event Category");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 16, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Search");

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "#", "Category Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            jButton1.setEnabled(false);
            int row = jTable1.getSelectedRow();
            String id = String.valueOf(jTable1.getValueAt(row, 0));  // assuming column 0 is the user ID

            // Query database using UserId to retrieve additional details
            String query = "SELECT * FROM event_category "
                    + "WHERE event_category.id = ?";

            // Assuming you have a method for database connection (connection)
            PreparedStatement pst = MYSQL.getConnection().prepareStatement(query);
            pst.setString(1, id);  // Sets the 'email' as a parameter in the WHERE clause
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                jTextField2.setText(rs.getString("name"));
                jTextArea1.setText(rs.getString("description"));

            }
            rs.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTable1.clearSelection();
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        loadJobRole("id", "ASC", jTextField1.getText());
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String text = jTextField1.getText();
        loadJobRole("id", "ASC", text);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = jTextField2.getText();
        String description = jTextArea1.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Category Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Description(Qualification)", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event_category` WHERE `name` = '" + name + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Category Is Alredy Added! Please New Category Added", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String insertQuery = "INSERT INTO `event_category` (`name`,`description`)"
                            + "VALUES ('" + name + "', '" + description + "')";

                    MYSQL.executeIUD(insertQuery);

                    JOptionPane.showMessageDialog(this, "New Category Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadJobRole("id", "ASC", jTextField2.getText());
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Uncomment for logging if needed
                // logger.log(Level.WARNING, "Exception in Employee Management in create button", e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = jTable1.getSelectedRow();
        String id = String.valueOf(jTable1.getValueAt(row, 0));
        String name = jTextField2.getText();
        String description = jTextArea1.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Category Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Description(Qualification)", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event_category` WHERE `name` = '" + name + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Category Is Alredy updated", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String updateQuery = "UPDATE `event_category` SET name = '" + name + "',description = '" + description + "' WHERE `id` = '" + id + "'";

                    MYSQL.executeIUD(updateQuery);

                    JOptionPane.showMessageDialog(this, "Category Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadJobRole("id", "ASC", jTextField2.getText());
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Uncomment for logging if needed
                // logger.log(Level.WARNING, "Exception in Employee Management in create button", e);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTable1.clearSelection();
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        loadJobRole("id", "ASC", jTextField1.getText());
        jButton1.setEnabled(true);
    }
}
