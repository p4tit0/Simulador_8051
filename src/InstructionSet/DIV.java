/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe que descreve o funcionamento da instrução DIV;
 * @author Gerson Menezes e Vinícius Santos
 * @version 1.0
 */
public class DIV extends Instruction{
    
    /**
     * Método construtor da classe, recebe todas as informações sobre a chamada da intrução.
     * @param _byte opCode da instrução.
     * @param args operandos da intrução.
     * @param operands tipos dos operandos passados.
     */
    public DIV(int _byte, int[] args, String[] operands){
        super(_byte, args, "DIV", "DIV", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: DIV");
        try {
            int B = Memory.getByte(0xF0);
            
            if(B == 0){
                Memory.setByte(0xE0, 0);     //valor indefinido
                Memory.setByte(0xF0, 0);    //valor indefinido
                Memory.setBit(210, 1); //set OV flag
                System.out.println("DIV: Error - Division by Zero");
                return;
            }
                
            int A = Memory.getByte(0xE0);
            
            Memory.setByte(0xE0, A / B);
            Memory.setByte(0xF0, A % B);
            
            Memory.setBit(210, 0);
            Memory.setBit(215, 0);
            
            int P = (A % 2 == 1) ? 1 : 0;
            Memory.setBit(208, P);
            
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}