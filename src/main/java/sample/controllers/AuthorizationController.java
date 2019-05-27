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

/**
 * Controller for the login page
 */
public class AuthorizationController {

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label err;


    /**
     * method checks the data entered. if the data is correct, go to the main page.
     * if the data is incorrect, displays an error message
     */
    @FXML
    public void enter() {

        String log = login.getText();
        String pass = password.getText();
        Employee employee;
        if (log != null && pass != null) {
            employee = DataBaseHandler.getInstance().authenticate(log, pass);
            if (employee != null && employee.getId() > 0) {
                User.getInstance().setInstance(employee);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/roomsList.fxml"));
                    Main.parentWindow.setTitle("Главная");
                    Main.parentWindow.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
