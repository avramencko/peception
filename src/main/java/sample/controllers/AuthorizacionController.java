package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.data_base.DataBaseHandler;
import sample.models.Employee;
import sample.models.User;

import java.io.IOException;

public class AuthorizacionController {
    @FXML
    private AnchorPane content;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label err;

    @FXML
    public void button1(ActionEvent event) throws IOException {

        String log = login.getText();
        String pass = password.getText();
        Employee employee;
        if(log!=null&&pass!=null) {
            employee = DataBaseHandler.getInstance().authenticate(log, pass);
            if (employee != null && employee.getId() > 0) {
                User.getInstance().setInstance(employee);
                Parent root = FXMLLoader.load(getClass().getResource("../view/roomsList.fxml"));
                Main.parentWindow.setTitle("Главная");
                Main.parentWindow.setScene(new Scene(root));
            } else {
                err.setText("Неверный логин или пароль");
            }
        }
    }
    //TODO DELETE
    public void initialize() {
        login.setText("admin");
        password.setText("admin");
    }
}
