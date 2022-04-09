package views.components;


import javax.swing.*;

import config.UI;

import java.awt.*;
import java.awt.event.*;

public class Toggle extends JPanel implements MouseListener{

    private boolean state;
    private ActionListener actionListener;

    public Toggle() {

        this.addMouseListener(this);

        // this.setBackground(UI.SIDE_BAR_BG);
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }

    public boolean getState() {
        return this.state;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // g2d.setColor(Color.WHITE);
        // g2d.setStroke(new BasicStroke(3));
        // g2d.drawLine(0, diameter/2, length, diameter/2);
        // g2d.setStroke(new BasicStroke(0));
        // g2d.fillOval(position, 0, diameter,diameter);

        this.revalidate();
        this.repaint();
    }

    // ..
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
