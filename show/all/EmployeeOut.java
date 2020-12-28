package show.all;

public class EmployeeOut {
    private String employee_id;
    private String employee_surname;
    private String employee_name;
    private String employee_fname;
    private String stafflist_id;

    public EmployeeOut(String employee_id, String employee_surname, String employee_name, String employee_fname, String stafflist_id) {
        this.employee_id = employee_id;
        this.employee_surname = employee_surname;
        this.employee_name = employee_name;
        this.employee_fname = employee_fname;
        this.stafflist_id = stafflist_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_surname() {
        return employee_surname;
    }

    public void setEmployee_surname(String employee_surname) {
        this.employee_surname = employee_surname;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_fname() {
        return employee_fname;
    }

    public void setEmployee_fname(String employee_fname) {
        this.employee_fname = employee_fname;
    }

    public String getStafflist_id() {
        return stafflist_id;
    }

    public void setStafflist_id(String stafflist_id) {
        this.stafflist_id = stafflist_id;
    }
}