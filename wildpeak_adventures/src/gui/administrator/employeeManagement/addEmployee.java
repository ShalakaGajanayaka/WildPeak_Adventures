/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.employeeManagement;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class addEmployee extends javax.swing.JPanel {

    private static HashMap<String, Integer> LoadGenderMap = new HashMap<>();
    private static HashMap<String, Integer> LoadPositionMap = new HashMap<>();
    private static HashMap<String, Integer> LoadStatuMap = new HashMap<>();

    /**
     * Creates new form addEmployee
     */
    public addEmployee() {
        initComponents();
        loadPosition();
        loadStatus();
        loadGender();
        loadEmployee("id", "ASC", jTextField2.getText(), jTextField2.getText(), jTextField2.getText(), jTextField2.getText());
        jTable2.revalidate();
        jTable2.repaint();
        reset();
        getEmployeeCount();
        setupRightClickMenu();
    }
//

    private void setupRightClickMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewAddressItem = new JMenuItem("View Address");
        viewAddressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row's data when right-click menu option is selected
                int row = jTable2.getSelectedRow();
                if (row != -1) {
                    String nic = String.valueOf(jTable2.getValueAt(row, 3)); // Assuming NIC is in the 4th column
                    String userId = String.valueOf(jTable2.getValueAt(row, 0)); // Assuming user ID is in the 1st column

                    Frame EmployeeManagement = null;
                    JDialog addressView = new AddressView(EmployeeManagement, true, userId, nic);
                    addressView.setVisible(true);
                }
            }
        });
        popupMenu.add(viewAddressItem);

        // Add mouse listener to display popup menu on right-click
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable2.rowAtPoint(e.getPoint());
                    jTable2.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable2.rowAtPoint(e.getPoint());
                    jTable2.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }
        });
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

    private void loadEmployee(String column, String orderby, String fname, String lname, String nic, String mobile) {
        try {
            String query = "SELECT * FROM employee "
                    + "INNER JOIN job_position ON employee.job_position_id = job_position.id "
                    + "INNER JOIN gender ON employee.gender_id = gender.id "
                    + "INNER JOIN status ON employee.status_id = status.id "
                    + "WHERE employee.fname LIKE ? "
                    + "OR employee.lname LIKE ? "
                    + "OR employee.nic LIKE ? "
                    + "OR employee.mobile LIKE ? "
                    + "OR job_position.name LIKE ? "
                    + "ORDER BY employee." + column + " " + orderby;

            try (PreparedStatement statement = MYSQL.getConnection().prepareStatement(query)) {
                String searchPattern = "%" + fname + "%";

                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);
                statement.setString(3, searchPattern);
                statement.setString(4, searchPattern);
                statement.setString(5, searchPattern);

                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
                defaultTableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("employee.id"));
                    vector.add(resultSet.getString("employee.fname") + " " + resultSet.getString("employee.lname"));
                    vector.add(resultSet.getString("employee.email"));
                    vector.add(resultSet.getString("employee.nic"));
                    vector.add(resultSet.getString("employee.mobile"));
                    vector.add(resultSet.getString("job_position.name"));

                    defaultTableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPosition() {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `job_position`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Job Role");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoadPositionMap.put(resultSet.getString("name"), resultSet.getInt("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void loadStatus() {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Status");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoadStatuMap.put(resultSet.getString("name"), resultSet.getInt("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void loadGender() {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Gender");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoadGenderMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Search");

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Name", "Email", "Nic", "Mobile", "Job Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("You can add Or edit Employee");

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("First Name");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Last Name");

        jTextField5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Mobile");

        jTextField7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jTextField8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Email");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Role");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Gender");

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

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Job Role", " " }));

        jButton4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton4.setText("View Job Role");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel29.setText("NIC");

        jTextField12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel30.setText("Password");

        jTextField13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel31.setText("Status");

        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status" }));

        jComboBox3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 16, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("2345");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
// <<<<<<< HEAD
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
// =======
//                             .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
//                         .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                 .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
// >>>>>>> chamod-event
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String searchtext = jTextField2.getText();
        loadEmployee("id", "ASC", searchtext, searchtext, searchtext, searchtext);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        try {
            jButton1.setEnabled(false);
            int row = jTable2.getSelectedRow();
            String email = String.valueOf(jTable2.getValueAt(row, 2));  // assuming column 0 is the user ID

            // Query database using UserId to retrieve additional details
            String query = "SELECT * FROM employee "
            + "INNER JOIN job_position ON employee.job_position_id = job_position.id "
            + "INNER JOIN gender ON employee.gender_id = gender.id "
            + "INNER JOIN status ON employee.status_id = status.id "
            + "WHERE employee.email = ?";

            // Assuming you have a method for database connection (connection)
            PreparedStatement pst = MYSQL.getConnection().prepareStatement(query);
            pst.setString(1, email);  // Sets the 'email' as a parameter in the WHERE clause
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Set text fields with database values
                jTextField5.setText(rs.getString("fname"));
                jTextField6.setText(rs.getString("lname"));
                jTextField8.setText(rs.getString("email"));
                jTextField7.setText(rs.getString("mobile"));
                jTextField12.setText(rs.getString("nic"));
                jTextField12.setEditable(false);
                jTextField13.setText(rs.getString("password"));
                jComboBox1.setSelectedItem(rs.getString("job_position.name"));
                jComboBox2.setSelectedItem(rs.getString("status.name"));
                jComboBox3.setSelectedItem(rs.getString("gender.name"));
            }
            rs.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        JDialog View_jobrole = null;
        Frame EmployeeManagement = null;
        View_jobrole = new View_jobRole(EmployeeManagement, true);
        View_jobrole.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String fname = jTextField5.getText();
        String lname = jTextField6.getText();
        String email = jTextField8.getText();
        String mobile = jTextField7.getText();
        String nic = jTextField12.getText();
        String password = jTextField13.getText();
        String role = String.valueOf(jComboBox1.getSelectedItem());
        String gender = String.valueOf(jComboBox3.getSelectedItem());
        String status = String.valueOf(jComboBox2.getSelectedItem());

        // Input validation
        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter First Name", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Last Name", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Email Address", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Email Address.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Mobile Number", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! Please Enter Valid Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter National ID Number", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Password", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")) {
            JOptionPane.showMessageDialog(this, "Please Enter Strong Password", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (role.equals("Select Job Role")) {
            JOptionPane.showMessageDialog(this, "Please Select Job Position", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(this, "Please Select Gender", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            boolean update = false;

            try {
                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM employee WHERE mobile = '" + mobile + "' AND email = '" + email + "'");
                if (resultSet.next()) {

                    if (!resultSet.getString("nic").equals(nic)) {
                        JOptionPane.showMessageDialog(this, "This Mobile number or Email already used", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        update = true;
                    }

                } else {
                    update = true;
                }
                if (update) {
                    MYSQL.executeIUD("UPDATE employee SET fname = '" + fname + "',lname = '" + lname + "',email = '" + email + "' , nic = '" + nic + "',mobile = '" + mobile + "',Status_id = '" + LoadStatuMap.get(status) + "',"
                        + "job_position_id = '" + LoadPositionMap.get(role) + "',password = '" + password + "',"
                        + "gender_id = '" + LoadGenderMap.get(gender) + "'"
                        + "WHERE nic = '" + nic + "'");
                    JOptionPane.showMessageDialog(this, "Employee is Updated Succsessfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadEmployee("id", "ASC", jTextField2.getText(), jTextField2.getText(), jTextField2.getText(), jTextField2.getText());
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String fname = jTextField5.getText();
        String lname = jTextField6.getText();
        String email = jTextField8.getText();
        String mobile = jTextField7.getText();
        String nic = jTextField12.getText();
        String password = jTextField13.getText();
        String role = String.valueOf(jComboBox1.getSelectedItem());
        String gender = String.valueOf(jComboBox3.getSelectedItem());
        String status = String.valueOf(jComboBox2.getSelectedItem());

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter First Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Last Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Email Address", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Email Address.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Mobile Number", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! Please Enter Valid Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter National ID Number", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Password", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")) {
            JOptionPane.showMessageDialog(this, "Please Enter Strong Password", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (role.equals("Select Job Role")) {
            JOptionPane.showMessageDialog(this, "Please Select Job Position", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(this, "Please Select Gender", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `employee` WHERE `nic` = '" + nic + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Employee Already Exists in Database", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:");

                    // Debugging print statements
                    System.out.println("Status ID: " + LoadStatuMap.get(status));
                    System.out.println("Role ID: " + LoadPositionMap.get(role));
                    System.out.println("Gender ID: " + LoadGenderMap.get(gender));

                    // Insert command with proper comma separation
                    String insertQuery = "INSERT INTO `employee` (`fname`, `lname`, `email`, `nic`, `mobile`, `register_date`, "
                    + "`status_id`, `job_position_id`, `password`, `gender_id`) "
                    + "VALUES ('" + fname + "', '" + lname + "', '" + email + "', '" + nic + "', '" + mobile + "', '"
                    + sdf.format(date) + "', '" + LoadStatuMap.get(status) + "', '" + LoadPositionMap.get(role) + "', '"
                    + password + "', '" + LoadGenderMap.get(gender) + "')";

                    System.out.println("Insert Query: " + insertQuery);  // Debug: Print the final SQL insert query

                    MYSQL.executeIUD(insertQuery);

                    JOptionPane.showMessageDialog(this, "New Employee Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadEmployee("id", "ASC", jTextField2.getText(), jTextField2.getText(), jTextField2.getText(), jTextField2.getText());
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Uncomment for logging if needed
                // logger.log(Level.WARNING, "Exception in Employee Management in create button", e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    public void reset() {
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField8.setText("");
        jTextField7.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jTextField5.grabFocus();
        jTextField12.setEditable(true);
        jTable2.clearSelection();
        jTextField2.setText("");
        jButton1.setEnabled(true);
        loadEmployee("id", "ASC", jTextField2.getText(), jTextField2.getText(), jTextField2.getText(), jTextField2.getText());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
