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
public class CPL extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public CPL(int _byte, int[] args, String[] operands){
        super(_byte, args, "CPL", "CPL", operands);
    }

    @Override
    public void exec() throws Exception{
        int param, dest;
        
        if (operands[0].equals("A")) {
            Memory.setByte(0xE0, ~Memory.getByte(0xE0));
            System.out.println("CPL A");
            return;
        }
        
        if (operands[0].equals("C")) 
            param = 0xD7;
        else 
            param = args[0];
        
        if (param <= 127)
            dest = param/8 + 0x20;
        else 
            dest = param - param%8;
        Memory.setBit(dest, param % 8, ~Memory.getBit(dest, param % 8));
        System.out.println("CPL: " + String.format("%02x", param).toUpperCase());
    }
}