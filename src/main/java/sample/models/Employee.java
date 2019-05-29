package sample.models;

/**
 * Employee class with properties <b>id</b>, <b>surname</b>, <b>name</b>, <b>username</b> and <b>password</b>
 */
public class Employee {
    /** employee's ID*/
    private int id;
    /** employee's surname*/
    private String surname;
    /** employee's name*/
    private String name;
    /** employee's username*/
    private String username;
    /** employee's password*/
    private String password;

    /**
     *  default constructor
     *  @see Employee#Employee(String, String)
     *  @see Employee#Employee(int, String, String, String, String)
     */
    public Employee(){
        this.id  = -1;
    }

    /**
     * constructor with parameters
     * @param surname employee's surname
     * @param name employee's name
     */
    public Employee(String surname, String name){
        this.surname = surname;
        this.name = name;
    }
    /**
     * constructor with parameters
     * @param id employee's ID
     * @param surname employee's surname
     * @param name employee's name
     * @param username employee's username
     * @param password employee's password
     */
    public Employee(int id, String surname, String name, String username, String password){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.username = username;
        this.password = password;
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
