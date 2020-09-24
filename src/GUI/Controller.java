package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {
    
     DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    
    @FXML
    private TableView <ModelTable>tableTableview;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnOrderID;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnFirstname;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnLastname;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnModel;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnLoanLength;
    
    @FXML
    private Label labelTotalOrders;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    /*
    @FXML
    private Label labelID;
    
    @FXML
    private Label labelFirstname;
    
    @FXML
    private Label labelLastname;
     
    @FXML
    private Label labelModel;
    
    @FXML
    private Label labelLoanLength;
      */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHeaderData();
       
       
        tableColumnOrderID.setCellValueFactory(new PropertyValueFactory<>("Order_ID"));
        tableColumnFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tableColumnLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tableColumnLoanLength.setCellValueFactory(new PropertyValueFactory<>("loanlength"));
        
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("Select * from rent_orders");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("Order_ID"),rs.getString("Firstname"),rs.getString("Lastname"),rs.getString("Model"), rs.getString("Loan_Length")));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        tableTableview.setItems(oblist);
        
        
        /*Node[] nodes = new Node[6];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
                //give the items some effect
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }
    
    


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }
    
    public void getOrderData(){
        
    }
    
    public void setHeaderData(){
        String numberOrders="";
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT COUNT(*) FROM rent_orders");
            while(rs.next()){
                numberOrders = rs.getString("COUNT(*)");
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        labelTotalOrders.setText(numberOrders);
    }
    
    public void backToLoginOnAction(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("LoginSystem.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
}
