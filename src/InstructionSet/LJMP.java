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
public class LJMP extends Instruction{

    public LJMP(int _byte, int[] args, String[] operands){
        super(_byte, args, "LJMP", "LJMP", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: LJMP");
    }
}