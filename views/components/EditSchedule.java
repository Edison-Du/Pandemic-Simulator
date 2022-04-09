package views.components;


import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;
import config.Globals;

public class EditSchedule extends Page implements ActionListener{
    public Slider[] sliders = new Slider[4];
    public Toggle[] toggles = new Toggle[4];
    public JLabel[] periodLengths = new JLabel[4];
    public int[] oldSliderPositions = new int[4];
    public boolean[] oldToggles = new boolean[4];

    public CustomButton saveBtn, cancelBtn;
    public Window window;

    public EditSchedule(Window window){
        this.setLayout(null);
        this.setBackground(UI.MAIN_PANEL_BG);

        this.window = window;

        for(int i = 0; i < 4; i++){
            sliders[i] = new Slider(300, 30, 15);

            sliders[i].position = (sliders[i].length/sliders[i].partition) * (Globals.P_LENGTH[0]/10 - 1);
            oldSliderPositions[i] = sliders[i].position;
            
            sliders[i].setBounds(30, 100 + i*100, 300, 30);
            sliders[i].setBackground(UI.MAIN_PANEL_BG);
            sliders[i].addActionListener(this);
            this.add(sliders[i]);

            periodLengths[i] = new JLabel("70 min");
            periodLengths[i].setBounds(350, 100 + i*100, 100, 30);
            periodLengths[i].setForeground(Color.white);
            periodLengths[i].setFont(UI.orkney18);
            this.add(periodLengths[i]);

            toggles[i] = new Toggle(60, 30);
            toggles[i].setBounds(560, 100 + i*100, 60, 30);
            this.add(toggles[i]);
        }
        cancelBtn = new CustomButton("Cancel");
        cancelBtn.setBounds(30, 600, 100, 50);
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        saveBtn = new CustomButton("Save");
        saveBtn.setBounds(170, 600, 100, 50);
        saveBtn.addActionListener(this);
        this.add(saveBtn);

        
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveBtn){
            for(int i = 0; i < 4; i++){
                oldToggles[i] = toggles[i].getState();
                oldSliderPositions[i] = sliders[i].position;
                Globals.P_LENGTH[i] = (sliders[i].getSliderPosition() + 1) * 10;
            }   
            Globals.schedule.generateIntervals();
            window.changePage(Pages.SIMULATION_PAGE);

        }else if(e.getSource() == cancelBtn){
            for(int i = 0; i < 4; i++){
                sliders[i].position = oldSliderPositions[i];
                toggles[i].setState(oldToggles[i]);
            }
            window.changePage(Pages.SIMULATION_PAGE);
        }
        updateText();
    }

    public void updateText() {
        
        for (int i = 0; i < 4; i++) {
            periodLengths[i].setText(((sliders[i].getSliderPosition() + 1) * 10) + " min");
        }
        
        this.revalidate();
        this.repaint();
    }

    public void updatePage() {}

}
