/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement.allCustomer;

import gui.administrator.customerManagement.allCustomers;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class filter extends javax.swing.JPanel {

    private allCustomers parent; // Field to store the parent panel

    private Color color1 = new Color(46, 125, 50);    // Forest green
    private Color color2 = new Color(129, 199, 132);
    private Color midpointColor;
    private int cornerRadius = 20;

    /**
     * Creates new form filter
     */
    public filter(allCustomers parent) {
        this.parent = parent; // Store the parent reference
        initComponents();
 
        panelColor();

        loadType();
        loadGender();
        loadAgeRange();
        loadEvent();
    }

    public void panelColor() {
        // Calculate the midpoint color
        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;
        midpointColor = new Color(red, green, blue);

        // Set the background to the midpoint color
        setBackground(midpointColor);
    }

    // Getter for the parent panel (optional)
    public allCustomers getParentPanel() {
        return parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable antialiasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Create the rounded rectangle shape
        RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(
                0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        // Create the gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, color1,
                getWidth(), getHeight(), color2);

        // Fill the rounded rectangle with the gradient
        g2d.setPaint(gradient);
        g2d.fill(roundRect);

        // Optional: Add a border
        g2d.setColor(new Color(0, 0, 0, 50));  // Semi-transparent black
        g2d.setStroke(new BasicStroke(1f));
        g2d.draw(roundRect);
    }

    public void loadType() {
        try {
            // Query to fetch all unique customer types
            String query = "SELECT DISTINCT `name` FROM `customer_type`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("name"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(v));

            // Close the result set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGender() {
        try {
            // Query to fetch all unique customer types
            String query = "SELECT `name` FROM `gender`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("name"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(v));

            // Close the result set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAgeRange() {
        try {
            // Query to fetch age ranges from the database
            String query = "SELECT `range` FROM `age_range`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold the age range values
            Vector<String> v = new Vector<>();

            // Add the default "Select" item
            v.add("Select");

            // Add each age range from the ResultSet to the Vector
            while (rs.next()) {
                String type = rs.getString("range"); // Ensure "range" matches the column name
                v.add(type);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_age_range.setModel(new javax.swing.DefaultComboBoxModel<>(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvent() {
        try {
            // Query to fetch all columns from the event table
            String query = "SELECT * FROM `event`";
            ResultSet rs = MYSQL.executeSearch(query);

            // Create a Vector to hold event names
            Vector<String> v = new Vector<>();

            // Add a default "Select" item to the Vector
            v.add("Select");

            // Fetch each row and add the 'name' column to the Vector
            while (rs.next()) {
                String eventName = rs.getString("name"); // Assuming 'name' is the column storing event names
                v.add(eventName);
            }

            // Close the ResultSet
            rs.close();

            // Set the Vector as the model for the JComboBox
            cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(v));
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

        cmb_type = new javax.swing.JComboBox<>();
        cmb_gender = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_age_range = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmb_event = new javax.swing.JComboBox<>();
        btn_clear_filter = new javax.swing.JButton();
        btn_filter = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        cmb_type.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmb_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_typeItemStateChanged(evt);
            }
        });

        cmb_gender.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        jDateChooser1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Type");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Gender");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Date");

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Filter");

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Age");

        cmb_age_range.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_age_range.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "20 -30", "30 - 40", "40 - 60" }));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Event  :");

        cmb_event.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "20 -30", "30 - 40", "40 - 60" }));

        btn_clear_filter.setText("C");
        btn_clear_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_filterActionPerformed(evt);
            }
        });

        btn_filter.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        btn_filter.setText("Filter");
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(":");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(":");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(":");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_age_range, 0, 210, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_event, 0, 336, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_clear_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_age_range, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_clear_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_typeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_typeItemStateChanged

    private void btn_clear_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_filterActionPerformed
        // Reset all filters to default
        cmb_type.setSelectedIndex(0); // Reset customer type combo box to the first item
        cmb_gender.setSelectedIndex(0); // Reset gender combo box to the first item
        cmb_age_range.setSelectedIndex(0); // Reset age range combo box to the first item
        cmb_event.setSelectedIndex(0); // Reset event combo box to the first item
        jDateChooser1.setDate(null); // Clear the date chooser

        parent.loadCustomer();
    }//GEN-LAST:event_btn_clear_filterActionPerformed

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        try {
            String selectedType = cmb_type.getSelectedItem().toString();
            String selectedGender = cmb_gender.getSelectedItem().toString();
            String selectedAgeRange = cmb_age_range.getSelectedItem().toString();
            String selectedEvent = cmb_event.getSelectedItem().toString();
            java.util.Date selectedDate = jDateChooser1.getDate();

            StringBuilder queryBuilder = new StringBuilder("SELECT `customer`.`fname`, `customer`.`lname`, `customer`.`email`, "
                    + "`customer`.`mobile`, `customer`.`age`, `customer`.`register_date`, "
                    + "`gender`.`name`, "
                    + "`customer_type`.`name`  ");

            // Start building FROM and JOINs before WHERE
            queryBuilder.append("FROM `customer` ")
                    .append("INNER JOIN `customer_type` ON `customer`.`customer_type_id` = `customer_type`.`id` ")
                    .append("INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id` ");

            // Add joins for booking_event and event if an event is selected
            if (!selectedEvent.equals("Select")) {
                queryBuilder.append("INNER JOIN `booking_event` ON `customer`.`mobile` = `booking_event`.`customer_mobile` ")
                        .append("INNER JOIN `event` ON `event`.`id` = `booking_event`.`event_id` ");
            }

            // Start WHERE clause
            queryBuilder.append("WHERE 1=1");

            if (!selectedType.equals("Select")) {
                queryBuilder.append(" AND `customer_type`.`name` = '").append(selectedType).append("'");
            }
            if (!selectedGender.equals("Select")) {
                queryBuilder.append(" AND `gender`.`name` = '").append(selectedGender).append("'");
            }
            if (!selectedAgeRange.equals("Select")) {
                // Split the selected age range into lower and upper bounds
                String[] ageBounds = selectedAgeRange.split(" - ");
                int lowerBound = Integer.parseInt(ageBounds[0]);
                int upperBound = Integer.parseInt(ageBounds[1]);

                queryBuilder.append(" AND `age` BETWEEN ").append(lowerBound).append(" AND ").append(upperBound);
            }
            // Filter by date
            if (selectedDate != null) {
                // Format date to SQL-compatible string
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);
                queryBuilder.append(" AND DATE(`register_date`) = '").append(formattedDate).append("'");
            }

            if (!selectedEvent.equals("Select")) {
                queryBuilder.append(" AND `event`.`name` = '").append(selectedEvent).append("'");
            }

//                        System.out.println(queryBuilder);
            ResultSet resultSet = MYSQL.executeSearch(queryBuilder.toString());

            DefaultTableModel defaultTableModel = (DefaultTableModel) parent.jTable1.getModel();
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
    }//GEN-LAST:event_btn_filterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear_filter;
    private javax.swing.JButton btn_filter;
    private javax.swing.JComboBox<String> cmb_age_range;
    private javax.swing.JComboBox<String> cmb_event;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JComboBox<String> cmb_type;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
