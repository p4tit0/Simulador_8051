/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

import Main.*;
/**
 *
 * @author space
 */
public class JZ extends Instruction{

    public JZ(int _byte, int[] args, String[] operands){
        super(_byte, args, "JZ", "JZ", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: JZ");
    }
}