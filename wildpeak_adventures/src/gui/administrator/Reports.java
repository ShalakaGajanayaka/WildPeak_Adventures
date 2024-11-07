/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator;

import gui.administrator.report.dailyIncome;

/**
 *
 * @author shalaka
 */
public class Reports extends javax.swing.JPanel {

    /**
     * Creates new form Reports
     */
    public Reports() {
        initComponents();
        
        dailyIncomePanel.add(new dailyIncome());
//        dailyIncomePanel.add(new dailyIncome());
//        dailyIncomePanel.add(new dailyIncome());
//        dailyIncomePanel.add(new dailyIncome());
//        dailyIncomePanel.add(new dailyIncome());
//        dailyIncomePanel.add(new dailyIncome());
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
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dailyIncomePanel = new javax.swing.JPanel();
        monthlyIncomePanle = new javax.swing.JPanel();
        employeesPanel = new javax.swing.JPanel();
        customerPanel = new javax.swing.JPanel();
        stockPanel = new javax.swing.JPanel();
        eventPanel = new javax.swing.JPanel();
        jobRolePanel = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setText("Reports");

        jTabbedPane1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        dailyIncomePanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Daily Income", dailyIncomePanel);

        monthlyIncomePanle.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Monthly Income", monthlyIncomePanle);

        employeesPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Employees", employeesPanel);

        customerPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Customers", customerPanel);

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Stock", stockPanel);

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Event", eventPanel);

        javax.swing.GroupLayout jobRolePanelLayout = new javax.swing.GroupLayout(jobRolePanel);
        jobRolePanel.setLayout(jobRolePanelLayout);
        jobRolePanelLayout.setHorizontalGroup(
            jobRolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
        );
        jobRolePanelLayout.setVerticalGroup(
            jobRolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Job Role", jobRolePanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel dailyIncomePanel;
    private javax.swing.JPanel employeesPanel;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jobRolePanel;
    private javax.swing.JPanel monthlyIncomePanle;
    private javax.swing.JPanel stockPanel;
    // End of variables declaration//GEN-END:variables
}
