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
public class DIV extends Instruction{

    public DIV(int _byte, int[] args, String[] operands){
        super(_byte, args, "DIV", "DIV", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: DIV");
    }
}