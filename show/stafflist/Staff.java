package show.stafflist;

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

public class Staff {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StafflistOut> showAllTable;

    @FXML
    private TableColumn<EmployeeOut, String> allID;

    @FXML
    private TableColumn<EmployeeOut, String> depID;

    @FXML
    private TableColumn<EmployeeOut, String> posID;

    @FXML
    private Button goBackButton;

    ResultSet resultSet = null;

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

    private void refreshTable(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ObservableList<StafflistOut> staffList = FXCollections.observableArrayList();
        //String query = "SELECT " + Employee.TABLE + "." + Employee.ID + "," + Employee.TABLE + "." + Employee.SURNAME + "," + Employee.TABLE + "." + Employee.NAME + "," + Employee.TABLE + "." + Employee.FNAME + "," + Employee.TABLE + "." + Employee.STID + "\nFROM oblik." + Employee.TABLE + ";";
        //String query = "SELECT stafflist_id, department_id, position_id FROM oblik.stafflist;";
        String query = "select stafflist.stafflist_id, department.department_name, position.position_name from stafflist left outer join department on stafflist.department_id = department.department_id left outer join position on stafflist.position_id = position.position_id;";
        try {
            PreparedStatement prStr = dbHandler.getDbConnection().prepareStatement(query);
            resultSet = prStr.executeQuery();

            while (resultSet.next()){
                staffList.add(new StafflistOut(resultSet.getString("stafflist_id"),
                        resultSet.getString("department_name"), resultSet.getString("position_name")));
                showAllTable.setItems(staffList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        allID.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("stafflist_id"));
        depID.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("department_name"));
        posID.setCellValueFactory(new PropertyValueFactory<EmployeeOut, String>("position_name"));
    }

    @FXML
    void initialize() {
        refreshTable();
    }
}
