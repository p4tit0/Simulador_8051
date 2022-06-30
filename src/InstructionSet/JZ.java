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
 * Classe que descreve o funcionamento da instrução JZ;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class JZ extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public JZ(int _byte, int[] args, String[] operands){
        super(_byte, args, "JZ", "JZ", operands);
    }
    
    @Override
    public void exec(){
        System.out.println("exec: JZ");
        
        Cpu.addPC(2);
        try {
            if (Memory.getByte(0xE0) == 0){
                Cpu.addPC(args[0]);
            } 
        } catch (Exception ex) {
            Logger.getLogger(JZ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}