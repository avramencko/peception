package sample.data_base;

import org.mindrot.jbcrypt.BCrypt;
import sample.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBaseHandler extends Configs {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static DataBaseHandler instance;
    private DataBaseHandler(){}
    public static DataBaseHandler getInstance() {
        if (instance == null) {
            instance = new DataBaseHandler();
        }
        return instance;
    }
    private Connection getDbConnection(){
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection succesfull!");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed...");
            e.printStackTrace();
        }
        try {
//            createDatabase();
//            delete();
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?currentSchema=" + dbSchema + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            if (dbConnection != null) {
                System.out.println("eeee");
            }
            return dbConnection;
        } catch (SQLException e) {
            dbConnection = createDatabase();
        }
        return dbConnection;
    }
    private void delete(){
        Connection con = null;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/?user="+dbUser+"&password=" + dbPass+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            Statement s = con.createStatement();
            s.executeUpdate("DROP DATABASE " + dbName);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection createDatabase() {
        Connection con = null;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/?user="+dbUser+"&password=" + dbPass+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            Statement s = con.createStatement();
            int myResult = s.executeUpdate("CREATE DATABASE " + dbName);
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?currentSchema=" + dbSchema + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(connectionString, dbUser, dbPass);
            s = con.createStatement();
            myResult = s.executeUpdate("CREATE TABLE "+Const.EMPLOYEES_TABLE+"\n" +
                    "(\n" +
                    "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "    surname varchar(50) NOT NULL,\n" +
                    "    name varchar(50) NOT NULL,\n" +
                    "    username varchar(100) NOT NULL,\n" +
                    "    password varchar(200) NOT NULL\n" +
                    ");");
            myResult = s.executeUpdate("CREATE TABLE "+Const.GUESTS_TABLE+"\n" +
                    "(\n" +
                    "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "    name varchar(50) NOT NULL,\n" +
                    "    surname varchar(50) NOT NULL,\n" +
                    "    phone varchar(100) NOT NULL\n" +
                    ");");
            myResult = s.executeUpdate("CREATE TABLE "+Const.ROOMS_TABLE+"\n" +
                    "(\n" +
                    "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "    number int NOT NULL,\n" +
                    "    numberBeds int,\n" +
                    "    tv int,\n" +
                    "    fridge int,\n" +
                    "    airConditioning int,\n" +
                    "    balcony int,\n" +
                    "    price int\n" +
                    ");");
            myResult = s.executeUpdate("CREATE TABLE orders\n" +
                    "(\n" +
                    "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "    id_room int NOT NULL,\n" +
                    "    id_employee int NOT NULL,\n" +
                    "    id_guest int NOT NULL,\n" +
                    "    arrival datetime NOT NULL,\n" +
                    "    eviction datetime NOT NULL,\n" +
                    "    notice varchar(255),\n" +
                    "    CONSTRAINT orders_rooms_id_fk FOREIGN KEY (id_room) REFERENCES rooms (id),\n" +
                    "    CONSTRAINT orders_employees_id_fk FOREIGN KEY (id_employee) REFERENCES employees (id),\n" +
                    "    CONSTRAINT orders_guests_id_fk FOREIGN KEY (id_guest) REFERENCES guests (id)\n" +
                    ");");
            savePass("Анна","Адамовская","admin","admin");
            saveRooms();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return getDbConnection();
    }

    public Employee authenticate(String log, String pass) {
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM "+Const.EMPLOYEES_TABLE+" WHERE " +Const.EMPLOYEES_USERNAME+" = ? ");//AND "+Const.EMPLOYEES_PASSWORD+" = ?
            statement.setString(1, log);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(BCrypt.checkpw(pass, resultSet.getString(5))) {
                    return new Employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Employee();

    }
    public void savePass(String nam, String surn,String log, String pass) {
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO "+Const.EMPLOYEES_TABLE+" ("+Const.EMPLOYEES_SURNAME+", "+Const.EMPLOYEES_NAME+", "+Const.EMPLOYEES_USERNAME+", "+Const.EMPLOYEES_PASSWORD+") VALUES ( ? , ? , ? , ? )");
            statement.setString(1, surn);
            statement.setString(2, nam);
            statement.setString(3, log);
            String newpass = BCrypt.hashpw(pass, BCrypt.gensalt());
            statement.setString(4, newpass);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveRooms() {
        try (Connection conn = getDbConnection()) {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO "+dbName+".`rooms` (`number`, `numberBeds`, `tv`, `fridge`, `airConditioning`, `balcony`, `price`) VALUES (1, 1, 0, 0, 0, 0, 100)");
            statement.execute("INSERT INTO "+dbName+".`rooms` (`number`, `numberBeds`, `tv`, `fridge`, `airConditioning`, `balcony`, `price`) VALUES (2, 1, 1, 0, 0, 0, 120)");
            statement.execute("INSERT INTO "+dbName+".`rooms` (`number`, `numberBeds`, `tv`, `fridge`, `airConditioning`, `balcony`, `price`) VALUES (3, 2, 0, 1, 1, 1, 160)");
            statement.execute("INSERT INTO "+dbName+".`rooms` (`number`, `numberBeds`, `tv`, `fridge`, `airConditioning`, `balcony`, `price`) VALUES (4, 2, 1, 1, 1, 0, 165)");
            statement.execute("INSERT INTO "+dbName+".`rooms` (`number`, `numberBeds`, `tv`, `fridge`, `airConditioning`, `balcony`, `price`) VALUES (5, 3, 1, 1, 1, 1, 230)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Order>  getOrders(LocalDate begin, LocalDate end){
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT "+Const.ORDERS_ID+", "+Const.ORDERS_ARRIVAL+", "+Const.ORDERS_EVICTION+", "+Const.ROOMS_TABLE+".*\n" +
                    "FROM "+Const.ROOMS_TABLE+" \n" +
                    "       LEFT JOIN "+Const.ORDERS_TABLE+" ON "+Const.ROOMS_ID+" = "+Const.ORDERS_ID_ROOM+"\n" +
                    "WHERE ("+Const.ORDERS_ARRIVAL+" BETWEEN ? AND ?)\n" +
                    "   OR ("+Const.ORDERS_EVICTION+" BETWEEN ? AND ?) OR ("+Const.ORDERS_ARRIVAL+" < ? AND "+Const.ORDERS_EVICTION+" > ? ) or ("+Const.ORDERS_ID+" is NULL )");
            java.sql.Date b = java.sql.Date.valueOf(begin);
            java.sql.Date e = java.sql.Date.valueOf(end);
            statement.setDate(1, b);
            statement.setDate(2, e);
            statement.setDate(3, b);
            statement.setDate(4, e);
            statement.setDate(5, b);
            statement.setDate(6, e);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Room room = new Room(resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9),resultSet.getInt(10),resultSet.getInt(11));
                LocalDate arrival = (resultSet.getDate(2)!=null)?(resultSet.getDate(2).toLocalDate()):(null);
                LocalDate eviction = (resultSet.getDate(3)!=null)?(resultSet.getDate(3).toLocalDate()):(null);
                Order order = new Order.Builder(arrival,eviction).withId(resultSet.getInt(1)).withRoom(room).build();
//                        new Order(resultSet.getInt(1), room, new Guest(), new Employee(), arrival, eviction);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public ArrayList<Room>  getRooms(){
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT "+Const.ROOMS_TABLE+".* FROM "+Const.ROOMS_TABLE);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();
            while(resultSet.next()) {
                Room room = new Room(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8));
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public ArrayList<Order>  getOrdersForRoom(int roomId, LocalDate begin, LocalDate end){
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT "+Const.ORDERS_ARRIVAL+", "+Const.ORDERS_EVICTION+" FROM "+Const.ORDERS_TABLE+"\n" +
                    "WHERE ("+Const.ORDERS_ID_ROOM+" = ?) AND (("+Const.ORDERS_ARRIVAL+" BETWEEN ? AND ?)\n" +
                    "                            OR ("+Const.ORDERS_EVICTION+" BETWEEN ? AND ?) OR ("+Const.ORDERS_ARRIVAL+" < ? AND "+Const.ORDERS_EVICTION+" > ? ))");
            java.sql.Date b = java.sql.Date.valueOf(begin.plusDays(1));
            java.sql.Date e = java.sql.Date.valueOf(end.plusDays(1));
            statement.setInt(1,roomId);
            statement.setDate(2, b);
            statement.setDate(3, e);
            statement.setDate(4, b);
            statement.setDate(5, e);
            statement.setDate(6, b);
            statement.setDate(7, e);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Order order = new Order.Builder(resultSet.getDate(1).toLocalDate(),resultSet.getDate(2).toLocalDate()).build();
//                        (resultSet.getDate(1).toLocalDate(), resultSet.getDate(2).toLocalDate());
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public ArrayList<Order>  getOrdersList(){
        try (Connection conn = getDbConnection()) {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT "+Const.ROOMS_NUMBER+", "+Const.GUESTS_NAME+", "+Const.GUESTS_SURNAME+", "+Const.ORDERS_ARRIVAL+", "+Const.ORDERS_EVICTION+", "+Const.ORDERS_NOTICE+", "+Const.EMPLOYEES_NAME+", "+Const.EMPLOYEES_SURNAME+", "+Const.ORDERS_ID+" FROM "+Const.ORDERS_TABLE +
                    " LEFT JOIN "+Const.ROOMS_TABLE+" on "+Const.ORDERS_ID_ROOM+" = "+Const.ROOMS_ID +
                    " LEFT JOIN "+Const.EMPLOYEES_TABLE+" on "+Const.ORDERS_ID_EMPLOYEES+" = "+Const.EMPLOYEES_ID +
                    " LEFT JOIN "+Const.GUESTS_TABLE+" on "+Const.ORDERS_ID_GUEST+" = "+Const.GUESTS_ID);
            ArrayList<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Room room = new Room(resultSet.getInt(1));
                Guest guest = new Guest(resultSet.getString(3),resultSet.getString(2));
                Employee employee = new Employee(resultSet.getString(7),resultSet.getString(8));
                LocalDate arrival = (resultSet.getDate(4)!=null)?(resultSet.getDate(4).toLocalDate()):(null);
                LocalDate eviction = (resultSet.getDate(5)!=null)?(resultSet.getDate(5).toLocalDate()):(null);
                Order order = new Order.Builder(arrival,eviction).withId(resultSet.getInt(9)).withRoom(room).withGuest(guest).withEmployee(employee).withNotice(resultSet.getString(6)).build();
//                        (resultSet.getInt(9), room, guest, employee, arrival, eviction,resultSet.getString(6));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public void deleteOrder(int id){
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM "+Const.ORDERS_TABLE+" WHERE "+Const.ORDERS_ID+" = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Order getOrderById(int id){
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT "+Const.ROOMS_NUMBER+", "+Const.ROOMS_NUMBER_BEDS+", "+Const.ROOMS_TV+", "+Const.ROOMS_FRIDGE+", "+Const.ROOMS_AIRCONDITIONING+", "+Const.ROOMS_BALCONY+", "+Const.ROOMS_PRICE+",\n" +
                    "       "+Const.GUESTS_ID+", "+Const.GUESTS_NAME+", "+Const.GUESTS_SURNAME+", "+Const.GUESTS_PHONE+", "+Const.ORDERS_ARRIVAL+", "+Const.ORDERS_EVICTION+", "+Const.ORDERS_NOTICE+", "+Const.ORDERS_ID+",\n" +
                    "       "+Const.EMPLOYEES_NAME+", "+Const.EMPLOYEES_SURNAME+" FROM "+Const.ORDERS_TABLE+"\n" +
                    "                    LEFT JOIN "+Const.ROOMS_TABLE+" on "+Const.ORDERS_ID_ROOM+" = "+Const.ROOMS_ID+"\n" +
                    "                    LEFT JOIN "+Const.EMPLOYEES_TABLE+" on "+Const.ORDERS_ID_EMPLOYEES+" = "+Const.EMPLOYEES_ID+"\n" +
                    "                    LEFT JOIN "+Const.GUESTS_TABLE+" on "+Const.ORDERS_ID_GUEST+" = "+Const.GUESTS_ID+"\n" +
                    "where "+Const.ORDERS_ID+" = ?\n");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Room room = new Room(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                Guest guest = new Guest(resultSet.getInt(8),resultSet.getString(10),resultSet.getString(9),resultSet.getString(11));
                Employee employee = new Employee(resultSet.getString(16),resultSet.getString(17));
                LocalDate arrival = (resultSet.getDate(12)!=null)?(resultSet.getDate(12).toLocalDate()):(null);
                LocalDate eviction = (resultSet.getDate(13)!=null)?(resultSet.getDate(13).toLocalDate()):(null);
                return new Order.Builder(arrival,eviction).withId(resultSet.getInt(15)).withRoom(room).withGuest(guest).withEmployee(employee).withNotice(resultSet.getString(14)).build();
//                        (resultSet.getInt(15), room, guest, employee, arrival, eviction,resultSet.getString(14));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //TODO return order&??
    }
    public int saveOrder(Order order){
        if(getOrdersForRoom(order.getRoom().getId(),order.getArrival().plusDays(1),order.getEviction().minusDays(1)).size()>0)
            return 0;
        try (Connection conn = getDbConnection()) {
            Guest guest = order.getGuest();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO "+Const.GUESTS_TABLE+" ("+Const.GUESTS_NAME+", "+Const.GUESTS_SURNAME+", "+Const.GUESTS_PHONE+") VALUES (?, ?, ?)");
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setString(3, guest.getPhone());
            statement.execute();
            statement = conn.prepareStatement("SELECT "+Const.GUESTS_ID+" FROM "+Const.GUESTS_TABLE+" WHERE "+Const.GUESTS_NAME+" = ? AND "+Const.GUESTS_SURNAME+" = ? AND "+Const.GUESTS_PHONE+" = ?");
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setString(3, guest.getPhone());
            ResultSet resultSet = statement.executeQuery();
            int idGuest = 0;
            if(resultSet.next())
                idGuest = resultSet.getInt(1);
            statement = conn.prepareStatement("INSERT INTO "+Const.ORDERS_TABLE+" ("+Const.ORDERS_ID_ROOM+", "+Const.ORDERS_ID_GUEST+", "+Const.ORDERS_ARRIVAL+", "+Const.ORDERS_EVICTION+", "+Const.ORDERS_ID_EMPLOYEES+", "+Const.ORDERS_NOTICE+") VALUES (?, ?, ?, ?, ?, ?)");
            java.sql.Date a = java.sql.Date.valueOf(order.getArrival().plusDays(1));
            java.sql.Date e = java.sql.Date.valueOf(order.getEviction().plusDays(1));
            statement.setInt(1, order.getRoom().getId());
            statement.setInt(2, idGuest);
            statement.setDate(3, a);
            statement.setDate(4, e);
            statement.setInt(5, User.getInstance().getId());
            statement.setString(6, order.getNotice());
            statement.execute();
            statement = conn.prepareStatement("SELECT "+Const.ORDERS_ID+" FROM "+Const.ORDERS_TABLE+" WHERE "+Const.ORDERS_ID_ROOM+" = ? AND "+Const.ORDERS_ID_GUEST+" = ? AND "+Const.ORDERS_ARRIVAL+" = ? AND "+Const.ORDERS_EVICTION+" = ? AND "+Const.ORDERS_NOTICE+" = ? ");
            statement.setInt(1, order.getRoom().getId());
            statement.setInt(2, idGuest);
            statement.setDate(3, a);
            statement.setDate(4, e);
            statement.setString(5, order.getNotice());
            resultSet = statement.executeQuery();
            int id = 0;
            if(resultSet.next())
                id = resultSet.getInt(1);
            return id;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean saveChangeOrder(Order order, Guest guest){
        if(getOrdersForRoom(order.getRoom().getId(),order.getArrival().plusDays(1),order.getEviction().minusDays(1)).size()>1)
            return false;
        try (Connection conn = getDbConnection()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE "+Const.ORDERS_TABLE+" SET "+Const.ORDERS_ID_ROOM+" = ?, "+Const.ORDERS_ID_GUEST+" = ?, " +
                    Const.ORDERS_ARRIVAL+" = ?, "+Const.ORDERS_EVICTION+" = ?, "+Const.ORDERS_ID_EMPLOYEES+" = ?, "+Const.ORDERS_NOTICE+" = ? WHERE "+Const.ORDERS_ID+" = ?");
            java.sql.Date a = java.sql.Date.valueOf(order.getArrival().plusDays(1));
            java.sql.Date e = java.sql.Date.valueOf(order.getEviction().plusDays(1));
            statement.setInt(1, order.getRoom().getId());
            statement.setInt(2, guest.getId());
            statement.setDate(3, a);
            statement.setDate(4, e);
            statement.setInt(5, User.getInstance().getId());
            statement.setString(6, order.getNotice());
            statement.setInt(7, order.getId());
            statement.execute();
            statement = conn.prepareStatement("UPDATE "+Const.GUESTS_TABLE+" SET "+Const.GUESTS_NAME+" = ?, "+Const.GUESTS_SURNAME+" = ?, "+Const.GUESTS_PHONE+" = ? WHERE "+Const.GUESTS_ID+" = ?");
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setString(3, guest.getPhone());
            statement.setInt(4, guest.getId());
            statement.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
