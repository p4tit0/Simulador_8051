/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução SETB;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class SETB extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public SETB(int _byte, int[] args, String[] operands){
        super(_byte, args, "SETB", "SETB", operands);
    }

    @Override
    public void exec() throws Exception{
        if (operands[0].equals("C")){
            Memory.setBit(208, 1);
            System.out.println("SETB: C");
        } else if (operands[0].equals("bit")) {
            Memory.setBit(args[0], 1);
            System.out.println("SETB: " + String.format("%02x", args[0]).toUpperCase());
        }
    }
}