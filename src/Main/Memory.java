/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author space
 */
public class Memory {
    
    Object[] rom = new Object[4095];
    RAM ram = new RAM(128);
    
    public class RAM{
        int size = 0;
        
        public RAM(int size){
            this.size = size;
        }
        
        Object[] upper = new Object[size];
        Object[] lower = new Object[size];
    }
    
}
