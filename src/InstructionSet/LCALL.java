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
public class LCALL extends Instruction{

    public LCALL(int _byte, int[] args, String[] operands){
        super(_byte, args, "LCALL", "LCALL", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: LCALL " + args[0]);
        Cpu.PC += 3;                      // PC += 3
        Memory.ram[0x81]++;               // SP++
        Memory.ram[Memory.ram[0x81]] = Cpu.PC & 0xff;// (SP) = PC[7-0]
        Memory.ram[0x81]++;               // SP++
        Memory.ram[Memory.ram[0x81]] = (Cpu.PC>>8) & 0xff;// (SP) = PC[15-8]
        Cpu.PC = (short)args[0]; //PC = addr16   
    }
}