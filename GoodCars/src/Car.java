/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dimitris
 */
public class Car {
    
    private String  Ccode;
    private String  Cname;
    private double  Ccost;
    
    public Car () {
        this.Ccode = "";
        this.Cname = "";
        this.Ccost = 0;        
    }
    public Car (String Ccode, String Cname, double Ccost) {
        this.Ccode = Ccode;
        this.Cname = Cname;
        this.Ccost = Ccost;        
    }
    
    
    public void SetCcode (String Ccode) {
        this.Ccode = Ccode;
    }

    public void SetCname (String Cname) {
        this.Cname = Cname;
    }

    public void SetCcost (double Ccost) {
        this.Ccost = Ccost;
    }

    public String getCcode(){
        return Ccode;
    }
    
    public String getCname(){
        return Cname;
    }
        
    public  double getCcost(){
        return Ccost;
    }
    
       public void ShowCar()
  {
       System.out.println(
               "Code: " + getCcode() + "\t\t" + 
               "Name: " + getCname() + "\t\t" +
               "Price: " + getCcost()) ;
  }


}





