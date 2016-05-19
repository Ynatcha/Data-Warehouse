/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;

/**
 *
 * @author Yuri
 */
import edu.sit.cs.db.CSDbDelegate;
import java.util.ArrayList;
import java.util.HashMap;
public class View_WH {
   CSDbDelegate db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014","csc105");
    //Insert a student05_test
    public void addBLBuy(){
        String blBuy = "INSERT INTO WH_BLBuy(Date, ProductID, ProductName, Categories, BuyAmount, Cost, FactoryName, Username)VALUES("
                                      +"'',"
                                      +","
                                      +"'',"
                                      +"'',"
                                      +"'',"
                                      +"'',"
                                      + "''"
                                      + ")";
    }
    public void printBLBuy(){
        String sql = "SELECT Date, ProductID, ProductName, Categories, BuyAmount, Cost, FactoryName, Username FROM WH_BLbuy";
        ArrayList<HashMap> buyHistory = db.queryRows(sql);
        for(HashMap std: buyHistory){
            System.out.printf("%s\t%s\t%s\t%s\t%d\t%.2f\t%s\t%s\n", std.get("Date"), std.get("ProductID"), std.get("ProductName"), std.get("Categories"), std.get("BuyAmount"), std.get("Cost"), std.get("FactoryName"), std.get("Username"));
        
    }
        
        
}
}
