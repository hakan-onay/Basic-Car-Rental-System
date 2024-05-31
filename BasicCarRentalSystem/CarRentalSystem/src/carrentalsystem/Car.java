
package carrentalsystem;
import java.sql.*;
import java.util.Scanner;

public class Car implements IInfo  {
    
    private int ID;
    private String brand;
    private String model;
    private int carYear;
    private int dailyRentalRate;
    private String bodyType;
    private String fuelType;
    private String transmission;
    private boolean rented;

    public Car(int ID, String brand, String model, int carYear, int dailyRentalRate, String bodyType, String fuelType, String transmission, Boolean rented) {
        this.ID = ID;
        this.brand = brand;
        this.model = model;
        this.carYear = carYear;
        this.dailyRentalRate = dailyRentalRate;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.rented = rented;
    }
    
    
    @Override
    public String getInfo() {
        return "Car "+ID+"  {ID= "+ID+ ", Brand=" + brand + ", Model=" + model + ", Year=" + carYear + ", Daily Rental Rate=" + dailyRentalRate + ", Body Type=" + bodyType + ", Fuel Type=" + fuelType + ", Transmission=" + transmission + ", Is Rented?=" + rented + '}';
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getcarYear() {
        return carYear;
    }

    public void setcarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getDailyRentalRate() {
        return dailyRentalRate;
    }

    public void setDailyRentalRate(int dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isRented() {
        return rented;
    }
    
    
    public static void carAdd() throws SQLException {
        
        Scanner input = new Scanner(System.in);
        
        // CONNECT SQLITE DATABASE
        Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Hakan\\Desktop\\CarRentalDB\\CarRentalSystem.db");
        Statement st = c.createStatement();
        
        // GET SOME VALUES FROM ADMIN TO ADD CAR
        System.out.println("Please enter car Brand: ");
        String inputBrand = input.next();
        System.out.println("Please enter car Model: ");
        String inputModel = input.next();
        System.out.println("Please enter car CarYear: ");
        int inputCarYear = input.nextInt();
        System.out.println("Please enter car DailyRentalRate: ");
        int inputDailyRentalRate = input.nextInt();
        System.out.println("Please enter car BodyType: ");
        String inputBodyType = input.next();
        System.out.println("Please enter car FuelType: ");
        String inputFuelType = input.next();
        System.out.println("Please enter car Transmission: ");
        String inputTransmission = input.next();
        System.out.println("Please enter car Rented: (Is it rented please enter 1 otherwise enter 0)");
        int inputRented = input.nextInt();

        
        // CREATE QUERY TO INSERT VALUES TO THE CAR TABLE
        String query = "INSERT INTO Car (brand, model, carYear, dailyRentalRate, bodyType, fuelType, transmission, rented) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pt = c.prepareStatement(query);
        pt.setString(1, inputBrand);
        pt.setString(2, inputModel);
        pt.setInt(3, inputCarYear);
        pt.setInt(4, inputDailyRentalRate);
        pt.setString(5, inputBodyType);
        pt.setString(6, inputFuelType);
        pt.setString(7, inputTransmission);
        pt.setInt(8, inputRented);
        
        //INSERT THE VALUES TO THE CAR TABLE
        pt.executeUpdate();
    }

    
    
    
    

    
    
    
    
    
}
