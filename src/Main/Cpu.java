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
    public static int PC = 0;
    
    public static void exec(){
        PC = 0;
        while(PC < memory.rom.length){
            if (memory.rom[PC] != null){
                try {
                    memory.rom[PC].exec();
                } catch (Exception ex) {
                    Logger.getLogger(Cpu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            PC++;
        }
    }
    
    public static void step(){        
        if (PC >= memory.rom.length){
            PC = 0;
        }
        if (memory.rom[PC] != null){
            try {
                memory.rom[PC].exec();
            } catch (Exception ex) {
                Logger.getLogger(Cpu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PC++; 
        while(memory.rom[PC] == null){
            PC++;
            if (PC >= memory.rom.length){
                PC = 0;
                break;
            }
        }
        
        CodeMemory.color(PC);
        //System.out.println("COLORE" + PC);
        //CodeMemory.color(PC);
             
    }
    
    public static void reset(){
        PC = 0;
    }
    
    public static int getPC(){
        return PC;
    }
    
    public static void setPC(int value){
        PC = value;
    }
    
    public static void addPC(int value){
        PC += value;
    }
    
}
