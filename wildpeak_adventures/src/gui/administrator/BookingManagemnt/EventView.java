/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.administrator.BookingManagemnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author USER
 */
public class EventView extends javax.swing.JDialog {

    private Booking event;

    public void setevent(Booking event) {
        this.event = event;
    }
    private ManageBooking mevent;

    public void setManageEvent(ManageBooking mevent) {
        this.mevent = mevent;
    }

    public EventView() {
        initComponents();
        loadEvent("id", "ASC", jTextField2.getText());
    }

    private void loadEvent(String column, String orderby, String text) {
        try {
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

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + text + "%";

                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);
                statement.setString(3, searchPattern);
                statement.setString(4, searchPattern);
                statement.setString(5, searchPattern);
                statement.setString(6, searchPattern);
                statement.setString(7, searchPattern);
                statement.setString(8, searchPattern);

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    // Add event details
                    vector.add(resultSet.getString("event_id"));
                    vector.add(resultSet.getString("event_name"));
                    vector.add(resultSet.getString("category_name"));
                    vector.add(resultSet.getString("event.max_participants"));
                    vector.add(resultSet.getString("offer_name"));
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
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Customer View");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("You Can double click the table row select customer");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Event Name", "Event Category", "QTY", "Offer (%)", "Price (Rs)", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String searchtext = jTextField2.getText();
        loadEvent("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {
            if (event != null) {
                event.getjLabel5().setText(String.valueOf(jTable1.getValueAt(row, 1)));
                event.getjLabel6().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                event.getjLabel7().setText(String.valueOf(jTable1.getValueAt(row, 3)));
                event.getjLabel8().setText(String.valueOf(jTable1.getValueAt(row, 6)));
                event.getjLabel9().setText(String.valueOf(jTable1.getValueAt(row, 5)));
                event.getjLabel10().setText(String.valueOf(jTable1.getValueAt(row, 4)));
                event.getjLabel11().setText(String.valueOf(jTable1.getValueAt(row, 0)));
            } else {
                System.out.println("event: " + (event != null ? "Initialized" : "null"));
            }

            if (mevent != null) {
                mevent.getJTextField6().setText(String.valueOf(jTable1.getValueAt(row, 1)));
            } else {
                System.out.println("mevent: " + (mevent != null ? "Initialized" : "null"));
            }

            this.dispose();
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
