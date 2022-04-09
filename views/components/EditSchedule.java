package views.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;

public class EditSchedule extends JPanel implements ActionListener{
    public Slider [] sliders = new Slider[4];

    public EditSchedule(){
        for(int i = 0; i < 4; i++){
            sliders[i] = new Slider(280, 30, 14);
            sliders[i].setBounds(30, 100 + i*100, 280, 30);
            sliders[i].addActionListener(this);
            this.add(sliders[i]);
        }
    }
    public void actionPerformed(ActionEvent e) {
        
    }
}
