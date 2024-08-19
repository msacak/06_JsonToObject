package com.sacak.FutbolAPI;

public class Result {

    private String skor;
    private String date;
    private String away;
    private String home;

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }


    @Override
    public String toString() {
        return

                "date='" + getDate().substring(0, 10) +"\t"+
                        "skor='" + getSkor()+"  "
                        + getHome() +" - "
                        + getAway()

                ;
    }
}
