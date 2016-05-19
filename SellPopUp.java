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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static termproject_wh.BuyPopUp.amount;
import static termproject_wh.BuyPopUp.getConnection;

/**
 *
 * @author Yuri
 */
public class SellPopUp extends javax.swing.JFrame {

    /**
     * Creates new form SellPopUp
     */
    CSDbDelegate db;
    int index;
    int oldAmount;
    static int amount;
    String productName;
    String brandName;
    String category;
    Float price;
    String factoryName;
    public SellPopUp() {
        initComponents();
        this.setLocationRelativeTo(null);  
        db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014","csc105");
        db.connect();
        //System.out.print("Kuyyyyy");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amountIn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sell = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        amountIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountInActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Insert Sell Amount:");

        sell.setText("Sell");
        sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(amountIn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(sell)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sell)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void amountInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountInActionPerformed

    private void sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellActionPerformed
        String amountText = amountIn.getText();
        amount = Integer.parseInt(amountText);
        setAllValue();
        System.out.println(index +" lol "+  productName +" lol "+ brandName +" lol "+ category +" lol "+ amount+" lol "+ price+" lol "+ factoryName);
        try {
            addToBackLog(index, productName, brandName, category, amount, price, factoryName);
        } catch (Exception ex) {
            Logger.getLogger(SellPopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        amountCal(index);
        if(oldAmount >= amount){
         update(index);
         
        }
        db.disconnect();
        this.setVisible(false);
        
    }//GEN-LAST:event_sellActionPerformed
    public void amountCal(int index){
        //get the current amount of the product 
        String sql = "SELECT AmountLeft FROM WH_ProductList WHERE ProductID = '"+index+"'";
        
        ArrayList<HashMap> data = db.queryRows(sql);
        oldAmount = Integer.parseInt(""+data.get(0).get("AmountLeft"));
        if(oldAmount < amount){
            
        JOptionPane.showMessageDialog(null,"NOT ENOUGH STOCK!!" , "", JOptionPane.PLAIN_MESSAGE);
        }
        if(oldAmount >= amount){
            amount = oldAmount - amount;
        }
    }
    
    public static void addToBackLog(int proID, String name, String brand, String cat, int amount, float price, String facCont) throws Exception {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO WH_BLSell (ProductID, ProductName, BrandName, Categories, SellAmount, Price, FactoryName, Date) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
            stmt.setInt(1, proID);
            stmt.setString(2, name);
            stmt.setString(3, brand);
            stmt.setString(4, cat);
            stmt.setInt(5, amount);
            stmt.setFloat(6, price);
            stmt.setString(7, facCont);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            JOptionPane.showMessageDialog(null, "Update the Backlog Completed!!", "Insert", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
     public void setAllValue(){
        //get the current amount of the product 
        String sql = "SELECT * FROM WH_ProductList WHERE ProductID = '"+index+"'";  
        ArrayList<HashMap> data = db.queryRows(sql);

            productName = (String)data.get(0).get("ProductName");
            brandName = (String)data.get(0).get("Brand");
            category = (String)data.get(0).get("Categories");
            price = Float.parseFloat((String)data.get(0).get("Price"));
            factoryName = (String)data.get(0).get("FactoryName");

    }
     
    public static void update(int index) {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE WH_ProductList SET AmountLeft = ? WHERE ProductID = '" + index + "' ");
            stmt.setInt(1, amount);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            JOptionPane.showMessageDialog(null, "Sell Completed!!", "UPDATE", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
     public static Connection getConnection() throws Exception {
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
    public void setIndex(int index){
            this.index = index;
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SellPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellPopUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton sell;
    // End of variables declaration//GEN-END:variables
}
