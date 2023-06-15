package com.nostalgia;

public class Statistics {

    private int kills;
    private int deaths;
    private double timePlayed;

    public Statistics() {
        this.kills = 0;
        this.deaths = 0;
        this.timePlayed = 0.0;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(double timePlayed) {
        this.timePlayed = timePlayed;
    }
}
