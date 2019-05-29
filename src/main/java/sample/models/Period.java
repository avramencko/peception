package sample.models;

import java.time.LocalDate;

/**
 * Period class with properties <b>date</b> and <b>days</b>. to work with the organization of orders
 */
public class Period {
    /**period start date*/
    private LocalDate date;
     /**number of days*/
    private int days;

    /**
     * default constructor
     * @see Period#Period(LocalDate, int)
     */
    public Period(){}

    /**
     * constructor with parameters
     * @param date period start date
     * @param days number of days
     * @see Period#Period()
     */
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
