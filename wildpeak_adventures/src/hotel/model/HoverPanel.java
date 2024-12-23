/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author shalaka
 */
public class HoverPanel {

    public static JPanel createHoverPanel(JPanel panel) {
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.BLUE); // Initial color is blue
//        panel.setBounds(x, y, width, height); // Set position and size

        // Add a MouseListener to change color on hover
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(java.awt.Color.decode("#cfe2ff")); // Change to red when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(null); // Revert to blue when mouse exits
            }
        });

        return panel;

    }
}
