/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author space
 */
public class RL extends Instruction{

    public RL(int _byte, int[] args, String[] operands){
        super(_byte, args, "RL", "RL", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: RL");
        try {
            
            int A0 = (Memory.getByte(0xE0) & 128) >> 7;
            Memory.setByte(0xE0, ((Memory.getByte(0xE0) << 1) | A0) & 0xFF );
            
        } catch (Exception ex) {
            Logger.getLogger(RL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}