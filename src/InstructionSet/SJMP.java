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
public class SJMP extends Instruction{

    public SJMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "SJMP", "SJMP", operands);
    }
    
    public int toSignedNumber(int unsignedData) {
        if (unsignedData >= 0 && unsignedData <= 127)
            return unsignedData; 
        return unsignedData - 256;
    }

    @Override
    public void exec(){
        System.out.println("SJMP: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", Cpu.getPC() + toSignedNumber(args[0])));
        Cpu.addPC(toSignedNumber(args[0] - 1));
    }
}