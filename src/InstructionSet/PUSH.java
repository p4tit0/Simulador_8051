/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução PUSH;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class PUSH extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public PUSH(int _byte, int[] args, String[] operands){
        super(_byte, args, "PUSH", "PUSH", operands);
    }

    @Override
    public void exec() throws Exception{
        if (args[0] < 0 || args[0] > 0x7F){
            throw new Exception(String.format("%02X", args[0]) + ", adress out of range");
        }
        Memory.addByte(0x81, 1);
        if (Memory.getByte(0x81) > 0x7F) {
            Memory.setByte(0x81, 0x07);
        }
        Memory.setByte(Memory.getByte(0x81), args[0]);
        System.out.println("PUSH: " + String.format("%02X", Memory.getByte(0x81)) + " <-- " + String.format("%02X", args[0]));
    }
}