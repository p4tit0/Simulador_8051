/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução SJMP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class SJMP extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public SJMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "SJMP", "SJMP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: SJMP");
        //System.out.println("SJMP: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", Cpu.getPC() + Memory.toSignedNumber(args[0])));
        Cpu.addPC(2);
        Cpu.addPC(args[0]);
    }
}