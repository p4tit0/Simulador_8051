/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe que descreve o funcionamento da instrução JMP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class JMP extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public JMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "JMP", "JMP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: JMP");
        
        
        try {
            //int a = (Memory.getByte(0x83) << 8) | Memory.getByte(0x82);
            Cpu.setPC(Memory.getByte(0xE0) + ((Memory.getByte(0x83) << 8) | Memory.getByte(0x82)));
            
        } catch (Exception ex) {
            Logger.getLogger(JMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}