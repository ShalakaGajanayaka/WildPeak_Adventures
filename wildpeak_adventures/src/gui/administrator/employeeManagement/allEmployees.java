package gui.administrator.employeeManagement;

import gui.administrator.EmployeeManagement;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

public class allEmployees extends javax.swing.JPanel {

    /**
     * Creates new form allEmployees
     */
    public allEmployees() {
        initComponents();
        loadEmployee("id", "ASC", jTextField2.getText());
        jTable1.revalidate();
        jTable1.repaint();
        getEmployeeCount();
        refreshTable();
        setupRightClickMenu();
        jDateChooser1.getDateEditor().setEnabled(false);

    }
    
    
    private void setupRightClickMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewAddressItem = new JMenuItem("View Address");
        viewAddressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row's data when right-click menu option is selected
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    String nic = String.valueOf(jTable1.getValueAt(row, 4)); // Assuming NIC is in the 4th column
                    String userId = String.valueOf(jTable1.getValueAt(row, 0)); // Assuming user ID is in the 1st column
                    Frame EmployeeManagement = null;
                    JDialog addressView = new AddressView(EmployeeManagement, true, userId, nic);
                    addressView.setVisible(true);
                }
            }
        });
        popupMenu.add(viewAddressItem);

        // Add mouse listener to display popup menu on right-click
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable1.rowAtPoint(e.getPoint());
                    jTable1.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable1.rowAtPoint(e.getPoint());
                    jTable1.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }
        });
    }


    public void getEmployeeCount() {
        try {
            String query = "SELECT COUNT(*) AS employee_count FROM `employee`";

            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int employeeCount = resultSet.getInt("employee_count");
                jLabel6.setText("Total Employees: " + employeeCount);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployee(String column, String orderby, String Text) {

        try {

            String query = "SELECT * FROM employee "
                    + "LEFT JOIN job_position ON employee.job_position_id = job_position.id "
                    + "LEFT JOIN gender ON employee.gender_id = gender.id "
                    + "LEFT JOIN status ON employee.status_id = status.id "
                    + "LEFT JOIN address ON address.employee_id = employee.id "
                    + "WHERE employee.fname LIKE ? "
                    + "OR employee.lname LIKE ? "
                    + "OR employee.email LIKE ? "
                    + "OR employee.nic LIKE ? "
                    + "OR employee.mobile LIKE ? "
                    + "OR employee.register_date LIKE ? "
                    + "OR job_position.name LIKE ? "
                    + "OR gender.name LIKE ? "
                    + "OR status.name LIKE ? "
                    + "OR address.line_1 LIKE ? "
                    + "OR address.line_2 LIKE ? "
                    + "OR address.line_3 LIKE ? "
                    + "ORDER BY employee." + column + " " + orderby;

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + Text + "%";
                for (int i = 1; i <= 12; i++) {
                    statement.setString(i, searchPattern);
                }

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("employee.id"));
                    vector.add(resultSet.getString("employee.fname") + " " + resultSet.getString("employee.lname"));
                    vector.add(resultSet.getString("employee.email"));
                    vector.add(resultSet.getString("employee.password"));
                    vector.add(resultSet.getString("employee.nic"));
                    vector.add(resultSet.getString("employee.mobile"));
                    vector.add(resultSet.getString("employee.register_date"));
                    vector.add(resultSet.getString("job_position.name"));
                    vector.add(resultSet.getString("gender.name"));
                    vector.add(resultSet.getString("status.name"));
                    vector.add(resultSet.getString("address.line_1") + " " + resultSet.getString("address.line_2") + " " + resultSet.getString("address.line_3"));

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
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Search");

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("2345");
        jLabel6.setToolTipText("");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Name", "Email", "Password", "Nic", "Mobile", "Joined Date", "Job Role", "gender", "Status", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String searchtext = jTextField2.getText();
        loadEmployee("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date selectedDate = jDateChooser1.getDate();
        if (selectedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(selectedDate);

            String query = "SELECT * FROM employee "
                    + "LEFT JOIN job_position ON employee.job_position_id = job_position.id "
                    + "LEFT JOIN gender ON employee.gender_id = gender.id "
                    + "LEFT JOIN status ON employee.status_id = status.id "
                    + "LEFT JOIN address ON address.employee_id = employee.id "
                    + "WHERE employee.register_date = ? "
                    + "ORDER BY employee.id ASC";

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                statement.setString(1, dateString);

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("employee.id"));
                    vector.add(resultSet.getString("employee.fname") + " " + resultSet.getString("employee.lname"));
                    vector.add(resultSet.getString("employee.email"));
                    vector.add(resultSet.getString("employee.password"));
                    vector.add(resultSet.getString("employee.nic"));
                    vector.add(resultSet.getString("employee.mobile"));
                    vector.add(resultSet.getString("employee.register_date"));
                    vector.add(resultSet.getString("job_position.name"));
                    vector.add(resultSet.getString("gender.name"));
                    vector.add(resultSet.getString("status.name"));
                    vector.add(resultSet.getString("address.line_1") + " " + resultSet.getString("address.line_2") + " " + resultSet.getString("address.line_3"));

                    defaultTableModel.addRow(vector);
                }

                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else {
            loadEmployee("id", "ASC", jTextField2.getText());
            JOptionPane.showMessageDialog(this, "Please select a date.");

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextField2.setText("");
        jDateChooser1.setDate(null);
        jTable1.clearSelection();
        jTextField2.grabFocus();
        loadEmployee("id", "ASC", jTextField2.getText());
    }//GEN-LAST:event_jButton2ActionPerformed
    private void refreshTable() {
        // Clear the existing table rows
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  // This removes all rows from the table

        try {
            String query = "SELECT * "
                    + "FROM employee "
                    + "LEFT JOIN job_position ON employee.job_position_id = job_position.id "
                    + "LEFT JOIN gender ON employee.gender_id = gender.id "
                    + "LEFT JOIN status ON employee.status_id = status.id "
                    + "LEFT JOIN address ON address.employee_id = employee.id "
                    + "WHERE employee.fname LIKE ? "
                    + "OR employee.lname LIKE ? "
                    + "OR employee.email LIKE ? "
                    + "OR employee.nic LIKE ? "
                    + "OR employee.mobile LIKE ? "
                    + "OR employee.register_date LIKE ? "
                    + "OR job_position.name LIKE ? "
                    + "OR gender.name LIKE ? "
                    + "OR status.name LIKE ? "
                    + "OR address.line_1 LIKE ? "
                    + "OR address.line_2 LIKE ? "
                    + "OR address.line_3 LIKE ? "
                    + "ORDER BY employee.id";

            // Prepare the statement and set the parameters
            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);

            // Assuming these are the search values for each placeholder (adjust them according to your actual variables)
            for (int i = 1; i <= 12; i++) {
                stmt.setString(i, "%");  // Use a wildcard search, change as necessary
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Add each row to the table
            while (rs.next()) {
                Object[] row = new Object[13];  // Array to hold each row's data
                row[0] = rs.getInt("id");
                row[1] = rs.getString("fname") + " " + rs.getString("lname");
                row[2] = rs.getString("email");
                row[3] = rs.getString("password");
                row[4] = rs.getString("nic");
                row[5] = rs.getString("mobile");
                row[6] = rs.getString("register_date");
                row[7] = rs.getString("job_position.name");
                row[8] = rs.getString("gender.name");
                row[9] = rs.getString("status.name");
                row[10] = rs.getString("address.line_1") + "," + rs.getString("address.line_2") + "'" + rs.getString("address.line_3");

                // Add the row to the table model
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
