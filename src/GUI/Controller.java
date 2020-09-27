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
import javafx.collections.transformation.FilteredList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    @FXML
    private TextField filterField;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    private final ObservableList <ModelTable> dataList = FXCollections.observableArrayList();
    
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
    
    static int IDReservation, IDCar,IDCus;
    static double Amount;
    public static int getID(){
        return IDReservation;
    }
    public static int getIDCar(){
        return IDCar;
    }
    public static int getIDCus(){
        return IDCus;
    }
     public static double getAmount(){
        return Amount;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHeaderData();
       
        tableColumnOrderID.setCellValueFactory(new PropertyValueFactory<>("IDReservation"));
        tableColumnFirstname.setCellValueFactory(new PropertyValueFactory<>("IDCar"));
        tableColumnLastname.setCellValueFactory(new PropertyValueFactory<>("IDCus"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        tableColumnLoanLength.setCellValueFactory(new PropertyValueFactory<>("IDLoc"));
        
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("Select * from reservations");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("IDReservation"),rs.getString("IDCar"),rs.getString("IDCus"),rs.getString("Amount"), rs.getString("IDLoc")));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        tableTableview.setItems(oblist);
        
        
       /*FilteredList<ModelTable> filteredData = new FilteredList<>(dataList, b-> true);
   
       
       filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getIDReservation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getIDCar().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
 */

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
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT COUNT(*) FROM reservations");
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
    
    
    public void checkTableClick(MouseEvent event){
        /*if(event.getButton().equals(MouseButton.PRIMARY)){
            int index = tableTableview.getSelectionModel().getSelectedIndex();
            String ausgabe = tableTableview.getItems().get(index).toString();
            
            System.out.println(ausgabe);
        }*/
        
        /*ObservableList selectedCells = tableTableview.getSelectionModel().getSelectedCells();

        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        String val = tablePosition.getTableColumn().getCellData(tablePosition.getRow()).toString();
        System.out.println("Selected Value" + val);*/
        
                /*TableViewSelectionModel selectionModel = tableTableview.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(0);
                System.out.println("Selected value IS :" + val);*/
                
                ObservableList<ModelTable> tableList;
                tableList = tableTableview.getSelectionModel().getSelectedItems();
                IDReservation =  Integer.parseInt(tableList.get(0).getIDReservation());
                IDCar = Integer.parseInt(tableList.get(0).getIDCar());
                IDCus = Integer.parseInt(tableList.get(0).getIDCus());
                Amount = Double.parseDouble(tableList.get(0).getAmount());
                try{
            Parent root = FXMLLoader.load(getClass().getResource("selectedReservation.fxml"));
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setX(1150);
            stage.setY(300);
            stage.setTitle("Order View");
            //stage.setAlwaysOnTop(true);
            
            stage.initOwner(Main.getStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root, 400, 450));
            stage.show();
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
     
       
  }

