/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_wh;

/**
 *
 * @author หญิง
 */
public abstract class WIinfodecorator implements WIinfo{
    protected WIinfo decoratedInfo;
    
    public WIinfodecorator(WIinfo dcorated){
        this.decoratedInfo = dcorated;
    }
    
    public String[][] getInfo(int typ){
       
    return decoratedInfo.getInfo(typ);
    }
    
    public String[][] genInfo(String sql){
        
     return decoratedInfo.genInfo(sql);
    }
}
