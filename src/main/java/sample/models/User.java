package sample.models;

/**
 * User class - singleton
 */
public class User extends Employee {
    private static User instance;
    private User(){}

    /**
     * return the User. creates an object if it is not created
     * @return user
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    /**
     * constructor for user instance
     * @param employee employee who is a user
     */
    public void setInstance(Employee employee){
        instance.setId(employee.getId());
        instance.setSurname(employee.getSurname());
        instance.setName(employee.getName());
        instance.setUsername(employee.getUsername());
        instance.setPassword(employee.getPassword());
    }

}
