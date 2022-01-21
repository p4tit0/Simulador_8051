/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução ACALL;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class ACALL extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public ACALL(int _byte, int[] args, String[] operands){
        super(_byte, args, "ACALL", "ACALL", operands);
    }

    @Override
    public void exec() throws Exception{
        System.out.println("exec: ACALL");
        
        try{
            Cpu.PC += 2;                      //PC += 2
            Memory.addByte(0x81, 1);               // SP++
            Memory.setByte(Memory.getByte(0x81), Cpu.PC & 0xFF);// (SP) = PC[7-0]
            Memory.addByte(0x81, 1);              // SP++
            Memory.setByte(Memory.getByte(0x81), (Cpu.PC & 0xFF00) >> 8);// (SP) = PC[15-8]
            Cpu.PC = ((opCode & 0xE0) << 3)| args[0] - 1; //?? PC10-0 = A10-0
        }catch(Exception e){
            throw e;
        }
    }
}