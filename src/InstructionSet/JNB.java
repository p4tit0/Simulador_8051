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
public class JNB extends Instruction{

    public JNB(int _byte, int[] args, String[] operands){
        super(_byte, args, "JNB", "JNB", operands);
    }
    
    public int toSignedNumber(int unsignedData) {
        if (unsignedData >= 0 && unsignedData <= 127)
            return unsignedData; 
        return unsignedData - 256;
    }

    @Override
    public void exec() throws Exception{
        int address = 0;
        if (args[0] <= 127){
            address = args[0] / 8 + 32; 
        } else {
            address = args[0] - args[0] % 8;
        }
        
        if (Memory.getBit(address, args[0] % 8) == 0){
            System.out.println("JNB: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", Cpu.getPC() + toSignedNumber(args[1])));
            Cpu.addPC(toSignedNumber(args[1] - 1));
        } else {
            System.out.println("JNB: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", args[1]));
        }
    }
}