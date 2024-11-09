/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.employeeManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;
import java.awt.event.KeyEvent;

/**
 *
 * @author shalaka
 */
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

    }
        public void getEmployeeCount() {
        try {
            // Define the SQL query to get the employee count
            String query = "SELECT COUNT(*) AS employee_count FROM `employee`";

            // Execute the query and get the result
            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            // Process the result
            if (resultSet.next()) {
                int employeeCount = resultSet.getInt("employee_count");

                // Set the result (employee count) as the text for jLabel6
                jLabel6.setText("Total Employees: " + employeeCount);
            }

            // Close the resources
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
                    + "OR address.line_1 LIKE ? "
                    + "OR address.line_2 LIKE ? "
                    + "OR address.line_3 LIKE ? "
                    + "ORDER BY employee." + column + " " + orderby;

            System.out.println("Executing query: " + query);  // Debug: Print the query

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + Text + "%";
                System.out.println("Search pattern: " + searchPattern);  // Debug: Print search pattern

                // Set all search patterns
                for (int i = 1; i <= 11; i++) {
                    statement.setString(i, searchPattern);
                }

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No data found");  // Debug: Print if ResultSet is empty
                }

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
                System.out.println("Error in executing query.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private void loadEmployee(String column, String orderby, String Text) {
//        try {
//            // Check if Text is null or empty before proceeding
//            if (Text == null || Text.isEmpty()) {
//                System.out.println("Error: Text is empty or null.");
//                return; // Skip if Text (date) is empty
//            }
//
//            // Construct the query
//            String query = "SELECT * FROM employee "
//                    + "LEFT JOIN job_position ON employee.job_position_id = job_position.id "
//                    + "LEFT JOIN gender ON employee.gender_id = gender.id "
//                    + "LEFT JOIN status ON employee.status_id = status.id "
//                    + "LEFT JOIN address ON address.employee_id = employee.id "
//                    + "WHERE employee.fname LIKE ? "
//                    + "OR employee.lname LIKE ? "
//                    + "OR employee.email LIKE ? "
//                    + "OR employee.nic LIKE ? "
//                    + "OR employee.mobile LIKE ? "
//                    + "OR employee.register_date LIKE ? "
//                    + "OR job_position.name LIKE ? "
//                    + "OR gender.name LIKE ? "
//                    + "OR address.line_1 LIKE ? "
//                    + "OR address.line_2 LIKE ? "
//                    + "OR address.line_3 LIKE ? "
//                    + "AND employee.register_date = ? " // Exact date match
//                    + "ORDER BY employee." + column + " " + orderby;
//
//            System.out.println("Executing query: " + query);  // Debug: Print the query
//
//            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
//                String searchPattern = "%" + Text + "%";
//                System.out.println("Search pattern: " + searchPattern);  // Debug: Print search pattern
//
//                // Set all search patterns (first 11 placeholders)
//                for (int i = 1; i <= 11; i++) {
//                    statement.setString(i, searchPattern);
//                }
//
//                // Set the value for the exact date match (parameter 12)
//                statement.setString(12, Text);  // Ensure Text is in 'yyyy-MM-dd' format
//
//                ResultSet resultSet = statement.executeQuery();
//                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
//                defaultTableModel.setRowCount(0);
//
//                if (!resultSet.isBeforeFirst()) {
//                    System.out.println("No data found");  // Debug: Print if ResultSet is empty
//                }
//
//                while (resultSet.next()) {
//                    Vector<String> vector = new Vector<>();
//                    vector.add(resultSet.getString("employee.id"));
//                    vector.add(resultSet.getString("employee.fname") + " " + resultSet.getString("employee.lname"));
//                    vector.add(resultSet.getString("employee.email"));
//                    vector.add(resultSet.getString("employee.password"));
//                    vector.add(resultSet.getString("employee.nic"));
//                    vector.add(resultSet.getString("employee.mobile"));
//                    vector.add(resultSet.getString("employee.register_date"));
//                    vector.add(resultSet.getString("job_position.name"));
//                    vector.add(resultSet.getString("gender.name"));
//                    vector.add(resultSet.getString("status.name"));
//                    vector.add(resultSet.getString("address.line_1") + " " + resultSet.getString("address.line_2") + " " + resultSet.getString("address.line_3"));
//
//                    defaultTableModel.addRow(vector);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Error in executing query.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("2345");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(14, Short.MAX_VALUE))
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
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
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String searchtext = jTextField2.getText();
        loadEmployee("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField2KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
