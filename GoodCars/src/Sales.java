
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dimitris
 */


public class Sales{
    
private    String sellerafm;
private    String carcode;
private    int numcarsales;
private    double carprice;
private    int ysale,msale,dsale;
    
public Sales(String sellerafm,String carcode,int numcarsales,double carprice, int dsale,int msale, int ysale){
        this.sellerafm = sellerafm;
        this.carcode = carcode;
        this.numcarsales= numcarsales;
        this.carprice = carprice;
        this.dsale = dsale;
        this.msale = msale;
        this.ysale = ysale;        
    }// end constractor
    
public Sales(){
    }// end default constractor
    
    public void Setsellerafm (String sellerafm) {
        this.sellerafm = sellerafm;
    }

    public void Setcarcode (String carcode) {
        this.carcode = carcode;
    }

    public void Setnumcarsales (int numcarsales) {
        this.numcarsales = numcarsales;
    }

    public void Setcarprice (double carprice) {
        this.carprice = carprice;
    }
    
    public void Setysale (int ysale) {
        this.ysale = ysale;
    }    

    public void Setmsale (int msale) {
        this.msale = msale;
    }
    
    public void Setdsale (int dsale) {
        this.dsale = dsale;        
    }
    
    public String getsellerafm(){
        return sellerafm;
    }
    
    public String getcarcode(){
        return carcode;
    }
        
    public int getnumcarsales(){
        return numcarsales;
    }
    
    public double getcarprice(){
        return carprice;
    }
    
    public int getdsale(){
        return dsale;
    } 
    
    public int getmsale(){
        return msale;
    }
    
    public int getysale(){
        return ysale;
    } 

    public void ShowSales()
    {
       System.out.println(
                        "SellerAFM: "+getsellerafm()+" CarCode: "+getcarcode()+
                        " Numcars: "+getnumcarsales()+
                        " Carprice: "+getcarprice()+" Day: "+getdsale()+
                        " Months: "+getmsale()+" Year: "+getysale());
    }    

}
        
        
    
