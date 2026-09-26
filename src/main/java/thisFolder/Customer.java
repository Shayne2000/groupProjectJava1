/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package thisFolder;

/**
 *
 * @author Shayne
 */




public class Customer {
    
    private final String ID;
    private int cashback;
    
    Customer(String id) {
        ID = id;
        cashback = 0;
    }
    
    public int getCashback () {
        return cashback;
    }
    
    public void setCashback (int newCashBack) {
        cashback = newCashBack;
    }
    
    public String getID () {
        return ID;
    }
}
