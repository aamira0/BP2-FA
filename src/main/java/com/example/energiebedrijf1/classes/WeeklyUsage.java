package com.example.energiebedrijf1.classes;

public class WeeklyUsage {
    private int weekNumber;
    private double stroomVerbruik;
    private double gasVerbruik;

    public WeeklyUsage(int weekNumber, double stroomVerbruik, double gasVerbruik) {
        this.weekNumber = weekNumber;
        this.stroomVerbruik = stroomVerbruik;
        this.gasVerbruik = gasVerbruik;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public double getStroomVerbruik() {
        return stroomVerbruik;
    }

    public double getGasVerbruik() {
        return gasVerbruik;
    }
}


