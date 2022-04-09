package config;

public final class Globals {
    // These don't need to be finals, if we ever want to modify them
    public static int TIME_ELAPSED = 0;
    public static int infectionChance = 500; // {0, 1000}
    public static int incubationPeriod = 2; // {0, 7}
    public static int quarantinePeriod = 7;

}