/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Controller.IDCar;
import static GUI.PasswordResetController.generateRandomString;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class SelectedReservationController implements Initializable {

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    
     String getDataCustomer = "SELECT Firstname,Lastname,Email,Birthdate,Street,City,Postalcode,State, Country FROM customers INNER JOIN reservations ON customers.IdCus ="+ Controller.getIDCus()+" && reservations.IdCus="+Controller.getIDCus();
     String getDataCar = "SELECT brand,model FROM cars INNER JOIN reservations ON cars.IDCar ="+ Controller.getIDCar()+" && reservations.IDCar="+Controller.getIDCar();
     
     //SELECT brand,model FROM cars INNER JOIN reservations ON cars.IDCar = reservations.IDCar
    
    @FXML
    private Label labelIDReservation;
    @FXML
    private Label labelFirstname;
    @FXML
    private Label labelLastname;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelBirthdate;
    @FXML
    private Label labelStreet;
    @FXML
    private Label labelCity;
    @FXML
    private Label labelPostalcode;
    @FXML
    private Label labelstate;
    @FXML
    private Label labelBrand;
    @FXML
    private Label labelModel;
    @FXML
    private Label labelAmount;
    @FXML
    private Label labelCountry;
    @FXML
    private Button buttonBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        try {
            Statement statement = connectDB.createStatement();
            labelIDReservation.setText(String.valueOf(Controller.getID()));
            labelAmount.setText(String.valueOf(Controller.getAmount())+" $");
            ResultSet queryResultDataCar = statement.executeQuery(getDataCar);
              while (queryResultDataCar.next()){
              labelBrand.setText( queryResultDataCar.getString(1));
               labelModel.setText( queryResultDataCar.getString(2));  
           }
        
             ResultSet queryResultDataCustomer = statement.executeQuery(getDataCustomer);  
             while (queryResultDataCustomer.next()){
              labelFirstname.setText( queryResultDataCustomer.getString(1));
              labelLastname.setText( queryResultDataCustomer.getString(2));     
              labelEmail.setText( queryResultDataCustomer.getString(3)); 
              labelBirthdate.setText( queryResultDataCustomer.getString(4));
              labelStreet.setText( queryResultDataCustomer.getString(5));
              labelCity.setText( queryResultDataCustomer.getString(6));
              labelPostalcode.setText( queryResultDataCustomer.getString(7));
              labelstate.setText( queryResultDataCustomer.getString(8));
              labelCountry.setText( queryResultDataCustomer.getString(9));  
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void backButtonOnAction(ActionEvent e){
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }
}
