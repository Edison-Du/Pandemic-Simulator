package simulation;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

import config.Globals;
import config.UI;

public class Classroom extends JPanel {
    public final int ROWS = 4;
    public final int COLS = 5;
    
    public Student[][] seating;
    public boolean[][] seatingClone;
    public int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
    public int[] dy = {1, -1, 1, -1, 1, -1, 0, 0};

    public Classroom() {

        reset();

        this.setBackground(UI.SIDE_BAR_BG);
        this.setBorder(BorderFactory.createLineBorder(Color.white));
    }

	public void reset() {
        seating = new Student[ROWS][COLS];
        seatingClone = new boolean[ROWS][COLS];
	}
    
    public void resetClone(){
        for (int i = 0; i < seatingClone.length; i++){
            for (int j = 0; j < seatingClone[i].length; j++){
                seatingClone[i][j] = false;
            }
        }
    }

    public boolean isWithin(int x, int y){
        return (0 <= x && x < ROWS && 0 <= y && y < COLS);
    }

    public void infectAdjacent(int x, int y){
        for (int i = 0; i < dx.length; i++){
            for (int j = 0; j < dy.length; j++){
                int curX = x + dx[i];
                int curY = y + dy[i];
                if (isWithin(curX, curY) && ThreadLocalRandom.current().nextInt(1, Globals.infectionChance) <= 1 
                && seating[curX][curY].status == Status.SUSCEPTIBLE){
                    seatingClone[curX][curY] = true;
                }
            }
        }
    }

    public void simulateSpread(){
        // Simulate the spread of the virus within a classroom
        resetClone();
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if (seating[i][j].status == Status.INFECTED){
                    infectAdjacent(i, j);
                }
            }
        }
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if (seatingClone[i][j]) seating[i][j].status = Status.INFECTED;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // 280, 200

                if (seating[i][j] == null) break;
                if (seating[i][j].status == Status.SUSCEPTIBLE) {
                    g2d.setColor(UI.SUSCEPTIBLE_COLOR);
                } else if (seating[i][j].status == Status.INFECTED) {
                    g2d.setColor(UI.INFECTED_COLOR);
                } else if (seating[i][j].status == Status.RECOVERED){
                    g2d.setColor(UI.REMOVED_COLOR);
                }
                g2d.fillOval(j * (280/5), i * (200/4), 20, 20);

            }
        }

        this.revalidate();
        this.repaint();
    }

    public void clearSeats() {
        // System.out.println("BRo");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seating[i][j] = null;
            }
        }
    }


}