package sample.models;

import java.time.LocalDate;

/**
 * Order class with properties <b>id</b>, <b>room</b>, <b>guest</b>, <b>employee</b>, <b>arrival</b>, <b>eviction</b> and <b>notice</b>
 */
public class Order {
    /**order's ID*/
    private int id;
    /**order's room*/
    private Room room;
    /**guest who made the order*/
    private Guest guest;
    /**employee who created the order*/
    private Employee employee;
    /** date of arrival*/
    private LocalDate arrival;
    /** date of eviction*/
    private LocalDate eviction;
    /**comment to the order*/
    private String notice;
    /**
     * constructor with parameters. take an object of Builder and store all its fields
     * @param builder object whose fields are copied
     */
    private Order(Builder builder){
        this.id = builder.id;
        this.room = builder.room;
        this.guest = builder.guest;
        this.employee = builder.employee;
        this.arrival = builder.arrival;
        this.eviction = builder.eviction;
        this.notice = builder.notice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    public Guest getGuest() {
        return guest;
    }


    public void setGuest(Guest guest) {
        this.guest = guest;
    }


    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public LocalDate getArrival() {
        return arrival;
    }


    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }


    public LocalDate getEviction() {
        return eviction;
    }


    public void setEviction(LocalDate eviction) {
        this.eviction = eviction;
    }


    public String getNotice() {
        return notice;
    }


    public void setNotice(String notice) {
        this.notice = notice;
    }
    /**
     * Builder is part of the builder pattern. It knows the interface of the builder
     * and builds a complex object with the help of the builder
     */
    public static class Builder {
        private int id;
        private Room room;
        private Guest guest;
        private Employee employee;
        private LocalDate arrival;
        private LocalDate eviction;
        private String notice;

        public Builder(LocalDate arrival, LocalDate eviction) {
            this.arrival = arrival;
            this.eviction = eviction;
        }
        public Builder setId(int id){
            this.id = id;
            return this;
        }
        public Builder setRoom(Room room){
            this.room = room;
            return this;
        }
        public Builder setGuest(Guest guest){
            this.guest = guest;
            return this;
        }
        public Builder setEmployee(Employee employee){
            this.employee = employee;
            return this;
        }
        public Builder setNotice(String notice){
            this.notice = notice;
            return this;
        }
        public Order build(){
            return new Order(this);
        }
    }
}
