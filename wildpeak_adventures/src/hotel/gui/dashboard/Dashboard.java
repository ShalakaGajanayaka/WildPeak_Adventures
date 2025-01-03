/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.gui.dashboard;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import hotel.gui.dashboard.subpanels.DashboardSub;
import hotel.gui.dashboard.subpanels.HotelsSub;
import hotel.gui.dashboard.subpanels.Transaction;
import hotel.gui.dashboard.subpanels.hotelssub.AssignHouseKeeping;
import hotel.gui.dashboard.subpanels.hotelssub.BookingReports;
import hotel.gui.dashboard.subpanels.hotelssub.CabBooking;
import hotel.gui.dashboard.subpanels.hotelssub.CabList;
import hotel.gui.dashboard.subpanels.hotelssub.ItemList;
import hotel.gui.dashboard.subpanels.hotelssub.Personalised;
import hotel.gui.dashboard.subpanels.hotelssub.PromocodeList;
import hotel.gui.dashboard.subpanels.hotelssub.PurchaseReport;
import hotel.gui.dashboard.subpanels.hotelssub.RoomBookingList;
import hotel.gui.dashboard.subpanels.hotelssub.RoomBookingPanel;
import hotel.gui.dashboard.subpanels.hotelssub.RoomFacilitesList;
import hotel.gui.dashboard.subpanels.hotelssub.RoomImages;
import hotel.gui.dashboard.subpanels.hotelssub.RoomList;
import hotel.gui.dashboard.subpanels.hotelssub.RoomStatus;
import hotel.gui.dashboard.subpanels.hotelssub.StockReport;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author shalaka
 */
public class Dashboard extends javax.swing.JFrame {

    private boolean isLightTheme = true; // Track the current theme (Light by default) 

    int startedWidth;
    Boolean startedWidthSet = false;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        pnl_SidebarPanel.add(new Sidbar(this));

        addPlaceholderStyoe(txtSearch);
        pnl_change_panel.add(new DashboardSub());

    }

    public void addPlaceholderStyoe(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.GRAY);
    }

    public void removePlaceholderStyoe(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
    }

    public void dashboardSub() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new DashboardSub());

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void hotelsSub() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new HotelsSub(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void transactionSub() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new Transaction());

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void fillOutBookingDetailsPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomBookingPanel(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void roomListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void roomStatusPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomStatus(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void roomImagesPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomImages(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void roomFacilitesListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomFacilitesList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void assignHouseKeepingPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new AssignHouseKeeping(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void cabBookingPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new CabBooking(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void cabListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new CabList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void itemListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new ItemList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void personalisedPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new Personalised(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void promocodeListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new PromocodeList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void bookingReportsPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new BookingReports(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }

    public void purchaseReportPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new PurchaseReport(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }
    
    
    public void stockReportPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new StockReport(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
    }
    
    
     public void roomBookingListPanel() {
        pnl_change_panel.removeAll();

        pnl_change_panel.add(new RoomBookingList(this));

        pnl_change_panel.revalidate();
        pnl_change_panel.repaint();
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
        pnl_SidebarPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnl_change_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        pnl_SidebarPanel.setBackground(new java.awt.Color(255, 0, 204));
        pnl_SidebarPanel.setLayout(new java.awt.CardLayout());

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/back_icon.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/logo.png"))); // NOI18N

        txtSearch.setText("Search");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/bell_icon.png"))); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/internet_icon.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/sun_icon.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/setting_icon.png"))); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSearch)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jSeparator1, jSeparator2, txtSearch});

        pnl_change_panel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_change_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_change_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_SidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_SidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Define target width based on current size
        if (startedWidthSet == false) {
            startedWidth = pnl_SidebarPanel.getWidth();
            startedWidthSet = true;
        }

        int targetWidth = (pnl_SidebarPanel.getWidth() == 80) ? startedWidth : 80;
        int initialWidth = pnl_SidebarPanel.getWidth();
        int height = pnl_SidebarPanel.getHeight();

        // Create a Timer to handle the animation
        javax.swing.Timer timer = new javax.swing.Timer(1, new java.awt.event.ActionListener() {
            int currentWidth = initialWidth;

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Adjust the width gradually
                if (targetWidth > currentWidth) {
                    currentWidth += 80; // Increase width
                    if (currentWidth >= targetWidth) {
                        currentWidth = targetWidth;
                        ((javax.swing.Timer) e.getSource()).stop(); // Stop animation
                    }
                } else {
                    currentWidth -= 80; // Decrease width
                    if (currentWidth <= targetWidth) {
                        currentWidth = targetWidth;
                        ((javax.swing.Timer) e.getSource()).stop(); // Stop animation
                    }
                }

                // Update the panel size
                pnl_SidebarPanel.setPreferredSize(new java.awt.Dimension(currentWidth, height));
                pnl_SidebarPanel.revalidate();
                pnl_SidebarPanel.repaint();
            }
        });

        timer.start(); // Start the animation
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        if (txtSearch.getText().equals("Search")) {
            txtSearch.setText(null);
            txtSearch.requestFocus();

            removePlaceholderStyoe(txtSearch);
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().length() == 0) {
            addPlaceholderStyoe(txtSearch);
            txtSearch.setText("Search");
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained

    }//GEN-LAST:event_jPanel3FocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
        this.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        this.requestFocusInWindow();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        this.requestFocusInWindow();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try {
            if (isLightTheme) {
                // Switch to FlatMacDarkLaf
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } else {
                // Switch to FlatMacLightLaf
                UIManager.setLookAndFeel(new FlatMacLightLaf());
            }
            isLightTheme = !isLightTheme; // Toggle theme tracker

            // Refresh the UI to apply the new theme
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();
//        FlatMacDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnl_SidebarPanel;
    private javax.swing.JPanel pnl_change_panel;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
