package fire;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Fire {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> employee_id;

    @FXML
    private Button fireButton;

    @FXML
    private Button goBackButton;

    @FXML
    void fireMethod(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String query = "UPDATE employee SET employee_isfired = 1 WHERE employee_id = " + employee_id.getValue() + ";";

        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            prStr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBackMethod(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/sample.fxml"));

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

    ObservableList<String> employeeList = FXCollections.observableArrayList();

    @FXML
    void setBox(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = null;
        String query = "SELECT employee_id FROM oblik.employee WHERE employee_isfired = 0;";

        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                String s = resultSet.getString("employee_id");
                employeeList.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        setBox();
        employee_id.setItems(employeeList);
    }
}
