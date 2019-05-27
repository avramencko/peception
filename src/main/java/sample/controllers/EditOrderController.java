package sample.controllers;

import com.sun.corba.se.spi.orb.ORBData;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_base.DataBaseHandler;
import sample.drawing.CalendarFactory;
import sample.models.Guest;
import sample.models.Order;
import sample.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;


public class EditOrderController {
    private Order order;
    private ArrayList<Room> rooms;
    private final LocalDate today = LocalDate.now();

    @FXML
    public ComboBox numberCB;
    @FXML
    public Label numberBedsLabel;
    @FXML
    public Label tvLabel;
    @FXML
    public Label fridgeLabel;
    @FXML
    public Label airConditioningLabel;
    @FXML
    public Label balconyLabel;
    @FXML
    public Label priceLabel;
    @FXML
    private DatePicker ArrivalDatePicker;
    @FXML
    private DatePicker EvictionDatePicker;
    @FXML
    private Label employeeLabel;
    @FXML
    private TextField guestName;
    @FXML
    private TextField guestSurname;
    @FXML
    private TextField guestPhone;
    @FXML
    private TextArea notice;


    public void initData(int id) {
        rooms = DataBaseHandler.getInstance().getRooms();
        this.order = DataBaseHandler.getInstance().getOrderById(id);
        int[] numbers = rooms.stream().mapToInt(Room::getNumber).toArray();
        for (int i : numbers)
            numberCB.getItems().add(i);
        numberCB.getSelectionModel().select((Object) order.getRoom().getNumber());
        numberCB.setOnAction(arg0 -> redraw((int) numberCB.getValue()));

        CalendarFactory calendarFactory = new CalendarFactory();
        ArrivalDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today));
        EvictionDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today));
        ArrivalDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            EvictionDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory((newValue != null) ? (newValue.plusDays(1)) : (today)));
        order.setArrival(ArrivalDatePicker.getValue());
        });
        EvictionDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            ArrivalDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today, (newValue != null) ? (newValue.minusDays(1)) : (today.plusMonths(3))));
            order.setEviction(EvictionDatePicker.getValue());
        });
        ArrivalDatePicker.setValue(order.getArrival());
        EvictionDatePicker.setValue(order.getEviction());
        employeeLabel.setText(order.getEmployee().getName() + " " + order.getEmployee().getSurname());
        guestName.setText(order.getGuest().getName());
        guestSurname.setText(order.getGuest().getSurname());
        guestPhone.setText(order.getGuest().getPhone());
        notice.setText(order.getNotice());
        redraw((int) numberCB.getValue());
    }

    private void redraw(int number) {
        for (Room room : rooms)
            if (room.getNumber() == number) {
                numberBedsLabel.setText(String.valueOf(room.getNumberBeds()));
                tvLabel.setText(room.isTv() ? "есть" : "нет");
                fridgeLabel.setText(room.isFridge() ? "есть" : "нет");
                airConditioningLabel.setText(room.isAirConditioning() ? "есть" : "нет");
                balconyLabel.setText(room.isBalcony() ? "есть" : "нет");
                priceLabel.setText(String.valueOf(room.getPrice()));

                order.setRoom(room);
            }
    }
    @FXML
    private void save(){
        boolean correctData = false;
        if(order.getArrival()!=null&&order.getEviction()!=null) {
            Guest guest = order.getGuest();
            guest.setSurname(guestSurname.getText());
            guest.setName(guestName.getText());
            guest.setPhone(guestPhone.getText());
            order.setNotice(notice.getText());
            if (DataBaseHandler.getInstance().saveChangeOrder(order, guest)) {
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                VBox layout = new VBox(10);
                layout.getStylesheets().add("sample/view/Styles.css");
                layout.setAlignment(Pos.CENTER);
                Label label = new Label("Изменения сохранены.");
                Button button = new Button("ОК");
                button.setOnAction(event -> window.close());
                layout.getChildren().addAll(label, button);
                Scene scene = new Scene(layout, 200, 150);
                window.setScene(scene);
                window.showAndWait();
                correctData = true;
            }
        }
        if(correctData){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            VBox layout = new VBox(10);
            layout.getStylesheets().add("sample/view/Styles.css");
            layout.setAlignment(Pos.CENTER);
            Label label = new Label("Изменения не были сохранены. \nПроверьте введенные данные.");
            Button button = new Button("ОК");
            button.setOnAction(event->window.close());
            layout.getChildren().addAll(label,button);
            Scene scene = new Scene(layout,200,150);
            window.setScene(scene);
            window.showAndWait();
        }
    }
}
