package sample.models;

/**
 * Employee class with properties <b>id</b>, <b>surname</b>, <b>name</b>, <b>username</b> and <b>password</b>
 */
public class Employee {
    private int id;
    private String surname;
    private String name;
    private String username;
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
     * @param surname constructor with parameters
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

    /**
     * return the value of the {@link Employee#id} field
     * @return employee's ID
     */
    public int getId() {
        return id;
    }

    /**
     * set the meaning of the {@link Employee#id}
     * @param id employee's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return the value of the {@link Employee#surname} field
     * @return employee's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * set the meaning of the {@link Employee#surname}
     * @param surname employee's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * return the value of the {@link Employee#name} field
     * @return employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * set the meaning of the {@link Employee#name}
     * @param name employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return the value of the {@link Employee#username} field
     * @return employee's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the meaning of the {@link Employee#username}
     * @param username employee's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * return the value of the {@link Employee#password} field
     * @return employee's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the meaning of the {@link Employee#password}
     * @param password employee's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
