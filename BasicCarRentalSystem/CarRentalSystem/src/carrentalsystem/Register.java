
package carrentalsystem;

import java.sql.*;
import java.util.Scanner;



public class Register {
    
    
    
    public static void register() throws SQLException {
        
        // CONNECT SQLITE DATABASE
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db");
        Statement st = c.createStatement();
        Scanner input = new Scanner(System.in);
        
        // GET SOME VALUES FROM USER THAT WANT TO REGISTER
        System.out.println("Please enter your name");
        String inputName = input.next();
        System.out.println("Please enter your surname");
        String inputSurname = input.next();
        System.out.println("Please enter your email");
        String inputEmail = input.next();
        System.out.println("Please enter your password");
        String inputPassword = input.next();
        System.out.println("Please enter your age");
        int inputAge = input.nextInt();
        System.out.println("Please enter your budget");
        double inputBudget = input.nextDouble();
        System.out.println("Do you have got an driver liceance ? (If you have got please write 1 otherwise write 0)");
        int inputDriverLiceance = input.nextInt();
        
        //CREATE QUERY TO INSERT VALUES SQL DATABASE
        String query = "INSERT INTO User (name,surname,email,password,age,budget,driverLiceance) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pt = c.prepareStatement(query);
        pt.setString(1, inputName);
        pt.setString(2, inputSurname);
        pt.setString(3, inputEmail);
        pt.setString(4, inputPassword);
        pt.setInt(5, inputAge);
        pt.setDouble(6, inputBudget);
        pt.setInt(7, inputDriverLiceance);
        
        // INSERT VALUES THAT WE GOT INTO SQLITE USER TABLE
        pt.executeUpdate();
        
    }
    
    
}
