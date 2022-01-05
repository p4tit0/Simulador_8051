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
public class RETI extends Instruction{

    public RETI(int _byte, int[] args, String[] operands){
        super(_byte, args, "RETI", "RETI", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: RETI");
        //input = low | (high<<8)
        
        //PC15-8 = (SP)
        //SP = SP - 1
        //PC7-0 = (SP)
        //SP = SP - 1
        Cpu.PC = (Memory.ram[Memory.ram[0x81]] - 1) | (Memory.ram[Memory.ram[0x81]] << 8);
        Memory.ram[0x81] -= 2;
    }
}