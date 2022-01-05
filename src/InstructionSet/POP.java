/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução POP;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class POP extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public POP(int _byte, int[] args, String[] operands){
        super(_byte, args, "POP", "POP", operands);
    }

    @Override
    public void exec() throws Exception{
        if (args[0] < 0 || args[0] > 0x7F){
            throw new Exception(String.format("%02X", args[0]) + ", adress out of range");
        }
        Memory.ram[args[0]] = Memory.ram[Memory.ram[0x81]];
        Memory.ram[0x81]--;
        if (Memory.ram[0x81] < 0x00) {
            Memory.ram[0x81] = 0x7F;
        }
        System.out.println("POP: " + String.format("%02X", Memory.ram[0x81]) + " <-- " + String.format("%02X", args[0]));
    }
}