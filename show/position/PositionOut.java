package show.position;

public class PositionOut {
    private String position_id;
    private String position_name;
    private String position_salary;

    public PositionOut(String position_id, String position_name, String position_salary) {
        this.position_id = position_id;
        this.position_name = position_name;
        this.position_salary = position_salary;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_salary() {
        return position_salary;
    }

    public void setPosition_salary(String position_salary) {
        this.position_salary = position_salary;
    }
}
