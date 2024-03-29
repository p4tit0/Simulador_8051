/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução AJMP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class AJMP extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public AJMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "AJMP", "AJMP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: AJMP");
        
        Cpu.addPC(2);
        /*
        int aaaa = opCode >> 5;
        aaaa <<= 8;
        aaaa |= args[0];
        */
        Cpu.setPC(((opCode >> 5) << 8) | args[0]);
    }
}