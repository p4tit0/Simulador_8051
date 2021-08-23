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
public class ADDC extends Instruction{

    public ADDC(int _byte, int[] args, String[] operands){
        super(_byte, args, "ADDC", "ADDC", operands);
    }

    @Override
    public void exec(){
        System.out.println("exec: ADDC");
    }
}