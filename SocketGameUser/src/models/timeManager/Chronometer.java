package models.timeManager;

public class Chronometer {
    private long startTime;
    private long endTime;

    public Chronometer() {
        startTime = 0;
        endTime = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    //Get start time
    public long getStartTime() {
        return startTime;
    }

    //Get end time
    public long getEndTime() {
        return endTime;
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    //get minutes
    public int getElapsedMinutes() {
        return (int) ((endTime - startTime)/60000);
    }

    //get seconds
    public int getElapsedSeconds() {
        return (int) ((endTime - startTime)/1000);
    }

    //get milliseconds
    public int getElapsedMilliseconds() {
        return (int) (endTime - startTime);
    }

    //format time to minutes:seconds:miliseconds
    public String getFormattedTime() {
        int minutes = getElapsedMinutes();
        int seconds = getElapsedSeconds() - (minutes*60);
        int miliseconds = getElapsedMilliseconds() - (minutes*60000) - (seconds*1000);
        return minutes + ":" + seconds + ":" + miliseconds;
    }
}
