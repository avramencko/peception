package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.data_base.DataBaseHandler;
import sample.models.Order;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the controller, which is responsible for the window with a list of all orders
 */
public class OrdersListController {
    @FXML
    private GridPane gridPane;

    /**
     * when the scene is initialized, all order data is displayed
     */
    public void initialize() {
        redraw();
    }

    /**
     * updates the order list
     */
    private void redraw() {
        gridPane.getChildren().clear();
        ArrayList<Order> orderArrayList = DataBaseHandler.getInstance().getOrdersList();
        for (int i = 0; i < orderArrayList.size(); i++) {
            Order order = orderArrayList.get(i);
            Label room = new Label(String.valueOf(order.getRoom().getNumber()));
            room.setAlignment(Pos.TOP_RIGHT);
            Label guest = new Label(order.getGuest().getName() + "\n" + order.getGuest().getSurname());
            Label arrival = new Label(order.getArrival().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            Label eviction = new Label(order.getEviction().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            String not = (order.getNotice() != null) ? (order.getNotice()) : ("");
            not = (not.length()<20)?(not):not.substring(0,19);
            Text notice = new Text(not);
            Label employee = new Label(order.getEmployee().getName() + "\n" + order.getEmployee().getSurname());
            Button show = new Button("Показать");
            show.setOnAction(event -> showOrder(order.getId()));
            show.setPrefWidth(80);
            Button delete = new Button("Удалить");
            delete.setOnAction(event -> deleteOrder(order.getId()));
            delete.setPrefWidth(80);
            VBox vBox = new VBox(5);
            vBox.getChildren().addAll(show, delete);
            vBox.setAlignment(Pos.TOP_RIGHT);
            gridPane.add(room, 0, i*2);
            gridPane.add(guest, 1, i*2);
            gridPane.add(arrival, 2, i*2);
            gridPane.add(eviction, 3, i*2);
            gridPane.add(notice, 4, i*2);
            gridPane.add(employee, 5, i*2);
            gridPane.add(vBox, 6, i*2);
            for(int j = 0; j<6;j++){
                Separator separator = new Separator(Orientation.HORIZONTAL);
                gridPane.add(separator, j, i*2+1);
            }

        }
    }

    /**
     * returns to the main page
     */
    @FXML
    public void back() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/roomsList.fxml"));
            Main.parentWindow.setTitle("Главная");
            Main.parentWindow.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showOrder(int id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/editOrder.fxml"));
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        EditOrderController controller = loader.getController();
        controller.initData(id);
        stage.setOnCloseRequest(event -> {
            stage.close();
            redraw();
        });
        stage.show();
    }

    private void deleteOrder(int id) {
        AtomicBoolean delete = new AtomicBoolean(false);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox(10);
        layout.getStylesheets().add("sample/view/Styles.css");
        layout.setAlignment(Pos.CENTER);
        Label label = new Label("Вы уверены?");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        Button yes = new Button("Да");
        Button no = new Button("Отмена");
        yes.setOnAction(event->{window.close();
            delete.set(true);});
        no.setOnAction(event->window.close());
        hBox.getChildren().addAll(yes, no);
        layout.getChildren().addAll(label,hBox);
        Scene scene = new Scene(layout,200,150);
        window.setScene(scene);
        window.showAndWait();
        if(delete.get())
            DataBaseHandler.getInstance().deleteOrder(id);
        redraw();
    }
}
