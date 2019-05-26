package sample.models;

import java.time.LocalDate;

public class Order {
    private int id;
    private Room room;
    private Guest guest;
    private Employee employee;
    private LocalDate arrival;
    private LocalDate eviction;
    private String notice;
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
        public Builder withId(int id){
            this.id = id;
            return this;
        }
        public Builder withRoom(Room room){
            this.room = room;
            return this;
        }
        public Builder withGuest(Guest guest){
            this.guest = guest;
            return this;
        }
        public Builder withEmployee(Employee employee){
            this.employee = employee;
            return this;
        }
        public Builder withNotice(String notice){
            this.notice = notice;
            return this;
        }
        public Order build(){
            return new Order(this);
        }
    }
}
