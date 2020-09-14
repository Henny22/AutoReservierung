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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hendrik
 */
public class LoginSystemController implements Initializable {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button buttonLogin;
    @FXML
    private Label labelStatus;
    @FXML
    private Button buttonCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loginButtonOnAction(ActionEvent event){
        
        if(textFieldUsername.getText().isEmpty() == false && textFieldPassword.getText().isEmpty() == false){
            validateLogin();
        }else {
            labelStatus.setText("Please enter username and password!");
        }
    }
    
    
    public void validateLogin(){
       /* try{  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/accountmanagement","root","");  
         
        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("SELECT count(1) FROM user_account WHERE username ='" + usernameTextField.getText() +"' AND password ='"+ enterPasswordField.getText()+ "'");  
        while (rs.next()){
               if (rs.getInt(1)==1){
                   loginMessageLabel.setText("Congratulation logged in!");
               }else {
                   loginMessageLabel.setText("Invalid login. Please try again");
               }
           }  
         
        }catch(Exception e){ 
            System.out.println(e);}  
        }    */   
        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        String verifyLogin = "SELECT count(1) FROM userdata WHERE Username ='" + textFieldUsername.getText() +"' AND Password ='"+ textFieldPassword.getText()+ "'";
           
        
        try{
           Statement statement = connectDB.createStatement(); 
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           
           while (queryResult.next()){
               if (queryResult.getInt(1)==1){
                   
                 //username = usernameTextField.getText() ;
                 //password = enterPasswordField.getText();
                //loginMessageLabel.setText("Congratulation logged in!");
                   loginMainMenu();
               }else {
                   labelStatus.setText("Invalid login. Please try again");
               }
           }
           
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void loginMainMenu(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));       
        Main.getStage().setScene(new Scene(root,1050,576));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
    public void showResetPassword(){
        try{
          
            Parent root = FXMLLoader.load(getClass().getResource("PasswordReset.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 400, 310));
            stage.show();

        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
    public void showRegistry(){
        try{ 
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
     public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}
