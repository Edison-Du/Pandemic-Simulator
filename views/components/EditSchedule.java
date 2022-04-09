package views.components;


import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;
import config.Globals;

public class EditSchedule extends Page implements ActionListener{
    public Slider[] sliders = new Slider[4];
    public int[] oldSliderPositions = new int[4];
    public CustomButton[] asyncBtns = new CustomButton[4];
    public CustomButton saveBtn, cancelBtn;
    public Window window;

    public EditSchedule(Window window){
        this.setLayout(null);
        this.setBackground(UI.MAIN_PANEL_BG);

        this.window = window;

        for(int i = 0; i < 4; i++){
            sliders[i] = new Slider(280, 30, 14);
            sliders[i].setBounds(30, 100 + i*100, 280, 30);
            sliders[i].setBackground(UI.MAIN_PANEL_BG);
            sliders[i].addActionListener(this);
            this.add(sliders[i]);
        }
        cancelBtn = new CustomButton("Cancel");
        cancelBtn.setBounds(30, 600, 100, 50);
        cancelBtn.setRound(true);
        cancelBtn.setBorderRadius(10);
        cancelBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        cancelBtn.setHoverColor(new Color(122, 122, 122));
        cancelBtn.setPressedColor(new Color(122, 122, 122));
        cancelBtn.setBackground(new Color(102, 102, 102));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        saveBtn = new CustomButton("Save");
        saveBtn.setBounds(170, 600, 100, 50);
        saveBtn.setRound(true);
        saveBtn.setBorderRadius(10);
        saveBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        saveBtn.setHoverColor(new Color(122, 122, 122));
        saveBtn.setPressedColor(new Color(122, 122, 122));
        saveBtn.setBackground(new Color(102, 102, 102));
        saveBtn.addActionListener(this);
        this.add(saveBtn);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveBtn){
            for(int i = 0; i < 4; i++){
                oldSliderPositions[i] = (sliders[i].getSliderPosition()+1)*(sliders[i].length/sliders[i].partition);
                // Globals.schedule.intervalTimes.get(i) = (sliders[i].getSliderPosition()+1)*10;
            }
            Globals.schedule.generateIntervals();
            window.changePage(Pages.SIMULATION_PAGE);

        }else if(e.getSource() == cancelBtn){
            for(int i = 0; i < 4; i++){
                sliders[i].position = oldSliderPositions[i];
            }
            window.changePage(Pages.SIMULATION_PAGE);
        }
    }



    public void updatePage() {}

}
