/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.Instruction;
import InstructionSet.InstructionSet_8051;
import static Main.Cpu.memory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * b
 * @author space
 */
public class Memory {
    
    public static Instruction[] rom = new Instruction[4095];
    public static int[] ram = new int[256];
        
    public Object[] load(int[] data){
        
        for (int i = 0; i<data.length; i++){
            Object[] opcode_info = InstructionSet_8051.instruction_set[data[i]];
            try {
                int[] args = new int[(int) opcode_info[0] - 1];
                for (int k = 1; k<=args.length; k++){
                    args[k-1] = data[i+k];
                }
                System.out.println(data[i] +" "+ opcode_info[1]);
                Constructor c = Class.forName("InstructionSet." + opcode_info[1]).getConstructor(new Class[]{int.class, int[].class, String[].class});
                c.setAccessible(true);
                rom[i] = (Instruction) c.newInstance(data[i], args, (String[]) opcode_info[2]);
                i += args.length;
                
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        Object[] mem = {data, rom};
        return mem;
    }
    
    
    
    public static boolean isBitAddressable(int address) {
        Integer[] bit_address_sfrs = new Integer[]{128, 136, 144, 152, 160, 168, 176, 184, 208, 224, 240};
        return ((address >= 32 && address <= 47) || (Arrays.asList(bit_address_sfrs).contains(address)));
    }
    
    public static void setByte(int address, int value) throws Exception{
        if (address < 0 || address > 255) {
            throw new Exception("Address out of range");
        }
        if (value < 0 || value > 255) {
            throw new Exception("Value out of range");
        }
        ram[address] = value;
        Ram.setByte(address, value);
    }
    
    public static void setBit(int address, int bit, int value) throws Exception{
        if (isBitAddressable(address)) {
            if (value == 1) {
                int data = ram[address];
                int mask = 1;
                mask <<= bit;
                data |= mask;
                ram[address] = data;
            } else if (value == 0) {
                int data = ram[address];
                int mask = 1;
                mask = mask << bit ^ 0xFF;
                data &= mask;
                ram[address] = data;
            } else {
                throw new Exception("Invalid value");
            }
            Ram.setBit(address, bit, value);
        } else {
            throw new Exception(String.format("%02x", address).toUpperCase() + " isn't bit addressable");
        }
    }
    
    public static int getBit(int address, int bit) throws Exception{
        if (isBitAddressable(address)) {
            int data = ram[address];
            data >>= bit;
            data &= 0x1;
            return data;
        }
        throw new Exception(String.format("%02x", address).toUpperCase() + " isn't bit addressable");
    }
    
    public static int getBank(){
        return (ram[0xD0] & 0x18) >> 3;
    }
    
    public static void setBank(int value){
        value <<= 3;
        ram[0xD0] |= value;
        value |= 0xE7;
        ram[0xD0] &= value;
    }
    
    public static void reset(){
        rom = new Instruction[rom.length];
        ram = new int[ram.length];
    }
}
