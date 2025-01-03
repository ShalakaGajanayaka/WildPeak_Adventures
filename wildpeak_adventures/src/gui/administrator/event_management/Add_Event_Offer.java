package gui.administrator.event_management;

import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add_Event_Offer extends javax.swing.JPanel {

    public Add_Event_Offer() {
        initComponents();
        loadOffer("id", "ASC", jTextField1.getText());
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

    private void loadOffer(String column, String orderby, String Text) {

        try {

            String query = "SELECT * FROM event_offer "
                    + "WHERE event_offer.name LIKE ? "
                    + "OR event_offer.discount_price LIKE ? "
                    + "ORDER BY event_offer." + column + " " + orderby;

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + Text + "%";
                for (int i = 1; i <= 2; i++) {
                    statement.setString(i, searchPattern);
                }

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("event_offer.id"));
                    vector.add(resultSet.getString("event_offer.name"));
                    vector.add(resultSet.getString("event_offer.start_date"));
                    vector.add(resultSet.getString("event_offer.end_date"));
                    vector.add(resultSet.getString("event_offer.discount_price"));

                    defaultTableModel.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDescription(String descId) {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT description FROM event_offer WHERE id = '" + descId + "'");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Offer Name");

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

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

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("You can add Or edit Event Offer");

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Start Date");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("End Date");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("0.00");
        jFormattedTextField1.setToolTipText("0.00");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Price");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Offer Name", "Start Date", "End Date", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setText("Description");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addGap(15, 15, 15))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
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

        int row = jTable1.getSelectedRow();

        try {
            jButton1.setEnabled(false);

            String id = String.valueOf(jTable1.getValueAt(row, 0));  // assuming column 0 is the user ID

            // Query database using UserId to retrieve additional details
            String query = "SELECT * FROM event_offer "
                    + "WHERE event_offer.id = ?";

            // Assuming you have a method for database connection (connection)
            PreparedStatement pst = MYSQL.getConnection().prepareStatement(query);
            pst.setString(1, id);  // Sets the 'email' as a parameter in the WHERE clause
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                jTextField2.setText(rs.getString("name"));
                // Correctly set date values
                java.sql.Date date1 = rs.getDate("start_date"); // Use actual column name for start date
                java.sql.Date date2 = rs.getDate("end_date");   // Use actual column name for end date

                if (date1 != null) {
                    jDateChooser1.setDate(new java.util.Date(date1.getTime()));
                }
                if (date2 != null) {
                    jDateChooser2.setDate(new java.util.Date(date2.getTime()));
                }

                jFormattedTextField1.setText(rs.getString("discount_price"));
                jTextArea1.setText(rs.getString("description"));

            }
            rs.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String text = jTextField1.getText();
        loadOffer("id", "ASC", text);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get values from the fields
        String name = jTextField2.getText().trim();

        // Date formatting
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = (jDateChooser1.getDate() != null) ? sdf.format(jDateChooser1.getDate()) : "";
        String enddate = (jDateChooser2.getDate() != null) ? sdf.format(jDateChooser2.getDate()) : "";

        String price = jFormattedTextField1.getText().trim();
        String description = jTextArea1.getText().trim();

        // Validation for category name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Category Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (sdate.isEmpty()) {
            // Validation for start date
            JOptionPane.showMessageDialog(this, "Please select a start date", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (enddate.isEmpty()) {
            // Validation for end date
            JOptionPane.showMessageDialog(this, "Please select an end date", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                Date startDate = sdf.parse(sdate);
                Date endDate = sdf.parse(enddate);

                // Check if the start date is before today
                Date today = new Date(); // Get today's date
                if (startDate.before(today)) {
                    JOptionPane.showMessageDialog(this, "Start date must be today or in the future", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (endDate.before(startDate)) {
                    // Check if the end date is before the start date
                    JOptionPane.showMessageDialog(this, "End date must be after the start date", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (price.isEmpty()) {
                    // Validation for price (must be a valid number)
                    JOptionPane.showMessageDialog(this, "Please enter a valid price", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        double discountPrice = Double.parseDouble(price);
                        if (discountPrice <= 0) {
                            JOptionPane.showMessageDialog(this, "Please enter a valid price greater than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else if (description.isEmpty()) {
                            // Validation for description
                            JOptionPane.showMessageDialog(this, "Please Enter Description (Qualification)", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // Proceed with valid inputs
                            // Add your code here for further processing or saving data
                            try {
                                // Check if the event offer already exists
                                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event_offer` WHERE `name` = '" + name + "'");
                                if (resultSet.next()) {
                                    JOptionPane.showMessageDialog(this, "This Offer is Already Added.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    // Insert new event offer into the database with proper SQL syntax
                                    String sql = "INSERT INTO `event_offer` (`name`, `description`, `start_date`, `end_date`, `discount_price`) "
                                            + "VALUES (?, ?, ?, ?, ?)";
                                    try (PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql)) {
                                        stmt.setString(1, name);
                                        stmt.setString(2, description);
                                        stmt.setString(3, sdate);
                                        stmt.setString(4, enddate);
                                        stmt.setDouble(5, discountPrice);

                                        stmt.executeUpdate();
                                        loadOffer("id", "ASC", jTextField1.getText());
                                        reset();
                                        JOptionPane.showMessageDialog(this, "Offer added successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(this, "Error while inserting the offer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "There was an error processing the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid price", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "There was an error processing the dates", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String id = String.valueOf(jTable1.getValueAt(row, 0));
        // Get values from the fields
        String name = jTextField2.getText().trim();

        // Date formatting
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = (jDateChooser1.getDate() != null) ? sdf.format(jDateChooser1.getDate()) : "";
        String enddate = (jDateChooser2.getDate() != null) ? sdf.format(jDateChooser2.getDate()) : "";

        String price = jFormattedTextField1.getText().trim();
        String description = jTextArea1.getText().trim();

        // Validation for category name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Category Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (sdate.isEmpty()) {
            // Validation for start date
            JOptionPane.showMessageDialog(this, "Please select a start date", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (enddate.isEmpty()) {
            // Validation for end date
            JOptionPane.showMessageDialog(this, "Please select an end date", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                Date startDate = sdf.parse(sdate);
                Date endDate = sdf.parse(enddate);

                // Check if the start date is before today
                Date today = new Date(); // Get today's date
                if (startDate.before(today)) {
                    JOptionPane.showMessageDialog(this, "Start date must be today or in the future", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (endDate.before(startDate)) {
                    // Check if the end date is before the start date
                    JOptionPane.showMessageDialog(this, "End date must be after the start date", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (price.isEmpty()) {
                    // Validation for price (must be a valid number)
                    JOptionPane.showMessageDialog(this, "Please enter a valid price", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        double discountPrice = Double.parseDouble(price);
                        if (discountPrice <= 0) {
                            JOptionPane.showMessageDialog(this, "Please enter a valid price greater than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else if (description.isEmpty()) {
                            // Validation for description
                            JOptionPane.showMessageDialog(this, "Please Enter Description (Qualification)", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // Proceed with valid inputs
                            // Check if the event offer already exists
                            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event_offer` WHERE `name` = '" + name + "'");
                            if (resultSet.next()) {
                                JOptionPane.showMessageDialog(this, "This Offer is Already Added.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                try {
                                    // Format the dates as String (yyyy-MM-dd)
                                    String formattedStartDate = sdf.format(startDate);
                                    String formattedEndDate = sdf.format(endDate);

                                    // Update the event offer in the database
                                    String sql = "UPDATE `event_offer` SET `name` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `discount_price` = ? WHERE `id` = ?";
                                    try (PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql)) {
                                        stmt.setString(1, name);
                                        stmt.setString(2, description);
                                        stmt.setString(3, formattedStartDate);  // Use formatted date
                                        stmt.setString(4, formattedEndDate);    // Use formatted date
                                        stmt.setDouble(5, discountPrice);
                                        stmt.setString(6, id); // Assuming you are updating based on the existing event name

                                        stmt.executeUpdate();
                                        loadOffer("id", "ASC", jTextField1.getText());
                                        reset();
                                        JOptionPane.showMessageDialog(this, "Offer Updated successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(this, "Error while updating the offer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(this, "There was an error processing the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid price", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(Add_Event_Offer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Add_Event_Offer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "There was an error processing the dates", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        // Clear table selection
        if (jTable1.getRowCount() > 0) {
            jTable1.clearSelection();
        }

        // Clear text fields and text area
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jFormattedTextField1.setText("0.00");

        // Clear date choosers
        clearDateChooser(jDateChooser1);
        clearDateChooser(jDateChooser2);

        // Reload job roles (ensure method handles empty input properly)
        loadOffer("id", "ASC", jTextField1.getText());

        // Enable the main button
        jButton1.setEnabled(true);

        System.out.println("Reset completed successfully!");
    }

    private void clearDateChooser(JDateChooser dateChooser) {
        if (dateChooser != null) {
            // Set the date to null to clear the chooser
            dateChooser.setDate(null);

            // Attempt to clear the visible text (if the editor component supports it)
            if (dateChooser.getDateEditor().getUiComponent() instanceof JTextField) {
                ((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
            }

            // Repaint the component to ensure the UI updates
            dateChooser.repaint();
        }
    }

}
