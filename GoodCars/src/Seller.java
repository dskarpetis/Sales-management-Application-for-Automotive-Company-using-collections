/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dimitris
 */
public class Seller {
    
    private String  Scode;
    private String  Sname;
    private String  Saddress;
    private String  Snumber;
    
    public Seller () {
        this.Scode = "";
        this.Sname = "";
        this.Saddress = "";
        this.Snumber = "";        
    }
    public Seller (String Scode, String Sname, String Saddress, String Snumber) {
        this.Scode = Scode;
        this.Sname = Sname;
        this.Saddress = Saddress;
        this.Snumber = Snumber;        
    }
    
    public void SetScode (String Scode) {
        this.Scode = Scode;
    }

    public void SetSname (String Sname) {
        this.Sname = Sname;
    }

    public void SetSaddress (String Saddress) {
        this.Saddress = Saddress;
    }
    
    public void SetSnumber (String Snumber) {
        this.Snumber = Snumber;
    }

    public String getScode(){
        return Scode;
    }
    
    public String getSname(){
        return Sname;
    }
        
    public String getSaddress(){
        return Saddress;
    }
    
    public String getSnumber(){
        return Snumber;
    }
    
    public void ShowSeller()
    {
       System.out.println(
               "Code: " + getScode() + "  \t\t" + 
               "Fullname: " + getSname() + "  \t\t" +
               "Address: " + getSaddress() + "  \t\t" +
               "Phone: " + getSnumber()) ;
    }
}
