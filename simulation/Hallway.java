package simulation;

import java.awt.*;
import java.io.Console;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

import config.Consts;
import config.Globals;
import config.UI;

public class Hallway extends JPanel {
    public School school;
    
    public Hallway (School school) {
        this.school = school;
        this.setBackground(UI.HALLWAY_BG);
        this.setBorder(BorderFactory.createLineBorder(Color.white, 2));
    }

    public void simulateSpread() {
        int j = ThreadLocalRandom.current().nextInt(0, Consts.NUM_STUDENTS);
        if (school.students.get(j).status == Status.SUSCEPTIBLE && 
        ThreadLocalRandom.current().nextInt(1, Globals.infectionChance) <= 1) school.students.get(j).status = Status.INFECTED;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (school != null && school.students.size() > 0 && Globals.schedule.getCurrentInterval() == Periods.HALL) {
            for (int i = 0; i < Consts.NUM_STUDENTS; i++) {
                int x = (i%20)*(810/20);// + ThreadLocalRandom.current().nextInt(1, 5);
                int y = (i/20)*(120/6);// + ThreadLocalRandom.current().nextInt(1, 5);
                // if ((i/20)%2 == 0) x += 20;

                if (school.students.get(i).status == Status.SUSCEPTIBLE) {
                    g2d.setColor(UI.SUSCEPTIBLE_COLOR);
                } else if (school.students.get(i).status == Status.INFECTED) {
                    g2d.setColor(UI.INFECTED_COLOR);
                } else if (school.students.get(i).status == Status.REMOVED){
                    g2d.setColor(getBackground());
                } else if (school.students.get(i).status == Status.RECOVERED){
                    g2d.setColor(UI.RECOVERED_COLOR);
                }
                g2d.fillOval(x, y, 10, 10);
            }
        }

        this.revalidate();
        this.repaint();
    }
}
