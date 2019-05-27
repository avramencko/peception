package sample.models;

import java.time.LocalDate;

/**
 * Order class with properties <b>id</b>, <b>room</b>, <b>guest</b>, <b>employee</b>, <b>arrival</b>, <b>eviction</b> and <b>notice</b>
 */
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

    /**
     * return the value of the {@link Order#id} field
     * @return order's ID
     */
    public int getId() {
        return id;
    }

    /**
     * set the meaning of the {@link Order#id}
     * @param id order's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return the value of the {@link Order#room} field
     * @return order's room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * set the meaning of the {@link Order#room}
     * @param room order's room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * return the value of the {@link Order#guest} field
     * @return guest who made the order
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * set the meaning of the {@link Order#guest}
     * @param guest guest who made the order
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * return the value of the {@link Order#employee} field
     * @return employee who created the order
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * set the meaning of the {@link Order#employee}
     * @param employee employee who created the order
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * return the value of the {@link Order#arrival} field
     * @return date of arrival
     */
    public LocalDate getArrival() {
        return arrival;
    }

    /**
     * set the meaning of the {@link Order#arrival}
     * @param arrival date of arrival
     */
    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    /**
     * return the value of the {@link Order#eviction} field
     * @return date of eviction
     */
    public LocalDate getEviction() {
        return eviction;
    }

    /**
     * set the meaning of the {@link Order#eviction}
     * @param eviction date of eviction
     */
    public void setEviction(LocalDate eviction) {
        this.eviction = eviction;
    }

    /**
     * return the value of the {@link Order#notice} field
     * @return comment to the order
     */
    public String getNotice() {
        return notice;
    }

    /**
     * set the meaning of the {@link Order#notice}
     * @param notice comment to the order
     */
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
