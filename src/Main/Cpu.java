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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.ConstructorParameters;

/**
 *
 * @author famil
 */
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
public class Cpu {
    
    static int size = 16;
    public static Instruction[] memory;//= new ArrayList<Object[]>(size);

    
    //-=-=-=-=-=-=-=-=-=- <APAGA> -=-=-=-=-=-=-=-=-=-=-=-
    public static void print2D(Object[][] mat)
    {
        for (Object[] row : mat) {
            System.out.println(String.format("[%s, %s]", row[0], Arrays.toString((int[]) row[1])));
        }
    }
    //-=-=-=-=-=-=-=-=-=- </APAGA> -=-=-=-=-=-=-=-=-=-=-=-
    
    public Cpu(){
//        for(int i = 0; i < size; i++){
//            Instructions.MOV a = inst.new MOV(1,"4");
//            Object[] data = {"batata", a};
//            memory.add(data);
//            System.out.println(i);
//        }
        //System.out.println(memory.toString());
    }
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
            inst.exec();
        }
    }
    
    public void load(Object[][] inst){
        ArrayList<Integer> data = new ArrayList<Integer>();
        for (Object[] line : inst){
            switch((int)line[0]){
                case 0:
                    for (int _byte : (int[]) line[1]){
                        data.add(_byte);
                    }                   
                    break;
            }
        }
        
        ArrayList<Object> opcodes = new ArrayList<>();
        for (int i = 0; i<data.size(); i++){
            String[] opcode_info = getOpcodeInfo(data.get(i));
            try {
                int[] args = new int[Integer.valueOf(opcode_info[1]) - 1];
                for (int k = 1; k<=args.length; k++){
                    args[k-1] = data.get(i+k);
                }
                Constructor c = Class.forName("InstructionSet." + opcode_info[2]).getConstructor(new Class[]{int.class, int[].class});
                c.setAccessible(true);                
                opcodes.add(c.newInstance(data.get(i), args));
                i += args.length;
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        this.memory = opcodes.toArray(new Instruction[0]);
        
        for(Instruction i : this.memory){
            System.out.println(i.mnemonic);
//            if((int) i[0] == 0){
//                //System.out.println((int[]) i[1]);
//                System.out.println(Arrays.toString((int[]) i[1]));
//            }
        }
    }
}
