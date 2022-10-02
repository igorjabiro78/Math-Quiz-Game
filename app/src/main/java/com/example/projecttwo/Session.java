package com.example.projecttwo;

public class Session {
    private int score;
    private String date;
    private int avgtime;
    private int bonus;
    private String level;

    public Session() {
        avgtime=0;
        date="";
        score = 0;
        bonus=0;


    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(int avgtime) {
        this.avgtime = avgtime;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}


