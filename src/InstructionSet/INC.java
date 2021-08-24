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
public class INC extends Instruction{

    public INC(int _byte, int[] args, String[] operands){
        super(_byte, args, "INC", "INC", operands);
    }

    @Override
    public void exec() throws Exception{
        int address = 0;
        if (operands[0].startsWith("@R")){
            int bank = Byte.parseByte(String.format("%8s", Integer.toBinaryString(Memory.ram[208] & 0xFF)).replace(' ', '0').substring(3,5), 2);
            address = Memory.ram[Integer.valueOf(operands[0].substring(operands[0].length()-1)) + 8 * bank];
        } else if (operands[0].startsWith("R")) {
            byte bank = Byte.parseByte(String.format("%8s", Integer.toBinaryString(Memory.ram[208] & 0xFF)).replace(' ', '0').substring(3,5), 2);
            address = Integer.valueOf(operands[0].substring(operands[0].length()-1)) + 8 * bank;
        } else if (operands[0].equals("direct") || operands[0].equals("DPTR")){
            address = args[0];
        } else if (operands[0].equals("A")) {
            address =  224;
        }
        
        Memory.setByte(address, Memory.ram[address] + 1);
    }
}