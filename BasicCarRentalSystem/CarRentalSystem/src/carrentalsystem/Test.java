
package carrentalsystem;

import java.sql.*;
import java.util.Scanner;

public class Test {
    
 
   public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in); 
        
        
        System.out.println("---Welcome to Car Rental System---\n");
        System.out.println("---Please enter the number of the operation you want to perform.---");
        System.out.println("1. Register\n"
                         + "2. Login\n"
                         + "3. Exit");
        int enteredValue = input.nextInt();
        
        if(enteredValue == 1){
           Register.register();
           
                    System.out.println("\n---Welcome to Car Rental System---\n");

                            System.out.println("Email: ");
                            String email = input.next();
                            System.out.println("Password: ");
                            String password = input.next();

                            if (User.login(email, password)) {
                                System.out.println("Login succesfully!");
                                System.out.println("Please enter the number of the operation you want to perform.");
                                System.out.println("1. Rent a Car\n"
                                                  +"2. Set Back Rented Car\n"
                                                  +"3. Exit");
                                
                                int enteredValue3 = input.nextInt();
                                
                                if(enteredValue3 == 1){
                                    Rent.rent();
                                }else if(enteredValue3 == 2){
                                    Rent.setBackRentedCar();
                                }else if(enteredValue3 == 3){
                                    System.out.println("The application exits successfully.");
                                    input.close();
                                }else{
                                    System.out.println("You have entered an incorrect value. For security reasons, the application is closing. Please try again later.");
                                    input.close();
                                }
                               
                                
                            } else {
                                System.out.println("Login is not succesfully! E mail or password wrong!\nFor security reasons, the application is closing. Please try again later.");
                                input.close();
                            }
           
           
        }else if(enteredValue == 2){
            
            System.out.println("If you are admin please press 1 if you are user please press 2");
            int enteredValue2 = input.nextInt();
            
                   if(enteredValue2 == 1){
                        
                            System.out.println("Email: ");
                            String email = input.next();
                            System.out.println("Password: ");
                            String password = input.next();

                            
                            if (Admin.login(email, password)) {
                                System.out.println("Login succesfully!");
                                System.out.println("Which process do you want to do?");
                                System.out.println("1. Add Car");
                                System.out.println("2. Add Admin");
                                System.out.println("3. Exit");
                                int enteredValue4 = input.nextInt();
                                if(enteredValue4 == 1){
                                    Car.carAdd();
                                }else if(enteredValue4 == 2){
                                    Admin.addAdmin();
                                }else if(enteredValue4 == 3){
                                    System.out.println("The application exits successfully.");
                                    input.close();
                                }else{
                                    System.out.println("You have entered an incorrect value. For security reasons, the application is closing. Please try again later.");
                                    input.close();
                                }
                            } else {
                                System.out.println("Login is not succesfully! E-mail or password wrong!\nFor security reasons, the application is closing. Please try again later.");
                                input.close();
                            }
        
                  
                
                  }else if(enteredValue2 == 2){
                      
                            System.out.println("Email: ");
                            String email = input.next();
                            System.out.println("Password: ");
                            String password = input.next();

                            
                            if (User.login(email, password)) {
                                System.out.println("Login succesfully!");
                                System.out.println("Which case do you want to do?\n"
                                                   +"1. Rent a Car\n"
                                                   +"2. Set Back Rented Car\n"
                                                   +"3. Exit");
                                
                                int enteredValue5 = input.nextInt();
                                
                                   if(enteredValue5 == 1){
                                        Rent.rent();
                                   }else if(enteredValue5 == 2){
                                        Rent.setBackRentedCar();
                                   }else if(enteredValue5 == 3){
                                        System.out.println("The application exits successfully.");
                                        input.close();
                                   }else{
                                        System.out.println("You have entered an incorrect value. For security reasons, the application is closing. Please try again later.");
                                        input.close();
                                   }
                                        
                            } else {
                                System.out.println("Login is not succesfully! E-mail or password wrong!\nFor security reasons, the application is closing. Please try again later.");
                                input.close();
                            }
                  }else{
                        System.out.println("You have entered an incorrect value. For security reasons, the application is closing. Please try again later.");
                        input.close();
                  }
                   
            
        }else if(enteredValue == 3){
            System.out.println("The application exits successfully.");
            input.close();
            
        }else{
            System.out.println("You have entered an incorrect value. For security reasons, the application is closing. Please try again later.");
            input.close();
        } 
         
        

    }
    
}
                  


        
        

        

        
        
        
        
   
    
    

