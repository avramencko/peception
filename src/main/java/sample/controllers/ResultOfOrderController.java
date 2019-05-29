package sample.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_base.DataBaseHandler;
import sample.files.Writer;
import sample.models.Order;

import java.nio.file.Path;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * controller for the window that displays data about the order made
 */
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
    @FXML
    private TextField path;

    /**
     * initializes the order data fields
     * @param order - order data about which want to show
     */
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
        if(writer.save(path.getText(),String.valueOf(order.getId()))){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            VBox layout = new VBox(10);
            layout.getStylesheets().add("sample/view/Styles.css");
            layout.setAlignment(Pos.CENTER);
            Label label = new Label("Заказ был сохранен");
            Button button = new Button("ОК");
            button.setOnAction(event -> window.close());
            layout.getChildren().addAll(label, button);
            Scene scene = new Scene(layout, 200, 150);
            window.setScene(scene);
            window.showAndWait();
        }else{
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            VBox layout = new VBox(10);
            layout.getStylesheets().add("sample/view/Styles.css");
            layout.setAlignment(Pos.CENTER);
            Label label = new Label("Заказ не был сохранен. \nПроверьте путь для сохранения.");
            Button button = new Button("ОК");
            button.setOnAction(event -> window.close());
            layout.getChildren().addAll(label, button);
            Scene scene = new Scene(layout, 200, 150);
            window.setScene(scene);
            window.showAndWait();
        }


    }

}
