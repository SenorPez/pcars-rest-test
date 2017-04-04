package com.senorpez.projectcars;

public class Round {
    private final String track;
    private final Integer laps;

    public Round(String track, Integer laps) {
        this.track = track;
        this.laps = laps;
    }

    public String getTrack() {
        return track;
    }

    public Integer getLaps() {
        return laps;
    }
}
