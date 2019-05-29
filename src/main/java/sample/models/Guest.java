package sample.models;

/**
 * Guest class with properties <b>id</b>, <b>surname</b>, <b>name</b> and <b>phone</b>
 */
public class Guest {
    /**guest's ID*/
    private int id;
    /**guest's surname*/
    private String surname;
    /**guest's name*/
    private String name;
    /**guest's phone*/
    private String phone;

    /**
     * default constructor
     * @see Guest#Guest(String, String)
     * @see Guest#Guest(String, String, String)
     * @see Guest#Guest(int, String, String, String)
     */
    public Guest(){}

    /**
     * constructor with parameters
     * @param surname guest's surname
     * @param name guest's name
     * @see Guest#Guest()
     * @see Guest#Guest(String, String, String)
     * @see Guest#Guest(int, String, String, String)
     */
    public Guest(String surname, String name){
        this.surname = surname;
        this.name = name;
    }

    /**
     * constructor with parameters
     * @param surname guest's surname
     * @param name guest's name
     * @param phone guest's phone
     * @see Guest#Guest()
     * @see Guest#Guest(String, String)
     * @see Guest#Guest(int, String, String, String)
     */
    public Guest(String surname, String name, String phone){
        this.surname = surname;
        this.name = name;
        this.phone = phone;
    }

    /**
     * constructor with parameters
     * @param id guest's ID
     * @param surname guest's surname
     * @param name guest's name
     * @param phone guest's phone
     * @see Guest#Guest()
     * @see Guest#Guest(String, String)
     * @see Guest#Guest(String, String, String)
     */
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
