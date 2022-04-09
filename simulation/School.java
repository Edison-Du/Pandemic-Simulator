package simulation;

import java.util.*;

import config.Consts;
import config.Globals;

public class School {
    public ArrayList<Student> students;
    public ArrayList<Classroom> classes;
    // public Schedule schedule;
    public Hallway hallway;

    public School() {
        students = new ArrayList<>();
        classes = new ArrayList<>();
        hallway = new Hallway(this);

        // schedule = new Schedule();

        for (int i = 0; i < Consts.NUM_STUDENTS; i++) {
            students.add(new Student());
        }

        for (int i = 0; i < Consts.NUM_CLASSES; i++) {
            classes.add(new Classroom());
        }

        resetSchool();
    }

    public void resetSchool() {
        synchronized(students) {
        synchronized(classes) {

            for (Student s : students) s.reset();
            for (Classroom c : classes) c.reset();
    
            generateSchedules();
            students.get(0).status = Status.INFECTED;

        }}
    }

    public void generateSchedules() {

        int[] classCount = new int[Consts.NUM_CLASSES];

        // 4 course schedule
        for (int i = 0; i < 4; i++) {
            
            // Shuffle Students
            Collections.shuffle(students);

            for (int j = 0; j < Consts.NUM_STUDENTS; j++) {

                int classN = j / Consts.CLASS_SIZE;
                
                students.get(j).schedule[i] = classN;

                // 4 rows, 5 cols,   (r, c)
                students.get(j).seating[i][0] = classCount[classN]/5;
                students.get(j).seating[i][1] = classCount[classN]%5;

                classCount[classN]++;

                /**
                 *  0  1  2  3  4
                 *  5  6  7  8  9
                 * 10 11 12 13 14
                 * 15 16 17 18 19
                 */
            }

            for (int j = 0; j < Consts.NUM_CLASSES; j++) {
                classCount[j] = 0;
            }
        }


        
        // for (int j = 0; j < Consts.NUM_STUDENTS; j++) {
        //     for (int i = 0; i < 4; i++) {
        //         System.out.print(students.get(j).schedule[i] + ": (" + students.get(j).seating[i][0] + ", " + students.get(j).seating[i][1] + " | ");
        //     }
        //     System.out.println();
        // }
    }

    public void dayChange(){
        for (int i = 0; i < Consts.NUM_STUDENTS; i++){
            if (students.get(i).status == Status.INFECTED) {
                students.get(i).daysIncubated++;
                if (students.get(i).daysIncubated == Globals.incubationPeriod) students.get(i).status = Status.REMOVED;
            }
            else if (students.get(i).status == Status.REMOVED){
                students.get(i).daysQuarantined++;
                if (students.get(i).daysQuarantined == Globals.quarantinePeriod) students.get(i).status = Status.RECOVERED;    
            } 
        }
    }

    public void updateStudents() {
        synchronized(students) {
        synchronized(classes) {

            int p = -1;
            Periods now = Globals.schedule.getCurrentInterval();

            boolean sync = Globals.schedule.currentIntervalSynchronous();

            if (now == Periods.P_1) p = 0;
            if (now == Periods.P_2) p = 1;
            if (now == Periods.P_3) p = 2;
            if (now == Periods.P_4) p = 3;

            if (p >= 0 && sync) {
                for (int i = 0; i < Consts.NUM_STUDENTS; i++) {

                    int classN = students.get(i).schedule[p];
                    int r = students.get(i).seating[p][0];
                    int c = students.get(i).seating[p][1];
                    
                    classes.get(classN).seating[r][c] = students.get(i);
                }
                for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                    classes.get(i).simulateSpread();
                }
                return;

            } else {
                for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                    classes.get(i).reset();
                }
                if (now == Periods.HALL && sync) hallway.simulateSpread();
            }

        }}
    }
}