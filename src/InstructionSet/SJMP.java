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

    @Override
    public void exec(){
        System.out.println("SJMP: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", Cpu.getPC() + Memory.toSignedNumber(args[0])));
        Cpu.addPC(Memory.toSignedNumber(args[0] - 1));
    }
}