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
public class SJMP extends Instruction{
    
    public SJMP(int _byte, int[] args){
        this.mnemonic = "SJMP";
        this.description = "SJMP";
    }
    
    @Override
    public void exec(){
        System.out.println("exec: SJMP");
    }
}
