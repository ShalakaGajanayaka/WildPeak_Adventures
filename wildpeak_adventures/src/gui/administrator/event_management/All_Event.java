/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.administrator.event_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author USER
 */
public class All_Event extends javax.swing.JPanel {

    /**
     * Creates new form All_Event
     */
    public All_Event() {
        initComponents();
        loadTable("id", "ASC", jTextField1.getText());
        getEventCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
        jTable1.getSelectionModel()
                .addListSelectionListener(event -> {
                    if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                        int selectedRow = jTable1.getSelectedRow();
                        String jobId = (String) jTable1.getValueAt(selectedRow, 0); // Assuming ID is in the first column

                        loadDescription(jobId);
                    }
                }
                );
    }

    public void loadTable(String column, String orderby, String name) {
        try {
            // Construct the query with parameterized placeholders for the search pattern
            String query = "SELECT `event`.`id` AS event_id, `event`.`name` AS event_name, `event_category`.`name` AS category_name, "
                    + "`event_offer`.`name` AS offer_name, `event`.`max_participants`, `event`.`price`, "
                    + "`event_location`.`line_1`, `event_location`.`line_2`, `city`.`name` AS city_name "
                    + "FROM `event` "
                    + "INNER JOIN `event_category` ON `event`.`event_category_id` = `event_category`.`id` "
                    + "INNER JOIN `event_offer` ON `event`.`event_offer_id` = `event_offer`.`id` "
                    + "INNER JOIN `event_location` ON `event`.`event_location_id` = `event_location`.`id` "
                    + "INNER JOIN `city` ON `event_location`.`city_id` = `city`.`id` "
                    + "WHERE `event`.`name` LIKE ? "
                    + "OR `event`.`price` LIKE ? "
                    + "OR `event_category`.`name` LIKE ? "
                    + "OR `event_offer`.`name` LIKE ? "
                    + "OR `event_location`.`line_1` LIKE ? "
                    + "OR `event_location`.`line_2` LIKE ? "
                    + "OR `city`.`name` LIKE ? "
                    + "OR `event`.`max_participants` LIKE ? "
                    + "ORDER BY `event`." + column + " " + orderby;

            // Prepare the search pattern
            String searchPattern = "%" + name + "%";

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                // Set search pattern for all placeholders
                for (int i = 1; i <= 8; i++) {
                    statement.setString(i, searchPattern);
                }
                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Get the table model and reset the row count
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0); // Clear existing rows

                // Process the result set and add data to the table
                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    // Add event details
                    vector.add(resultSet.getString("event_id"));
                    vector.add(resultSet.getString("event_name"));
                    vector.add(resultSet.getString("category_name"));
                    vector.add(resultSet.getString("offer_name"));
                    vector.add(resultSet.getString("max_participants"));
                    vector.add(resultSet.getString("price"));

                    // Combine address components into a single field
                    String value1 = resultSet.getString("line_1");
                    String value2 = resultSet.getString("line_2");
                    String value3 = resultSet.getString("city_name");
                    // Handle potential null values for address fields
                    value1 = (value1 != null) ? value1 : "";
                    value2 = (value2 != null) ? value2 : "";
                    value3 = (value3 != null) ? value3 : "";

                    // Add address to the vector
                    vector.add(value1 + ", " + value2 + ", " + value3);

                    // Add the row to the table model
                    model.addRow(vector);
                }
            } catch (SQLException e) {
                // Handle SQL exceptions
                e.printStackTrace();
            }
        } catch (Exception e) {
            // Catch any other exceptions
            e.printStackTrace();
            System.out.println("Error in loading address data.");
        }
    }

    private void loadDescription(String descId) {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT description FROM event WHERE id = '" + descId + "'");
            if (resultSet.next()) {
                jTextArea1.setText(resultSet.getString("description"));
            } else {
                jTextArea1.setText("No description available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in loading job description.");
        }
    }

    public void getEventCount() {
        try {
            // Define the SQL query to get the employee count
            String query = "SELECT COUNT(*) AS event_count FROM `event`";

            // Execute the query and get the result
            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            // Process the result
            if (resultSet.next()) {
                int employeeCount = resultSet.getInt("event_count");

                // Set the result (employee count) as the text for jLabel6
                jLabel1.setText("Total Events: " + employeeCount);
            }

            // Close the resources
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setText("Search ");

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("jLabel1");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel26.setText("View Event");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(24, 24, 24))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Event Name", "Category", "Offer", "Qty", "Price", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String text = jTextField1.getText();
        loadTable("id", "ASC", text);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTextField1.setText("");
        jTextArea1.setText("");
        jTable1.clearSelection();
        jTable1.revalidate();
        jTable1.repaint();
        loadTable("id", "ASC", jTextField1.getText());
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
