/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução SUBB;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class SUBB extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public SUBB(int _byte, int[] args, String[] operands){
        super(_byte, args, "SUBB", "SUBB", operands);
    }

    @Override
    public void exec() throws Exception{
        int parameter = 0;
        
        if (operands[1].startsWith("@R")){
            parameter = Memory.getByte(Memory.getByte((8 * Memory.getBank()) + (opCode & 1)));
        } else if (operands[1].startsWith("R")) {
            parameter = Memory.getByte((8 * Memory.getBank()) + (opCode & 7));
        } else if (operands[1].equals("direct")){
            parameter = Memory.ram[args[0]];
        } else if (operands[1].equals("#immed")){
            parameter = args[0];
        }
        
        Memory.addByte(0xE0, -(Memory.getBit(0xD0, 7) + parameter) );
        System.out.println("exec: SUBB A, " + String.format("%02x", parameter));
    }
}