/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator;

import gui.administrator.customerManagement.addCustomer;
import gui.administrator.customerManagement.allCustomers;
import gui.administrator.customerManagement.forignCustomers;
import gui.administrator.customerManagement.localCustomers;

/**
 *
 * @author shalaka
 */
public class CustomerManagement extends javax.swing.JPanel {

    /**
     * Creates new form CustomerManagement
     */
    public CustomerManagement() {
        initComponents();
        
        allCustomersPanel.add(new allCustomers());
        localCustomersPanel.add(new localCustomers());
        forignCustomersPanel.add(new forignCustomers());
        addCustomerPanel.add(new addCustomer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        typeGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        allCustomersPanel = new javax.swing.JPanel();
        localCustomersPanel = new javax.swing.JPanel();
        forignCustomersPanel = new javax.swing.JPanel();
        addCustomerPanel = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setText("Customer Management");

        jTabbedPane1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        allCustomersPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("All Customers", allCustomersPanel);

        localCustomersPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Local Customers", localCustomersPanel);

        forignCustomersPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Forign Customers", forignCustomersPanel);

        addCustomerPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Add/Edit Customer", addCustomerPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addCustomerPanel;
    private javax.swing.JPanel allCustomersPanel;
    private javax.swing.JPanel forignCustomersPanel;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel localCustomersPanel;
    private javax.swing.ButtonGroup typeGroup;
    // End of variables declaration//GEN-END:variables
}
