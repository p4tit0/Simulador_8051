/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução JNC;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class JNC extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public JNC(int _byte, int[] args, String[] operands){
        super(_byte, args, "JNC", "JNC", operands);
    }

    @Override
    public void exec() throws Exception{
        if (Memory.getBit(215) == 0){
            System.out.println("JC: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", Cpu.getPC() + Memory.toSignedNumber(args[1])));
            Cpu.addPC(Memory.toSignedNumber(args[0] - 1));
        } else {
            System.out.println("JC: " + String.format("%02x", Cpu.getPC()) + " --> " + String.format("%02x", args[1]));
        }
    }
}