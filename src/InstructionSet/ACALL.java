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
public class ACALL extends Instruction{

    public ACALL(int _byte, int[] args, String[] operands){
        super(_byte, args, "ACALL", "ACALL", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: ACALL");
        
        Cpu.PC += 2;                      //PC += 2
        Memory.ram[0x81]++;               // SP++
        Memory.ram[Memory.ram[0x81]] = (Cpu.PC & 0xff00) >> 8;// (SP) = PC[7-0]
        Memory.ram[0x81]++;               // SP++
        Memory.ram[Memory.ram[0x81]] = Cpu.PC & 0x00ff;// (SP) = PC[15-8]
                    //?? PC10-0 = A10-0
    }
}