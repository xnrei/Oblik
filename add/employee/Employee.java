package add.employee;

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
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Employee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField fNameField;

    @FXML
    private ChoiceBox<String> stafflist_id;

    @FXML
    private TextField employeeField;

    @FXML
    private CheckBox degreeBox;

    @FXML
    private CheckBox oldBox;

    @FXML
    private Button goBackButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button addEmployeeButton;

    private void showError(){
        errorLabel.setTextFill(Color.RED);
        errorLabel.setText("Помилка");
    }

    @FXML
    void addEmployeeMethod(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String old = new String();
        String degree = new String();

        if(degreeBox.isSelected())
            degree = "1";
        else
            degree = "0";

        if(oldBox.isSelected())
            old = "1";
        else
            old = "0";

        dbHandler.addEmployee(employeeField.getText(), surnameField.getText(), nameField.getText(), fNameField.getText(),  stafflist_id.getValue(), degree, old);
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

    ObservableList<String> staffList = FXCollections.observableArrayList();

    @FXML
    void setBox(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = null;
        String query = "SELECT stafflist_id FROM oblik.stafflist;";

        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                String s = resultSet.getString("stafflist_id");
                staffList.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            showError();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        setBox();
        stafflist_id.setItems(staffList);
    }
}
