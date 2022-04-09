package config;

import simulation.Schedule;

public final class Globals {
    // These don't need to be finals, if we ever want to modify them
    public static int TIME_ELAPSED = 0;
    public static int infectionChance = 500; // {0, 1000}
    public static int incubationPeriod = 2; // {0, 7}
    public static int quarantinePeriod = 7;
    public static int infectionPeriod = 1;


    public static int[] P_LENGTH = {70, 70, 70, 70};
    // public static int P1_LENGTH = 70;
    // public static int P2_LENGTH = 70;
    // public static int P3_LENGTH = 70;
    // public static int P4_LENGTH = 70;
    public static int HALLWAY_TIME = 10;

    public static Schedule schedule = new Schedule();
}
