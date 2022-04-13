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
public class RLC extends Instruction{

    public RLC(int _byte, int[] args, String[] operands){
        super(_byte, args, "RLC", "RLC", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: RLC");
        int A0;
        try {
            
            A0 = (Memory.getByte(0xE0) & 128) >> 7;
            Memory.setByte(0xE0, ((Memory.getByte(0xE0) << 1) | Memory.getBit(0xD0, 7)) & 0xFF);
            Memory.setBit(0xD0, 7, A0);
            
        } catch (Exception ex) {
            Logger.getLogger(RLC.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}