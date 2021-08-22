/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author famil
 */
public class Cpu {
    
    static int size = 16;
    public static Instruction[] memory = new Instruction[100];//= new ArrayList<Object[]>(size);
    
    public String[] getOpcodeInfo(int _byte){
        String[][] table = Reader.readFile("src\\res\\instruction_set_8051.xlsx");
        for (String[] row : table){
            if (Integer.parseInt(row[0], 16) == _byte){
                return row;
            }
        }
        return null;        
    }
    
    public void exec(){
        for(Instruction inst : memory){
            if (inst != null){
                inst.exec();
            }
        }
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
                Constructor c = Class.forName("InstructionSet." + opcode_info[2]).getConstructor(new Class[]{int.class, int[].class});
                c.setAccessible(true);                
                memory[i] = (Instruction) c.newInstance(data.get(i), args);
                i += args.length;
                
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
        
        Object[] mem = {data, memory};
        return mem;
    }
}
