package sample.models;

public class Employee {
    private int id;
    private String surname;
    private String name;
    private String username;
    private String password;
    public Employee(){
        this.id  = -1;
    }
    public Employee(int id, String surname, String name, String username, String password){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public Employee(String surname, String name){
        this.surname = surname;
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
