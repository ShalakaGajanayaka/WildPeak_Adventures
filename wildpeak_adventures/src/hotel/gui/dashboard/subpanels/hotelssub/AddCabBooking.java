/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package hotel.gui.dashboard.subpanels.hotelssub;

import hotel.model.MYSQL2;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author shalaka
 */
public class AddCabBooking extends javax.swing.JDialog {

    private final HashMap<String, String> cabMap = new HashMap<>();
    HashMap<String, String> customerMap = new HashMap<>();
    HashMap<String, String> cabNumberMap = new HashMap<>();
    HashMap<String, String> DriverMap = new HashMap<>();
    HashMap<String, String> TranspotationTypeMap = new HashMap<>();
    private CabBooking parent;

    /**
     * Creates new form AddRoomImages
     */
    public AddCabBooking(CabBooking parent, boolean modal) {
//        super(parent, modal);
        this.parent = parent;
        initComponents();
        cab();
        customer();
        cabNumber();
        Driver();
        TranspotationType();
        jDateChooser1.getDateEditor().setEnabled(false);
        setLocationRelativeTo(null);
    }

    private void cab() {
        try {
            ResultSet resultSet = hotel.model.MYSQL2.executeSearch("SELECT * FROM cab");
            if (resultSet == null) {
                System.out.println("ResultSet is null.");
                return; // Exit if ResultSet is null
            }

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                if (name != null && id != null) {
                    vector.add(name);
                    cabMap.put(name, id);
                } else {
                    System.out.println("Received null name or id.");
                }
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            if (car != null) {
                car.setModel(model);
            } else {
                System.out.println("jComboBox1 is not initialized.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customer() {
        try {
            // Execute the query to get the customer data
            ResultSet resultSet = MYSQL2.executeSearch("SELECT * FROM `customer`");

            // Check if the ResultSet is valid
            if (resultSet == null) {
                System.out.println("ResultSet is null.");
                return; // Exit if ResultSet is null
            }

            Vector<String> vector = new Vector<>();
            vector.add("Select"); // Add default option

            // Iterate through the ResultSet
            while (resultSet.next()) {
                String firstName = resultSet.getString("fname");
                String lastName = resultSet.getString("lname");
                String customerIdentity = resultSet.getString("customer_identity");

                // Check for null values before concatenation
                if (firstName != null && lastName != null && customerIdentity != null) {
                    String fullName = firstName + " " + lastName; // Add a space between names
                    vector.add(fullName);
                    customerMap.put(fullName, customerIdentity); // Store in cabMap with full name as key
                } else {
                    System.out.println("Received null value for customer data.");
                }
            }

            // Create and set the model for jComboBox2
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            if (jComboBox2 != null) {
                jComboBox2.setModel(model);
            } else {
                System.out.println("jComboBox2 is not initialized.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Optionally log the exception
            // logger.log(Level.WARNING, "Exception in Customer Management", e);
        }
    }

    private void cabNumber() {
        try {
            // Execute the query to get cab numbers
            ResultSet resultSet = MYSQL2.executeSearch("SELECT number, id FROM `cab`");

            // Initialize a vector to hold cab numbers
            Vector<String> vector = new Vector<>();
            vector.add("Select"); // Add default option

            // Check if ResultSet is not empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found in the cab table.");
                return; // Exit if no records found
            }

            // Iterate through the ResultSet
            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String id = resultSet.getString("id");

                // Check for null values before adding to vector and map
                if (number != null && id != null) {
                    vector.add(number);
                    cabNumberMap.put(number, id); // Store in cabNumberMap with number as key
                } else {
                    System.out.println("Received null value for cab number or ID.");
                }
            }

            // Create and set the model for jComboBox3
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            if (jComboBox3 != null) {
                jComboBox3.setModel(model);
            } else {
                System.out.println("jComboBox3 is not initialized.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Optionally log the exception
            // logger.log(Level.WARNING, "Exception in cabNumber method", e);
        }
    }

    private void Driver() {
        try {
            // Execute the query to get cab numbers
            ResultSet resultSet = MYSQL2.executeSearch("SELECT name, id FROM `driver`");

            // Initialize a vector to hold cab numbers
            Vector<String> vector = new Vector<>();
            vector.add("Select"); // Add default option

            // Check if ResultSet is not empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found in the driver table.");
                return; // Exit if no records found
            }

            // Iterate through the ResultSet
            while (resultSet.next()) {
                String number = resultSet.getString("name");
                String id = resultSet.getString("id");

                // Check for null values before adding to vector and map
                if (number != null && id != null) {
                    vector.add(number);
                    DriverMap.put(number, id); // Store in cabNumberMap with number as key
                } else {
                    System.out.println("Received null value for driver name or ID.");
                }
            }

            // Create and set the model for jComboBox3
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            if (jComboBox4 != null) {
                jComboBox4.setModel(model);
            } else {
                System.out.println("jComboBox4 is not initialized.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Optionally log the exception
            // logger.log(Level.WARNING, "Exception in cabNumber method", e);
        }
    }

    private void TranspotationType() {
        try {
            // Execute the query to get cab numbers
            ResultSet resultSet = MYSQL2.executeSearch("SELECT name, id FROM `transportation_type`");

            // Initialize a vector to hold cab numbers
            Vector<String> vector = new Vector<>();
            vector.add("Select"); // Add default option

            // Check if ResultSet is not empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found in the transportaion_type table.");
                return; // Exit if no records found
            }

            // Iterate through the ResultSet
            while (resultSet.next()) {
                String number = resultSet.getString("name");
                String id = resultSet.getString("id");

                // Check for null values before adding to vector and map
                if (number != null && id != null) {
                    vector.add(number);
                    TranspotationTypeMap.put(number, id); // Store in cabNumberMap with number as key
                } else {
                    System.out.println("Received null value for transportaion_type name or ID.");
                }
            }

            // Create and set the model for jComboBox3
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            if (jComboBox5 != null) {
                jComboBox5.setModel(model);
            } else {
                System.out.println("jComboBox5 is not initialized.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Optionally log the exception
            // logger.log(Level.WARNING, "Exception in cabNumber method", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timingTargetAdapter1 = new org.jdesktop.animation.timing.TimingTargetAdapter();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        car = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("Add Cab Booking");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Car");

        car.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        car.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Car", "Innova", "Nissan leaf", "Maruti 800", "Honda fit" }));

        jButton1.setBackground(new java.awt.Color(102, 0, 102));
        jButton1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(245, 245, 245));
        jButton1.setText("Save changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jButton2.setText("Close");

        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Name", "Customer 1", "Customer 2" }));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Customer Name");

        jComboBox3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Cab Number", "ABC 1355", "AAB 4565" }));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setText("Cab Number");

        jComboBox4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Driver Name", "Isuru", "Roshan", "Lakshan" }));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setText("Driver");

        jComboBox5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Pick up", "Drop" }));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel8.setText("Transportation Type");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel9.setText("Transportation Time");

        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setText("Show time picker");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel10.setText("Transportation Date");

        jDateChooser1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(car, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox5, jDateChooser1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        timingTargetAdapter1.showPopup(this,100,100);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Fetch values from input fields
        String carField = String.valueOf(car.getSelectedItem());
        String customer = String.valueOf(jComboBox2.getSelectedItem());
        String cno = String.valueOf(jComboBox3.getSelectedItem());
        String driver = String.valueOf(jComboBox4.getSelectedItem());
        String type = String.valueOf(jComboBox5.getSelectedItem());
        Date date = jDateChooser1.getDate();

        // Validate inputs
        if (carField == null || carField.equals("Select") || carField.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a car.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (customer == null || customer.equals("Select") || customer.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a customer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cno == null || cno.equals("Select") || cno.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a contact number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (driver == null || driver.equals("Select") || driver.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a driver.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (type == null || type.equals("Select") || type.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a type.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (date == null) {
            JOptionPane.showMessageDialog(this, "Please select a date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate that the selected date is today or in the future
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (date.before(today)) {
            JOptionPane.showMessageDialog(this, "The selected date cannot be in the past. Please select today or a future date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format the date to "yyyy-MM-dd"
        String formattedDate = sdf.format(date);

        // Set default time (e.g., current time)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String defaultTime = timeFormat.format(new Date()); // Current time as default

        // Add your submission logic here (e.g., saving to database)
        try {
            ResultSet rs = MYSQL2.executeSearch("SELECT * FROM `cab_booking` WHERE `cab_id` = '" + cabMap.get(carField) + "' AND `driver_id` = '" + DriverMap.get(driver) + "' AND `date` = '" + formattedDate + "'");

            if (!rs.next()) { // Check if no existing booking found
                // Prepare the insert statement
                String insertQuery = "INSERT INTO `cab_booking` (`date`, `time`, `cab_id`, `driver_id`, `transportation_type_id`, `customer_customer_identity`) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = MYSQL2.getConnection().prepareStatement(insertQuery);

                pstmt.setString(1, formattedDate);
                pstmt.setString(2, defaultTime);
                pstmt.setString(3, cabMap.get(carField));
                pstmt.setString(4, DriverMap.get(driver));
                pstmt.setString(5, TranspotationTypeMap.get(type));
                pstmt.setString(6, customerMap.get(customer));

                // Inside the actionPerformed method after inserting into the database
                int rowsInserted = pstmt.executeUpdate(); // Execute the insert

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Booking successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Reload the table to show the new booking
                    parent.cabBooking("cab_booking.date", "ASC", "");  // Reload table with updated data
                    resetFields(); // Reset the form fields after submission
                } else {
                    JOptionPane.showMessageDialog(this, "Booking creation failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "A booking already exists for this cab and driver on this date.", "Booking Exists", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while processing your request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void resetFields() {
        car.setSelectedIndex(0); // Reset car selection
        jComboBox2.setSelectedIndex(0); // Reset customer selection
        jComboBox3.setSelectedIndex(0); // Reset contact number selection
        jComboBox4.setSelectedIndex(0); // Reset driver selection
        jComboBox5.setSelectedIndex(0); // Reset type selection
        jDateChooser1.setDate(null); // Clear the date chooser
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> car;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private org.jdesktop.animation.timing.TimingTargetAdapter timingTargetAdapter1;
    // End of variables declaration//GEN-END:variables
}
