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
public class RET extends Instruction{

    public RET(int _byte, int[] args, String[] operands){
        super(_byte, args, "RET", "RET", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: RET");
        //input = low | (high<<8)
        
        //PC15-8 = (SP)
        //SP = SP - 1
        //PC7-0 = (SP)
        //SP = SP - 1
        Cpu.PC = (Memory.ram[Memory.ram[0x81]] - 1) | (Memory.ram[Memory.ram[0x81]] << 8);
        Memory.ram[0x81] -= 2;
    }
}