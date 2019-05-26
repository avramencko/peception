package sample.models;

public class User extends Employee {
    private static User instance;
    private User(){}
    public static User getInstance() {
        if (instance == null) {        //если объект еще не создан
            instance = new User();    //создать новый объект
        }
        return instance;        // вернуть ранее созданный объект
    }
    public void setInstance(Employee employee){
        instance.setId(employee.getId());
        instance.setSurname(employee.getSurname());
        instance.setName(employee.getName());
        instance.setUsername(employee.getUsername());
        instance.setPassword(employee.getPassword());
    }

}
