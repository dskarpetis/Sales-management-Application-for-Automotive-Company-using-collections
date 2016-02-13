
import java.util.Hashtable;
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
public class GoodCars {
    


    public static void main(String[] args) throws Exception {

        
       
        for(;;)
        {
            System.out.println("=======  GoodCars  =======");
            System.out.println("\n  == Press from 1 to 5 ==\n");
            System.out.println("1. Cars");
            System.out.println("2. Seller");
            System.out.println("3. Sales");
            System.out.println("4. Results for records"); 
            System.out.println("5. Exit\n");

            int i = new java.util.Scanner(System.in).nextInt();

            if (i==1) {

                CarRec carrec1 = new CarRec();
                carrec1.DisplayMenu();

            }

            else if (i==2) {

                SellerRec sellerec1 = new SellerRec();
                sellerec1.DisplayMenu();
            }

            else if (i==3) {

                SalesRec salesrec1 = new SalesRec();
                salesrec1.DisplayMenu();

            }

            else if (i==4) {

                Stats sts = new Stats();
                sts.StatsVector();
                if (sts.vstats.isEmpty())
                {
                System.out.println("NO RECORDS\n");
                
                }
                else 
                {        

                sts.SellersYear();
                sts.SellersMonth();
                sts.CarsYear();
                sts.CarsMonth();
               
                }

            }
            else if (i==5) {
                
                break;
            }
            else {
                 System.out.println("Only 1 or 2 or 3 or 4 or 5");
                 System.out.println("Thank you");
         
            }
        }  
     
        

      
        
     

    }   
}
    
