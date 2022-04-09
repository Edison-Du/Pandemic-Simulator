package simulation;
import java.util.*;

import config.Globals;

public class Schedule{

    public final int START_TIME = 8*60+30; // 8:30

    public ArrayList<Interval> schedules; 

    public Schedule(){
        schedules = new ArrayList<>();
        // intervalTimes = new ArrayList<>();
        for(int i = 0; i < 9; i++)schedules.add(new Interval(0, 0, Periods.NONE, false));
        generateIntervals();
    }

    public void generateIntervals() {
        synchronized(schedules) {
            int currentTime = START_TIME;
            // Interval temp = new Interval(currentTime, currentTime + 10, Periods.HALL);
            // System.out.println(temp.left + " " + temp.right);
            schedules.set(0, new Interval(currentTime, currentTime + Globals.HALLWAY_TIME, Periods.HALL, false));
            currentTime += Globals.HALLWAY_TIME;
            for(int i = 0; i < 4; i++){
                int left, right;
                left = currentTime;
                right = currentTime + Globals.P_LENGTH[i];
                // 0    1     2     3    4     5    6    7     8
                // H -> P1 -> H -> P2 -> H -> P3 -> H -> P4 -> H
                if(i == 0)schedules.set(i*2+1, new Interval(left, right, Periods.P_1, Globals.P_SYNC[i]));
                else if(i == 1)schedules.set(i*2+1, new Interval(left, right, Periods.P_2, Globals.P_SYNC[i]));
                else if(i == 2)schedules.set(i*2+1, new Interval(left, right, Periods.P_3, Globals.P_SYNC[i]));
                else if(i == 3)schedules.set(i*2+1, new Interval(left, right, Periods.P_4, Globals.P_SYNC[i]));
                schedules.set(i*2+2, new Interval(right, right + Globals.HALLWAY_TIME, Periods.HALL, false));
                currentTime += Globals.P_LENGTH[i] + Globals.HALLWAY_TIME;
            }
            // [LEFT, RIGHT], if one of left and right are synchronous then hallway is synchronous
            // Out of bounds errors
            
            for (int i = 0; i < schedules.size(); i++) {
                boolean flag = true;
                if (schedules.get(i).period == Periods.HALL) {
                    if (i > 0) flag &= (!schedules.get(i-1).sync);
                    if (i < schedules.size()-1) flag &= (!schedules.get(i+1).sync);
                    schedules.set(i, 
                        new Interval(schedules.get(i).left, schedules.get(i).right, schedules.get(i).period, !flag));
                }
            }

            for (Interval i : schedules) {
                System.out.println(i.left + " " + i.right + " " + i.period);
            }
        }
    }

    public int getInterval(int minutes){
        synchronized(schedules) {
            for(int i = 0; i < schedules.size(); i++){
                if(minutes >= schedules.get(i).left && minutes < schedules.get(i).right){
                    return i;
                }
            }
        }
        return -1;
    }

    public Periods getCurrentInterval() {
        synchronized(schedules) {
            for(Interval i : schedules){
                int time = Globals.TIME_ELAPSED % (60 * 24);
                if(time >= i.left && time < i.right){ // exclusive
                    return i.period;
                }
            }
        }
        return Periods.NONE;
    }

    public String getCurrentIntervalString() {
        if (getCurrentInterval() == Periods.P_1){
            return "Period 1";
        }else if (getCurrentInterval() == Periods.P_2){
            return "Period 2";
        }else if (getCurrentInterval() == Periods.P_3){
            return "Period 3";
        }else if (getCurrentInterval() == Periods.P_4){
            return "Period 4";
        }else if (getCurrentInterval() == Periods.HALL) {
            return "Hallway";
        }
        return "";
    }

    public class Interval{
        public int left;
        public int right;
        public Periods period;
        public boolean sync;
        // Period, Hallway
        Interval(int left, int right, Periods period, boolean sync){
            this.left = left;
            this.right = right;
            this.period = period;
            this.sync = sync;
        }
    }
}