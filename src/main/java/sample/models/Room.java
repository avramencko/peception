package sample.models;

/**
 * Room class with properties <b>id</b>, <b>number</b>, <b>numberBeds</b>, <b>tv</b>, <b>fridge</b>, <b>airConditioning</b>, <b>balcony</b> and <b>price</b>
 */
public class Room {
    private int id;
    private int number;
    private int numberBeds;
    private boolean tv;
    private boolean fridge;
    private boolean airConditioning;
    private boolean balcony;
    private int price;

    private Room(Builder builder){
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

    /**
     * return the value of the {@link Room#id} field
     * @return room's ID
     */
    public int getId() {
        return id;
    }

    /**
     * set the meaning of the {@link Room#id}
     * @param id room's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return the value of the {@link Room#number} field
     * @return room's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * set the meaning of the {@link Room#number}
     * @param number room's number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * return the value of the {@link Room#numberBeds} field
     * @return room's numberBeds
     */
    public int getNumberBeds() {
        return numberBeds;
    }

    /**
     * set the meaning of the {@link Room#numberBeds}
     * @param numberBeds room's numberBeds
     */
    public void setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
    }

    /**
     * return the value of the {@link Room#tv} field
     * @return room's tv
     */
    public boolean isTv() {
        return tv;
    }

    /**
     * set the meaning of the {@link Room#tv}
     * @param tv room's tv
     */
    public void setTv(boolean tv) {
        this.tv = tv;
    }

    /**
     * return the value of the {@link Room#fridge} field
     * @return room's fridge
     */
    public boolean isFridge() {
        return fridge;
    }

    /**
     * set the meaning of the {@link Room#fridge}
     * @param fridge room's fridge
     */
    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    /**
     * return the value of the {@link Room#airConditioning} field
     * @return room's airConditioning
     */
    public boolean isAirConditioning() {
        return airConditioning;
    }

    /**
     * set the meaning of the {@link Room#id}
     * @param airConditioning room's airConditioning
     */
    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    /**
     * return the value of the {@link Room#balcony} field
     * @return room's balcony
     */
    public boolean isBalcony() {
        return balcony;
    }

    /**
     * set the meaning of the {@link Room#balcony}
     * @param balcony room's balcony
     */
    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    /**
     * return the value of the {@link Room#price} field
     * @return room's price
     */
    public int getPrice() {
        return price;
    }

    /**
     *set the meaning of the {@link Room#price}
     * @param price room's price
     */
    public void setPrice(int price) {
        this.price = price;
    }

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

        public Builder withId(int id){
            this.id = id;
            return this;
        }
        public Builder withNumber(int number){
            this.number = number;
            return this;
        }
        public Builder withNumberBeds(int numberBeds){
            this.numberBeds = numberBeds;
            return this;
        }
        public Builder withTv(int tv){
            this.tv = tv != 0;
            return this;
        }
        public Builder withFridge(int fridge){
            this.fridge= fridge != 0;
            return this;
        }
        public Builder withAirConditioning(int airConditioning){
            this.airConditioning= airConditioning != 0;
            return this;
        }
        public Builder withBalcony(int balcony){
            this.balcony= balcony != 0;
            return this;
        }
        public Builder withPrice(int price){
            this.price = price;
            return this;
        }
        public Room build(){
            return new Room(this);
        }
    }
}
