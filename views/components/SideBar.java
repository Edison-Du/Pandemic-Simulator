package views.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;

public class SideBar extends JPanel implements ActionListener{

    public CustomButton playBtn, resetBtn, editScheduleBtn;
    public Window window;
    public Slider sliderTest;

    public SideBar(Window window) {
        this.setPreferredSize(new Dimension(UI.SIDE_BAR_WIDTH, UI.MAIN_PANEL_HEIGHT));
        this.setBackground(UI.SIDE_BAR_BG);
        this.setLayout(null);
        
        this.window = window;

        playBtn = new CustomButton("Play");
        playBtn.setBounds(30, 50, 100, 50);
        playBtn.setRound(true);
        playBtn.setBorderRadius(10);
        playBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        playBtn.setHoverColor(new Color(122, 122, 122));
        playBtn.setPressedColor(new Color(122, 122, 122));
        playBtn.setBackground(new Color(102, 102, 102));
        playBtn.addActionListener(this);
        this.add(playBtn);
        

        resetBtn = new CustomButton("Reset");
        resetBtn.setBounds(170, 50, 100, 50);
        resetBtn.setRound(true);
        resetBtn.setBorderRadius(10);
        resetBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        resetBtn.setHoverColor(new Color(122, 122, 122));
        resetBtn.setPressedColor(new Color(122, 122, 122));
        resetBtn.setBackground(new Color(102, 102, 102));
        resetBtn.addActionListener(this);
        this.add(resetBtn);


        editScheduleBtn = new CustomButton("Edit Schedule");
        editScheduleBtn.setBounds(30, 600, 240, 50);
        editScheduleBtn.setRound(true);
        editScheduleBtn.setBorderRadius(10);
        editScheduleBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        editScheduleBtn.setHoverColor(UI.SUSCEPTIBLE_COLOR.brighter());
        editScheduleBtn.setPressedColor(UI.SUSCEPTIBLE_COLOR.brighter());
        editScheduleBtn.setBackground(UI.SUSCEPTIBLE_COLOR);
        editScheduleBtn.addActionListener(this);
        this.add(editScheduleBtn);

        sliderTest = new Slider(240, 20, 12);
        sliderTest.setBounds(30, 100, 240, 20);
        sliderTest.addActionListener(this);
        this.add(sliderTest);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            window.toggleRunning();
            
        } else if (e.getSource() == resetBtn) {
            window.reset();

        } else if (e.getSource() == sliderTest) {
            System.out.println(e.getActionCommand());

        } else if (e.getSource() == editScheduleBtn) {

        }
    }

}