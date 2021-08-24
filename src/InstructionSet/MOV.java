/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
import java.util.Arrays;
/**
 *
 * @author space
 */
public class MOV extends Instruction{

    public MOV(int _byte, int[] args, String[] operands){
        super(_byte, args, "MOV", "MOV", operands);
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
        } else if (operands[0].equals("C")) {
            if (args[0] <= 127){
                address = args[0] / 8 + 32; 
            } else {
                address = args[0] - args[0] % 8;
            }
            Memory.setBit(208, 0, Memory.getBit(address, args[0] % 8));
            System.out.println("MOV: C <-- " + String.format("%02x", args[0]).toUpperCase());
            return;
        }
        
        int val = 0;
        if (operands[1].startsWith("@R")){
            int bank = Byte.parseByte(String.format("%8s", Integer.toBinaryString(Memory.ram[208] & 0xFF)).replace(' ', '0').substring(3,5), 2);
            int rn_val = Memory.ram[Integer.valueOf(operands[1].substring(operands[1].length()-1)) + 8 * bank];
            val = Memory.ram[rn_val]; 
        } else if (operands[1].startsWith("R")) {
            int bank = Byte.parseByte(String.format("%8s", Integer.toBinaryString(Memory.ram[208] & 0xFF)).replace(' ', '0').substring(3,5), 2);
            val = Memory.ram[Integer.valueOf(operands[1].substring(operands[1].length()-1)) + 8 * bank];
        } else if (operands[1].startsWith("#")) {
            val = args[args.length-1];
        } else if (operands[1].equals("direct")){
            val = Memory.ram[args[args.length-1]];
        } else if (operands[1].equals("A")) {
            val = Memory.ram[224];
        } else if (operands[1].equals("C")) {
            if (args[0] <= 127){
                address = args[0] / 8 + 32; 
            } else {
                address = args[0] - args[0] % 8;
            }
            Memory.setBit(address, args[0] % 8, Memory.getBit(208, 0));
            System.out.println("MOV: " + String.format("%02x", args[0]).toUpperCase() + " <-- C" );
            return;
        }
        
        
        System.out.println("MOV: " + String.format("%02x", address).toUpperCase() + " <-- " + String.format("%02x", val).toUpperCase());
        Memory.setByte(address, val);
    }
}