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
public class MOV extends Instruction{

    public MOV(int _byte, int[] args, String[] operands){
        super(_byte, args, "MOV", "MOV", operands);
    }

    @Override
    public void exec(){
        System.out.println("MOV: " + operands[0] + " <-- " + operands[1]);
    }
}