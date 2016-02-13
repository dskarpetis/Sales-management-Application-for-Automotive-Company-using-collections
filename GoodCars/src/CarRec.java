
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;
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
public class CarRec {
    
     Vector<Car> vcar = new Vector<Car>();

    
    
    
    public boolean CcodeExists(String Ccode)
    {
        if(CheckPosbyCcode(Ccode) < 0) 
            return false;
        else 
            return true;
    }
    
    
     
    public int CheckPosbyCcode(String Ccode)
    {
        int thesi = -1;
        for(int i = 0; i < vcar.size(); i++)
        {
            if(Ccode.equals(vcar.elementAt(i).getCcode()))
            {
                thesi = i;
                break;
            }
        }
        return thesi;
    }
    
    
        public void ShowCcodes() throws Exception
    {
        System.out.print("\n== SHOW Car records ===\n");
    
            vcar.removeAllElements();
            readcars();
                
            for(int i = 0; i < vcar.size(); i++)
            {
                System.out.print((i+1) + ". ");  
                vcar.elementAt(i).ShowCar();
            }

        System.out.println();
    }
    
        
    public void AddCar() throws Exception
    {
        System.out.print("\n== ADD Car ==\n");
        
        System.out.print("Type Code : ");
        String Ccode = new java.util.Scanner(System.in).next(); 
        System.out.print("Type Name :");
        String Cname = new java.util.Scanner(System.in).next();
        System.out.print("Type Cost :");      
        double Ccost = new java.util.Scanner(System.in).nextDouble();
       
        readcars();
        
        if(!CcodeExists(Ccode))
        {
            vcar.removeAllElements();  
            Car x = new Car(Ccode, Cname, Ccost);
            readcars();
            vcar.add(x);
            writecars();
            System.out.println("Success...");
          
        }
        else
        {
            System.out.println("Try again, Code allready exists");
        }
        System.out.println();
    }
    
        public void DeleteCar() throws Exception
    {
        System.out.print("\n== DELETE Car ==\n");
        ShowCcodes();
        System.out.print("\nType Code to delete a car : ");
        vcar.removeAllElements(); 
        readcars();
        String Ccode = new java.util.Scanner(System.in).next(); 
        
        int thesi = CheckPosbyCcode(Ccode); 
        
        if(thesi > -1)
        {
            vcar.remove(thesi);
            writecars();
            System.out.println("Success...");
        }
        else
        {
            System.out.println("Try again, name doesn't  exists");
        }
        
        System.out.println();
    }
        
    public void ModifyCar() throws Exception
    {
        int number=0;
        System.out.print("\n== Modify Car ==\n");
        ShowCcodes();
        
        vcar.removeAllElements(); 
        readcars();
        
        System.out.print("Choose code of car for modifation ");
        String sc = new java.util.Scanner(System.in).next();
     
        
        int thesi = CheckPosbyCcode(sc); 
        
        if(thesi > -1)
        {
         
            System.out.println("\n==You choose the record==");
            vcar.elementAt(thesi).ShowCar();
            System.out.println();
            System.out.println("Press 1 or 2 or 3 to change the record\n");
            System.out.println("1. Change the code ");
            System.out.println("2. Change the name");
            System.out.println("3. Change the price");

            number = new java.util.Scanner(System.in).nextInt();
            

            if  ( (number > 0) && (number < 4)) 
            {
            
                if(number==1){
                System.out.println("\nInput new code: ");    
                String Ccode = new java.util.Scanner(System.in).next();
                vcar.removeAllElements();
                readcars();
                
                    if(!CcodeExists(Ccode)){
                       
                    vcar.elementAt(thesi).SetCcode(Ccode);
                    writecars();
                    System.out.println("Success...");
                    }
                
                    else
                    {
                        System.out.println("Try again, Code allready exists");
                    }
    
                
                }

                else if(number==2){
                System.out.println("\nInput new name: ");   
                String Cname = new java.util.Scanner(System.in).next();
                vcar.elementAt(thesi).SetCname(Cname);
                writecars();
                System.out.println("Success...");
                }

                else if(number==3){
                System.out.println("\nInput new price: ");       
                double Ccost = new java.util.Scanner(System.in).nextDouble();
                vcar.elementAt(thesi).SetCcost(Ccost);
                writecars();
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
        
    

    
     public void writecars() {
        try {
            PrintWriter pw = new PrintWriter( new File("cars.txt"));
            
            Enumeration all = vcar.elements();
            while(all.hasMoreElements())
            {  
                Car x = (Car)all.nextElement();
                pw.println(x.getCcode()+","+x.getCname()+","+x.getCcost());   
                
            }
            
            pw.close();
        }
        
        catch (IOException ioe ) { ioe.printStackTrace();
        }
    }
      
    public void readcars() throws Exception {
        String line="";
        
        try {
            FileReader carsfile = new FileReader("cars.txt");
            BufferedReader br = new BufferedReader(carsfile);
            
            while ((line = br.readLine())!=null) {               
                String[] temp = line.split(",");
                vcar.add(new Car(temp[0],temp[1],Double.parseDouble(temp[2])));
            }
        }
        catch (IOException | NumberFormatException e) {
        }    
    }  
      
        
        
     public void DisplayMenu() throws Exception
    {
        int option;
        for(;;)
        {
        System.out.println("=== CARS ===");
        System.out.println("1. Add a new Car");
        System.out.println("2. Delete a Car");
        System.out.println("3. Modify a Car");
        System.out.println("4. Show Cars");
        System.out.println("5. Exit");
        
         System.out.print("Type option : "); 
         option = new java.util.Scanner(System.in).nextInt();
         if(option == 1)
         {
             AddCar();
         }
         else if(option == 2)
         {
             DeleteCar();
         }
         else if(option == 3)
         {
             ModifyCar();
         }
         else if(option == 4)
         {
             ShowCcodes();
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
