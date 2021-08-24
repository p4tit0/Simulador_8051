/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 *
 * @author space
 */
public class JZ extends Instruction{

    public JZ(int _byte, int[] args, String[] operands){
        super(_byte, args, "JZ", "JZ", operands);
    }
    
    public int toSignedNumber(int unsignedData) {
        if (unsignedData >= 0 && unsignedData <= 127)
            return unsignedData; 
        return unsignedData - 256;
    }
    
    @Override
    public void exec(){
        if (Memory.ram[224] == 0){
            System.out.println("JZ: " + String.format("%02x", Cpu.inst_idx) + " --> " + String.format("%02x", Cpu.inst_idx + toSignedNumber(args[0])));
            Cpu.inst_idx += toSignedNumber(args[0] - 1);
        } else {
            System.out.println("JZ: " + String.format("%02x", Cpu.inst_idx) + " --> " + String.format("%02x", args[0]));
        }
    }
}