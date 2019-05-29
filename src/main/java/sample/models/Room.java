package sample.models;

/**
 * Room class with properties <b>id</b>, <b>number</b>, <b>numberBeds</b>, <b>tv</b>, <b>fridge</b>, <b>airConditioning</b>, <b>balcony</b> and <b>price</b>
 */
public class Room {
    /** room's ID*/
    private int id;
    /**room's number*/
    private int number;
    /**number of beds in the room*/
    private int numberBeds;
    /**TV in the room*/
    private boolean tv;
    /**fridge in the room*/
    private boolean fridge;
    /**air conditioning in the room*/
    private boolean airConditioning;
    /**balcony in the room*/
    private boolean balcony;
    /**room price per day*/
    private int price;

    /**
     * constructor with parameters. take an object of Builder and store all its fields
     * @param builder object whose fields are copied
     */
    private Room(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.numberBeds = builder.numberBeds;
        this.tv = builder.tv;
        this.fridge = builder.fridge;
        this.airConditioning = builder.airConditioning;
        this.balcony = builder.balcony;
        this.price = builder.price;
    }

 /**
 * Indicates whether some other object is "equal to" this one. rooms with the same id are considered equal
 * @param obj  the object with which to compare.
 * @return  {@code true} if this object is the same as the obj
 *         argument; {@code false} otherwise.
 */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Room room = (Room) obj;
        return id == room.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Builder is part of the builder pattern. It knows the interface of the builder
     * and builds a complex object with the help of the builder
     */
    public static class Builder {
        private int id;
        private int number;
        private int numberBeds;
        private boolean tv;
        private boolean fridge;
        private boolean airConditioning;
        private boolean balcony;
        private int price;
        public Builder(){}

        public Builder setId(int id){
            this.id = id;
            return this;
        }
        public Builder setNumber(int number){
            this.number = number;
            return this;
        }
        public Builder setNumberBeds(int numberBeds){
            this.numberBeds = numberBeds;
            return this;
        }

        //if the parameter is not 0 then {true}, else {false}
        public Builder setTv(int tv){
            this.tv = tv != 0;
            return this;
        }
        //if the parameter is not 0 then {true}, else {false}
        public Builder setFridge(int fridge){
            this.fridge= fridge != 0;
            return this;
        }
        //if the parameter is not 0 then {true}, else {false}
        public Builder setAirConditioning(int airConditioning){
            this.airConditioning= airConditioning != 0;
            return this;
        }
        //if the parameter is not 0 then {true}, else {false}
        public Builder setBalcony(int balcony){
            this.balcony= balcony != 0;
            return this;
        }
        public Builder setPrice(int price){
            this.price = price;
            return this;
        }
        public Room build(){
            return new Room(this);
        }
    }
}
