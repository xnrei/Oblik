package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.sql.*;

public class DatabaseHandler extends Config{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return  dbConnection;
    }

    public void addDepartment(String departmentID, String departmentName){
        String insert = "INSERT INTO " + Department.TABLE + "(" + Department.ID + "," + Department.NAME + ")" + "VALUES(?,?);";

        try {
            PreparedStatement prStr = getDbConnection().prepareStatement(insert);
            prStr.setString(1, departmentID);
            prStr.setString(2, departmentName);
            prStr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPosition(String positionID, String positionName, String positionSalary){
        String insert = "INSERT INTO " + Position.TABLE + " (" + Position.ID + "," + Position.NAME + "," + Position.SALARY + ")" + "VALUES(?,?,?);";

        try {
            PreparedStatement prStr = getDbConnection().prepareStatement(insert);
            prStr.setString(1, positionID);
            prStr.setString(2, positionName);
            prStr.setString(3, positionSalary);
            prStr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStafflist(String stafflistID, String departmentID, String positionID){
        String insert = "INSERT INTO " + Stafflist.TABLE + "(" + Stafflist.ID + "," + Stafflist.DEPID + "," + Stafflist.POSID + ")" + "VALUES(?,?,?);";

        try {
            PreparedStatement prStr = getDbConnection().prepareStatement(insert);
            prStr.setString(1, stafflistID);
            prStr.setString(2, departmentID);
            prStr.setString(3, positionID);
            prStr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String employeeID, String employeeSurname, String employeeName, String employeeFName, String stafflistID, String employeeDegree, String employeeOld){
        String insert = "INSERT INTO " + Employee.TABLE + "(" + Employee.ID + "," + Employee.SURNAME + "," + Employee.NAME + "," + Employee.FNAME + "," + Employee.STID + "," + Employee.DEGREE + "," + Employee.OLD + "," + Employee.ISFIRED + ")" + "VALUES(?,?,?,?,?,?,?,?);";


        try {
            PreparedStatement prStr = getDbConnection().prepareStatement(insert);
            prStr.setString(1, employeeID);
            prStr.setString(2, employeeSurname);
            prStr.setString(3, employeeName);
            prStr.setString(4, employeeFName);
            prStr.setString(5, stafflistID);
            prStr.setString(6, employeeDegree);
            prStr.setString(7, employeeOld);
            prStr.setString(8, "0");
            prStr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
