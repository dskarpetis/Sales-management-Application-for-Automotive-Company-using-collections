
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
public class SalesRec {
    
    Vector<Sales> vsales = new  Vector<Sales>();
    
    public boolean SellercodeExists(String sellerafm)
    {
        if(CheckPosbySalescode(sellerafm) < 0) 
            return false;
        else 
            return true;
    }
    
    
     
    public int CheckPosbySalescode(String sellerafm)
    {
        int thesi = -1;
        for(int i = 0; i < vsales.size(); i++)
        {
            if(sellerafm.equals(vsales.elementAt(i).getsellerafm()))
            {
                thesi = i;
                break;
            }
        }
        return thesi;
    }
    
    
    public void Showsalescode() throws Exception
    {
        System.out.print("\n== SHOW sales records ===\n");
        
            vsales.removeAllElements();
            readsales();

                
            for(int i = 0; i < vsales.size(); i++)
            {
                System.out.print((i+1) + ". ");  
                vsales.elementAt(i).ShowSales();
            }
        
        System.out.println();
    }
    
    
    public void Addsales() throws FileNotFoundException, Exception{
        CarRec Car0=new CarRec();
        SellerRec Seller0 = new SellerRec();
        Sales sales0 = new Sales();
        
        Seller0.readsellers();
        Seller0.ShowScodes();
        
        System.out.print("\n== ADD Sales ==\n");
        System.out.print("\nInput code seller: ");
        
        
        String sc = new java.util.Scanner(System.in).next();
        int thesi1 = Seller0.CheckPosbyScode(sc);
        
        if (thesi1 > -1)
        {
            
            
            sales0.Setsellerafm(sc);
            
            Car0.readcars();
            Car0.ShowCcodes();
            System.out.print("\nInput code car: ");
            String cc = new java.util.Scanner(System.in).nextLine();
            
            int thesi2 = Car0.CheckPosbyCcode(cc);
            
                if (thesi2 > -1)
                {
                    sales0.Setcarcode(cc);
                    sales0.Setcarprice(Car0.vcar.elementAt(thesi2).getCcost());
                 
                    System.out.print("Input the number of sold cars: ");
                    int num = new java.util.Scanner(System.in).nextInt();
                    sales0.Setnumcarsales(num);
                     
                    System.out.print("Input day: ");
                    int da = new java.util.Scanner(System.in).nextInt();
                    sales0.Setdsale(da);
                    
                    System.out.print("Input month: ");
                    int mo = new java.util.Scanner(System.in).nextInt();
                    sales0.Setmsale(mo);
                    
                    System.out.print("Input year: ");
                    int ye = new java.util.Scanner(System.in).nextInt();
                    sales0.Setysale(ye);
                    
                }
                
                else
                {
                    System.out.println("Try again, car code doesn't  exists");
                }
                    
        vsales.removeAllElements();
        readsales();
        vsales.add(new Sales(sales0.getsellerafm(),sales0.getcarcode(),sales0.getnumcarsales(),sales0.getcarprice(),sales0.getdsale(),sales0.getmsale(),sales0.getysale()));
        writesales();  
        System.out.println("Success...");
        }
        
        else
        {
            System.out.println("Try again, seller code doesn't  exists");
        }

    }
    
    public void DeleteSales() throws Exception
    {
        System.out.print("\n== DELETE Sales ==\n");
        Showsalescode();
        System.out.print("\nType Scode : ");
        vsales.removeAllElements();
        readsales();
        

        String sellerafm = new java.util.Scanner(System.in).nextLine(); 

        int thesi = CheckPosbySalescode(sellerafm); 
        
        if(thesi > -1)
        {
            vsales.remove(thesi);
            writesales();            
            System.out.println("Success...");
        }
        else
        {
            System.out.println("Try again, name doesn't  exists");
        }
        
        System.out.println();
    }
    
    
 public void ModifySales() throws Exception
    {
        int number=0;
        System.out.print("\n== Modify Sales ==\n");
        Showsalescode();
        
        vsales.removeAllElements();
        readsales();
            
        System.out.print("Choose number of record for modifation : ");
        
        int i = new java.util.Scanner(System.in).nextInt();

        if( (i-1) > -1)
        {
         
            System.out.println("\nThe record for modifation is");
            vsales.elementAt(i-1).ShowSales();
            System.out.println();
            System.out.println("\n=== SALES ===\n");
            System.out.println("Press 1 to change the code ");
            System.out.println("Press 2 to change the name");
            System.out.println("Press 3 to change the number");
            System.out.println("Press 4 to change the day ");
            System.out.println("Press 5 to change the month ");
            System.out.println("Press 6 to change the year ");            

            number = new java.util.Scanner(System.in).nextInt();

            if  ( (number > 0) && (number < 7)) 
            {
            
                if(number==1){  
                System.out.println("\nInput new code");    
                String srcode = new java.util.Scanner(System.in).nextLine();
                SellerRec s = new SellerRec();        

                s.readsellers();

                    if(s.ScodeExists(srcode)){

                    vsales.elementAt(i-1).Setsellerafm(srcode);
                    writesales();
                    System.out.println("Success...");
                    }
                
                    else
                    {
                     System.out.println("Try again, Code not exists");  
                       
                    }                
                
                }

                else if(number==2){

                CarRec cr = new CarRec();
                cr.readcars();
                System.out.println("\nInput new car code ");    
                String cd = new java.util.Scanner(System.in).nextLine();                
                vsales.removeAllElements();
                readsales();
                int thesi3 = cr.CheckPosbyCcode(cd);                
                
                    if(cr.CcodeExists(cd)){
                       
                    vsales.elementAt(i-1).Setcarcode(cd);
                    writesales();
                    System.out.println("Success...");
                    }
                
                    else
                    {
                        System.out.println("Try again, Code allready exists");
                    }               
                }

                else if(number==3){
                System.out.println("\nInput new number: ");                     
                int Snumber = new java.util.Scanner(System.in).nextInt();
                vsales.elementAt(i-1).Setnumcarsales(Snumber);
                writesales();
                System.out.println("Success...");                
                }
                                
                else if(number==4){
                int Sday=0;
                if ((Sday<1) || Sday>31){    
                System.out.println("\nInput new day: ");                     
                Sday = new java.util.Scanner(System.in).nextInt();
                }
                vsales.elementAt(i-1).Setdsale(Sday);
                writesales();
                System.out.println("Success...");
                }
                
                else if(number==5){
                int Smonth=0;    
                if ((Smonth<1) || Smonth>12){    
                System.out.println("\nInput new month: ");                     
                Smonth = new java.util.Scanner(System.in).nextInt();
                }
                vsales.elementAt(i-1).Setmsale(Smonth);
                writesales();
                System.out.println("Success...");                 
                }
                        
                else if(number==6){
                int Syear=0;
                System.out.println("\nInput new year: ");                     
                Syear = new java.util.Scanner(System.in).nextInt();
                vsales.elementAt(i-1).Setysale(Syear);
                writesales();
                System.out.println("Success...");
                
                }                              
            }
            
            else
            {
               System.out.println("wrong number");              
            }    
        }
        
        else            
        {
            System.out.println("Try again, name doesn't  exists");
        }
    }
    


    public void writesales() {
        try {
            PrintWriter pw = new PrintWriter( new File("sales.txt") );
            
            Enumeration all = vsales.elements();
            while(all.hasMoreElements())     
            {
                Sales x = (Sales)all.nextElement();
                pw.println(x.getsellerafm()+","+x.getcarcode()+","+x.getnumcarsales()+","+x.getcarprice()+","+x.getdsale()+","+x.getmsale()+","+x.getysale());
            }
            pw.close();
        }
        catch (IOException ioe ) { ioe.printStackTrace();
        }
    }      

    
    public void readsales() throws Exception {
        String line="";
        
        try {
            FileReader salesfile = new FileReader("sales.txt");
            BufferedReader br = new BufferedReader(salesfile);
            while ((line = br.readLine())!=null) {
                String[] temp = line.split(",");
                vsales.add(new Sales(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]),Integer.parseInt(temp[4]),Integer.parseInt(temp[5]),Integer.parseInt(temp[6])));
            }
        }
        catch (Exception e) {
        }
    } 

  
    public void DisplayMenu() throws Exception
    {
        int option;
        for(;;)
        {
        System.out.println("=== SALES ===");
        System.out.println("1. Add a new Sale");
        System.out.println("2. Delete a Sale");
        System.out.println("3. Modify a Sale");
        System.out.println("4. Show Sales");
        System.out.println("5. Exit");
        
         System.out.print("Type option : "); 
         option = new java.util.Scanner(System.in).nextInt();
         if(option == 1)
         {
             Addsales();
         }
         else if(option == 2)
         {
             DeleteSales();
         }
         else if(option == 3)
         {
             ModifySales();
         }
         else if(option == 4)
         {
            Showsalescode();
         }
         else if(option == 5)
         {

             break;
         }
         else
         {
             System.out.println("Only 1 or 2 or 3 or 4 or 5");
             System.out.println("Thank you");
         }
      }
    }
}

