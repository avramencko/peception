package sample.models;

import java.time.LocalDate;

public class Period {
    private LocalDate date;
    private int days;
    public Period(){}
    public Period(LocalDate date, int days){
        this.date = date;
        this.days = days;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
