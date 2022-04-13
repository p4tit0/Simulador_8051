/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução MOVX;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class MOVX extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public MOVX(int _byte, int[] args, String[] operands){
        super(_byte, args, "MOVX", "MOVX", operands);
    }

    @Override
    public void exec() throws Exception{
        int addr;
        if (operands[0].equals("A")){
            if (operands[1].equals("@DPTR")){
                addr = (Memory.getByte(0x83) << 7) | Memory.getByte(0x82);
            } else {
                addr = Memory.getByte((8 * Memory.getBank()) + (opCode & 1));
            }
            Memory.setByte(0xE0, Memory.getByte(addr));
            System.out.println("MOVX A, #" + String.format("%02x", Memory.getByte(addr)).toUpperCase() + "H");
            return;
        }

        if (operands[0].equals("@DPTR"))
            addr = (Memory.getByte(0x83) << 7) | Memory.getByte(0x82);
        else 
            addr = Memory.getByte((8 * Memory.getBank()) + (opCode & 1));
        Memory.setByte(addr, Memory.getByte(0xE0));
        
        System.out.println("MOVX " + addr + "H, #" + String.format("%02x", Memory.getByte(0xE0)).toUpperCase() + "H");
    }
}