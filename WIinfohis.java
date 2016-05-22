/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;
import edu.sit.cs.db.CSDbDelegate;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author หญิง 
 */
public class WIinfohis implements WIinfo {
      public String[][] getInfo(int typ){
     String sql = "";
     switch(typ){
         case 1: sql = "SELECT * FROM WH_History WHERE Type = 'buy' ORDER BY Time DESC";
             break;
         case 2: sql = "SELECT * FROM WH_History WHERE Type = 'sell' ORDER BY Time DESC";
             break;
         default: sql ="SELECT * FROM WH_History ORDER BY Time DESC";
             break;
  
        }
       return genInfo(sql);
      }
      
      public String[][] genInfo(String sql){
        CSDbDelegate db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014","csc105");
        db.connect();
        ArrayList<HashMap> data = db.queryRows(sql);
        String[][] gundam = new String[data.size()][9];
        

            if(!data.isEmpty()){
            gundam = new String[data.size()][12];

            for(int i = 0 ; i < gundam.length;i++){
                gundam[i][0] = (String)data.get(i).get("HistoryID");
                gundam[i][1] = (String)data.get(i).get("Type");
                gundam[i][2] = (String)data.get(i).get("ProductName");
                gundam[i][3] = (String)data.get(i).get("Brand");
                gundam[i][4] = (String)data.get(i).get("Categories");
                gundam[i][5] = (String)data.get(i).get("Cost");
                gundam[i][6] = (String)data.get(i).get("Price");
                gundam[i][7] = (String)data.get(i).get("Amount");
                gundam[i][8] = (String)data.get(i).get("Status");
                gundam[i][9] = (String)data.get(i).get("Comment");
                gundam[i][10] = (String)data.get(i).get("Factory");
                gundam[i][11] = (String)data.get(i).get("Time");
            }
        }
        db.disconnect();
    return gundam;
      }   
   
}
