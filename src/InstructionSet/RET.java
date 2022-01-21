/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução RET;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class RET extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public RET(int _byte, int[] args, String[] operands){
        super(_byte, args, "RET", "RET", operands);
    }

    @Override
    public void exec() throws Exception{
        System.out.println("exec: RET");
        
        //input = low | (high<<8)
        // ---------- passo a passo -------------
        try{
            Cpu.PC = Memory.getByte(Memory.getByte(0x81)) << 8; //PC15-8 = (SP)
            Memory.addByte(0x81, -1); //SP = SP - 1
            Cpu.PC |= Memory.getByte(Memory.getByte(0x81)); //PC7-0 = (SP)
            Memory.addByte(0x81, -1); //SP = SP - 1
        }catch(Exception e){
            throw e;
        }
        
        // ---------- direto -------------
        //Cpu.PC = (Memory.ram[Memory.ram[0x81] - 1]) | (Memory.ram[Memory.ram[0x81]] << 8);
        //Memory.ram[0x81] -= 2;
    }
}