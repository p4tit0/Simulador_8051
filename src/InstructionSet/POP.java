/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

/**
 *
 * @author space
 */
public class POP extends Instruction{

    public POP(int _byte, int[] args){
        super(_byte, args, "POP", "POP");
    }

    @Override
    public void exec(){
        System.out.println("exec: POP");
    }
}