package edit;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import show.stafflist.StafflistOut;

public class Edit {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox degreeBox;

    @FXML
    private CheckBox oldBox;

    @FXML
    private ChoiceBox<String> employee_id;

    @FXML
    private ChoiceBox<String> stafflist_id;

    @FXML
    private Button goBackButton;

    @FXML
    private Button editButton;

    @FXML
    void editMethod(ActionEvent event) {
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

        String query = "UPDATE oblik.employee SET stafflist_id = " + stafflist_id.getValue() + ", employee_degree = " + degree + ", employee_old = " + old + " WHERE employee_id = " + employee_id.getValue() + ";";

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
    ObservableList<String> staffList = FXCollections.observableArrayList();

    @FXML
    void setBox(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = null;
        String query = "SELECT employee_id FROM oblik.employee;";

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


        query = "SELECT stafflist_id FROM oblik.stafflist;";
        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()) {
                String s = resultSet.getString("stafflist_id");
                staffList.add(s);
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
        stafflist_id.setItems(staffList);
    }
}
