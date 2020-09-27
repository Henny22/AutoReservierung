/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField textFieldStaffNumber;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonClose;
    @FXML
    private  PasswordField textFieldConfirmPassword;
    @FXML
    private Label labelPasswordNotMatch;
    @FXML
    private Label labelAlreadyRegistered;
    
    boolean NumberInStuff, NumberInUserData; 
    boolean existNumberInStuff = false, existNumberInUserData = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void backToMainMenuOnAction(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("LoginSystem.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
    public void registerButtonOnAction(ActionEvent e){
       if(textFieldPassword.getText().equals(textFieldConfirmPassword.getText())){
             labelAlreadyRegistered.setText(""); 
             labelPasswordNotMatch.setText("");
             registerUser();  
        }else {
            labelPasswordNotMatch.setText("Password does not match"); 
        }    
    }
    
    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        int staffNumber = Integer.parseInt(textFieldStaffNumber.getText());
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        // int randomNum = ThreadLocalRandom.current().nextInt(1000, 55000 + 1);
        
        String checkStuffNumberInStaff = "SELECT IDStaff from staffdata where IDStaff="+staffNumber+"";
        String checkStuffNumberInUserData = "SELECT IDStaff from staffaccounts where IDStaff="+staffNumber+"";
        
        try{
           Statement statement = connectDB.createStatement(); 
           Statement statement2 = connectDB.createStatement(); 
           Statement statement3 = connectDB.createStatement(); 
           ResultSet queryResultNumberInStaff = statement.executeQuery(checkStuffNumberInStaff);
           ResultSet queryResultNumberInUserData = statement2.executeQuery(checkStuffNumberInUserData);
           
           existNumberInStuff = queryResultNumberInStaff.next();
           existNumberInUserData = queryResultNumberInUserData.next();
           
           if (existNumberInStuff ==true && existNumberInUserData == false){
               String insertFields ="INSERT INTO staffaccounts( IDStaff, Username, Password) VALUES ('";
               String insertValues =staffNumber + "','"+ username +"','"+ password +"')";
               String insertToRegister = insertFields + insertValues;
               statement3.executeUpdate(insertToRegister);
               labelAlreadyRegistered.setText("User has been registered successfully. Try to login now!");
           }
           else if (existNumberInStuff ==true && existNumberInUserData == true) {
               labelAlreadyRegistered.setText("You are already registered. Please try to login!");
           }else {
               labelAlreadyRegistered.setText("We couldn't find this Staff Number. Please try to conntact HR!");
           }        
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        
    }
}
           /*while (queryResultNumberInStaff.next() ){
               if (queryResultNumberInStaff.getInt(1)==1  ){
                NumberInStuff = true;
               }else {
                   NumberInStuff = false;
               }
           }
            ResultSet queryResultNumberInUserData = statement2.executeQuery(checkStuffNumberInUserData);
           while (queryResultNumberInUserData.next()){
               if (queryResultNumberInUserData.getInt(1)==1  ){
                NumberInUserData = true;
               }else {
                   NumberInUserData = false;
               }
           }
           labelAlreadyRegistered.setText("");
           if (NumberInStuff == true && NumberInUserData == false){
               String insertFields ="INSERT INTO userdata(Username, Password, staffID) VALUES ('";
               String insertValues =username + "','"+ password +"','"+ staffNumber +"')";
               String insertToRegister = insertFields + insertValues;
               
               statement.executeUpdate(insertToRegister);
            
               labelAlreadyRegistered.setText("User has been registered successfully. Try to login now!");
           } else if (NumberInStuff == false && NumberInUserData == false){
               labelAlreadyRegistered.setText("We couldn't find this Staff Number. Please try to conntact HR!");
           } else if (NumberInStuff == true && NumberInUserData == true){
            labelAlreadyRegistered.setText("User already registered. Please to try to login!");
        } else {    
                labelAlreadyRegistered.setText("Something went completly wrong. Try to contact your local IT!");
           }
          */ 
           
        
          
    

