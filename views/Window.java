package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import config.Globals;
import config.UI;
import simulation.School;
import simulation.Status;
import views.components.SideBar;
import views.components.MainPanel;

public class Window extends JFrame {

    public SideBar sideBar;
    public MainPanel mainPanel; 

    public School school;

    private boolean simRunning = false;

    public Window() {

        school = new School();

        sideBar = new SideBar(this);
        mainPanel = new MainPanel(school);


        // this.setTitle(UserInterface.WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel, BorderLayout.WEST);
        this.getContentPane().add(sideBar);
        this.setVisible(true);
        this.pack();
        
        
        Thread frameUpdateThread = new Thread(
            new Runnable() {
                public void run() {
                    updateFrame();
                }
            }
        );
        frameUpdateThread.start();
    }

    /**
     * updateFrame
     * Repaints the JFrame on a set interval
     */
    private void updateFrame() {
        try {
            while (true) {
                if (simRunning) {
                    Globals.TIME_ELAPSED += 10;
                    if (Globals.TIME_ELAPSED % (60 * 24) == 0){
                        school.dayChange();
                    }
                    school.updateStudents();
                }
                mainPanel.timeTracker.updateText();
                this.repaint();
                Thread.sleep(UI.UPDATE_RATE * 10);
            }

        } catch (Exception e) {
            System.out.println("Window update thread interrupted.");
            e.printStackTrace();
        }
    }

    public void play() {
        
    }


    public void reset() {
        Globals.TIME_ELAPSED = 0;
        school.resetSchool();

        if (simRunning) toggleRunning();
    }

    public void toggleRunning() {
        simRunning = !simRunning;
        if (simRunning) {
            sideBar.playBtn.setText("Pause");

        } else {
            sideBar.playBtn.setText("Play");

        }
    }
}
