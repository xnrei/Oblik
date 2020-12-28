package show.stafflist;

public class StafflistOut {
    private String stafflist_id;
    private String department_name;
    private String position_name;

    public StafflistOut(String stafflist_id, String department_name, String position_name) {
        this.stafflist_id = stafflist_id;
        this.department_name = department_name;
        this.position_name = position_name;
    }

    public String getStafflist_id() {
        return stafflist_id;
    }

    public void setStafflist_id(String stafflist_id) {
        this.stafflist_id = stafflist_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
}
