/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class PasswordResetController implements Initializable {
    
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
    

    @FXML
    private TextField textFieldPersonnelNumber;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private Button buttonGeneratePassword;
    @FXML
    private Button buttonBackToLogin;
    @FXML
    private Label labelShowNewPassword;
    @FXML
    private Label labelPassword;
    
     boolean checkExistance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void backToMainMenuOnAction(){
       Stage stage = (Stage) buttonBackToLogin.getScene().getWindow();
       stage.close();
    }

    public void checkData(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
         int staffNumber = Integer.parseInt(textFieldPersonnelNumber.getText());
        String username = textFieldUsername.getText();
        
        String checkData = "SELECT IDStaff,Username from staffaccounts where IDStaff="+staffNumber+" && "+" Username='"+username+"'";
        
         
        try {
            Statement statement = connectDB.createStatement();
            Statement statement2 = connectDB.createStatement();
            ResultSet queryResultCheckData = statement.executeQuery(checkData);
            
             checkExistance = queryResultCheckData.next();
            
           
            if (checkExistance == true){
                String newGeneratedPassword = generateRandomString(5);   
                String setNewPassword  ="Update staffaccounts SET Password='"+newGeneratedPassword+"' WHERE IDStaff="+staffNumber+" && Username='"+username+"'";
                
                statement2.executeUpdate(setNewPassword);
                labelShowNewPassword.setText("Your password has been changed. New Password: "+newGeneratedPassword);
                valueToClipboard(newGeneratedPassword);              
                }
                else {
                    labelShowNewPassword.setText("Couldn't find the combination of StaffId and Username. Please try again or contact IT-Support!"); 
                }     
        } catch (Exception e) {
            e.printStackTrace();
        }
           
    }
    
    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }
        return sb.toString();
    }
    
    public void valueToClipboard(String value){
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(value);
                clipboard.setContent(content);         
    }
    
}