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
    private float cashback;
    
    Customer(String id) {
        ID = id;
        cashback = 0;
    }
    
    public float getCashback () {
        return cashback;
    }
    
    public void setCashback (int newCashBack) {
        cashback = newCashBack;
    }
    
    public String getID () {
        return ID;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (ID.equals(((Customer)obj).getID())){
            return true;
        }else{
            return false;
        }
    }
}
