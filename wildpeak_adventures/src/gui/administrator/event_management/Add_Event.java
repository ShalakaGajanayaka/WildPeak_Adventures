/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.administrator.event_management;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add_Event extends javax.swing.JPanel {

    public Add_Event() {
        initComponents();
        loadTable("id", "ASC", jTextField5.getText());
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
        getEventCount();

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

    private String Categoryid;

    public void setCategoryid(String Categoryid) {
        this.Categoryid = Categoryid;
    }
    private String offerid;

    public void setofferid(String offerid) {
        this.offerid = offerid;
    }
    private String locationid;

    public void setlocationid(String locationid) {
        this.locationid = locationid;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public JTextField getjTextField3() {
        return jTextField3;
    }

    public JTextField getjTextField6() {
        return jTextField6;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("Event Name");

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Category");

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("You can add Or edit Event");

        jButton1.setText("Select Category");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Offer");

        jTextField3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jButton2.setText("Select Offer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Quantity");

        jTextField4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("0");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/minus.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/plus.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Price");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("0.00");
        jFormattedTextField1.setToolTipText("0.00");

        jButton5.setText("Add To Event");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Update To Event");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel26.setText("Location");

        jTextField6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jButton8.setText("Select Locaton");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(4, 4, 4)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setText("Search To Price & Event Name");

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("jLabel1");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    public void loadTable(String column, String orderby, String name) {
        try {
            // Construct the query with parameterized placeholders for the search pattern
            String query = "SELECT event.id, event.name, event_category.name AS category_name, "
                    + "event_offer.name AS offer_name,event.max_participants, event.price, "
                    + "event_location.line_1, event_location.line_2, city.name "
                    + "FROM `event` "
                    + "INNER JOIN `event_category` ON `event`.`event_category_id` = `event_category`.`id` "
                    + "INNER JOIN `event_offer` ON `event`.`event_offer_id` = `event_offer`.`id` "
                    + "INNER JOIN `event_location` ON `event`.`event_location_id` = `event_location`.`id` "
                    + "INNER JOIN `city` ON `event_location`.`city_id` = `city`.`id` "
                    + "WHERE `event`.`name` LIKE ? OR `event`.`price` LIKE ? "
                    + "ORDER BY event." + column + " " + orderby;

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                // Prepare the search pattern to use in the query
                String searchPattern = "%" + name + "%";
                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Get the table model and reset the row count
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0); // Clear existing rows

                // Process the result set and add data to the table
                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    // Add event information
                    vector.add(resultSet.getString("event.id"));
                    vector.add(resultSet.getString("event.name"));
                    vector.add(resultSet.getString("category_name"));
                    vector.add(resultSet.getString("offer_name"));
                    // Add max participants
                    vector.add(resultSet.getString("event.max_participants"));
                    vector.add(resultSet.getString("event.price"));
                    // Combine address fields into a single string
                    String value1 = resultSet.getString("event_location.line_1");
                    String value2 = resultSet.getString("event_location.line_2");
                    String value3 = resultSet.getString("city.name");

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String name = jTextField1.getText();
        String category = jTextField2.getText();
        String offer = jTextField3.getText();
        String qty = jTextField4.getText();
        String price = jFormattedTextField1.getText();
        String desc = jTextArea1.getText();
        String location = jTextField6.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Event Name", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Category Name", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (offer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Offer", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Qty", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            int quantity = Integer.parseInt(qty);
            if (quantity < 1 || quantity > 30) {
                JOptionPane.showMessageDialog(this, "Quantity must be between 1 and 30", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (price.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Please Enter Event Price", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            double eventprice = Double.parseDouble(price);
            if (eventprice < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Event Description", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Event Location", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            try {
                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event` WHERE `name` = '" + name + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Event Already Added.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                } else {
                    MYSQL.executeIUD("INSERT INTO `event` (`name`,`description`,`price`,`max_participants`,`event_category_id`,`event_location_id`,`event_offer_id`)"
                            + "VALUES('" + name + "','" + desc + "','" + price + "','" + qty + "','" + Categoryid + "','" + locationid + "','" + offerid + "')");
                    JOptionPane.showMessageDialog(this, "Event Added Succsesfully", "Succses", JOptionPane.INFORMATION_MESSAGE);
                    loadTable("id", "ASC", jTextField5.getText());
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int qty = Integer.parseInt(jTextField4.getText());
            if (qty > 0) { // Prevent going below 0
                qty--;
                jTextField4.setText(String.valueOf(qty));
            } else {
                JOptionPane.showMessageDialog(this, "Quantity cannot be less than 0", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number in quantity field", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField4.setText("0"); // Reset to default value
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int qty = Integer.parseInt(jTextField4.getText());
            if (qty < 30) {
                qty++;
                jTextField4.setText(String.valueOf(qty));
            } else {
                JOptionPane.showMessageDialog(this, "Quantity cannot be Higher than 30", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid number in quantity field", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField4.setText("30"); // Reset to default value
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Select_Category selcet_Category = new Select_Category();
        selcet_Category.setVisible(true);
        selcet_Category.setevent(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Select_Offer select_Offer = new Select_Offer();
        select_Offer.setVisible(true);
        select_Offer.setevent(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Select_Location select_Location = new Select_Location();
        select_Location.setVisible(true);
        select_Location.setevent(this);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        String text = jTextField5.getText();
        loadTable("id", "ASC", text);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            jButton5.setEnabled(false);
            int row = jTable1.getSelectedRow();

            String name = String.valueOf(jTable1.getValueAt(row, 1));
            jTextField1.setText(name);

            String category = String.valueOf(jTable1.getValueAt(row, 2));
            jTextField2.setText(category);

            String offer = String.valueOf(jTable1.getValueAt(row, 3));
            jTextField3.setText(offer);

            String qty = String.valueOf(jTable1.getValueAt(row, 4));
            jTextField4.setText(qty);

            String price = String.valueOf(jTable1.getValueAt(row, 5));
            jFormattedTextField1.setText(price);

            String location = String.valueOf(jTable1.getValueAt(row, 6));
            jTextField6.setText(location);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private boolean isValidDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Get form inputs
        String id = String.valueOf(jTable1.getValueAt(row, 0));
        String name = jTextField1.getText().trim();
        String category = jTextField2.getText().trim();
        String offer = jTextField3.getText().trim();
        String qty = jTextField4.getText().trim();
        String price = jFormattedTextField1.getText().trim();
        String desc = jTextArea1.getText().trim();
        String location = jTextField6.getText().trim();

        // Input Validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the event name.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a category name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fetch Category ID
        String categoryId = null;
        try {
            PreparedStatement ps = MYSQL.getConnection().prepareStatement("SELECT id FROM event_category WHERE name = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoryId = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Category Name. Please enter a valid category.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error while fetching category: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (Exception ex) {
            Logger.getLogger(Add_Event.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validate Offer
        String offerId = null;
        if (offer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an offer.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            PreparedStatement ps = MYSQL.getConnection().prepareStatement("SELECT id FROM event_offer WHERE name = ?");
            ps.setString(1, offer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                offerId = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Offer. Please enter a valid offer name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error while validating offer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (Exception ex) {
            Logger.getLogger(Add_Event.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Additional Validations
        if (qty.isEmpty() || !qty.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidDouble(price)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate Location Format
        String[] locationParts = location.split(",");
        if (locationParts.length < 3) {
            JOptionPane.showMessageDialog(this, "Location format must be 'line1, line2, city'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String locationId = null;
        try {
            PreparedStatement ps = MYSQL.getConnection().prepareStatement(
                    "SELECT el.id FROM event_location el "
                    + "INNER JOIN city c ON el.city_id = c.id "
                    + "WHERE el.line_1 = ? AND el.line_2 = ? AND c.name = ?");
            ps.setString(1, locationParts[0].trim());
            ps.setString(2, locationParts[1].trim());
            ps.setString(3, locationParts[2].trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                locationId = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid location. Please enter a valid location.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching location ID: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (Exception ex) {
            Logger.getLogger(Add_Event.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an event description.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Validate Quantity and Price
        try {
            int quantity = Integer.parseInt(qty);
            if (quantity < 1 || quantity > 30) {
                JOptionPane.showMessageDialog(this, "Quantity must be between 1 and 30.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            double eventPrice = Double.parseDouble(price);
            if (eventPrice <= 0) {
                JOptionPane.showMessageDialog(this, "Price must be greater than 0.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Check if event exists
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `event` WHERE `id` = '" + id + "'");
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(this, "This event does not exist.", "Information", JOptionPane.INFORMATION_MESSAGE);
                reset();
                return;
            }

            // Update the event in database
            String sql = "UPDATE `event` SET `name` = ?, `description` = ?, `price` = ?, `max_participants` = ?, "
                    + "`event_category_id` = ?, `event_location_id` = ?, `event_offer_id` = ? WHERE `id` = ?";
            try (PreparedStatement pst = MYSQL.getConnection().prepareStatement(sql)) {
                pst.setString(1, name);
                pst.setString(2, desc);
                pst.setDouble(3, eventPrice);
                pst.setInt(4, quantity);
                pst.setString(5, categoryId);
                pst.setString(6, locationId);
                pst.setString(7, offerId);
                pst.setString(8, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Event updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                loadTable("id", "ASC", jTextField5.getText());
                reset();
            } catch (Exception ex) {
                Logger.getLogger(Add_Event.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(Add_Event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("0");
        jTextField5.setText("");
        jFormattedTextField1.setText("0.00");
        jTable1.clearSelection();
        jTextField1.grabFocus();
        jTextField6.setText("");
        jButton5.setEnabled(true);
        jTable1.revalidate();
        jTable1.repaint();

    }
}
