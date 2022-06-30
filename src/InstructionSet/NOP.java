/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução NOP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class NOP extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public NOP(int _byte, int[] args, String[] operands){
        super(_byte, args, "NOP", "NOP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: NOP");
    }
}