/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução CRL;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class CLR extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public CLR(int _byte, int[] args, String[] operands){
        super(_byte, args, "CLR", "CLR", operands);
    }

    @Override
    public void exec() throws Exception{
        if (operands[0].equals("C")){
            Memory.setBit(215, 0);
            System.out.println("CLR: C");
        } else if (operands[0].equals("A")) {
            Memory.setByte(0xE0, 0);
            System.out.println("CLR: A");
        } else {
            Memory.setBit(args[0], 0);
            System.out.println("CLR: " + String.format("%02x", args[0]).toUpperCase());
        }
    }
}