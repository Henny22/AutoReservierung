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
    
    String Order_ID, firstname, lastname, model, loanlength;
    
    public ModelTable(String Order_ID, String firstname, String lastname, String model, String loanlength){
        this.Order_ID = Order_ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.model = model;
        this.loanlength = loanlength;
    }
    
    public String getOrder_ID(){
        return Order_ID;
    }
    
    public void setId(String id){
        this.Order_ID= Order_ID;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public void setFirstname(String firstname){
        this.firstname= firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public void setLastname(String lastname){
        this.lastname= lastname;
    }
    
    public String getModel(){
        return model;
    }
    
    public void setModel(String model){
        this.model= model;
    }
    
    public String getLoanlength(){
        return loanlength;
    }
    
    public void setLoanlength(String loanlength){
        this.loanlength= loanlength;
    }
    
}
