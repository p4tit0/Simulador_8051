/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução MOVC;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class MOVC extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public MOVC(int _byte, int[] args, String[] operands){
        super(_byte, args, "MOVC", "MOVC", operands);
    }

    @Override
    public void exec() throws Exception{
        
        if (operands[1].equals("@A+DPTR"))
            Memory.setByte(0xE0, Memory.getByte(0xE0) + ((Memory.getByte(0x83) << 8) | Memory.getByte(0x82)));
        else 
            Memory.setByte(0xE0, Memory.getByte(0xE0) + ++Cpu.PC);
        System.out.println("MOVC A, " + operands[1]);
    }
}