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
public class SETB extends Instruction{

    public SETB(int _byte, int[] args, String[] operands){
        super(_byte, args, "SETB", "SETB", operands);
    }

    @Override
    public void exec() throws Exception{
        if (operands[0].equals("C")){
            Memory.setBit(208, 0, 1);
            System.out.println("SETB: C");
        } else if (operands[0].equals("bit")) {
           int address;
           if (args[0] <= 127){
                address = args[0] / 8 + 32; 
            } else {
                address = args[0] - args[0] % 8;
            }
            Memory.setBit(address, args[0] % 8, 1);
            System.out.println("SETB: " + String.format("%02x", args[0]).toUpperCase());
        }
    }
}