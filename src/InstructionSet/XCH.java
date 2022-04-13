/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução XCH;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class XCH extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public XCH(int _byte, int[] args, String[] operands){
        super(_byte, args, "XCH", "XCH", operands);
    }

    @Override
    public void exec() throws Exception{
        int addr = 0, a = Memory.getByte(0xE0);
        if (operands[0].startsWith("@R")){
            addr = Memory.getByte((8 * Memory.getBank()) + (opCode & 1));
        } else if (operands[0].startsWith("R")) {
            addr = (8 * Memory.getBank()) + (opCode & 7);
        } else if (operands[0].equals("direct")){
            addr = args[0]; 
        }
        Memory.setByte(0xE0, Memory.getByte(addr));
        Memory.setByte(addr, a);        
        System.out.println("XCH A, " + String.format("%02X", addr) + "H");
    }
}