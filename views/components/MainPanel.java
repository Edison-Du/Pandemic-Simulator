package views.components;

import javax.swing.*;

import java.awt.*;

import config.Consts;
import config.UI;
import simulation.School;

public class MainPanel extends JPanel {
    public School school;
    public TimeTracker timeTracker;

    public MainPanel(School school) { 
        this.setPreferredSize(new Dimension(UI.MAIN_PANEL_WIDTH, UI.MAIN_PANEL_HEIGHT));
        this.setBackground(UI.MAIN_PANEL_BG);
        this.setLayout(null);

        timeTracker = new TimeTracker();
        // timeTracker.setBorder(BorderFactory.createLineBorder(Color.white));
        timeTracker.setBackground(UI.MAIN_PANEL_BG);
        timeTracker.setBounds(80, 60, 200, 100);
        this.add(timeTracker);

        for (int i = 0; i < Consts.NUM_CLASSES; i++) {
            school.classes.get(i).setBounds((i%3) * 270 + 80, (i/3) * 290 + 180, 270 + 2, 170 + 2);
            this.add(school.classes.get(i));
        }

        school.hallway.setBounds(80, 350, 810 + 2, 120 + 2);
        this.add(school.hallway);
    }
}
