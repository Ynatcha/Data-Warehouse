/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author KLoOonal
 */
public class WIlistinfodecorator extends WIinfodecorator {
    
    public WIlistinfodecorator(WIinfo dcorated){
        super(dcorated);
    }
    
      public String[][] getInfo(int typ){
        decoratedInfo.getInfo(typ);
        
      return decoratedInfo.getInfo(typ);
    }
      
      public String[][] genInfo(String sql){
         String[][] dummy = new String[1][1];
         
         SqlExcute(sql);
         return dummy;
          }
            
   
      
      //return specific  // int column
     
      
      
      private void SqlExcute(String sql){
           try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            JOptionPane.showMessageDialog(null, "Operation complete", "Operation", JOptionPane.PLAIN_MESSAGE);
        }
    
    }
      
      
      
  
     private Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://csprog-in.sit.kmutt.ac.th:3306/CSC105_G2";
            String username = "csc105_2014";
            String password = "csc105";
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established.");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
