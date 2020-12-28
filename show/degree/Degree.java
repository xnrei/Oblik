package show.degree;

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

public class Degree {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<EmployeeOut> showAllTable;

    @FXML
    private TableColumn<EmployeeOut, String> allID;

    @FXML
    private TableColumn<EmployeeOut, String> Surname;

    @FXML
    private TableColumn<EmployeeOut, String> allName;

    @FXML
    private TableColumn<EmployeeOut, String> allFName;

    @FXML
    private TableColumn<EmployeeOut, String> allStafflist;

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
        ObservableList<EmployeeOut> employeeList = FXCollections.observableArrayList();
        //String query = "SELECT " + Employee.TABLE + "." + Employee.ID + "," + Employee.TABLE + "." + Employee.SURNAME + "," + Employee.TABLE + "." + Employee.NAME + "," + Employee.TABLE + "." + Employee.FNAME + "," + Employee.TABLE + "." + Employee.STID + "\nFROM oblik." + Employee.TABLE + ";";
        String query = "SELECT employee_id, employee_surname, employee_name, employee_fname, stafflist_id FROM oblik.employee WHERE employee_degree = 1;";
        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                employeeList.add(new EmployeeOut(resultSet.getString("employee_id"),
                        resultSet.getString("employee_surname"), resultSet.getString("employee_name"),
                        resultSet.getString("employee_fname"), resultSet.getString("stafflist_id")));
                showAllTable.setItems(employeeList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        allID.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("employee_id"));
        Surname.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("employee_surname"));
        allName.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("employee_name"));
        allFName.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("employee_fname"));
        allStafflist.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("stafflist_id"));
    }

    @FXML
    void initialize() {
        refreshTable();
    }
}
