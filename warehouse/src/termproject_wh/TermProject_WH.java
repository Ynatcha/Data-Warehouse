/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;

import edu.sit.cs.db.CSDbDelegate;

/**
 *
 * @author Yuri
 */
public class TermProject_WH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // connect to database
        CSDbDelegate db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014","csc105");
        System.out.println(db.connect());
        
        LogIn open = new LogIn();
        open.setVisible(true);
        
        
        // disconnect from database
       // System.out.println(db.disconnect());
    }
    
}
