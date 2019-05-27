package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_base.DataBaseHandler;
import sample.drawing.CalendarFactory;
import sample.drawing.OrderRectangle;
import sample.models.Guest;
import sample.models.Order;
import sample.models.Room;
import sample.models.User;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDataController {

    private final LocalDate today = LocalDate.now();
    private LocalDate beginPeriod;
    private LocalDate endPeriod;
    private final Color blue = Color.rgb(100, 100, 255, 0.7);


    @FXML
    private Label NumberLabel;
    @FXML
    private Label BedsLabel;
    @FXML
    private Label TVLabel;
    @FXML
    private Label FridgeLabel;
    @FXML
    private Label AirConditioningLabel;
    @FXML
    private Label BalconyLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private DatePicker ArrivalDatePicker;
    @FXML
    private DatePicker EvictionDatePicker;
    @FXML
    private GridPane CalendarGridPane;
    @FXML
    private AnchorPane CalendarAnchorPane;
    @FXML
    private TextField guestName;
    @FXML
    private TextField guestSurname;
    @FXML
    private TextField guestPhone;
    @FXML
    private TextArea notice;

    private Room room;

    public void initialize() {
        DayOfWeek[] dayOfWeeks = new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SATURDAY};
        int beg = 0;
        for (int i = 0; i < 7; i++) {
            if (today.getDayOfWeek() == dayOfWeeks[i]) {
                beg = i;
            }
        }
        beginPeriod = today.minusDays(beg);
        endPeriod = beginPeriod.plusDays(35);
        CalendarFactory calendarFactory = new CalendarFactory();

        ArrivalDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today));
        EvictionDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today));
        ArrivalDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            EvictionDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory((newValue != null) ? (newValue.plusDays(1)) : (today)));
            fillCalendar();
        });
        EvictionDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            ArrivalDatePicker.setDayCellFactory(calendarFactory.getDayCellFactory(today, (newValue != null) ? (newValue.minusDays(1)) : (today.plusMonths(3))));
            fillCalendar();
        });
    }

    void initData(Room room) {
        this.room = room;
        if (this.room != null) {
            NumberLabel.setText(room.getNumber() + "");
            BedsLabel.setText(room.getNumberBeds() + "");
            TVLabel.setText((room.isTv()) ? ("есть") : ("нет"));
            FridgeLabel.setText((room.isFridge()) ? ("есть") : ("нет"));
            AirConditioningLabel.setText((room.isAirConditioning()) ? ("есть") : ("нет"));
            BalconyLabel.setText((room.isBalcony()) ? ("есть") : ("нет"));
            PriceLabel.setText(room.getPrice() + "");
        }
        fillCalendar();
    }

    private void fillCalendar() {
        LocalDate current = beginPeriod;
        LocalDate b = beginPeriod;
        ArrayList<Rectangle> rects = new ArrayList<>();
        boolean isA = false;
        boolean isE = false;
        if (ArrivalDatePicker.getValue() != null)
            isA = true;
        if (EvictionDatePicker.getValue() != null)
            isE = true;
        CalendarGridPane.getChildren().clear();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 7; i++) {
                Label label = new Label();
                if (current.isBefore(endPeriod.plusDays(1))) {
                    String dayOfMonth = (current.getDayOfMonth() < 10) ? ("0" + current.getDayOfMonth()) : (current.getDayOfMonth() + "");
                    String month = (current.getMonthValue() < 10) ? ("0" + current.getMonthValue()) : (current.getMonthValue() + "");
                    label.setText(dayOfMonth + "." + month);
                    current = current.plusDays(1);
                }
                CalendarGridPane.add(label, i, j);
            }
            ArrayList<Order> orders = DataBaseHandler.getInstance().getOrdersForRoom(this.room.getId(), b, current.plusDays(1));

            OrderRectangle orderRectangle = new OrderRectangle();
            final double WIDTH_DAY_DOUBLE = 60d;
            final double HEIGHT_DAY_DOUBLE = 30d;
            for (Order order : orders) {
                int begin = (int) Duration.between(b.atStartOfDay(), order.getArrival().atStartOfDay()).toDays();
                int days = (int) Duration.between(order.getArrival().atStartOfDay(), order.getEviction().atStartOfDay()).toDays();
                rects.add(orderRectangle.getRectangleForCalendar(begin, days, WIDTH_DAY_DOUBLE, HEIGHT_DAY_DOUBLE, j));
            }
            if (isA && (!isE)) {
                int begin = (int) Duration.between(b.atStartOfDay(), ArrivalDatePicker.getValue().atStartOfDay()).toDays();
                if (begin >= 0 && begin < 7) {
                    rects.add(orderRectangle.getRectangleForCalendar(begin, 1, WIDTH_DAY_DOUBLE, HEIGHT_DAY_DOUBLE, j, blue));
                }
            }
            if (isE && (!isA)) {
                int begin = (int) Duration.between(b.atStartOfDay(), EvictionDatePicker.getValue().minusDays(1).atStartOfDay()).toDays();
                if (begin >= 0 && begin < 7) {
                    rects.add(orderRectangle.getRectangleForCalendar(begin, 1, WIDTH_DAY_DOUBLE, HEIGHT_DAY_DOUBLE, j, blue));
                }
            }
            if (isE && isA) {
                int begin = (int) Duration.between(b.atStartOfDay(), ArrivalDatePicker.getValue().atStartOfDay()).toDays();
                int days = (int) Duration.between(ArrivalDatePicker.getValue().atStartOfDay(), EvictionDatePicker.getValue().atStartOfDay()).toDays();
                rects.add(orderRectangle.getRectangleForCalendar(begin, days, WIDTH_DAY_DOUBLE, HEIGHT_DAY_DOUBLE, j, blue));
            }
            b = current;
        }
        CalendarAnchorPane.getChildren().removeIf(e -> e instanceof Rectangle);
        CalendarAnchorPane.getChildren().addAll(rects);
    }

    @FXML
    private void saveOrder() {
        boolean correctData = false;
        if (ArrivalDatePicker.getValue() != null && EvictionDatePicker.getValue() != null) {
            Guest guest = new Guest(guestSurname.getText(), guestName.getText(), guestPhone.getText());
            Order order = new Order.Builder(ArrivalDatePicker.getValue(), EvictionDatePicker.getValue()).withRoom(room).withGuest(guest).withEmployee(User.getInstance()).withNotice(notice.getText()).build();
//                (room, guest, User.getInstance(), ArrivalDatePicker.getValue(), EvictionDatePicker.getValue(),notice.getText());
            int id = DataBaseHandler.getInstance().saveOrder(order);
            if (id > 0) {
                order.setId(id);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/resultOfOrder.fxml"));
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene(loader.load()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ResultOfOrderController controller = loader.getController();
                controller.initData(order);
                stage.setOnCloseRequest(event -> {
                    stage.close();
                    fillCalendar();
                });
                ArrivalDatePicker.setValue(null);
                EvictionDatePicker.setValue(null);
                stage.show();

                correctData = true;
            }
        }
        if (correctData) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            VBox layout = new VBox(10);
            layout.getStylesheets().add("sample/view/Styles.css");
            layout.setAlignment(Pos.CENTER);
            Label label = new Label("Заказ не был сохранен. \nПроверьте введенные данные.");
            Button button = new Button("ОК");
            button.setOnAction(event -> window.close());
            layout.getChildren().addAll(label, button);
            Scene scene = new Scene(layout, 200, 150);
            window.setScene(scene);
            window.showAndWait();
        }

    }
}
