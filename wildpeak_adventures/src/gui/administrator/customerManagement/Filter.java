/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

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
public class Filter extends javax.swing.JPanel {

    private AllCustomerPanel parent;

    private Color color1 = new Color(46, 125, 50);    // Forest green
    private Color color2 = new Color(129, 199, 132);
    private Color midpointColor;
    private int cornerRadius = 20;

    /**
     * Creates new form FilterPanel
     */
    public Filter(AllCustomerPanel parent) {
        this.parent = parent;
        initComponents();
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

    public void clearFeild() {
//         Reset all filters to default
        cmb_type.setSelectedIndex(0); // Reset customer type combo box to the first item
        cmb_gender.setSelectedIndex(0); // Reset gender combo box to the first item
        cmb_age_range.setSelectedIndex(0); // Reset age range combo box to the first item
        cmb_event.setSelectedIndex(0); // Reset event combo box to the first item
        jDateChooser2.setDate(null); // Clear the date chooser

        parent.loadCustomer();
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
            String query = "SELECT DISTINCT `name` FROM `gender`";
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
            // Query to fetch all unique customer types
            String query = "SELECT DISTINCT `range` FROM `age_range`";
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

            // Close the result set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEvent() {
        try {
            // Query to fetch all unique customer types
            String query = "SELECT DISTINCT `name` FROM `event`";
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
            cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(v));

            // Close the result set
            rs.close();
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_type = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_gender = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_age_range = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmb_event = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Filter");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Type");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText(":");

        cmb_type.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Gender");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText(":");

        cmb_gender.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText(":");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Date");

        jDateChooser2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Age");

        cmb_age_range.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_age_range.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Event");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText(":");

        cmb_event.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cmb_event.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton1.setText("Filter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton2.setText("c");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_gender, 0, 104, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_age_range, 0, 104, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_event, 0, 132, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel4});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(cmb_event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(cmb_age_range, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_type, jDateChooser2, jLabel2, jLabel3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_gender, jLabel4, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_age_range, jButton1, jLabel8, jLabel9});

    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String selectedType = cmb_type.getSelectedItem().toString();
            String selectedGender = cmb_gender.getSelectedItem().toString();
            String selectedAgeRange = cmb_age_range.getSelectedItem().toString();
            String selectedEvent = cmb_event.getSelectedItem().toString();
            java.util.Date selectedDate = jDateChooser2.getDate();

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

//            System.out.println(queryBuilder);
            ResultSet resultSet = MYSQL.executeSearch(queryBuilder.toString());

            DefaultTableModel defaultTableModel = (DefaultTableModel) parent.jTable1.getModel();
            defaultTableModel.setRowCount(0);
//
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFeild();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_age_range;
    private javax.swing.JComboBox<String> cmb_event;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JComboBox<String> cmb_type;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
