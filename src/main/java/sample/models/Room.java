package sample.models;

public class Room {
    private int id;
    private int number;
    private int numberBeds;
    private boolean tv;
    private boolean fridge;
    private boolean airConditioning;
    private boolean balcony;
    private int price;

    public Room(){}
    public Room(int id, int number,int numberBeds, int tv, int fridge, int airConditioning,int balcony, int price){
        this.id = id;
        this.number = number;
        this.numberBeds = numberBeds;
        this.tv= tv != 0;
        this.fridge= fridge != 0;
        this.airConditioning= airConditioning != 0;
        this.balcony= balcony != 0;
        this.price = price;
    }
    public Room(int number,int numberBeds, int tv, int fridge, int airConditioning,int balcony, int price){
        this.id = id;
        this.number = number;
        this.numberBeds = numberBeds;
        this.tv= tv != 0;
        this.fridge= fridge != 0;
        this.airConditioning= airConditioning != 0;
        this.balcony= balcony != 0;
        this.price = price;
    }
    public Room(int id, int number, int price){
        this.id = id;
        this.number = number;
        this.price = price;
    }
    public Room(int id, int number){
        this.id = id;
        this.number = number;
    }
    public Room(int number){
        this.number = number;
    }
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
}
