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
    
    String IDReservation, IDCar, IDCus, Amount, IDLoc;
    
    public ModelTable(String IDReservation, String IDCar, String IDCus, String Amount, String IDLoc){
        this.IDReservation = IDReservation;
        this.IDCar = IDCar;
        this.IDCus = IDCus;
        this.Amount = Amount;
        this.IDLoc = IDLoc;
    }
    
    public String getIDReservation(){
        return IDReservation;
    }
    
    public void setIDReservation(String IDReservation){
        this.IDReservation= IDReservation;
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
    
    public String getAmount(){
        return Amount;
    }
    
    public void setAmount(String Amount){
        this.Amount= Amount;
    }
    
    public String getIDLoc(){
        return IDLoc;
    }
    
    public void setIDLoc(String IDLoc){
        this.IDLoc= IDLoc;
    }
    
}
