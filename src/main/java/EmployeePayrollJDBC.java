import java.sql.*;
import java.util.Enumeration;

public class EmployeePayrollJDBC {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSl=false";
        String userName = "root";
        String password = "bwj@1994";
        String query = "select * from employee_payroll";
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        listDrivers();
        try {
            System.out.println("Connecting to database" + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!!!!!" + con);
            Statement statement = con.createStatement();
            boolean result = statement.execute(query);
            if (result) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    System.out.println(id);
                    String name = rs.getString("Name");
                    System.out.println(name);
                    int salary = rs.getInt("Salary");
                    System.out.println(salary);
                    Date startDate = rs.getDate("StartDate");
                    System.out.println(startDate);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println("  " + driverClass.getClass().getName());
        }
    }
}
