
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SellerRec {
        
     Vector<Seller> vseller = new Vector<Seller>();
    
    
    
    public boolean ScodeExists(String Scode)
    {
        if(CheckPosbyScode(Scode) < 0) 
            return false;
        else 
            return true;
    }
    
    
     
    public int CheckPosbyScode(String Scode)
    {
        int thesi = -1;
        for(int i = 0; i < vseller.size(); i++)
        {
            if(Scode.equals(vseller.elementAt(i).getScode()))
            {
                thesi = i;
                break;
            }
        }
        return thesi;
    }
    
    
        public void ShowScodes() throws Exception
    {
        System.out.print("\n== SHOW Seller records ===\n");
        
            vseller.removeAllElements();
            readsellers();

                
            for(int i = 0; i < vseller.size(); i++)
            {
                System.out.print((i+1) + ". ");  
                vseller.elementAt(i).ShowSeller();
            }
        
        System.out.println();
    }
    
        
    public void AddSeller() throws Exception
    {
        System.out.print("\n== ADD Seller ==\n");
      
        System.out.print("Type Code : ");
        String Scode = new java.util.Scanner(System.in).nextLine(); 
        System.out.print("Type Full name :");
        String Sname = new java.util.Scanner(System.in).nextLine();
        System.out.print("Type Address :");
        String Saddress = new java.util.Scanner(System.in).nextLine();
        System.out.print("Type Phone number :");
        String Snumber = new java.util.Scanner(System.in).nextLine();
        
        readsellers();

        
        if(!ScodeExists(Scode))
        {
            vseller.removeAllElements(); 
            Seller x = new Seller(Scode, Sname, Saddress, Snumber);
            readsellers();
            vseller.add(x);
            writesellers();
            System.out.println("Success...");
        }
        else
        {
            System.out.println("Try again, Code allready exists");
        }
        System.out.println();
    }
    
        public void DeleteSeller() throws Exception
    {
        System.out.print("\n== DELETE Seller ==\n");
        ShowScodes();
        System.out.print("\nType Code to delete a seller : ");
        vseller.removeAllElements(); 
        readsellers();

        String Scode = new java.util.Scanner(System.in).nextLine(); 

        int thesi = CheckPosbyScode(Scode); 
        
        if(thesi > -1)
        {
            vseller.remove(thesi);
            writesellers();            
            System.out.println("Success...");
        }
        else
        {
            System.out.println("Try again, name doesn't  exists");
        }
        
        System.out.println();
    }
        
        
    public void ModifySeller() throws Exception
    {
        int number=0;
        System.out.print("\n== Modify Seller ==\n");
        ShowScodes();
        
        vseller.removeAllElements(); 
        readsellers();  

        System.out.print("Choose code of seller for modifation : ");
        String sc = new java.util.Scanner(System.in).nextLine();
        int thesi = CheckPosbyScode(sc); 
        
        if(thesi > -1)
        {
         
            System.out.println("\nH eggrafh pou epeleskes einai");
            vseller.elementAt(thesi).ShowSeller();
            System.out.println();
            System.out.println("Press 1 or 2 or 3 or 4 to change the record\n");
            System.out.println("Press 1 to change the code ");
            System.out.println("Press 2 to change the name");
            System.out.println("Press 3 to change the address");
            System.out.println("Press 4 to change the phone number");

            number = new java.util.Scanner(System.in).nextInt();

            if  ( (number > 0) && (number < 5)) 
            {
            
                if(number==1){
                System.out.println("\nInput new code: ");    
                String Scode = new java.util.Scanner(System.in).nextLine();
                vseller.removeAllElements();
                readsellers();

                    if(!ScodeExists(Scode)){
                       
                    vseller.elementAt(thesi).SetScode(Scode);
                    writesellers();
                    System.out.println("Success...");
                    }
                
                    else
                    {
                        System.out.println("Try again, Code allready exists");
                    }                
                
                }

                else if(number==2){
                System.out.println("\nInput new name: ");                     
                String Sname = new java.util.Scanner(System.in).nextLine();
                vseller.elementAt(thesi).SetSname(Sname);
                writesellers();
                System.out.println("Success...");                
                }

                else if(number==3){
                System.out.println("\nInput new address: ");                     
                String Saddress = new java.util.Scanner(System.in).nextLine();
                vseller.elementAt(thesi).SetSaddress(Saddress);
                writesellers();
                System.out.println("Success...");                
                }
                                
                else if(number==4){
                System.out.println("\nInput new phone number: ");                     
                String Snumber = new java.util.Scanner(System.in).nextLine();
                vseller.elementAt(thesi).SetSnumber(Snumber);
                writesellers();
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
        
    
    
    public void writesellers() {
        try {
            PrintWriter pw = new PrintWriter( new File("sellers.txt") );
            
            Enumeration all = vseller.elements();
            while(all.hasMoreElements())     
            {
                Seller x = (Seller)all.nextElement();
                pw.println(x.getScode()+","+x.getSname()+","+x.getSaddress()+","+x.getSnumber());
            }
            pw.close();
        }
        catch (IOException ioe ) { ioe.printStackTrace();
        }
    }// end writecars    
      
    public void readsellers() throws Exception {
        String line="";
        
        try {
            FileReader sellerfile = new FileReader("sellers.txt");
            BufferedReader br = new BufferedReader(sellerfile);
            
            while ((line = br.readLine())!=null) {
                String[] temp = line.split(",");
                vseller.add(new Seller(temp[0],temp[1],temp[2],(temp[3])));
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
        System.out.println("=== SELLERS ===");
        System.out.println("1. Add a new Seller");
        System.out.println("2. Delete a Seller");
        System.out.println("3. Modify a Seller");
        System.out.println("4. Show Sellers");
        System.out.println("5. Exit");
        
         System.out.print("Type option : "); 
         option = new java.util.Scanner(System.in).nextInt();
         if(option == 1)
         {
             AddSeller();
         }
         else if(option == 2)
         {
             DeleteSeller();
         }
         else if(option == 3)
         {
             ModifySeller();
         }
         else if(option == 4)
         {
             ShowScodes();
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
