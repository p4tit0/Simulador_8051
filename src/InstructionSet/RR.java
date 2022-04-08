/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução RR;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class RR extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public RR(int _byte, int[] args, String[] operands){
        super(_byte, args, "RR", "RR", operands);
    }

    @Override
    public void exec() throws Exception{
        int A0 = (Memory.getByte(0xE0) & 1) << 7;
        Memory.setByte(0xE0, (Memory.getByte(0xE0) >>> 1) | A0);
        System.out.println("RR A");
    }
}