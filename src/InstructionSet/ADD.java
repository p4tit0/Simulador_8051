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
public class ADD extends Instruction{

    public ADD(int _byte, int[] args, String[] operands){
        super(_byte, args, "ADD", "ADD", operands);
    }

    @Override
    public void exec(){
        int parameter;
        
        if (operands[0].startsWith("@R")){
            parameter = Memory.ram[Memory.ram[(8 * Memory.getBank()) + (opCode & 1)]];
        } else if (operands[0].startsWith("R")) {
            parameter = Memory.ram[(8 * Memory.getBank()) + (opCode & 7)];
        } else if (operands[0].equals("direct")){
            parameter = Memory.ram[args[0]];
        } else if (operands[0].equals("#immed")){
            parameter = args[0];
        }
        
        System.out.println("exec: ADD");
    }
}