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

/**
 *
 * @author famil
 */
public class Cpu {
    
    static int size = 16;
    public static Memory memory = new Memory();//= new ArrayList<Object[]>(size);
    
    public void exec(){
        for(Instruction inst : memory.rom){
            if (inst != null){
                inst.exec();
            }
        }
    }
    

}
