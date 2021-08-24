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
public class CPL extends Instruction{

    public CPL(int _byte, int[] args, String[] operands){
        super(_byte, args, "CPL", "CPL", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: CPL");
    }
}