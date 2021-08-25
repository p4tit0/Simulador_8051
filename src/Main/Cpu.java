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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *a
 * @author famil
 */
public class Cpu {
    
    static int size = 16;
    public static Memory memory = new Memory();//= new ArrayList<Object[]>(size);
    public static int inst_idx = 0;
    
    public static void exec(){
        inst_idx = 0;
        while(inst_idx < memory.rom.length){
            if (memory.rom[inst_idx] != null){
                try {
                    memory.rom[inst_idx].exec();
                } catch (Exception ex) {
                    Logger.getLogger(Cpu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            inst_idx++;
        }
    }
    
    public static void step(){
        if (inst_idx >= memory.rom.length){
            inst_idx = 0;
        }
        
        try {
            memory.rom[inst_idx].exec();
        } catch (Exception ex) {
            Logger.getLogger(Cpu.class.getName()).log(Level.SEVERE, null, ex);
        }
        inst_idx++; 
        while(memory.rom[inst_idx] == null){
            inst_idx++;
            if (inst_idx >= memory.rom.length){
                inst_idx = 0;
                break;
            }
        }
        
        System.out.println("COLORE" + inst_idx);
        CodeMemory.color(inst_idx);
             
    }
}
