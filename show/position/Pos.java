package show.position;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import show.all.EmployeeOut;

public class Pos {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PositionOut> showAllTable;

    @FXML
    private TableColumn<EmployeeOut, String> allID;

    @FXML
    private TableColumn<EmployeeOut, String> name;

    @FXML
    private TableColumn<EmployeeOut, String> salary;

    @FXML
    private Button goBackButton;

    @FXML
    void goBackButton(ActionEvent event) {
        goBackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/show/show.fxml"));

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

    ResultSet resultSet = null;

    @FXML
    private void refreshTable(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ObservableList<PositionOut> positionList = FXCollections.observableArrayList();
        //String query = "SELECT " + Employee.TABLE + "." + Employee.ID + "," + Employee.TABLE + "." + Employee.SURNAME + "," + Employee.TABLE + "." + Employee.NAME + "," + Employee.TABLE + "." + Employee.FNAME + "," + Employee.TABLE + "." + Employee.STID + "\nFROM oblik." + Employee.TABLE + ";";
        String query = "SELECT position_id, position_name, position_salary FROM oblik.position;";
        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                positionList.add(new PositionOut(resultSet.getString("position_id"),
                        resultSet.getString("position_name"), resultSet.getString("position_salary")));
                showAllTable.setItems(positionList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        allID.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("position_id"));
        name.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("position_name"));
        salary.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("position_salary"));
    }

    @FXML
    void initialize() {
        refreshTable();
    }
}
