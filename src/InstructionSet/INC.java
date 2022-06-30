/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução INC;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class INC extends Instruction{

    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public INC(int _byte, int[] args, String[] operands){
        super(_byte, args, "INC", "INC", operands);
    }

    @Override
    public void exec() throws Exception{
        int address = 0;
        if (operands[0].startsWith("@R")){
            address = Memory.ram[Integer.valueOf(operands[0].substring(operands[0].length()-1)) + 8 * Memory.getBank()];
        } else if (operands[0].startsWith("R")) {
            address = Integer.valueOf(operands[0].substring(operands[0].length()-1)) + 8 * Memory.getBank();
        } else if (operands[0].equals("direct")){// || operands[0].equals("DPTR")){
            address = args[0];
        } else if (operands[0].equals("A")) {
            address =  224;
        }
        
        System.out.println("INC: " + String.format("%02x", address).toUpperCase());
        Memory.setByte(address, Memory.ram[address] + 1);
    }
}