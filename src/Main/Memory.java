/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.Instruction;
import static Main.Cpu.memory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author space
 */
public class Memory {
    
    static Instruction[] rom = new Instruction[100];
    static byte[] ram = new byte[256];
    
    public String[] getOpcodeInfo(int _byte){
        String[][] table = Reader.readFile("src\\res\\instruction_set_8051.xlsx");
        for (String[] row : table){
            if (Integer.parseInt(row[0], 16) == _byte){
                return row;
            }
        }
        return null;        
    }
    
    public Object[] load(Object[][] inst){
        ArrayList<Integer> data = new ArrayList<>();
        for (Object[] line : inst){
            switch((int)line[0]){
                case 0 -> {
                    for (int _byte : (int[]) line[1]){
                        data.add(_byte);
                    }
                }
            }
        }
        
        for (int i = 0; i<data.size(); i++){
            String[] opcode_info = getOpcodeInfo(data.get(i));
            try {
                int[] args = new int[Integer.valueOf(opcode_info[1]) - 1];
                for (int k = 1; k<=args.length; k++){
                    args[k-1] = data.get(i+k);
                }
                Constructor c = Class.forName("InstructionSet." + opcode_info[2]).getConstructor(new Class[]{int.class, int[].class, String[].class});
                c.setAccessible(true);
                rom[i] = (Instruction) c.newInstance(data.get(i), args, opcode_info[3].split(", "));
                i += args.length;
                
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        Object[] mem = {data, rom};
        return mem;
    }
    
//    static RAM ram = new RAM(256);
//    
//    public static class RAM{
//        int size = 0;
//        byte[] upper = new byte[size];
//        byte[] lower = new byte[size];
//        
//        public RAM(int size){
//            this.size = size/2;
//        }
//       
//    } 
}
