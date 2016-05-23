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
        String[][] InfoData = new String[data.size()][9];
        

            if(!data.isEmpty()){
            InfoData = new String[data.size()][12];

            for(int i = 0 ; i < InfoData.length;i++){
                InfoData[i][0] = (String)data.get(i).get("HistoryID");
                InfoData[i][1] = (String)data.get(i).get("Type");
                InfoData[i][2] = (String)data.get(i).get("ProductName");
                InfoData[i][3] = (String)data.get(i).get("Brand");
                InfoData[i][4] = (String)data.get(i).get("Categories");
                InfoData[i][5] = (String)data.get(i).get("Cost");
                InfoData[i][6] = (String)data.get(i).get("Price");
                InfoData[i][7] = (String)data.get(i).get("Amount");
                InfoData[i][8] = (String)data.get(i).get("Status");
                InfoData[i][9] = (String)data.get(i).get("Comment");
                InfoData[i][10] = (String)data.get(i).get("Factory");
                InfoData[i][11] = (String)data.get(i).get("Time");
            }
        }
        db.disconnect();
    return InfoData;
      }   
   
}
