/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.administrator.BookingManagemnt;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.MYSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.bookingItem;

/**
 *
 * @author USER
 */
public class ManageBooking extends javax.swing.JPanel {

    HashMap<String, String> paymentMethodMap = new HashMap<>();

    /**
     * Creates new form ManageBooking
     */
    public ManageBooking() {
        initComponents();
        loadBookingEvent("id", "ASC", jTextField1.getText());
        // Add a listener to update the description area when a row is selected
        jTable1.getSelectionModel()
                .addListSelectionListener(event -> {
                    if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                        int selectedRow = jTable1.getSelectedRow();
                        String bookingeventid = (String) jTable1.getValueAt(selectedRow, 0); // Assuming ID is in the first column
                        loadTable2(bookingeventid);
                    }
                });
        loadPaymentMethods();
        reset();
        jDateChooser1.getDateEditor().setEnabled(false);
        jDateChooser2.getDateEditor().setEnabled(false);
    }
//customer

    public JTextField getJTextField5() {
        return jTextField5;
    }

//        event
    public JTextField getJTextField6() {
        return jTextField6;
    }

//        event qty
    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void loadBookingEvent(String column, String orderby, String Text) {
        // Validate column and orderby parameters
        List<String> validColumns = Arrays.asList("id", "order_date", "customer_mobile");
        List<String> validOrderBy = Arrays.asList("ASC", "DESC");

        if (!validColumns.contains(column) || !validOrderBy.contains(orderby.toUpperCase())) {
            throw new IllegalArgumentException("Invalid column or orderby parameter");
        }

        String query = "SELECT DISTINCT `booking_event`.`id`, "
                + "`customer`.`fname`, `customer`.`lname`, `customer`.`mobile`, `customer`.`email`, "
                + "`customer_type`.`name` AS `customer_type_name`, `booking_event`.`order_date`, "
                + "`payement_method`.`name` AS `payment_method_name`, "
                + "`employee`.`fname` AS `employee_fname`, `employee`.`lname` AS `employee_lname` "
                + "FROM `booking_event` "
                + "INNER JOIN `customer` ON `customer`.`mobile` = `booking_event`.`customer_mobile` "
                + "INNER JOIN `employee` ON `employee`.`id` = `booking_event`.`employee_id` "
                + "INNER JOIN `payement_method` ON `payement_method`.`id` = `booking_event`.`payement_method_id` "
                + "INNER JOIN `customer_type` ON `customer_type`.`id` = `customer`.`customer_type_id` "
                + "WHERE `booking_event`.`id` LIKE ? OR `booking_event`.`order_date` LIKE ? "
                + "OR `customer`.`mobile` LIKE ? OR `customer`.`fname` LIKE ? "
                + "OR `customer`.`lname` LIKE ? OR `customer`.`email` LIKE ? "
                + "OR `customer_type`.`name` LIKE ? OR `employee`.`fname` LIKE ? OR `employee`.`lname` LIKE ? "
                + "ORDER BY " + column + " " + orderby;

        try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
            String searchPattern = "%" + Text + "%";

            // Set parameters for the query
            for (int i = 1; i <= 9; i++) {
                statement.setString(i, searchPattern);
            }

            ResultSet resultSet = statement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing rows

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("customer_type_name"));
                vector.add(resultSet.getString("order_date"));
                vector.add(resultSet.getString("payment_method_name"));
                vector.add(resultSet.getString("employee_fname") + " " + resultSet.getString("employee_lname"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTable2(String bookingeventid) {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `booking_eventItem` "
                    + "INNER JOIN `event` ON `booking_eventItem`.`event_id` = `event`.`id` "
                    + "INNER JOIN `event_offer` ON `event_offer`.`id` = `event`.`event_offer_id` "
                    + "INNER JOIN `booking_event` ON `booking_event`.`id` = `booking_eventItem`.`booking_event_id` "
                    + "WHERE booking_event_id = '" + bookingeventid + "'");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0); // Clear existing rows

            int itemId = 1; // Start counter from 1 or any desired number

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                String eventName = resultSet.getString("event.name");
                String bookingDate = resultSet.getString("booking_date");
                int qty = resultSet.getInt("booking_eventItem.qty");
                double price = resultSet.getDouble("event.price");
                double discountPrice = resultSet.getDouble("event_offer.discount_price");

                // Calculate the total for the current row
                double effectivePrice = price - discountPrice;
                double total = effectivePrice * qty;

                // Add data to the vector
                vector.add(String.valueOf(itemId)); // Manually incrementing ID
                vector.add(eventName);
                vector.add(bookingDate);
                vector.add(String.valueOf(qty));
                vector.add(String.format("%.2f", price));
                vector.add(String.format("%.2f", discountPrice));
                vector.add(String.format("%.2f", total));

                // Add the vector to the table model
                model.addRow(vector);

                itemId++; // Increment the counter
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in loading booking details.");
        }
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private double paid_amount = 0;
    private double balance = 0;
    private double subtotal = 0;
    private double amountToPay = 0;
    private String paymentMethod = "cash";

    private void calculate() {
        // Access the table model
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        double total = 0.0;
        double totalDiscount = 0.0;

        // Column indexes for "Total (Rs)", "Discount (%)", and "Quantity"
        int totalColumnIndex = 6;     // Assuming "Total (Rs)" is the 7th column (index 6)
        int discountColumnIndex = 5;  // Assuming "Discount (%)" is the 6th column (index 5)
        int quantityColumnIndex = 3;  // Assuming "Quantity" is the 4th column (index 3)

        // Iterate through rows
        for (int i = 0; i < model.getRowCount(); i++) {
            Object totalValue = model.getValueAt(i, totalColumnIndex);
            Object discountValue = model.getValueAt(i, discountColumnIndex);
            Object quantityValue = model.getValueAt(i, quantityColumnIndex);

            try {
                // Parse and add total value
                if (totalValue != null) {
                    total += Double.parseDouble(totalValue.toString());
                }

                // Parse discount and quantity values (if applicable)
                if (discountValue != null && quantityValue != null) {
                    double discount = Double.parseDouble(discountValue.toString());
                    int quantity = Integer.parseInt(quantityValue.toString());
                    totalDiscount += discount * quantity; // Calculate discount based on quantity
                }
            } catch (NumberFormatException e) {
                // Handle invalid data gracefully
                System.err.println("Invalid number format at row " + i
                        + ": Total = " + totalValue
                        + ", Discount = " + discountValue
                        + ", Quantity = " + quantityValue);
            }
        }

        // Update UI with total and total discount
        jLabel29.setText(String.format("%.2f", total)); // Total
        jLabel30.setText(String.format("%.2f", totalDiscount)); // Total Discount

        try {
            // Parse payment and discount values
            String discountText = jLabel30.getText();
            double discount = (discountText.isEmpty()) ? 0 : Double.parseDouble(discountText);

            String totalText = jLabel29.getText();
            total = (totalText.isEmpty()) ? 0 : Double.parseDouble(totalText);

            String paidText = jLabel40.getText();
            double paidAmount = (paidText.isEmpty()) ? 0 : Double.parseDouble(paidText);

            String paymentText = jFormattedTextField1.getText();
            double payment = (paymentText.isEmpty() || paymentText.equals("0.00")) ? 0 : Double.parseDouble(paymentText);

            // Calculate subtotal and amount to pay
            double subtotal = total - discount;
            double amountToPay = subtotal - paidAmount;

            if (subtotal < 0) {
                JOptionPane.showMessageDialog(null, "Subtotal cannot be less than 0.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Handle overpayment (negative balance)
            double balance;
            if (amountToPay < 0) {
                balance = amountToPay; // Negative balance indicates overpayment
                jLabel34.setForeground(Color.RED); // Highlight in red
                jFormattedTextField1.setText(""); // Clear payment field
                jComboBox1.setSelectedItem("cash"); // Set payment method to cash
            } else {
                balance = 0;
                jLabel34.setForeground(Color.BLACK);
            }

            // Update subtotal and amount to pay labels
            jLabel31.setText(String.format("%.2f", subtotal)); // Subtotal
            jLabel42.setText(String.format("%.2f", amountToPay)); // Amount to Pay

            // Handle payment methods
            String paymentMethod = String.valueOf(jComboBox1.getSelectedItem());
            switch (paymentMethod) {
                case "cash":
                    jFormattedTextField1.setEditable(true);
                    balance = payment - amountToPay;
                    jButton8.setEnabled(balance >= 0); // Enable confirm button only if balance is valid
                    break;

                case "card":
                    if (amountToPay < 0) {
                        JOptionPane.showMessageDialog(null, "Cannot use card payment with negative balance.", "Warning", JOptionPane.WARNING_MESSAGE);
                        jComboBox1.setSelectedItem("cash"); // Force payment method to cash
                        return;
                    }
                    payment = amountToPay;
                    balance = 0;
                    jFormattedTextField1.setText(String.format("%.2f", amountToPay));
                    jFormattedTextField1.setEditable(false);
                    jButton8.setEnabled(true); // Confirm button enabled
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid payment method.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            // Update balance label
            jLabel34.setText(String.format("%.2f", balance));

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("Customer :");

        jTextField5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton1.setText("Select Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Event :");

        jTextField6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton2.setText("Select Event");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Booking Date :");

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Quantity :");

        jTextField7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton7.setText("Remove");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel24.setText("Edit Order");

        jLabel25.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel25.setText("Order Confirmation");

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel26.setText("Total (Rs) :");

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel27.setText("Discount (Rs):");

        jLabel28.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel28.setText("Sub Total (Rs) :");

        jLabel29.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("0.00");

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("0.00");

        jLabel31.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("0.00");

        jLabel32.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel32.setText("Payment (Rs) :");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("0.00");

        jLabel33.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel33.setText("Balance (Rs) :");

        jLabel34.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("0.00");

        jButton8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton8.setText("Confirm Order");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel58.setText("Payment Method :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Invoice Id :");

        jTextField8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel39.setText("Paid Amount (Rs) :");

        jLabel40.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("0.00");

        jLabel42.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("0.00");

        jLabel41.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Amount to pay (Rs) :");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel28)
                                .addComponent(jLabel26))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(jLabel33)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField5))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(7, 7, 7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking number", "Customer Name", "Customer Mobile", "Customer Email", "Customer Type", "Order Date", "Payement Method", "Employee Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Event Name", "Booking Date", "Quantity", "Price (Rs)", "Offer (%)", "Total (Rs)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel37.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel37.setText("Order Details");

        jLabel38.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel38.setText("Search :");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel37)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jButton9)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel35.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel35.setText("Pending Orders");

        jLabel36.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel36.setText("You Can View Order by Clicking Pending Oder");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextField8.setText("");
        jTextField8.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);
        jDateChooser1.setEnabled(false);
        jTextField6.setText("");
        jButton1.setEnabled(false);
        jTextField6.setText("");
        jLabel1.setText("0");
        jButton2.setEnabled(false);
        jDateChooser1.setDate(null);
        jTextField7.setText("0");
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jLabel29.setText("0.00");
        jLabel30.setText("0.00");
        jLabel40.setText("0.00");
        jLabel31.setText("0.00");
        jLabel42.setText("0.00");
        jComboBox1.setSelectedItem("cash");
        jFormattedTextField1.setText("0.00");
        jLabel34.setText("0.00");
        jButton8.setEnabled(false);
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTextField1.setText("");
        jDateChooser2.setDate(null);
        loadBookingEvent("id", "ASC", jTextField1.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CustomerView customerView = new CustomerView();
        customerView.setVisible(true);
        customerView.setManageCustomer(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EventView eventView = new EventView();
        eventView.setVisible(true);
        eventView.setManageEvent(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int qty = Integer.parseInt(jTextField7.getText());
            if (qty > 0) { // Prevent going below 0
                qty--;
                jTextField7.setText(String.valueOf(qty));
            } else {
                JOptionPane.showMessageDialog(this, "Quantity cannot be less than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number in quantity field", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField7.setText("0"); // Reset to default value
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            int availableQty = Integer.parseInt(jLabel1.getText());

            int qty = jTextField7.getText().isEmpty() ? 0 : Integer.parseInt(jTextField7.getText());
            if (qty < availableQty) {
                qty++;
                jTextField7.setText(String.valueOf(qty));
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
            jTextField7.setText("0");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        // Constants for column indexes
        final int CUSTOMER_NAME_COL = 1;
        final int INVOICE_NUMBER_COL = 0;
        final int EVENT_NAME_COL = 1;
        final int BOOKING_DATE_COL = 2;
        final int QUANTITY_COL = 3;

        // Get selected rows
        int selectedRow1 = jTable1.getSelectedRow();
        int selectedRow2 = jTable2.getSelectedRow();

        // Update customer details from jTable1
        if (selectedRow1 >= 0) {
            String customerName = String.valueOf(jTable1.getValueAt(selectedRow1, CUSTOMER_NAME_COL));
            jTextField5.setText(customerName);

            String invoiceNumber = String.valueOf(jTable1.getValueAt(selectedRow1, INVOICE_NUMBER_COL));
            jTextField8.setText(invoiceNumber);
        }

        // Update event details from jTable2
        if (selectedRow2 >= 0) {
            String eventName = String.valueOf(jTable2.getValueAt(selectedRow2, EVENT_NAME_COL));
            jTextField6.setText(eventName);

            // Fetch the quantity for the selected event
            String query = "SELECT max_participants FROM event WHERE name = ?";
            try (PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query)) {
                stmt.setString(1, eventName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int qty = rs.getInt("max_participants");
                        jLabel1.setText(String.valueOf(qty)); // Display quantity in label
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Set booking date
            String bookingDateStr = String.valueOf(jTable2.getValueAt(selectedRow2, BOOKING_DATE_COL));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date bookingDate = sdf.parse(bookingDateStr);
                jDateChooser1.setDate(bookingDate);
            } catch (ParseException e) {
                Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, e);
            }

            // Set quantity
            String quantity = String.valueOf(jTable2.getValueAt(selectedRow2, QUANTITY_COL));
            jTextField7.setText(quantity);
        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void fetchBookingDetails(String bookingId) {
        String query = "SELECT booking_event.paid_amount, booking_event.discount, booking_event.sub_total "
                + "FROM booking_event "
                + "WHERE booking_event.id = ?";

        try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
            statement.setString(1, bookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Set all required labels
                    jLabel40.setText(resultSet.getString("paid_amount"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with logging in production
        }
    }

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // Ensure all components are updated before calculating
        SwingUtilities.invokeLater(() -> {
            calculate();
        });
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        // Retrieve Invoice ID and ensure it is not modified
        String bookingId = jTextField8.getText().trim(); // Invoice ID field
        if (bookingId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invoice ID cannot be empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Retrieve Customer Name
        String customer = jTextField5.getText().trim();
        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer name cannot be empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Retrieve Event Name and Event ID
        String eventName = jTextField6.getText().trim();
        if (eventName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Event name cannot be empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Retrieve Event ID from the database (implement this method)
        int eventId = getEventIdByName(eventName);  // Assuming this method fetches event ID based on event name

        // Retrieve Booking Date
        java.util.Date selectedDate = jDateChooser1.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Booking date cannot be empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String bookingdate = sdf.format(selectedDate);

        // Retrieve Quantity
        String qty = jTextField7.getText().trim();
        if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(qty);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask for confirmation before updating the quantity
        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to update the quantity for booking ID \"" + bookingId + "\" and event \"" + eventName + "\"?",
                "Update Quantity",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            // Logic for updating data or processing the booking
            String query = "UPDATE booking_eventItem SET qty = ?, booking_date = ? WHERE booking_event_id = ? AND event_id = ?";
            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(qty)); // Quantity
                statement.setString(2, bookingdate); // Booking Date
                statement.setString(3, bookingId); // Booking ID
                statement.setInt(4, eventId); // Event ID (numeric)

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Booking details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTable2(bookingId); // Refresh the table with the updated data
                    calculate(); // Recalculate after the quantity is updated
                } else {
                    JOptionPane.showMessageDialog(this, "No changes were made. Please check the entered data.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Quantity update canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    // Method to get event ID by event name (you may need to modify this based on your database structure)
    private int getEventIdByName(String eventName) {
        int eventId = -1;
        String query = "SELECT id FROM event WHERE name = ?";
        try (PreparedStatement stmt = MYSQL.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                eventId = rs.getInt("id");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventId;
    }
    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            // Fetch and display booking event details
            String bookingId = String.valueOf(jTable1.getValueAt(row, 0));
            fetchBookingDetails(bookingId);

            // Ensure all components are updated before calculating
            SwingUtilities.invokeLater(() -> {
                calculate();
            });

            jTextField8.setEditable(true);
            jTextField5.setEditable(true);
            jTextField6.setEditable(true);
            jTextField7.setEditable(true);
            jDateChooser1.setEnabled(true);
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            jComboBox1.setSelectedItem("cash");
            jButton8.setEnabled(true);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // Get the invoice ID
            String invoiceid = jTextField8.getText();
            int row = jTable2.getSelectedRow();

            if (row < 0) {
                System.out.println("No row selected. Please select a row to delete.");
                return;
            }

            // Retrieve the event name from the selected row (assume event name is in column 1)
            String eventName = jTable2.getValueAt(row, 1).toString(); // Adjust column index if necessary

            // Log the event name for debugging
            System.out.println("Event name to delete: " + eventName);

            // Check if the invoice exists in the database
            ResultSet rs = MYSQL.executeSearch("SELECT * FROM `booking_eventItem` WHERE `booking_event_id` = '" + invoiceid + "'");

            if (rs.next()) {
                // Retrieve the event ID from the event table based on the event name
                ResultSet rs1 = MYSQL.executeSearch("SELECT id FROM `event` WHERE `name` = '" + eventName + "'");

                if (rs1.next()) {
                    // Get the event ID
                    String eventId = rs1.getString("id");

                    // Construct the SQL DELETE query using the event ID
                    String query = "DELETE FROM `booking_eventItem` WHERE `event_id` = '" + eventId + "'";

                    // Log the query for debugging
                    System.out.println("Executing query: " + query);

                    // Execute the query
                    MYSQL.executeIUD(query);

                    System.out.println("Record deleted successfully!");
                } else {
                    System.out.println("No event found with the name: " + eventName);
                }
            } else {
                System.out.println("No records found for invoice ID: " + invoiceid);
            }

        } catch (Exception e) {
            System.err.println("Error while deleting record:");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Connection conn = null;
        try {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row in the table.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Retrieve data
            String bookingid = String.valueOf(jTable1.getValueAt(row, 0));
            String paymethod = String.valueOf(jComboBox1.getSelectedItem());
            String paymentMethodId = paymentMethodMap.get(paymethod);

            if (paymentMethodId == null) {
                JOptionPane.showMessageDialog(this, "Invalid payment method selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = Double.parseDouble(jLabel31.getText().trim());
            double discount = Double.parseDouble(jLabel30.getText().trim());
            double paidamount = Double.parseDouble(jLabel31.getText().trim());

            if (subtotal <= 0 || paidamount < 0) {
                JOptionPane.showMessageDialog(this, "Invalid subtotal or paid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn = MYSQL.getConnection();
            conn.setAutoCommit(false);

            String bookingEventQuery = "UPDATE `booking_event` SET "
                    + "`paid_amount` = ?, `discount` = ?, `sub_total` = ?, `employee_id` = ?, `payement_method_id` = ? "
                    + "WHERE `id` = ?";

            try (PreparedStatement bookingStmt = conn.prepareStatement(bookingEventQuery)) {
                bookingStmt.setDouble(1, paidamount);
                bookingStmt.setDouble(2, discount); // Ensure discount is initialized
                bookingStmt.setDouble(3, subtotal);
                bookingStmt.setInt(4, getLoggedEmployeeId()); // Replace with method to fetch the current employee ID
                bookingStmt.setString(5, paymentMethodId);
                bookingStmt.setString(6, bookingid); // Specify the row to update

                bookingStmt.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Booking Event successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            reset(); // Reset the form
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this, "Error during booking process: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Date selectedDate = jDateChooser2.getDate();
        if (selectedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(selectedDate);

            String query = "SELECT DISTINCT `booking_event`.`id`, "
                    + "`customer`.`fname`, `customer`.`lname`, `customer`.`mobile`, `customer`.`email`, "
                    + "`customer_type`.`name` AS `customer_type_name`, `booking_event`.`order_date`, "
                    + "`payement_method`.`name` AS `payment_method_name`, "
                    + "`employee`.`fname` AS `employee_fname`, `employee`.`lname` AS `employee_lname` "
                    + "FROM `booking_event` "
                    + "INNER JOIN `customer` ON `customer`.`mobile` = `booking_event`.`customer_mobile` "
                    + "INNER JOIN `employee` ON `employee`.`id` = `booking_event`.`employee_id` "
                    + "INNER JOIN `payement_method` ON `payement_method`.`id` = `booking_event`.`payement_method_id` "
                    + "INNER JOIN `customer_type` ON `customer_type`.`id` = `customer`.`customer_type_id` "
                    + "WHERE `booking_event`.`order_date` = ? "
                    + "ORDER BY `booking_event`.`id` ASC";

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                // Set the date parameter
                statement.setString(1, dateString);

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0); // Clear existing rows

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                    vector.add(resultSet.getString("mobile"));
                    vector.add(resultSet.getString("email"));
                    vector.add(resultSet.getString("customer_type_name"));
                    vector.add(resultSet.getString("order_date"));
                    vector.add(resultSet.getString("payment_method_name"));
                    vector.add(resultSet.getString("employee_fname") + " " + resultSet.getString("employee_lname"));

                    model.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a date.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String searchtext = jTextField1.getText();
        loadBookingEvent("id", "ASC", searchtext);
    }//GEN-LAST:event_jTextField1KeyReleased

    private int getLoggedEmployeeId() {
        // Replace with actual logic to retrieve the current employee's ID
        return 1;
    }

    public void reset() {
        jTextField8.setText("");
        jTextField8.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);
        jDateChooser1.setEnabled(false);
        jTextField6.setText("");
        jButton1.setEnabled(false);
        jTextField6.setText("");
        jLabel1.setText("0");
        jButton2.setEnabled(false);
        jDateChooser1.setDate(null);
        jTextField7.setText("0");
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jLabel29.setText("0.00");
        jLabel30.setText("0.00");
        jLabel40.setText("0.00");
        jLabel31.setText("0.00");
        jLabel42.setText("0.00");
        jComboBox1.setSelectedItem("cash");
        jFormattedTextField1.setText("0.00");
        jLabel34.setText("0.00");
        jButton8.setEnabled(false);
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTextField1.setText("");
        jDateChooser2.setDate(null);
        loadBookingEvent("id", "ASC", jTextField1.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
