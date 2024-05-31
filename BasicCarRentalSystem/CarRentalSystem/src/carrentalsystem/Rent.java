
package carrentalsystem;

import java.sql.*;
import java.util.Scanner;


public class Rent {


    public static void rent() throws SQLException {

        Scanner input = new Scanner(System.in);
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db");
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Car");
        
         //SHOW CARS WITH USING OVERRIDE
        while (rs.next()) {
            Car car = new Car(rs.getInt("ID"), rs.getString("brand"), rs.getString("model"), rs.getInt("carYear"), rs.getInt("dailyRentalRate"), rs.getString("bodyType"), rs.getString("fuelType"), rs.getString("transmission"), rs.getBoolean("rented"));
            System.out.println(car.getInfo());
        }

        System.out.println("Which car do you want to rent? Please enter the number of the car:");
        int carId = input.nextInt();

        // CHECK THE CAR THAT CHOOSEN
        ResultSet selectedCarRs = st.executeQuery("SELECT * FROM Car WHERE ID = " + carId);
        if (selectedCarRs.next()) {
            boolean isRented = selectedCarRs.getBoolean("rented");
            int dailyRentalRate = selectedCarRs.getInt("dailyRentalRate");

            if (isRented) {
                System.out.println("Car rental failed. The car is already rented.");
            } else {
                // ASK HOW MANY DAYS TO THE USER
                System.out.println("How many days do you want to rent?");
                int howManyDays = input.nextInt();

                System.out.println("Please can you write down your ID.");
                int inputUserID = input.nextInt();

                ResultSet selectedUserRs = st.executeQuery("SELECT * FROM User WHERE ID = " + inputUserID);

                // CHECK THE USER
                if (selectedUserRs.next()) {
                    double budget = selectedUserRs.getDouble("budget");
                    boolean hasDriverLicense = selectedUserRs.getBoolean("driverLiceance");
                    
                     //CHECK OUR USER'S BUDGET AND DRIVER LICEANCE
                    if ((budget >= (dailyRentalRate * howManyDays)) && hasDriverLicense) {
                        budget-=dailyRentalRate * howManyDays;
                        
                        //DECREASE OUR USER'S BUDGET
                        String sql0 = "UPDATE User Set budget = ? WHERE ID = ?";
                        PreparedStatement pt1 = c.prepareStatement(sql0);
                        pt1.setDouble(1, budget);
                        pt1.setInt(2, inputUserID);
                        pt1.executeUpdate();

                        //INSTERT OUR CAR AND USER INTO RENTAL TABLE
                        String query = "INSERT INTO Rental (userID, carID, howManyDays) VALUES (?,?,?)";
                        PreparedStatement pt = c.prepareStatement(query);
                        pt.setInt(1, inputUserID);
                        pt.setInt(2, carId);
                        pt.setInt(3, howManyDays);
                        pt.executeUpdate();
                        //UPDATE THE CAR FROM CAR TABLE RENTED
                        st.executeUpdate("UPDATE Car SET rented = true WHERE ID = " + carId);
                        System.out.println("Car rental successful.");
                        
                        //WE CREATE NEW RS FOR PRINT NEW BUDGET OF USER
                        ResultSet rs6 = st.executeQuery("SELECT * FROM User WHERE ID = "+inputUserID);
                        
                        if(rs6.next()){
                        System.out.println("Your new budget is "+rs6.getInt("budget"));
                        }
                    } else {
                        System.out.println("You cannot rent this car! You don't have a driver license or enough budget.");
                    }
                } else {
                    System.out.println("User doesn't exist.");
                }

                // CLOSE RESULT SET USER
                if (selectedUserRs != null) selectedUserRs.close();
            }
        } else {
            System.out.println("Car with ID " + carId + " does not exist.");
        }

        // CLOSE RESULT SETS AND STATEMENTS
        if (selectedCarRs != null) 
               selectedCarRs.close();
        if (rs != null) 
               rs.close();
        if (st != null) 
               st.close();
        if (c != null) 
               c.close();
    }
    
    
    
    public static void setBackRentedCar() throws SQLException{
        Scanner input = new Scanner(System.in);
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db");
        Statement st = c.createStatement();
        
        //GET INPUT FROM USER THE CAR ID THAT RENTED
        System.out.println("Please can you write down the car ID that you rented");
        int rentedCarID = input.nextInt();
        
        //CREATE SQLITE QUERY
        String sql = "DELETE FROM Rental WHERE carID = ?";
        PreparedStatement pt = c.prepareStatement(sql);
        pt.setInt(1, rentedCarID);
        
        //EXECUTE QUERY
        pt.executeUpdate();
        
        if(pt.executeUpdate()==0){
            System.out.println("You set back the car succesfully,");
        }else{
            System.out.println("There is no rented car with ID = "+rentedCarID);
        }
        
        
        
         // CHECK RENTED CAR THAT SELECTED
        ResultSet selectedCarRs = st.executeQuery("SELECT rented FROM Car WHERE ID = " + rentedCarID);
        if (selectedCarRs.next()) {
           boolean isRented = selectedCarRs.getBoolean("rented");

           if (!isRented) {
            System.out.println("Car rental failed. The car is not rented.");
           } else {
              // UPDATE THE CAR THAT CHOOSEN NOT RENTED FROM CAR TABLE
              st.executeUpdate("UPDATE Car SET rented = false WHERE ID = " + rentedCarID);    
             }
        } else {
          System.out.println("Car with ID " + rentedCarID + " does not exist.");
          }
                        
   
    
     // CLOSE RESULT SETS AND STATEMENTS
        
        if (selectedCarRs != null) 
               selectedCarRs.close();
        if (pt != null) 
               pt.close();
        if (c != null) 
               c.close();
        if (st !=null)
               st.close();
    
        }     
    }



 
        