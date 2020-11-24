package com.group0179;

import java.time.Instant;
import java.util.Calendar;

public class TimeStatistics {
    private Calendar timeStamp;
    private Instant startTimeLoggedIn;
    private Instant finishTimeLoggedIn;
    private double timeLoggedInAsMinutes;

    public TimeStatistics(){
        this.startTimeLoggedIn = null;
        this.finishTimeLoggedIn = null;
        this.timeLoggedInAsMinutes = 0;
        this.timeStamp = null;

    }
    public void commenceTiming(){
        this.startTimeLoggedIn = Instant.now();
    }
    public void concludeTiming(){
        this.timeStamp = Calendar.getInstance();
        this.finishTimeLoggedIn = Instant.now();
        long timeInMilliseconds = this.finishTimeLoggedIn.toEpochMilli() - this.startTimeLoggedIn.toEpochMilli();
        this.timeLoggedInAsMinutes = ( (double) timeInMilliseconds) / 60000;
    }
    public double getTimeLoggedInAsMinutes(){
        return this.timeLoggedInAsMinutes;
    }
    public Calendar getTimeStamp(){
        return this.timeStamp;
    }

}
