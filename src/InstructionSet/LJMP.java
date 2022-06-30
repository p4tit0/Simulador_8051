/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução LJMP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class LJMP extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public LJMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "LJMP", "LJMP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: LJMP");
        Cpu.setPC((args[0] << 8) | args[1]);
    }
}