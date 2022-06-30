/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 * Classe que descreve o funcionamento da instrução DA;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class DA extends Instruction{
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public DA(int _byte, int[] args, String[] operands){
        super(_byte, args, "DA", "DA", operands);
    }

    @Override
    public void exec() throws Exception{
        
        int a = Memory.getByte(0xE0);
        int low_a = a & 0x0F;
        int hihg_a = a >> 4;
        
        if (low_a > 9 || Memory.getBit(214) == 1){a+=6;}
        
        if (a>255){
            a-=256;
            Memory.setBit(215, 1);
        }
        
        if (hihg_a > 9 || Memory.getBit(215) == 1) {
            a+=0x60;
            if (a > 255) {
                a-=256;
                Memory.setBit(215, 1);
            }
        }
        Memory.setByte(0xE0, a);
        
        System.out.println("exec: DA");
    }
}