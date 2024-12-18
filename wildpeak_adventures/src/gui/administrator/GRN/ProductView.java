/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.administrator.GRN;

import model.MYSQL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ProductView extends javax.swing.JFrame {

    private Grn grn;

    public void setGrn(Grn grn) {
        this.grn = grn;
    }

    public ProductView() {
        initComponents();
        loadProduct("product`.`id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1150, 610));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Category", "Brand", "Name", "Status"
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1120, 460));

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("SORT BY :");

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRODUCT ID ASENDING", "PRODUCT ID DESENDING", "PRODUCT NAME ASCENDING", "CATEGORY ASENDING", "BRAND ASENDING", "MODEL ASENDING", " ", " ", " " }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setToolTipText("SEARCH PRODUCT");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jComboBox1))
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1120, 110));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadProduct(String coloumn, String orderby, String id, String Category, String Brand, String Model, String Name) {
        try {
            ResultSet resultSet = MYSQL.executeSearch("SELECT * FROM `product` INNER JOIN `category` ON `product`.`Category_id` = `category`.`id`"
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`"
                    + "INNER JOIN `status` ON `product`.`status_id` = `status`.`id` "
                    + "WHERE `product`.`id` LIKE '" + id + "%' OR `product`.`name` LIKE '" + Name + "%' OR `category`.`name` LIKE '" + Category + "%' OR `brand`.`name` LIKE '" + Brand + "%'"
                    + "OR `status`.`name` LIKE '" + Model + "%' ORDER BY `" + coloumn + "`" + orderby + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("category.name"));
                vector.add(resultSet.getString("brand.name"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("status.name"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
//            logger.log(Level.WARNING, "Exception In Product View in loadProduct", e);
        }

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {
            if (grn != null) {
                grn.getjTextField3().setText(String.valueOf(jTable1.getValueAt(row, 0)));
                grn.getjTextField5().setText(String.valueOf(jTable1.getValueAt(row, 1)));
                grn.getjTextField4().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                grn.getjTextField8().setText(String.valueOf(jTable1.getValueAt(row, 3)));
                this.dispose();

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        int sort = jComboBox1.getSelectedIndex();

        if (sort == 0) {
            loadProduct("id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 1) {
            loadProduct("id", "DESC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 2) {
            loadProduct("product`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 3) {
            loadProduct("category`.`Category", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 4) {
            loadProduct("brand`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 5) {
            loadProduct("model`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased

        int sort = jComboBox1.getSelectedIndex();

        if (sort == 0) {
            loadProduct("id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 1) {
            loadProduct("id", "DESC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 2) {
            loadProduct("product`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 3) {
            loadProduct("category`.`Category", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 4) {
            loadProduct("brand`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 5) {
            loadProduct("model`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        }
    }//GEN-LAST:event_jTextField3KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
