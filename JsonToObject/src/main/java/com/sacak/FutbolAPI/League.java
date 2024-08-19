package com.sacak.FutbolAPI;

public class League {

    private String league;
    private String key;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "League{" +
                "league='" + getLeague() + '\'' +
                ", key='" + getKey() + '\'' +
                '}';
    }
}
