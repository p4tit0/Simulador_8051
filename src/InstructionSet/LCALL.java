/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução LCALL;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class LCALL extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public LCALL(int _byte, int[] args, String[] operands){
        super(_byte, args, "LCALL", "LCALL", operands);
    }

    @Override
    public void exec() throws Exception{
        System.out.println("exec: LCALL " + args[0]);
        
        try{
            //Cpu.PC += 3;                      // PC += 3
            Memory.addByte(0x81, 1);             // SP++
            Memory.setByte(Memory.getByte(0x81), Cpu.PC & 0x00FF);// (SP) = PC[7-0]
            Memory.addByte(0x81, 1);              // SP++
            Memory.setByte(Memory.getByte(0x81), (Cpu.PC & 0xFF00) >> 8);// (SP) = PC[15-8]
            Cpu.PC = (args[0] << 8) | args[1] - 1; //PC = addr16   
        }catch(Exception e){
            throw e;
        }
    }
}