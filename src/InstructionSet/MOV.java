/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
import java.util.Arrays;
/**
 * Classe que descreve o funcionamento da instrução MOV;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class MOV extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public MOV(int _byte, int[] args, String[] operands){
        super(_byte, args, "MOV", "MOV", operands);
    }
    
    @Override
    public void exec() throws Exception{
        int dest = 0;
        int src = 0;
        
        if (operands[0].startsWith("@R")){
            dest = Memory.getByte((8 * Memory.getBank()) + (opCode & 1));
        } else if (operands[0].startsWith("R")) {
            dest = (8 * Memory.getBank()) + (opCode & 7);
        } else if (operands[0].equals("direct")){
            dest = args[0]; 
        } else if (operands[0].equals("A")) {
            dest = 0xE0;
        } else if (operands[0].equals("DPTR")){
            Memory.setByte(0x83, args[0]);
            Memory.setByte(0x82, args[1]);
            System.out.println("MOV DPTR, #"+ String.format("%02x", args[0]).toUpperCase() + String.format("%02x", args[1]).toUpperCase() + "H");
            return;
        } else if (operands[0].equals("C")) {
            System.out.println(src);
            Memory.setBit(215, Memory.getBit(args[0]));
            System.out.println("MOV: C <-- " + String.format("%02x", args[0]).toUpperCase());
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
        } else if (operands[1].equals("C")) {
            Memory.setBit(args[0], Memory.getBit(215));
            System.out.println("MOV: " + String.format("%02x", dest).toUpperCase() + " <-- C");
            return;
        }
        
        
        System.out.println("MOV: " + String.format("%02x", dest).toUpperCase() + " <-- " + String.format("%02x", src).toUpperCase());
        Memory.setByte(dest, src);
    }
}