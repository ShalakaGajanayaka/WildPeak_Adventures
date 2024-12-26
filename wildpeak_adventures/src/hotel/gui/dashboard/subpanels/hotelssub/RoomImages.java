/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hotel.gui.dashboard.subpanels.hotelssub;

import hotel.gui.dashboard.Dashboard;
import hotel.model.MYSQL2;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shalaka
 */
public class RoomImages extends javax.swing.JPanel {

    private Dashboard parent;

    /**
     * Creates new form RoomList
     */
    public RoomImages(Dashboard parent) {
        initComponents();
        this.parent = parent;

//        roomImages();
        refreshImages();
//        roomImages("ri.", "ASC", jTextField2.getText());
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
    }

//    public void roomImages() {
//        try {
//            // SQL query to get the latest image for each room, ordered by Room_List_No in ascending order
//            String query = "SELECT ri.Room_List_No, rt.name AS Room_Type, ri.image "
//                    + "FROM room_images ri "
//                    + "INNER JOIN Room_List rl ON ri.Room_List_No = rl.No "
//                    + "INNER JOIN Room_Type rt ON rl.Room_Type_id = rt.id "
//                    + "WHERE ri.id IN ( "
//                    + "    SELECT MAX(id) FROM room_images GROUP BY Room_List_No "
//                    + ") "
//                    + "ORDER BY ri.Room_List_No ASC";
//
//            // Execute the query
//            ResultSet resultSet = MYSQL2.executeSearch(query);
//
//            // Get the table model and clear any previous data
//            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
//            defaultTableModel.setRowCount(0);
//
//            // Populate the table with the latest images for each room
//            while (resultSet.next()) {
//                // Create a new row
//                Vector<Object> row = new Vector<>();
//                row.add(resultSet.getString("Room_List_No")); // Room number
//                row.add(resultSet.getString("Room_Type"));    // Room type name
//                String imagePath = resultSet.getString("image"); // Image path
//
//                // Check if the image path is not null and exists
//                if (imagePath != null && !imagePath.isEmpty()) {
//                    // Load the image from the path
//                    ImageIcon imageIcon = null;
//
//                    // If the path is relative, use getClass().getResource() for resources in the classpath
//                    try {
//                        imageIcon = new ImageIcon(getClass().getResource("/" + imagePath));
//                        if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
//                            throw new Exception("Image not found or failed to load.");
//                        }
//                    } catch (Exception e) {
//                        // If the image doesn't exist in resources, try using an absolute path
//                        imageIcon = new ImageIcon(imagePath);  // Assuming the image path is absolute
//                    }
//
//                    // If image was successfully loaded, create a JLabel to display the image
//                    if (imageIcon != null && imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
//                        JLabel imageLabel = new JLabel(imageIcon);
//                        row.add(imageLabel); // Add the image label to the row
//                    } else {
//                        row.add("Image Not Found"); // Fallback text if image loading fails
//                    }
//                } else {
//                    row.add("No Image"); // No image path available
//                }
//
//                // Add the row to the table model
//                defaultTableModel.addRow(row);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void roomImages() {
        try {
            String query = "SELECT ri.Room_List_No, rt.name AS Room_Type, ri.image "
                    + "FROM room_images ri "
                    + "INNER JOIN Room_List rl ON ri.Room_List_No = rl.No "
                    + "INNER JOIN Room_Type rt ON rl.Room_Type_id = rt.id "
                    + "WHERE ri.id IN ( "
                    + "    SELECT MAX(id) FROM room_images GROUP BY Room_List_No "
                    + ") "
                    + "ORDER BY ri.Room_List_No ASC";
            ResultSet resultSet = MYSQL2.executeSearch(query);
            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            jTable1.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof ImageIcon) {
                        JLabel label = new JLabel((ImageIcon) value);
                        label.setHorizontalAlignment(JLabel.CENTER);
                        return label;
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            });

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("Room_List_No"));
                row.add(resultSet.getString("Room_Type"));

                String imagePath = resultSet.getString("image");
                if (imagePath != null && !imagePath.trim().isEmpty()) {
                    try {
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            // Load image directly from file system
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            row.add(new ImageIcon(scaledImage));
                        } else {
                            // Try loading from resources as fallback
                            URL resourceUrl = getClass().getResource("/" + imagePath);
                            if (resourceUrl != null) {
                                ImageIcon imageIcon = new ImageIcon(resourceUrl);
                                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                                row.add(new ImageIcon(scaledImage));
                            } else {
                                row.add("Image Not Found");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        row.add("Error Loading Image");
                    }
                } else {
                    row.add("No Image Available");
                }
                defaultTableModel.addRow(row);
            }

            jTable1.setRowHeight(100);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading room images: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//    public void roomImages(String column, String orderby, String searchText) {
//        try {
//            // SQL query setup remains unchanged...
//            String query = "SELECT rl.No AS RoomNo, rt.name AS RoomTypeName, ri.image "
//                    + "FROM room_images ri "
//                    + "INNER JOIN Room_List rl ON ri.Room_List_No = rl.No "
//                    + "INNER JOIN Room_Type rt ON rt.id = rl.Room_Type_id "
//                    + "INNER JOIN (SELECT Room_List_No, MAX(id) as max_id FROM room_images GROUP BY Room_List_No) latest "
//                    + "ON ri.Room_List_No = latest.Room_List_No AND ri.id = latest.max_id "
//                    + "WHERE LOWER(rl.No) LIKE ? OR LOWER(rt.name) LIKE ? "
//                    + "ORDER BY " + column + " " + orderby;
//
//            PreparedStatement ps = MYSQL2.getConnection().prepareStatement(query);
//            String pattern = "%" + searchText.toLowerCase() + "%";
//            ps.setString(1, pattern);
//            ps.setString(2, pattern);
//            ResultSet rs = ps.executeQuery();
//
//            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
//            if (defaultTableModel == null) {
//                System.out.println("DefaultTableModel is null.");
//                return; // Early exit if model is null
//            }
//
//            defaultTableModel.setRowCount(0);  // Clear the table
//
//            // Set custom renderer for images column...
//            while (rs.next()) {
//                Vector<Object> row = new Vector<>();
//                String roomNo = rs.getString("RoomNo");
//                String roomTypeName = rs.getString("RoomTypeName");
//                String imagePath = rs.getString("image");
//
//                row.add(roomNo);
//                row.add(roomTypeName);
//
//                if (imagePath != null && !imagePath.trim().isEmpty()) {
//                    File imageFile = new File(imagePath);
//                    if (imageFile.exists()) {
//                        // Load image directly from file system
//                        ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
//                        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//                        row.add(new ImageIcon(scaledImage));
//                    } else {
//                        System.out.println("Image file does not exist: " + imageFile.getAbsolutePath());
//                        addPlaceholderImage(row);
//                    }
//                } else {
//                    System.out.println("Image path is null or empty.");
//                    addPlaceholderImage(row);
//                }
//
//                defaultTableModel.addRow(row);
//            }
//
//            jTable1.setRowHeight(100);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error loading room images: " + e.getMessage(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void addPlaceholderImage(Vector<Object> row) {
//        ImageIcon placeholderIcon = loadPlaceholderImage1();
//        if (placeholderIcon != null) {
//            Image scaledImage = placeholderIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            row.add(new ImageIcon(scaledImage));
//        } else {
//            row.add("No Image Available");
//        }
//    }
//
//    private ImageIcon loadPlaceholderImage1() {
//        try {
//            System.out.println("Loading placeholder image...");
//            URL placeholderUrl = getClass().getResource("/resources/placeholder_image.png");
//            if (placeholderUrl != null) {
//                return new ImageIcon(placeholderUrl);
//            } else {
//                System.out.println("Placeholder image not found.");
//                return null;  // Return null if the placeholder is missing
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;  // Return null on error
//        }
//    }

    public void roomImages(String column, String orderby, String searchText) {
        try {
            // Modified query to get only the latest image for each room
            String query = "SELECT rl.No AS RoomNo, rt.name AS RoomTypeName, ri.image "
                    + "FROM room_images ri "
                    + "INNER JOIN Room_List rl ON ri.Room_List_No = rl.No "
                    + "INNER JOIN Room_Type rt ON rt.id = rl.Room_Type_id "
                    + "INNER JOIN (SELECT Room_List_No, MAX(id) as max_id FROM room_images GROUP BY Room_List_No) latest "
                    + "ON ri.Room_List_No = latest.Room_List_No AND ri.id = latest.max_id "
                    + "WHERE LOWER(rl.No) LIKE ? OR LOWER(rt.name) LIKE ? "
                    + "ORDER BY " + column + " " + orderby;

            PreparedStatement ps = MYSQL2.getConnection().prepareStatement(query);
            String pattern = "%" + searchText.toLowerCase() + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Rest of your code remains the same
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof ImageIcon) {
                        JLabel label = new JLabel((ImageIcon) value);
                        label.setHorizontalAlignment(JLabel.CENTER);
                        return label;
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            });

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("RoomNo"));
                row.add(rs.getString("RoomTypeName"));

                String imagePath = rs.getString("image");
                if (imagePath != null && !imagePath.trim().isEmpty()) {
                    try {
                        ImageIcon imageIcon;
                        URL resourceUrl = getClass().getResource("/" + imagePath);

                        if (resourceUrl != null) {
                            imageIcon = new ImageIcon(resourceUrl);
                        } else if (new File(imagePath).exists()) {
                            imageIcon = new ImageIcon(imagePath);
                        } else {
                            row.add("Image Not Found");
                            model.addRow(row);
                            continue;
                        }

                        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        row.add(new ImageIcon(scaledImage));

                    } catch (Exception e) {
                        e.printStackTrace();
                        row.add("Error Loading Image");
                    }
                } else {
                    row.add("No Image Available");
                }

                model.addRow(row);
            }

            jTable1.setRowHeight(100);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading room images: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/plus_icon.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/resources/filter_icon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel2.setText("Home / Room Images");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel46.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel46.setText("Room Images");

        jButton1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jButton1.setText("Add Room Images");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel46))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setText("Search");

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ROOM ID", "ROOM TYPE", "ROOM IMAGES", "ACTIONS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1284, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddRoomImages addRoomImages = new AddRoomImages(this, true);
        addRoomImages.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        roomImages("rl.No", "ASC", jTextField2.getText());
    }//GEN-LAST:event_jTextField2KeyReleased

    public void refreshImages() {
        SwingUtilities.invokeLater(() -> {
            roomImages("rl.No", "ASC", ""); // Or whatever default parameters you use
            roomImages();
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
