package sample.models;

import java.time.LocalDate;

/**
 * Period class with properties <b>date</b> and <b>days</b>. to work with the organization of orders
 */
public class Period {
    private LocalDate date;
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

    /**
     * return the value of the {@link Period#date} field
     * @return period start date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set the meaning of the {@link Period#date}
     * @param date period start date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * return the value of the {@link Period#days} field
     * @return number of days
     */
    public int getDays() {
        return days;
    }

    /**
     * set the meaning of the {@link Period#days}
     * @param days number of days
     */
    public void setDays(int days) {
        this.days = days;
    }
}
