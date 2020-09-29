/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Hendrik
 */
public class ModelTable {
    
    String IDReservation, Lastname, Brand, Location, Amount, IDCar, IDCus, IDLoc;
    
    public ModelTable(String IDReservation, String Lastname, String Brand, String Location, String Amount,String IDCar, String IDCus, String IDLoc){
        this.IDReservation = IDReservation;
        this.Lastname = Lastname;
        this.Brand = Brand;
        this.Location = Location;
        this.Amount = Amount;
        this.IDCar = IDCar;
        this.IDCus = IDCus;
        this.IDLoc = IDLoc;
    }
    
     public ModelTable(String IDReservation, String Lastname, String Brand, String Location, String Amount){
        this.IDReservation = IDReservation;
        this.Lastname = Lastname;
        this.Brand = Brand;
        this.Location = Location;
        this.Amount = Amount;
    }
    
    public String getIDReservation(){
        return IDReservation;
    }
    
    public void setIDReservation(String IDReservation){
        this.IDReservation= IDReservation;
    }
    
    public String getLastname(){
        return Lastname;
    }
    
    public void setLastname(String Lastname){
        this.Lastname= Lastname;
    }
    
    public String getBrand(){
        return Brand;
    }
    
    public void setBrand(String Brand){
        this.Brand= Brand;
    }
    
    public String getLocation(){
        return Location;
    }
    
    public void setLocation(String Location){
        this.Location= Location;
    }
    
    public String getAmount(){
        return Amount;
    }
    
    public void setAmount(String Amount){
        this.Amount= Amount;
    }
    
    public String getIDCar(){
        return IDCar;
    }
    
    public void setIDCar(String IDCar){
        this.IDCar= IDCar;
    }
    
    public String getIDCus(){
        return IDCus;
    }
    
    public void setIDCus(String IDCus){
        this.IDCus= IDCus;
    }
    
    public String getIDLoc(){
        return IDLoc;
    }
    
    public void setIDLoc(String IDLoc){
        this.IDLoc= IDLoc;
    }
    
}
