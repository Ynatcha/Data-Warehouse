/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;
import edu.sit.cs.db.CSDbDelegate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;



/**
 *
 * @author KLoOonal
 */
public class WIinfoget implements WIinfo {
    
    // >100 is specific id  such as  101  is id 1.
    public String[][] getInfo(int typ){
     String sql = "";
     switch(typ){
         case 1: sql = "SELECT * FROM WH_ProductList";
             break;
         case 2: sql = "SELECT * FROM WH_ProductList WHERE Categories = 'table'";
             break;
         case 3: sql = "SELECT * FROM WH_ProductList WHERE Categories = 'sofa'";
             break;
         case 4: sql = "SELECT * FROM WH_ProductList WHERE Categories = 'desk'";
             break;
         case 5: sql = "SELECT * FROM WH_ProductList WHERE Categories = 'bed'";
             break;
         case 6: sql = "SELECT * FROM WH_ProductList WHERE Categories = 'chair'";
             break;
         default: sql ="SELECT * FROM WH_ProductList WHERE ProductID = '"+(typ-100)+"'";
        // System.out.print("Ok passsssss"+ typ);
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
                gundam[i][0] = (String)data.get(i).get("ProductID");
                gundam[i][1] = (String)data.get(i).get("ProductName");
                gundam[i][2] = (String)data.get(i).get("Brand");
                gundam[i][3] = (String)data.get(i).get("Categories");
                gundam[i][4] = (String)data.get(i).get("Cost");
                gundam[i][5] = (String)data.get(i).get("Price");
                gundam[i][6] = (String)data.get(i).get("AmountLeft");
                gundam[i][7] = (String)data.get(i).get("Status");
                gundam[i][8] = (String)data.get(i).get("Comment");
                gundam[i][9] = (String)data.get(i).get("FactoryName");
                gundam[i][10] = (String)data.get(i).get("Added");
            }
        }
            //System.out.print(db.getDbConnection());
            db.disconnect();
    return gundam;
    }

    
}
