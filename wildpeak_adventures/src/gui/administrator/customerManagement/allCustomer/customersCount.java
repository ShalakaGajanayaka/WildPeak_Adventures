/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement.allCustomer;

import gui.administrator.customerManagement.AllCustomers;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import model.MYSQL;

/**
 *
 * @author shalaka
 */
public class customersCount extends javax.swing.JPanel {

    private AllCustomers parent;

    private Color color1 = new Color(46, 125, 50);    // Forest green
    private Color color2 = new Color(129, 199, 132);
    private Color midpointColor;
    private int cornerRadius = 20;

    /**
     * Creates new form customersCount
     */
    public customersCount(AllCustomers parent) {
        this.parent = parent;
        initComponents();
        panelColor();

        getCustomerCount();
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

    public void getCustomerCount() {
        try {
            // Query to get total customer count
            String totalQuery = "SELECT COUNT(*) AS total FROM customer";
            ResultSet totalRs = MYSQL.executeSearch(totalQuery);
            if (totalRs.next()) {
                int totalCustomers = totalRs.getInt("total");
                jLabel7.setText("All : " + String.valueOf(totalCustomers)); // Update jLabel1 with total count
            }

            // Query to get count of 'local' customers
            String localQuery = "SELECT COUNT(*) AS localCount FROM customer WHERE customer_type_id = '2'";
            ResultSet localRs = MYSQL.executeSearch(localQuery);
            if (localRs.next()) {
                int localCustomers = localRs.getInt("localCount");
                jLabel6.setText("Local : " + String.valueOf(localCustomers)); // Update jLabel2 with local count
            }

            // Query to get count of 'foreign' customers
            String foreignQuery = "SELECT COUNT(*) AS foreignCount FROM customer WHERE customer_type_id = '1'";
            ResultSet foreignRs = MYSQL.executeSearch(foreignQuery);
            if (foreignRs.next()) {
                int foreignCustomers = foreignRs.getInt("foreignCount");
                jLabel4.setText("Forign : " + String.valueOf(foreignCustomers)); // Update jLabel3 with foreign count
            }

            // Close result sets
            totalRs.close();
            localRs.close();
            foreignRs.close();
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

        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("All : 5");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Forign : Count");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Local :  Count");

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
