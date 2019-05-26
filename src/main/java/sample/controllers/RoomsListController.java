package sample.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Main;
import sample.data_base.DataBaseHandler;
import sample.drawing.CalendarFactory;
import sample.drawing.OrderRectangle;
import sample.drawing.Timeline;
import sample.models.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RoomsListController {
    private final LocalDate today = LocalDate.now();
    private LocalDate beginPeriod = today;
    private LocalDate endPeriod = today.plusDays(14);
//    private LocalDate beginPeriod = LocalDate.of(2019,5,1);
//    private LocalDate endPeriod = LocalDate.of(2019,5,14);

    @FXML
    public VBox listRooms;
    @FXML
    public Label userData;
    @FXML
    public RadioButton tvRadioButton;
    @FXML
    public RadioButton fridgeRadioButton;
    @FXML
    public RadioButton airConditioningRadioButton;
    @FXML
    public RadioButton balconyRadioButton;
    @FXML
    public ChoiceBox bedsChoiceBox;
    @FXML
    public Slider limitPrice;
    @FXML
    public Label priceLabel;
    @FXML
    public Button todayButton;
    @FXML
    public DatePicker datePicker;

    private ArrayList<Room> rooms;

    public void initialize() {
        userData.setText(User.getInstance().getSurname() + " " + User.getInstance().getName());

        tvRadioButton.setOnAction(arg0 -> redraw());
        fridgeRadioButton.setOnAction(arg0 -> redraw());
        airConditioningRadioButton.setOnAction(arg0 -> redraw());
        balconyRadioButton.setOnAction(arg0 -> redraw());

        bedsChoiceBox.getItems().addAll(1, 2, 3, 4);
        bedsChoiceBox.getSelectionModel().selectFirst();
        bedsChoiceBox.setOnAction(arg0 -> redraw());

        rooms = DataBaseHandler.getInstance().getRooms();
        int min = 0;
        int max = 0;
if(rooms.size()>0) {
    min = rooms.get(0).getPrice();
    max = min;
    for (Room room : rooms) {
        if (room.getPrice() < min)
            min = room.getPrice();
        if (room.getPrice() > max)
            max = room.getPrice();
    }
}

        limitPrice.setMin(min);
        limitPrice.setMax(max);
        limitPrice.setValue(max);
        priceLabel.setText("Цена до: " + (int)limitPrice.getValue());
        limitPrice.valueProperty().addListener((observable, oldValue, newValue) -> {
            priceLabel.setText("Цена до: " + newValue.intValue());
            redraw();
        });


        String pattern = "dd.MM.yyyy";
        todayButton.setText("Сегодня: "+today.format(DateTimeFormatter.ofPattern(pattern)));
//        datePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today));
        datePicker.valueProperty().addListener((ObservableValue<? extends LocalDate> ov, LocalDate oldValue, LocalDate newValue) -> {
            beginPeriod = newValue;
            endPeriod = beginPeriod.plusDays(14);
            redraw();
        });
        redraw();
    }

    private void redraw() {

        ArrayList<Room> visibleRoom = (ArrayList<Room>) rooms.clone();
        int beds = 0;
        if (bedsChoiceBox.getValue() != null)
            beds = Integer.parseInt(bedsChoiceBox.getValue().toString());
        int limit = (int) limitPrice.getValue();
        for (Room room : rooms) {
            if (beds > room.getNumberBeds()) {
                visibleRoom.removeIf(e -> e.getId() == room.getId());
            } else if (tvRadioButton.isSelected() && !room.isTv()) {
                visibleRoom.removeIf(e -> e.getId() == room.getId());
            } else if (fridgeRadioButton.isSelected() && !room.isFridge()) {
                visibleRoom.removeIf(e -> e.getId() == room.getId());
            } else if (airConditioningRadioButton.isSelected() && !room.isAirConditioning()) {
                visibleRoom.removeIf(e -> e.getId() == room.getId());
            } else if (balconyRadioButton.isSelected() && !room.isBalcony()) {
                visibleRoom.removeIf(e -> e.getId() == room.getId());
            } else if (room.getPrice() > limit)
                visibleRoom.removeIf(e -> e.getId() == room.getId());
        }
        listRooms.getChildren().clear();
        for (Room room : visibleRoom) {
            ArrayList<Order> orders = DataBaseHandler.getInstance().getOrdersForRoom(room.getId(), beginPeriod, endPeriod.plusDays(1));
            List<Period> periods = new ArrayList<>();
            for (Order order : orders) {
                if (order.getArrival() != null) {
                    int days = (int) Duration.between(order.getArrival().atStartOfDay(), order.getEviction().atStartOfDay()).toDays();
                    periods.add(new Period(order.getArrival(), days));
                }

            }
            listRooms.getChildren().add(roomWrapper(room, periods));
        }

    }

    private HBox roomWrapper(Room room, List<Period> periods) {
        HBox root = new HBox();
        root.getStyleClass().add("room-wrapper");
        Pane pane = new Pane();
        pane.setPadding(new Insets(8,0,0,0));
        root.setMaxHeight(60d);

        Label label = new Label("Комн. №" + room.getNumber());
        label.setPadding(new Insets(16,0,0,0));
        Button button = new Button("Заказать");
        button.setPadding(new Insets(4,8,4,8));
        button.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/roomData.fxml"));
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader.load()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            RoomDataController controller = loader.getController();
            controller.initData(room);
            stage.setOnCloseRequest(event -> {
                stage.close();
                redraw();
            });
            stage.show();
        });

        ArrayList<Rectangle> rects = new ArrayList<>();
        OrderRectangle orderRectangle = new OrderRectangle();
        final double LENGTH_DAY_DOUBLE = 32;
        for (Period period : periods) {
            int begin = (int)Duration.between(beginPeriod.atStartOfDay(),period.getDate().atStartOfDay()).toDays();
            int days = period.getDays();
            rects.add(orderRectangle.getRectangleForTimeline(begin, days, LENGTH_DAY_DOUBLE,20));
        }
        Timeline timeLine = new Timeline();

        pane.getChildren().add(timeLine.getTimeLine1(beginPeriod,endPeriod));
        for (Rectangle rectangle : rects)
            pane.getChildren().add(rectangle);
        root.getChildren().addAll(label, pane, button);
//        root.setSpacing(16);
        return root;
    }

    @FXML
    public void exit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/authorization.fxml"));
        Main.parentWindow.setTitle("authorization");
        Main.parentWindow.setScene(new Scene(root));
    }

    @FXML
    public void right(){
        beginPeriod = beginPeriod.plusDays(14);
        endPeriod = endPeriod.plusDays(14);
        redraw();
    }
    @FXML
    public void left(){
        beginPeriod = beginPeriod.minusDays(14);
        endPeriod = endPeriod.minusDays(14);
        redraw();
    }
    @FXML
    public void minusDay(){
        beginPeriod = beginPeriod.minusDays(1);
        endPeriod = endPeriod.minusDays(1);
        redraw();
    }
    @FXML
    public void plusDay(){
        beginPeriod = beginPeriod.plusDays(1);
        endPeriod = endPeriod.plusDays(1);
        redraw();
    }
    @FXML
    public void goToToday(){
        beginPeriod = today;
        endPeriod = beginPeriod.plusDays(14);
        redraw();
    }
    @FXML
    public void showOrdersList() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ordersList.fxml"));
        Main.parentWindow.setTitle("Заказы");
        Main.parentWindow.setScene(new Scene(root));
    }
}
