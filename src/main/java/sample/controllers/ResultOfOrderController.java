package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.data_base.DataBaseHandler;
import sample.files.Writer;
import sample.models.Order;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class ResultOfOrderController {
    private Order order;
    @FXML
    private Label number;
    @FXML
    private Label guestSurname;
    @FXML
    private Label guestName;
    @FXML
    private Label guestPhone;
    @FXML
    private Label employee;
    @FXML
    private Label price;
    @FXML
    private Label arrival;
    @FXML
    private Label eviction;

    public void initData(Order order){
        this.order = order;
        number.setText(String.valueOf(order.getRoom().getNumber()));
        guestSurname.setText(order.getGuest().getSurname());
        guestName.setText(order.getGuest().getName());
        guestPhone.setText(order.getGuest().getPhone());
        employee.setText(order.getEmployee().getName()+ " "+order.getEmployee().getSurname());
        arrival.setText(order.getArrival().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        eviction.setText(order.getEviction().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        int days = (int) Duration.between(order.getArrival().atStartOfDay(), order.getEviction().atStartOfDay()).toDays();
        price.setText(String.valueOf(order.getRoom().getPrice()*days));
    }
    @FXML
    private void save() {
        Writer writer = new Writer();
        writer.setOrder(order);
        writer.save("D:\\Учеба\\JAVA\\reception\\src\\main\\java\\ordersFiles",String.valueOf(order.getId()));
        //TODO КАК ПРОПИСАТЬ ПУТЬ?
    }

}
