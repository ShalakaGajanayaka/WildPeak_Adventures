/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hotel.gui.dashboard.sidebarminipanels;

import hotel.gui.dashboard.Dashboard;
import hotel.model.HoverPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author shalaka
 */
public class RoomBookPanel extends JPanel {

    private Dashboard parent;

    /**
     * Creates new form RoomBookPanel
     */
    public RoomBookPanel(Dashboard parent) {
        this.parent = parent;
        initComponents();

        hoverPanelStart();

    }

    private void hoverPanelStart() {
        HoverPanel hoverPanel = new HoverPanel();
        hoverPanel.createHoverPanel(btn_bookingList);
        hoverPanel.createHoverPanel(btn_roomCheckout);
        hoverPanel.createHoverPanel(btn_roomStatus);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        btn_bookingList = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btn_roomCheckout = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btn_roomStatus = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_bookingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_bookingListMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel12.setText("Booking List");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_bookingListLayout = new javax.swing.GroupLayout(btn_bookingList);
        btn_bookingList.setLayout(btn_bookingListLayout);
        btn_bookingListLayout.setHorizontalGroup(
            btn_bookingListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_bookingListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_bookingListLayout.setVerticalGroup(
            btn_bookingListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_bookingListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel13.setText("Room Checkout");

        javax.swing.GroupLayout btn_roomCheckoutLayout = new javax.swing.GroupLayout(btn_roomCheckout);
        btn_roomCheckout.setLayout(btn_roomCheckoutLayout);
        btn_roomCheckoutLayout.setHorizontalGroup(
            btn_roomCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_roomCheckoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_roomCheckoutLayout.setVerticalGroup(
            btn_roomCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_roomCheckoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap())
        );

        btn_roomStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_roomStatusMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel14.setText("Room Status");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_roomStatusLayout = new javax.swing.GroupLayout(btn_roomStatus);
        btn_roomStatus.setLayout(btn_roomStatusLayout);
        btn_roomStatusLayout.setHorizontalGroup(
            btn_roomStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_roomStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_roomStatusLayout.setVerticalGroup(
            btn_roomStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_roomStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bookingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_roomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_roomCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_bookingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_roomCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_roomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        parent.roomBookingListPanel(); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void btn_bookingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bookingListMouseClicked
        parent.roomBookingListPanel();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_bookingListMouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        parent.roomStatusPanel();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void btn_roomStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_roomStatusMouseClicked
        parent.roomStatusPanel();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_roomStatusMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_bookingList;
    private javax.swing.JPanel btn_roomCheckout;
    private javax.swing.JPanel btn_roomStatus;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
