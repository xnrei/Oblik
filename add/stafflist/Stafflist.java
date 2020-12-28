package add.stafflist;

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

public class Stafflist {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> department_id;

    @FXML
    private ChoiceBox<String> position_id;

    @FXML
    private TextField stafflistIDField;

    @FXML
    private Button goBackButton;

    @FXML
    private Button addStafflistButton;

    @FXML
    void addStafflistMethod(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.addStafflist(stafflistIDField.getText(), department_id.getValue(), position_id.getValue());
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

    ObservableList<String> departnentList = FXCollections.observableArrayList();
    ObservableList<String> positionList = FXCollections.observableArrayList();

    @FXML
    void setBox(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = null;
        String query = "SELECT department_id FROM oblik.department;";

        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                String s = resultSet.getString("department_id");
                departnentList.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        query = "SELECT position_id FROM oblik.position;";
        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()) {
                String s = resultSet.getString("position_id");
                positionList.add(s);
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
        department_id.setItems(departnentList);
        position_id.setItems(positionList);
    }
}
