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
public class XCHD extends Instruction{

    public XCHD(int _byte, int[] args, String[] operands){
        super(_byte, args, "XCHD", "XCHD", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: XCHD");
    }
}