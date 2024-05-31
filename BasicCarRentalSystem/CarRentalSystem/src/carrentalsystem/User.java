
package carrentalsystem;

import java.sql.*;




public class User extends Person {
    
    protected int age;
    protected int budget;
    protected boolean driverLiceance;

    public User(int age, int budget, boolean driverLiceance, int ID, String name, String surname, String email, String password) {
        super(ID, name, surname, email, password);
        this.age = age;
        this.budget = budget;
        this.driverLiceance = driverLiceance;
    }
    
    

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean isDriverLiceance() {
        return driverLiceance;
    }

    public void setDriverLiceance(boolean driverLiceance) {
        this.driverLiceance = driverLiceance;
    }

    
    public static boolean login(String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // CONNECT SQLITE DATABASE
            String url = "jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db";
            connection = DriverManager.getConnection(url);

            // CREATE SQL QUERY
            String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // EXECUTE THE QUERY AND GET RESULT
            resultSet = preparedStatement.executeQuery();

            // IF YOU HAVE GOT A RESULT RETURN TRUE
            if (resultSet.next()) {
                System.out.println("Your ID is = "+resultSet.getInt("ID"));
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // CLOSE SOURCES
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // IF YOU HAVE NOT GOT A RESULT RETURN FALSE
        return false;
    }
    
}
