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
 * A classe <b>Cpu</b> é a responsável pela execução do código na memória.
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class Cpu {
    
    static int size = 16;
    /**
     * <b>memory</b> é um objeto da classe <b>Memory</b>.
     */
    public static Memory memory = new Memory();//= new ArrayList<Object[]>(size);
    /**
     * <b>PC</b> é a representação do program count, PC, do microcontrolador.
     */
    public static int PC = 0;
    
    /**
     * O método <b>exec</b> executa todas as intruções na memória de uma vez.
     */
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
    
    /**
     * O método <b>step</b> executa as instruções na memória, mas uma por chamada.
     */
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
    
    /**
     * O método <b>reset</b> faz o PC retornar a 0, fazendo a fila de instruções resetar.
     */
    public static void reset(){
        PC = 0;
    }
    
    /**
     * O método <b>getPC</b> retorna o PC.
     */
    public static int getPC(){
        return PC;
    }
    
    /**
     * O método <b>setPC</b> serve para mudar o PC para um valor determinado.
     * @param value valor do PC desejado
     */
    public static void setPC(int value){
        PC = value;
    }
    
    /**
     * O método <b>addPC</b> serve para adicionar o PC.
     * @param value valor desejado para a adição.
     */
    public static void addPC(int value){
        PC += value;
    }
    
}
