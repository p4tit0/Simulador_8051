/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução XCHD;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class XCHD extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public XCHD(int _byte, int[] args, String[] operands){
        super(_byte, args, "XCHD", "XCHD", operands);
    }

    @Override
    public void exec() throws Exception{
        int A30 = Memory.getByte(0xE0) & 7;
        int addr = Memory.getByte((8 * Memory.getBank()) + (opCode & 1));
        Memory.setByte(0xE0, (Memory.getByte(0xE0) & 0xF8) | (Memory.getByte(addr) & 7));
        Memory.setByte(addr, (Memory.getByte(addr) & 0xF8) | A30);
        System.out.println("XCHD A, @R" + (opCode & 1));
    }
}