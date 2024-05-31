
package carrentalsystem;

import java.sql.*;
import java.util.Scanner;


public class Admin extends Person {

    public Admin(int ID, String name, String surname, String email, String password) {
        super(ID, name, surname, email, password);
    }

    public static void addAdmin() throws SQLException{
         //CONNECT SQL DATABSE
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db");
        Statement st = c.createStatement();
        Scanner input = new Scanner(System.in);
        //GET SOME VALUES OF NEW ADMIN FROM ADMIN
        System.out.println("Please enter your email");
        String inputEmail = input.next();
        System.out.println("Please enter your password");
        String inputPassword = input.next();
        //CREATE SQL QUERY
        String query = "INSERT INTO Admin (email,password) VALUES (?,?)";
        PreparedStatement pt = c.prepareStatement(query);
        pt.setString(1, inputEmail);
        pt.setString(2, inputPassword);
        // INSERT VALUES THAT WE GOT INTO SQLITE ADMIN TABLE
        pt.executeUpdate();

        
        
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
            String sql = "SELECT * FROM Admin WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // EXECUTE THE QUERY AND GET RESULT
            resultSet = preparedStatement.executeQuery();

            // IF YOU HAVE GOT A RESULT RETURN TRUE
            if (resultSet.next()) {
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
    
    
    
    
    

