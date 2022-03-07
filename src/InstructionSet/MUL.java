/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução MUL;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class MUL extends Instruction{
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public MUL(int _byte, int[] args, String[] operands){
        super(_byte, args, "MUL", "MUL", operands);
    }

    @Override
    public void exec() throws Exception{

        int r = Memory.getByte(0xE0) *  Memory.getByte(0xF0);
        
        Memory.setByte(0xE0, r & 0x00FF);
        Memory.setByte(0xE0, r >> 8);
        
        int OV = (r > 0x0FF) ? 1 : 0;
        Memory.setBit(0xD0, 2, OV);
        
        Memory.setBit(0xD0, 7, 0);
        
        int P = (Memory.getByte(0xE0) % 2 == 1) ? 1 : 0;
        Memory.setBit(0xD0, 0, P);
        
        System.out.println("exec: MUL");
    }
}