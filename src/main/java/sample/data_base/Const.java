package sample.data_base;

public class Const {
    public static final String EMPLOYEES_TABLE = "employees";
    public static final String EMPLOYEES_ID = "employees.id";
    public static final String EMPLOYEES_SURNAME = "employees.surname";
    public static final String EMPLOYEES_NAME = "employees.name";
    public static final String EMPLOYEES_USERNAME = "employees.username";
    public static final String EMPLOYEES_PASSWORD = "employees.password";

    public static final String GUESTS_TABLE = "guests";
    public static final String GUESTS_ID = "guests.id";
    public static final String GUESTS_SURNAME = "guests.surname";
    public static final String GUESTS_NAME = "guests.name";
    public static final String GUESTS_PHONE = "guests.phone";

    public static final String ROOMS_TABLE = "rooms";
    public static final String ROOMS_ID = "rooms.id";
    public static final String ROOMS_NUMBER = "rooms.number";
    public static final String ROOMS_NUMBER_BEDS = "rooms.numberBeds";
    public static final String ROOMS_TV = "rooms.tv";
    public static final String ROOMS_FRIDGE = "rooms.fridge";
    public static final String ROOMS_AIRCONDITIONING = "rooms.airConditioning";
    public static final String ROOMS_BALCONY = "rooms.balcony";
    public static final String ROOMS_PRICE = "rooms.price";

    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_ID = "orders.id";
    public static final String ORDERS_ID_ROOM = "orders.id_room";
    public static final String ORDERS_ID_GUEST = "orders.id_guest";
    public static final String ORDERS_ARRIVAL = "orders.arrival";
    public static final String ORDERS_EVICTION= "orders.eviction";
    public static final String ORDERS_ID_EMPLOYEES = "orders.id_employee";
    public static final String ORDERS_NOTICE = "orders.notice";
}
