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
public class CJNE extends Instruction{

    public CJNE(int _byte, int[] args, String[] operands){
        super(_byte, args, "CJNE", "CJNE", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: CJNE");
        
        Cpu.addPC(3);
       
        try {
            if(operands[0].startsWith("@R")){
                int Rn = (8 * Memory.getBank()) + (opCode & 1);
                
                if(Memory.getByte(Memory.getByte(Rn)) != args[0])
                    Cpu.addPC(args[1]);
                
                Memory.setBit(0xD0, 7, Memory.getByte(Memory.getByte(Rn)) < args[0] ? 1 : 0);
            }
            else if(operands[0].equals("A")){
                if(operands[1].equals("#immed")){
                    if(Memory.getByte(0xE0) != args[0])
                        Cpu.addPC(args[1]);

                    Memory.setBit(0xD0, 7, Memory.getByte(0xE0) < args[0] ? 1 : 0);
                }
                else{
                    if(Memory.getByte(0xE0) != Memory.getByte(args[0]))
                        Cpu.addPC(args[1]);

                    Memory.setBit(0xD0, 7, Memory.getByte(0xE0) < Memory.getByte(args[0]) ? 1 : 0);
                }
            }
            else if(operands[0].startsWith("R")){
                int Rn = (8 * Memory.getBank()) + (opCode & 7);
                 
                if(Memory.getByte(Rn) != args[0])
                  Cpu.addPC(args[1]);
                
                Memory.setBit(0xD0, 7, Memory.getByte(Rn) < args[0] ? 1 : 0);
            }
        } catch (Exception ex) {
            Logger.getLogger(CJNE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}