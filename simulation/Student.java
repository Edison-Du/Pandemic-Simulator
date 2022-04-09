package simulation;

public class Student {
    public Status status;
    public int[] schedule;
    public int[][] seating;
    public int daysIncubated, daysQuarantined;

    public Student() {
        reset();
    }
    
    public void reset(){
        status = Status.SUSCEPTIBLE;

        schedule = new int [4];
        seating = new int [4][2];
        
        daysIncubated = 0;
        daysQuarantined = 0;
    }
}
