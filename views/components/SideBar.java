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

    // public SimulationPage simulationPage;
    public Slider sliderTest;

    public SideBar(Window window) {
    // public SideBar(SimulationPage simulationPage) {

        this.setPreferredSize(new Dimension(UI.SIDE_BAR_WIDTH, UI.MAIN_PANEL_HEIGHT));
        this.setBackground(UI.SIDE_BAR_BG);
        this.setLayout(null);
        
        this.window = window;
        // this.simulationPage = simulationPage;

        playBtn = new CustomButton("Play");
        playBtn.setBounds(30, 50, 100, 50);
        playBtn.addActionListener(this);
        this.add(playBtn);
        

        resetBtn = new CustomButton("Reset");
        resetBtn.setBounds(170, 50, 100, 50);
        resetBtn.addActionListener(this);
        this.add(resetBtn);


        editScheduleBtn = new CustomButton("Edit Schedule");
        editScheduleBtn.setBounds(30, 600, 240, 50);
        editScheduleBtn.addActionListener(this);
        this.add(editScheduleBtn);

        sliderTest = new Slider(240, 20, 12);
        sliderTest.setBounds(30, 100, 240, 20);
        sliderTest.addActionListener(this);
        this.add(sliderTest);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            // window.toggleRunning();
            window.simulationPage.toggleRunning();

        } else if (e.getSource() == resetBtn) {
            // window.reset();
            window.simulationPage.reset();

        } else if (e.getSource() == sliderTest) {
            System.out.println(e.getActionCommand());

        } else if (e.getSource() == editScheduleBtn) {
            window.changePage(Pages.EDIT_SCHEDULE_PAGE);
        }
    }

}