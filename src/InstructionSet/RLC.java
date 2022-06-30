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
 * Classe que descreve o funcionamento da instrução RLC;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class RLC extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public RLC(int _byte, int[] args, String[] operands){
        super(_byte, args, "RLC", "RLC", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: RLC");
        int A0;
        try {
            
            A0 = (Memory.getByte(0xE0) & 128) >> 7;
            Memory.setByte(0xE0, ((Memory.getByte(0xE0) << 1) | Memory.getBit(215)) & 0xFF);
            Memory.setBit(215, A0);
            
        } catch (Exception ex) {
            Logger.getLogger(RLC.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}