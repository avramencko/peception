package sample.models;

public class Guest {
    private int id;
    private String surname;
    private String name;
    private String phone;

    public Guest(){}
    public Guest(String surname, String name){
        this.surname = surname;
        this.name = name;
    }
    public Guest(String surname, String name, String phone){
        this.surname = surname;
        this.name = name;
        this.phone = phone;
    }
    public Guest(int id, String surname, String name, String phone){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
