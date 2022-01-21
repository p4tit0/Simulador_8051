/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução ADD;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class ADD extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public ADD(int _byte, int[] args, String[] operands){
        super(_byte, args, "ADD", "ADD", operands);
    }

    @Override
    public void exec() throws Exception{
        int parameter = 0;
        
        if (operands[1].startsWith("@R")){
            parameter = Memory.ram[Memory.ram[(8 * Memory.getBank()) + (opCode & 1)]];
        } else if (operands[1].startsWith("R")) {
            parameter = Memory.ram[(8 * Memory.getBank()) + (opCode & 7)];
        } else if (operands[1].equals("direct")){
            parameter = Memory.ram[args[0]];
        } else if (operands[1].equals("#immed")){
            System.out.println("A");
            parameter = args[0];
        }
        Memory.addByte(0xE0, parameter);
        System.out.println("exec: ADD A, " + String.format("%02X", parameter));
    }
}