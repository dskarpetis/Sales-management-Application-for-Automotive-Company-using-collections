


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
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
public class Stats {
    
   
        
    String  StatScode;
    String  StatSname;
    String  StatCcode;
    int     StatCnum;  
    double  StatPrice;
    int     StatYsale;
    int     StatMsale;
    int     Statpos;
    int     Statdsale;
    String  StatCname;


    public Stats(String StatScode, String StatSname, String StatCcode, String  StatCname, int StatCnum,  
        double StatPrice, int StatYsale, int StatMsale){
        
        this.StatScode = StatScode;
        this.StatSname = StatSname;
        this.StatCcode = StatCcode;
        this.StatCname= StatCname;
        this.StatCnum = StatCnum;
        this.StatPrice = StatPrice;
        this.StatYsale= StatYsale;
        this.StatMsale= StatMsale;        
  
    } 
    public Stats(){
        this.StatScode = "";
        this.StatSname = "";
        this.StatCcode = "";
        this.StatCname= "";
        this.StatCnum = 0;
        this.StatPrice = 0;
        this.StatYsale= 0;
        this.StatMsale= 0; 
    }
    
    public void SetStatScode (String StatScode) {
        this.StatScode = StatScode;
    }

    public void SetStatSname (String StatSname) {
        this.StatSname = StatSname;
    }
    
    public void SetStatCcode (String StatCcode) {
    this.StatCcode = StatCcode;
    }

    public void SetStatCname (String StatCname) {
        this.StatCname = StatCname;
    }
    
    public void SetStatCnum (int StatCnum) {
        this.StatCnum = StatCnum;
    }

    public void SetStatPrice (double StatPrice) {
        this.StatPrice = StatPrice;
    }

    public void SetStatYsale (int StatYsale) {
        this.StatYsale = StatYsale;
    }
    
    public void SetStatMsale (int StatMsale) {
        this.StatMsale = StatMsale;
    }  
    

    public String getStatScode(){
        return StatScode;
    }
    
    public String getStatSname(){
        return StatSname;
    }
    
    public String getStatCcode(){
        return StatCcode;
    }
        
    public  String getStatCname(){
        return StatCname;
    }
    
    public int getStatCnum(){
        return StatCnum;
    }
        
    public  double getStatPrice(){
        return StatPrice;
    }
    
    public int getStatYsale(){
        return StatYsale;
    }
        
    public  int getStatMsale(){
        return StatMsale;
    }
 
    CarRec cars =new CarRec();
    SellerRec sellers = new SellerRec();
    SalesRec sales = new SalesRec();
    Vector<Stats> vstats = new  Vector<>();
    
    public void StatsVector() throws Exception{
    cars.readcars();
    sellers.readsellers();
    sales.readsales();
    
        for (int i=0;i<sales.vsales.size();i++) {
            SetStatScode(sales.vsales.elementAt(i).getsellerafm());
            Statpos=sellers.CheckPosbyScode(StatScode);
            SetStatSname(sellers.vseller.elementAt(Statpos).getSname());
            SetStatCnum(sales.vsales.elementAt(i).getnumcarsales());
            SetStatCcode(sales.vsales.elementAt(i).getcarcode());
            Statpos=cars.CheckPosbyCcode(StatCcode);
            SetStatCname(cars.vcar.elementAt(Statpos).getCname());
            SetStatPrice(sales.vsales.elementAt(i).getcarprice());
            SetStatYsale(sales.vsales.elementAt(i).getysale());
            SetStatMsale(sales.vsales.elementAt(i).getmsale());
            vstats.add(new Stats(getStatScode(),getStatSname(), getStatCcode(),getStatCname(),
                    getStatCnum(),getStatPrice(),getStatYsale(),
                    getStatMsale()));
        }      
}
    
///////////////////////////////////////////////////////////////////////////////////////////    
    
    public void SellersYear() {
        List<Stats> lst = vstats.subList(0,vstats.size());
        Collections.sort(lst, new Comparator() {
  
        public int compare(Object o1, Object o2) {
            Stats st1 = (Stats) o1;
            Stats st2 = (Stats) o2;
            if (st1.StatYsale > st2.StatYsale) {
                return 1;
            } else if (st1.StatYsale < st2.StatYsale) {
                return -1;
            } else if (st1.StatYsale == st2.StatYsale) {
                return 0;
            } else {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    });
        
        vstats = new Vector<Stats>(lst);
        Vector<Vector<Stats>> PerYearVector = new Vector<Vector<Stats>>();
        Vector<Stats> statsv = new Vector<Stats>();
        int StatsYearNow=vstats.get(0).getStatYsale();
        
        for (Stats oneYear: vstats) {
            if (oneYear.StatYsale == StatsYearNow) {
                statsv.add(oneYear);
            }
            else {
                StatsYearNow=oneYear.StatYsale;
                PerYearVector.add(statsv);
                statsv = new Vector<Stats>();
                statsv.add(oneYear);
            }
        }
        
        PerYearVector.add(statsv);
        int YearNow = 0;
        String YearStatus = null;
        System.out.println("***** Sellers Per Year *****");
        
        for (Vector<Stats> oneYear : PerYearVector) {
            YearNow = oneYear.get(0).StatYsale;
            YearStatus = Integer.toString(YearNow);
            System.out.println("\n  " + "==== " +YearStatus+" ====" + "\n" );
            
        Vector<Hashtable<String, String>> totalVector = sumPerSalesMan(oneYear);
        for (Hashtable<String, String> resultV : totalVector) {
            System.out.printf("%-15s %-25s %-15s %10.2f  \n", 
                    resultV.get("code"), 
                    resultV.get("Name"),
                    resultV.get("total"), 
                    Double.parseDouble(resultV.get("value")));
            
        }            

        }
        
    }    
 
///////////////////////////////////////////////////////////////////////////////////////////
    
    public void SellersMonth(){
           List<Stats> lst = vstats.subList(0,vstats.size());
           Collections.sort(lst, new Comparator() {
               
            @Override
            public int compare(Object o1, Object o2) {
                Stats st1 = (Stats) o1;
                Stats st2 = (Stats) o2;
                if (st1.StatYsale > st2.StatYsale) {
                    return 1;
                } else if (st1.StatYsale < st2.StatYsale) {
                    return -1;
                } else if (st1.StatYsale == st2.StatYsale) {
                    return 0;
                } else {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        });
           
vstats = new Vector<Stats>(lst);
        Vector<Vector<Stats>> PerYearVector = new Vector<Vector<Stats>>();
        Vector<Stats> statsv = new Vector<Stats>();
        int StatsYearNow=vstats.get(0).getStatYsale();
        for (Stats oneYear: vstats) {
            if (oneYear.StatYsale == StatsYearNow) {
                statsv.add(oneYear);
            }
            else {
                StatsYearNow=oneYear.StatYsale;
                PerYearVector.add(statsv);
                statsv = new Vector<Stats>();
                statsv.add(oneYear);
            }
        }
        
        PerYearVector.add(statsv);
        String YearStatus = null;
        int YearNow = 0;
        
        
        for (Vector<Stats> oneYear : PerYearVector) {
            YearNow = oneYear.get(0).StatYsale;
            YearStatus = Integer.toString(YearNow);
           
        Vector<Hashtable<String, String>> totalVector = sumPerSalesMan(oneYear);
        for (Hashtable<String, String> resultV : totalVector) {
          }            

        }
    
        
        
    Vector<Vector<Stats>> tempSaleVector = new Vector<Vector<Stats>>();
    for (Vector<Stats> vstats : PerYearVector) {      

            lst = vstats.subList(0, vstats.size());
            Collections.sort(lst, new Comparator() { 
              
                public int compare(Object o1, Object o2) {

                    Stats stats1 = (Stats) o1;
                    Stats stats2 = (Stats) o2;
                    if (stats1.StatMsale > stats2.StatMsale) {               
                        return 1;
                    } else if (stats1.StatMsale < stats2.StatMsale) {
                        return -1;
                    } else if (stats1.StatMsale == stats2.StatMsale) {
                        return 0;
                    } else {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }
            });
            vstats = new Vector<Stats>(lst); 
            tempSaleVector.add(vstats);
        }
        PerYearVector = tempSaleVector;
        System.out.println("\n***** Sellers Year Per Month *****");
        statsv = new Vector<Stats>();
        int currentMonth = 0;
        for (Vector<Stats> vstats : PerYearVector) {
            currentMonth = vstats.get(0).StatMsale;
            StatsYearNow = vstats.get(0).StatYsale;
            System.out.println("\n  " + "==== " +StatsYearNow+" ====");
            for (Stats stanew : vstats) {
                if (stanew.StatMsale == currentMonth) {
                    statsv.add(stanew);
                }
                else {
                    System.out.println("\n == month " + currentMonth + " ==");
                    StatsYearNow = stanew.StatYsale;
                    currentMonth = stanew.StatMsale;
                    
            Vector<Hashtable<String, String>> totalVector = sumPerSalesMan(statsv);
            for (Hashtable<String, String> resultV : totalVector) {
                System.out.printf("%-15s %-25s %-15s %10.2f  \n", 
                        resultV.get("code"), 
                        resultV.get("Name"),
                        resultV.get("total"),
                        Double.parseDouble(resultV.get("value")));    
                    } 
            statsv = new Vector<Stats>();
            statsv.add(stanew);
                }
            }
            
    System.out.println("\n == month " + currentMonth + " ==");
    Vector<Hashtable<String, String>> totalVector = sumPerSalesMan(statsv);
    for (Hashtable<String, String> resultV : totalVector) {
        System.out.printf("%-15s %-25s %-15s %10.2f  \n", 
                resultV.get("code"), 
                resultV.get("Name"),
                resultV.get("total"),
                Double.parseDouble(resultV.get("value")));
            }
    statsv = new Vector<Stats>();
        }
    }
    
///////////////////////////////////////////////////////////////////////////////////////////    
    
    public Vector<Hashtable<String, String>> sumPerSalesMan(Vector<Stats> SalesYear) {

        Vector<Hashtable<String, String>> totalVector = new Vector<Hashtable<String, String>>();
        Hashtable<String, String> resultV = null;
        String line="";
        Vector<Seller> vseller = new  Vector<Seller>(10);
        
        try {
            FileReader salesmenfile = new FileReader("sellers.txt");
            BufferedReader br = new BufferedReader(salesmenfile);
            while ((line = br.readLine())!=null) {
                String[] temp = line.split(",");
                vseller.add(new Seller(temp[0],temp[1],temp[2],temp[3]));
            } 
        }
        catch (Exception e) {
        }
        

        boolean f = false;
        String pItem = null;
        int nItem = 0;
        String pValue = null;
        double currentValue = 0;

        for (Seller Sman : vseller) {
            resultV = new Hashtable<String, String>();
            f = false;
            pItem = null;
            nItem = 0;
            pValue = null;
            currentValue = 0;

            for (Stats stanew : SalesYear) {
                
                if (Sman.getScode().equals(stanew.getStatScode())) {
                    f = true;
                    resultV.put("code", stanew.getStatScode());
                    resultV.put("Name", Sman.getSname());
                    pItem = resultV.put("total", Integer.toString(stanew.getStatCnum()));
                    if (pItem != null) {
                        nItem = stanew.getStatCnum() + Integer.parseInt(pItem);
                        resultV.put("total", Integer.toString(nItem));
                    } 
                    currentValue = stanew.getStatPrice()*stanew.getStatCnum();
                    pValue = resultV.put("value", Double.toString(currentValue));
                    if (pValue != null) {
                        currentValue = (stanew.getStatPrice()*stanew.getStatCnum() ) + Double.parseDouble(pValue);
                        resultV.put("value", Double.toString(currentValue));
                    } 
                } 
            } 
            if (!f) {
                resultV.put("code", Sman.getScode());
                resultV.put("Name", Sman.getSname());
                resultV.put("total", "0");
                resultV.put("value", "0");
            } 
            if (resultV.containsValue("0")){
                resultV.clear();
            }
            else {
            totalVector.add(resultV);    
            }
            
        } 
        return totalVector;

}    
    
///////////////////////////////////////////////////////////////////////////////////////////
     
    public void CarsYear() {
        List<Stats> lst = vstats.subList(0,vstats.size());
        Collections.sort(lst, new Comparator() {
            
            public int compare(Object o1, Object o2) {
                Stats st1 = (Stats) o1;
                Stats st2 = (Stats) o2;
                if (st1.getStatYsale() > st2.getStatYsale()) {
                    return 1;
                } else if (st1.getStatYsale() < st2.getStatYsale()) {
                    return -1;
                } else if (st1.getStatYsale() == st2.getStatYsale()) {
                    return 0;
                } else {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        });
        
        vstats = new Vector<Stats>(lst);
        Vector<Vector<Stats>> PerYearVector = new Vector<Vector<Stats>>();
        Vector<Stats> statsv = new Vector<Stats>();
        int StatsYearNow=vstats.get(0).getStatYsale();
        for (Stats oneYear: vstats) {
            if (oneYear.getStatYsale() == StatsYearNow) {
                statsv.add(oneYear);
            }
            else {
                StatsYearNow=oneYear.getStatYsale();
                PerYearVector.add(statsv);
                statsv = new Vector<Stats>();
                statsv.add(oneYear);
            }
        }
        
        PerYearVector.add(statsv);
        String YearStatus = null;
        int YearNow = 0;
        System.out.println();
        System.out.println("***** Cars Per Year *****");
        
        for (Vector<Stats> oneYear : PerYearVector) {
            YearNow = oneYear.get(0).getStatYsale();
            YearStatus = Integer.toString(YearNow);
            System.out.println("\n  " + "==== " +YearStatus +" ====" + "\n" );
            
        Vector<Hashtable<String, String>> totalVector = sumPerCar(oneYear);
            for (Hashtable<String, String> resultV : totalVector) {
                System.out.printf("%-15s %-25s %-15s \n", 
                        resultV.get("carcode"), 
                        resultV.get("carname"),
                        resultV.get("total")); 
            }       
            
        }
        
        }
    
 ///////////////////////////////////////////////////////////////////////////////////////////
    
    public void CarsMonth(){
           List<Stats> lst = vstats.subList(0,vstats.size());
           Collections.sort(lst, new Comparator() {
               
        @Override
        public int compare(Object o1, Object o2) {

            Stats stat1 = (Stats) o1;
            Stats stat2 = (Stats) o2;
                if (stat1.getStatYsale() > stat2.getStatYsale()) {
                    return 1;
                } else if (stat1.getStatYsale() < stat2.getStatYsale()) {
                    return -1;
                } else if (stat1.getStatYsale() == stat2.getStatYsale()) {
                    return 0;
                } else {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        });
        vstats = new Vector<Stats>(lst);
        Vector<Vector<Stats>> statsPerYearVector = new Vector<Vector<Stats>>();
        Vector<Stats> VectorRec = new Vector<Stats>();
        int YearNow=vstats.get(0).getStatYsale();
        for (Stats oneYear: vstats) {
            if (oneYear.getStatYsale() == YearNow) {
                VectorRec.add(oneYear);
            }
            else {
                YearNow=oneYear.getStatYsale();
                statsPerYearVector.add(VectorRec);
                VectorRec = new Vector<Stats>();
                VectorRec.add(oneYear);
            }
        }
        statsPerYearVector.add(VectorRec);
        
        String YearStatus = null;
        int currentYear = 0;

        
        for (Vector<Stats> oneYear : statsPerYearVector) {
            currentYear = oneYear.get(0).getStatYsale();
            YearStatus = Integer.toString(currentYear);

            
        Vector<Hashtable<String, String>> resultVector = sumPerCar(oneYear);
        for (Hashtable<String, String> resultV : resultVector) {

            }            
            
           
        }
        
     
    
    Vector<Vector<Stats>> tempSaleVector = new Vector<Vector<Stats>>();
    for (Vector<Stats> oneVector : statsPerYearVector) {      

            lst = oneVector.subList(0, oneVector.size());

            Collections.sort(lst, new Comparator() { 
      
                @Override
                public int compare(Object o1, Object o2) {

                    Stats stats1 = (Stats) o1;
                    Stats stats2 = (Stats) o2;
                    if (stats1.getStatMsale() > stats2.getStatMsale()) { 
                        return 1;
                    } else if (stats1.getStatMsale() < stats2.getStatMsale()) {
                        return -1;
                    } else if (stats1.getStatMsale() == stats2.getStatMsale()) {
                        return 0;
                    } else {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }
            });
            oneVector = new Vector<Stats>(lst); 
            tempSaleVector.add(oneVector);
        }
        statsPerYearVector = tempSaleVector;
        System.out.println();
        System.out.println("***** Cars Year per Month *****");
        VectorRec = new Vector<Stats>();
        int MonthNow = 0;
        for (Vector<Stats> oneVector : statsPerYearVector) {
            MonthNow = oneVector.get(0).getStatMsale();
            YearNow = oneVector.get(0).getStatYsale();
            System.out.println("\n  " + "==== " + YearNow + " ====" + "\n");
            for (Stats oneSale : oneVector) {
                if (oneSale.getStatMsale() == MonthNow) {
                    VectorRec.add(oneSale);
                }
                else {
                    System.out.println("\n\t For Month " + MonthNow + " ");
                    YearNow = oneSale.getStatYsale();
                    MonthNow = oneSale.getStatMsale();
                    
            Vector<Hashtable<String, String>> resultVector = sumPerCar(VectorRec);
            for (Hashtable<String, String> resultV : resultVector) {
                System.out.printf("%-15s %-25s %-15s \n",
                        resultV.get("carcode"), 
                        resultV.get("carname"),
                        resultV.get("total"));
            }
            VectorRec = new Vector<Stats>();
            VectorRec.add(oneSale);
                }
            }
            
    System.out.println("\n\t For Month " + MonthNow + " "); 
    Vector<Hashtable<String, String>> resultVector = sumPerCar(VectorRec);
            for (Hashtable<String, String> resultV : resultVector) {
                System.out.printf("%-15s %-25s %-15s \n", 
                        resultV.get("carcode"), 
                        resultV.get("carname"),
                        resultV.get("total"));    
            }
    VectorRec = new Vector<Stats>();
        }
    
    }
    
///////////////////////////////////////////////////////////////////////////////////////////
    
    public Vector<Hashtable<String, String>> sumPerCar(Vector<Stats> SalesYear) {

        Vector<Hashtable<String, String>> resultVector = new Vector<Hashtable<String, String>>();
        Hashtable<String, String> resultV = null;
        String line="";
        Vector<Car> vcars = new  Vector<Car>(10);
        
        try {
            FileReader carsfile = new FileReader("cars.txt");
            BufferedReader br = new BufferedReader(carsfile);
            while ((line = br.readLine())!=null) {
                String[] temp = line.split(",");
                vcars.add(new Car(temp[0],temp[1],Double.parseDouble(temp[2])));
            }
        } 
        catch (Exception e) {
        }
        

        boolean f = false;
        String pItem = null;
        int nItem = 0;

        for (Car aCar : vcars) {
            resultV = new Hashtable<String, String>();
            f = false;
            pItem = null;
            nItem = 0;

            for (Stats oneSale : SalesYear) {
                if (aCar.getCcode().equals(oneSale.getStatCcode())) {
                    f = true;
                    resultV.put("carcode", oneSale.getStatCcode());
                    resultV.put("carname", aCar.getCname());
                    pItem = resultV.put("total", Integer.toString(oneSale.getStatCnum()));
                    if (pItem != null) {
                        nItem = oneSale.getStatCnum() + Integer.parseInt(pItem);
                        resultV.put("total", Integer.toString(nItem));
                    } 
                } 
            } 
            if (!f) {
                resultV.put("carcode", aCar.getCcode());
                resultV.put("carname", aCar.getCname());
                resultV.put("total", "0");
            } 
            if (resultV.containsValue("0")){
                resultV.clear();
            }
            else {
            resultVector.add(resultV);    
            }   
        } 
        return resultVector;   
    }  
 
}



