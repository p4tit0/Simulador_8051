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
public class DJNZ extends Instruction{

    public DJNZ(int _byte, int[] args, String[] operands){
        super(_byte, args, "DJNZ", "DJNZ", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: DJNZ");
        Cpu.addPC(2);
        
        
        try {
            if (operands[0].equals("direct")){
                Memory.addByte(args[0], -1);
                if(Memory.getByte(args[0]) != 0)
                    Cpu.addPC(args[1]); 
            }
            else{
                int Rn = (8 * Memory.getBank()) + (opCode & 7);
                Memory.addByte(Rn, -1);
                if(Memory.getByte(Rn) != 0)
                    Cpu.addPC(args[0]);
            }
        } catch (Exception ex) {
            Logger.getLogger(DJNZ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}