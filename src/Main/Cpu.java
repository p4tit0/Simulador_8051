/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author famil
 */
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
public class Cpu {
    
    static int size = 16;
    static Object[][] memory;//= new ArrayList<Object[]>(size);

    
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
    
    public void load(Object[][] inst){
        this.memory = inst;
        System.out.println("-=-=-=-= <MEM> =-=-=-=-");
        print2D(this.memory);
        System.out.println("-=-=-=-= </MEM> =-=-=-=-");
    }
}
