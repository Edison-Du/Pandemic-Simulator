package simulation;
import java.util.*;

import config.Globals;

public class Schedule{
    public ArrayList<Interval> schedules; 
    public Schedule(){
        schedules = new ArrayList<>();
        // schedules.add(new Interval(8*60+50, 10*60+10, Periods.P_1));
        // schedules.add(new Interval(10*60+10, 11*60+30, Periods.P_2));
        schedules.add(new Interval(1*60+50, 6*60+10, Periods.P_1));
        schedules.add(new Interval(6*60+10, 7*60+10, Periods.HALL));
        schedules.add(new Interval(7*60+10, 12*60+30, Periods.P_2));
        schedules.add(new Interval(12*60+10, 13*60+10, Periods.HALL));
        schedules.add(new Interval(13*60+10, 18*60+10, Periods.P_3));
        schedules.add(new Interval(18*60+10, 19*60+10, Periods.HALL));
        schedules.add(new Interval(19*60+10, 23*60+30, Periods.P_4));
    }
    public int getInterval(int minutes){
        for(int i = 0; i < schedules.size(); i++){
            if(minutes >= schedules.get(i).left && minutes < schedules.get(i).right){
                return i;
            }
        }
        return -1;
    }

    public Periods getCurrentInterval() {
        for(Interval i : schedules){
            int time = Globals.TIME_ELAPSED % (60 * 24);
            if(time >= i.left && time < i.right){ // exclusive
                return i.period;
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
        // Period, Hallway
        Interval(int left, int right, Periods period){
            this.left = left;
            this.right = right;
            this.period = period;
        }
    }
}