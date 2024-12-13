/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.administrator.bookingManagement;

import gui.mainFrame.SignIn_Admin;
import gui.mainFrame.SignIn_Employee;
import gui.mainFrame.SignIn_SuperAdmin;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import model.MYSQL;
import model.bookingItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Booking extends javax.swing.JPanel {

    HashMap<String, bookingItem> bookingItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public Booking() {
        initComponents();
        ((AbstractDocument) jTextField1.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Set the username based on the signed-in role
        if (SignIn_Admin.getemail() != null) {
            jLabel12.setText(SignIn_Admin.getemail());
        } else if (SignIn_SuperAdmin.getemail() != null) {
            jLabel12.setText(SignIn_SuperAdmin.getemail());
        } else if (SignIn_Employee.getemail() != null) {
            jLabel12.setText(SignIn_Employee.getemail());
        } else {
            jLabel12.setText("Guest"); // Default text if no user is logged in
        }
        setid();
        generateBookingId();
        loadPaymentMethods();

    }

    // Custom Document Filter to allow only numeric input
    class NumericDocumentFilter extends DocumentFilter {

        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isNumeric(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isNumeric(String str) {
            return str.matches("\\d*"); // Allow only digits
        }
    }

    public void setid() {
        try {
            if (SignIn_Employee.getemail() != null) {
                ResultSet rs = MYSQL.executeSearch("SELECT `id` employee WHERE `id` = '" + SignIn_Employee.getemail() + "' ");

                if (rs.next()) {
                    jLabel13.setText(rs.getString("employee.id"));
                }
            } else if (SignIn_Admin.getemail() != null) {
                ResultSet rs = MYSQL.executeSearch("SELECT `id` admin WHERE `id` = '" + SignIn_Admin.getemail() + "' ");
                if (rs.next()) {
                    jLabel13.setText(rs.getString("admin.id"));
                }
            } else if (SignIn_SuperAdmin.getemail() != null) {
                ResultSet rs = MYSQL.executeSearch("SELECT `id` admin WHERE `id` = '" + SignIn_SuperAdmin.getemail() + "' ");
                if (rs.next()) {
                    jLabel13.setText(rs.getString("admin.id"));
                }
            } else {
                jLabel13.setText("Guest"); // Default text if no user is logged in
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    customer

    public JLabel getjLabel1() {
        return jLabel1;
    }
//    mobile

    public JLabel getjLabel2() {
        return jLabel2;
    }
//    type

    public JLabel getjLabel3() {
        return jLabel3;
    }
//    email

    public JLabel getjLabel4() {
        return jLabel4;
    }
//    event id

    public JLabel getjLabel11() {
        return jLabel11;
    }
//    event

    public JLabel getjLabel5() {
        return jLabel5;
    }
//    category

    public JLabel getjLabel6() {
        return jLabel6;
    }
//    qty

    public JLabel getjLabel7() {
        return jLabel7;
    }
//    location

    public JLabel getjLabel8() {
        return jLabel8;
    }
//    price

    public JLabel getjLabel9() {
        return jLabel9;
    }
//    offer

    public JLabel getjLabel10() {
        return jLabel10;
    }

    private void generateBookingId() {
        long id = System.currentTimeMillis();
        jLabel16.setText(String.valueOf(id));
    }

    private void loadBookingItems() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        double total = 0;
        double totalOfferPrice = 0; // Total price considering the offer
        int itemId = 1;

        for (bookingItem booking : bookingItemMap.values()) {
            Vector<String> vector = new Vector<>();

            // Dynamic item ID
            vector.add(String.valueOf(itemId));

            // Event details
            vector.add(booking.getEventname());
            vector.add(booking.getDate());
            vector.add(booking.getQty());
            vector.add(booking.getPrice());

            // Fetch the offer name and price from the database
            String offerName = booking.getOffer(); // Assuming `booking.getOffer()` returns the offer name
            double offerPrice = fetchOfferPriceFromDatabase(offerName); // Retrieve price from DB

            // Calculate discounted/offer price
            double itemPrice = Double.parseDouble(booking.getQty()) * offerPrice;
            totalOfferPrice += itemPrice; // Add to offer total

            total += Double.parseDouble(booking.getQty()) * Double.parseDouble(booking.getPrice());
            // Add details to the table
            vector.add(offerName); // Offer name
            vector.add(String.valueOf(total)); // Price after offer

            model.addRow(vector);

            // Update the total
            itemId++;
        }

        // Update labels
        jLabel45.setText(String.valueOf(total)); // Original total
        jLabel47.setText(String.valueOf(totalOfferPrice)); // Offer-adjusted total
        calculate();
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private double balance = 0;
    private double subtotal = 0;
    private String paymentMethod = "cash";

    private void calculate() {
        try {
            // Parse discount value
            String discountText = jLabel47.getText();
            discount = (discountText.isEmpty() || discountText.equals("0.00")) ? 0 : Double.parseDouble(discountText);

            // Parse payment value
            String paymentText = jFormattedTextField1.getText();
            payment = (paymentText.isEmpty() || paymentText.equals("0.00")) ? 0 : Double.parseDouble(paymentText);

            // Parse total value
            String totalText = jLabel45.getText();
            total = (totalText.isEmpty()) ? 0 : Double.parseDouble(totalText);

            //calculate sub total
            subtotal = total - discount;

            if (subtotal < 0) {
                JOptionPane.showMessageDialog(null, "Subtotal cannot be less than 0.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //Update the subtotal label
            jLabel49.setText(String.format("%.2f", subtotal));

            // Get selected payment method
            paymentMethod = String.valueOf(jComboBox1.getSelectedItem());

            // Handle payment methods
            switch (paymentMethod) {
                case "cash":
                    // Cash payment
                    jFormattedTextField1.setEditable(true);
                    balance = payment - subtotal;

                    break;

                case "card":
                    // Card payment
                    payment = subtotal;
                    balance = 0;
                    jFormattedTextField1.setText(String.format("%.2f", subtotal));

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid payment method.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            // Update balance display
            jLabel65.setText(String.format("%.2f", balance));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please check the entered values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `payement_method`");

            Vector<String> vector = new Vector<>();

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                paymentMethodMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double fetchOfferPriceFromDatabase(String offerName) {
        double offerPrice = 0.0;
        try {
            String query = "SELECT discount_price FROM event_offer WHERE name = ?";
            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                statement.setString(1, offerName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        offerPrice = resultSet.getDouble("discount_price");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerPrice;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel61 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel50.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel50.setText("Customer");

        jLabel51.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel51.setText("Name :");

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("name");

        jLabel53.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel53.setText("Mobile :");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("mobile");

        jLabel55.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel55.setText("Type :");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("type");

        jLabel57.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel57.setText("Email :");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("email");

        jButton10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton10.setText("Select A Customer");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel59.setText("Event");
        jPanel5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel60.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel60.setText("Name :");
        jPanel5.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 35, 66, 30));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("name");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 41, 137, -1));

        jLabel62.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel62.setText("Category :");
        jPanel5.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 71, -1, 30));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("category");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 71, 137, 30));

        jLabel64.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel64.setText("Quantity :");
        jPanel5.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 107, 66, 30));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("qty");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 107, 137, 30));

        jLabel66.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel66.setText("Location :");
        jPanel5.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 143, 66, 30));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("location");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 143, 273, 30));

        jButton11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton11.setText("Select A Event");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 179, 212, -1));

        jLabel77.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel77.setText("Price :");
        jPanel5.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 35, 61, 30));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("price");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 35, 120, 30));

        jLabel79.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel79.setText("Offer :");
        jPanel5.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 71, 61, 30));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("offer");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 71, 120, 30));

        jLabel80.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel80.setText("Id :");
        jPanel5.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 107, 61, 30));

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("id");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 107, 120, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel74.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel74.setText("Order Datails");

        jLabel75.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel75.setText("booking Date :");

        jLabel76.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel76.setText("Quantity :");

        jButton3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton3.setText("Add to Order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/minus.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/plus.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0");

        jButton18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton18.setText("Remove to Order");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel78.setText("ID :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("booking id");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel75)
                        .addGap(6, 6, 6)
                        .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton7)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton17))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton18)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel74)
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Event Name", "Booking date", "Quantity", "Price (Rs)", "Offer (%)", "Total (Rs)"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel44.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel44.setText("Total (Rs) :");

        jLabel45.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("0.00");

        jLabel46.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel46.setText("Discount (Rs) :");

        jLabel47.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("0.00");
        jLabel47.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel47KeyReleased(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel48.setText("Sub Total (Rs) :");

        jLabel49.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("0.00");

        jButton8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton8.setText("Confirm Order");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton9.setText("Cancel Order");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel52.setText("Employee");

        jLabel54.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel54.setText("Email :");

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("name");

        jLabel56.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel56.setText("id :");

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Id");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109))
        );

        jLabel58.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel58.setText("Payment Method :");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("0.00");
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel61.setText("Payement (Rs) :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel63.setText("Balance (Rs) :");

        jLabel65.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox1, 0, 235, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel44))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                        .addGap(14, 14, 14))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        try {
            // Retrieve data from the form
            String Eemail = jLabel12.getText().trim();
            String Cmobile = jLabel2.getText().trim();
            String eventid = jLabel11.getText().trim();
            String bookingid = jLabel16.getText().trim();
            String discount = jLabel47.getText().trim();
            Date bdate = jDateChooser5.getDate();
            String qty = jTextField1.getText().trim();
            String paymethod = String.valueOf(jComboBox1.getSelectedItem()); // Get payment method name
            String paymentMethodId = paymentMethodMap.get(paymethod); // Get payment method ID

            if (paymentMethodId == null) {
                JOptionPane.showMessageDialog(this, "Invalid payment method selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = Double.parseDouble(jLabel49.getText().trim());
            double paidamount = Double.parseDouble(jFormattedTextField1.getText().trim());

            // Validation for booking date
            if (bdate == null) {
                JOptionPane.showMessageDialog(this, "Booking date is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Date Format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String orderDate = sdf.format(new Date()); // Use today's date
            String date = sdf.format(bdate); // Use the selected booking date

            // Validation for other fields
            if (Eemail.isEmpty() || Cmobile.isEmpty() || eventid.isEmpty() || qty.isEmpty() || subtotal <= 0 || paidamount < 0) {
                JOptionPane.showMessageDialog(this, "All required fields must be filled out correctly.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Start database connection and prepare for transaction
            Connection conn = MYSQL.getConnection();
            conn.setAutoCommit(false); // Disable auto-commit for transaction management

            // Insert Booking Event Data
            String bookingEventQuery = "INSERT INTO `booking_event` (`id`, `order_date`, `paid_amount`, `discount`, `sub_total`, `customer_mobile`, `employee_id`, `payement_method_id`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement bookingStmt = conn.prepareStatement(bookingEventQuery)) {
                bookingStmt.setString(1, bookingid);
                bookingStmt.setString(2, orderDate); // Today's date
                bookingStmt.setDouble(3, paidamount);
                bookingStmt.setString(4, discount);
                bookingStmt.setDouble(5, subtotal);
                bookingStmt.setString(6, Cmobile);
                bookingStmt.setInt(7, 1); // Assuming employee ID is 1 for now
                bookingStmt.setString(8, paymentMethodId); // Payment method ID

                bookingStmt.executeUpdate();
            }

            // Insert Booking Event Items
            String bookingItemQuery = "INSERT INTO `booking_eventItem` (`booking_date`, `qty`, `event_id`, `booking_event_id`) "
                    + "VALUES (?, ?, ?, ?)";

            try (PreparedStatement itemStmt = conn.prepareStatement(bookingItemQuery)) {
                for (bookingItem bItem : bookingItemMap.values()) {
                    // Assume bItem.getDate() returns a String that needs to be converted to Date
                    String itemBookingDateStr = bItem.getDate();  // Get the booking date as a String

                    if (itemBookingDateStr == null || itemBookingDateStr.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Booking date for event item cannot be null or empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Parse the booking date string into a Date object using the appropriate format
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Format of the incoming string
                    Date itemBookingDate = null;
                    try {
                        itemBookingDate = inputDateFormat.parse(itemBookingDateStr); // Convert String to Date
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(this, "Invalid booking date format.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Format the Date object back to a String for insertion into the database
                    String itemBookingDateFormatted = sdf.format(itemBookingDate); // Use the desired format (yyyy-MM-dd)

                    itemStmt.setString(1, itemBookingDateFormatted); // Set the correctly formatted date
                    itemStmt.setString(2, bItem.getQty()); // Quantity for the item
                    itemStmt.setString(3, bItem.getEid()); // Event ID
                    itemStmt.setString(4, bookingid); // Booking ID

                    itemStmt.addBatch(); // Add to batch for better performance
                }

                itemStmt.executeBatch(); // Execute all insert operations in one go
            }

            // Commit the transaction if everything is successful
            conn.commit();
            JOptionPane.showMessageDialog(this, "Booking Event successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);

            reset(); // Reset the form after success

        } catch (SQLException e) {
            try {
                MYSQL.getConnection().rollback(); // Rollback transaction in case of failure
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Error occurred during booking process: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                MYSQL.getConnection().setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        reset();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        CustomerView customerView = new CustomerView();
        customerView.setVisible(true);
        customerView.setcustomer(this);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        EventView eventView = new EventView();
        eventView.setVisible(true);
        eventView.setevent(this);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
//        try {
//            // Get the selected row
//            int row = jTable1.getSelectedRow();
//
//            // Check if a row is selected
//            if (row == -1) {
//                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Validation Error", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            // Get the Event ID and Booking Date from the selected row
//            String eventId = jTable1.getValueAt(row, 0).toString(); // Assuming Event ID is in the first column
//            String bookingDate = jTable1.getValueAt(row, 1).toString(); // Assuming Booking Date is in the second column
//
//            // Combine Event ID and Booking Date to form the unique key
//            String uniqueKey = eventId + "-" + bookingDate;
//
//            // Confirm deletion
//            int confirm = JOptionPane.showConfirmDialog(this,
//                    "Are you sure you want to delete this entry?",
//                    "Confirm Deletion",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.WARNING_MESSAGE);
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                // Remove the entry from the bookingItemMap
//                if (bookingItemMap.containsKey(uniqueKey)) {
//                    bookingItemMap.remove(uniqueKey);
//                }
//
//                // Remove the row from the table
//                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//                model.removeRow(row);
//
//                // Manually update the table with the current data in the bookingItemMap
//                model.setRowCount(0); // Clear the table
//
//                loadBookingItems();
//                // Notify the user
//                JOptionPane.showMessageDialog(this, "Entry deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // Get available quantity from JLabel
            int availableQty = Integer.parseInt(jLabel7.getText());

            // Get current quantity from JTextField
            int qty = jTextField1.getText().isEmpty() ? 0 : Integer.parseInt(jTextField1.getText());

            // Increment only if within the limit
            if (qty < availableQty) {
                qty++;
                jTextField1.setText(String.valueOf(qty));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Quantity cannot exceed available quantity: " + availableQty,
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input in quantity field",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("0");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int qty = Integer.parseInt(jTextField1.getText());
            if (qty > 0) { // Prevent going below 0
                qty--;
                jTextField1.setText(String.valueOf(qty));
            } else {
                JOptionPane.showMessageDialog(this, "Quantity cannot be less than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number in quantity field", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("0"); // Reset to default value
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        try {
            String eid = jLabel11.getText(); // Event ID
            String ename = jLabel5.getText(); // Event Name
            String qty = jTextField1.getText(); // Quantity
            String price = jLabel9.getText(); // Price
            String offer = jLabel10.getText(); // Offer
            Date bdate = jDateChooser5.getDate(); // Booking Date

            // Date Format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(bdate); // Format the selected booking date

            // Validate required fields
            if (eid.isEmpty() || ename.isEmpty() || qty.isEmpty() || price.isEmpty() || offer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!qty.matches("\\d+(\\.\\d+)?") || Double.parseDouble(qty) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity greater than 0.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!price.matches("\\d+(\\.\\d+)?") || Double.parseDouble(price) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price greater than 0.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a new booking item
            bookingItem bookingitem = new bookingItem();
            bookingitem.setEid(eid);
            bookingitem.setDate(date);
            bookingitem.setEventname(ename);
            bookingitem.setQty(qty);
            bookingitem.setPrice(price);
            bookingitem.setOffer(offer);

            // Check if the event already exists with the same event ID and booking date
            String uniqueKey = eid + "-" + date; // Combine Event ID and Date as a unique key
            boolean eventExistsWithSameDate = bookingItemMap.containsKey(uniqueKey);

            // If the event exists with the same date, ask user for confirmation to update the quantity
            if (eventExistsWithSameDate) {
                bookingItem found = bookingItemMap.get(uniqueKey);
                int option = JOptionPane.showConfirmDialog(this,
                        "The event \"" + ename + "\" already exists with this date. Do you want to update the quantity?",
                        "Update Quantity",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // If the user clicks "Yes", update the quantity
                if (option == JOptionPane.YES_OPTION) {
                    double updatedQty = Double.parseDouble(found.getQty()) + Double.parseDouble(qty);
                    if (updatedQty <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity cannot be zero or negative after update.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    found.setQty(String.valueOf(updatedQty)); // Update the quantity
                    JOptionPane.showMessageDialog(this, "Quantity updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Quantity update canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    return; // Exit the method if the user clicks "No"
                }
            } else {
                // If event doesn't exist with the same booking date, insert it as a new record
                bookingItemMap.put(uniqueKey, bookingitem);
                JOptionPane.showMessageDialog(this, "Event \"" + ename + "\" added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            loadBookingItems(); // Refresh the table with the updated items

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton12ActionPerformed

    private void jLabel47KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel47KeyReleased
        calculate();
    }//GEN-LAST:event_jLabel47KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased
        calculate();
    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    private void reset() {
        // Reset fields
        jLabel1.setText("name");
        jLabel5.setText("name");
        jLabel12.setText("name");
        jLabel2.setText("mobile");
        jLabel3.setText("type");
        jLabel4.setText("email");
        jLabel6.setText("category");
        jLabel7.setText("qty");
        jLabel8.setText("location");
        jLabel9.setText("price");
        jLabel10.setText("offer");
        jLabel11.setText("id");
        jLabel13.setText("id");
        jDateChooser5.setDate(null);
        jTextField1.setText("0");

        jFormattedTextField1.setText("0.00");
        jLabel45.setText("0.00");
        jLabel47.setText("0.00");
        jLabel49.setText("0.00");
        jLabel65.setText("0.00");

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jComboBox1.setSelectedIndex(0);

        if (jLabel16.equals("booking id")) {
            generateBookingId();
        } else {
            jLabel16.setText("");
            generateBookingId();
        }

        jTable1.clearSelection();
        jTable1.revalidate();
        jTable1.repaint();
        jButton8.setEnabled(true);
        jButton9.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
