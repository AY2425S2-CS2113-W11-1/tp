package utils;

import java.time.LocalTime;

public class TimeChecker {

    // Method to get current time
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static void main(String[] args) {
        TimeChecker timeChecker = new TimeChecker();
        System.out.println("Current time: " + timeChecker.getCurrentTime());
    }
}
