package add.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Department {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField departmentIDField;

    @FXML
    private Button goBackButton;

    @FXML
    private Button addDepartmentButton;

    @FXML
    void addDepartmentMethod(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.addDepartment(departmentIDField.getText(), departmentField.getText());
    }

    @FXML
    void goBackMethod(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/add/add.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize() {
    }
}
