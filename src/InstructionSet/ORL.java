/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução ORL;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class ORL extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public ORL(int _byte, int[] args, String[] operands){
        super(_byte, args, "ORL", "ORL", operands);
    }

    @Override
    public void exec() throws Exception{
        int dest = 0;
        int src = 0;
        
        if (operands[0].equals("direct")){
            dest = args[0];
        } else if (operands[0].equals("A")) {
            dest = 0xE0;
        } else if (operands[0].equals("C")) {
            Memory.setBit(215, Memory.getBit(args[0]) | Memory.getBit(215));
            System.out.println("ORL: C, " + String.format("%02x", args[0]).toUpperCase());
            return;
        }
        
        if (operands[1].startsWith("@R")){
            src = Memory.getByte(Memory.getByte((8 * Memory.getBank()) + (opCode & 1)));
        } else if (operands[1].startsWith("R")) {
            src = Memory.getByte((8 * Memory.getBank()) + (opCode & 7));
        } else if (operands[1].equals("#immed")){
            src = args[args.length - 1];
        } else if (operands[1].equals("direct")){
            src = Memory.getByte(args[args.length - 1]);
        } else if (operands[1].equals("A")) {
            src = Memory.getByte(0xE0);
        }
        
        Memory.setByte(dest, Memory.getByte(dest) | src);
        
        System.out.println("ORL: " + String.format("%02x", Memory.getByte(dest)).toUpperCase() + ", " + String.format("%02x", src).toUpperCase());
    }
}